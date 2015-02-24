package eu.europa.ec.markt.dss.cookbook.example.validate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;

import eu.europa.ec.markt.dss.DSSUtils;
import eu.europa.ec.markt.dss.cookbook.example.Cookbook;
import eu.europa.ec.markt.dss.cookbook.sources.MockServiceInfo;
import eu.europa.ec.markt.dss.signature.DSSDocument;
import eu.europa.ec.markt.dss.signature.FileDocument;
import eu.europa.ec.markt.dss.validation102853.CommonCertificateVerifier;
import eu.europa.ec.markt.dss.validation102853.SignedDocumentValidator;
import eu.europa.ec.markt.dss.validation102853.crl.OnlineCRLSource;
import eu.europa.ec.markt.dss.validation102853.https.CommonsDataLoader;
import eu.europa.ec.markt.dss.validation102853.https.FileCacheDataLoader;
import eu.europa.ec.markt.dss.validation102853.ocsp.OnlineOCSPSource;
import eu.europa.ec.markt.dss.validation102853.report.DetailedReport;
import eu.europa.ec.markt.dss.validation102853.report.Reports;
import eu.europa.ec.markt.dss.validation102853.report.SimpleReport;
import eu.europa.ec.markt.dss.validation102853.tsl.TSLRefreshPolicy;
import eu.europa.ec.markt.dss.validation102853.tsl.TrustedListsCertificateSource;

/**
 * How to validate a XAdES-BASELINE-B enveloped signature with online sources.
 */
public class ValidateXmlXadesLTWithOnlineSources extends Cookbook {

	public static void main(String[] args) throws IOException {

		// To be able to validate our fake signature, we must define one of the certificates in the chain as trusted anchor.
		// If you have a real signature for which it is possible to build the chain till the the TSL then just skip this point.
		preparePKCS12TokenAndKey();

		final X509Certificate[] certificateChain = privateKey.getCertificateChain();
		final X509Certificate trustedCertificate = certificateChain[0];

		// Already signed document
		DSSDocument document = new FileDocument("signedXmlXadesLT.xml");

		SignedDocumentValidator validator = SignedDocumentValidator.fromDocument(document);

		CommonsDataLoader commonsDataLoader = new CommonsDataLoader();

		CommonCertificateVerifier verifier = new CommonCertificateVerifier();
		OnlineCRLSource crlSource = new OnlineCRLSource();
		crlSource.setDataLoader(commonsDataLoader);
		verifier.setCrlSource(crlSource);

		OnlineOCSPSource ocspSource = new OnlineOCSPSource();
		// The default OCSPDataLoader is created. You can also create your own HttpDataLoader.
		verifier.setOcspSource(ocspSource);

		// SEE NOTE 1
		FileCacheDataLoader fileCacheDataLoader = new FileCacheDataLoader();
		File cacheFolder = new File("/temp");
		fileCacheDataLoader.setFileCacheDirectory(cacheFolder);

		TrustedListsCertificateSource certificateSource = new TrustedListsCertificateSource();
		certificateSource.setLotlUrl("https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-mp.xml");
		certificateSource.setCheckSignature(true);
		certificateSource.setDataLoader(fileCacheDataLoader);
		certificateSource.setTslRefreshPolicy(TSLRefreshPolicy.NEVER);
		certificateSource.setLotlCertificate("file:/" + getPathFromResource("/lotl.cer"));
		certificateSource.init();

		certificateSource.addCertificate(trustedCertificate, new MockServiceInfo());
		verifier.setTrustedCertSource(certificateSource);

		verifier.setDataLoader(fileCacheDataLoader);

		validator.setCertificateVerifier(verifier);

		Reports reports = validator.validateDocument();
		SimpleReport simpleReport = reports.getSimpleReport();
		DetailedReport detailReport = reports.getDetailedReport();

		InputStream is = new ByteArrayInputStream(simpleReport.toByteArray());
		DSSUtils.saveToFile(is, "validationXmlXadesLT_Online_simpleReport.xml");

		is = new ByteArrayInputStream(detailReport.toByteArray());
		DSSUtils.saveToFile(is, "validationXmlXadesLT_Online_detailReport.xml");

	}
}