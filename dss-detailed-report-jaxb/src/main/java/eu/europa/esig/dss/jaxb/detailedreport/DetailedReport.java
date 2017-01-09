//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.09 at 10:41:17 AM CET 
//


package eu.europa.esig.dss.jaxb.detailedreport;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Signatures" type="{http://dss.esig.europa.eu/validation/detailed-report}Signature" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BasicBuildingBlocks" type="{http://dss.esig.europa.eu/validation/detailed-report}BasicBuildingBlocks" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Article32Block" type="{http://dss.esig.europa.eu/validation/detailed-report}Article32Block" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "signatures",
    "basicBuildingBlocks",
    "article32Block"
})
@XmlRootElement(name = "DetailedReport")
public class DetailedReport {

    @XmlElement(name = "Signatures")
    protected List<XmlSignature> signatures;
    @XmlElement(name = "BasicBuildingBlocks")
    protected List<XmlBasicBuildingBlocks> basicBuildingBlocks;
    @XmlElement(name = "Article32Block")
    protected XmlArticle32Block article32Block;

    /**
     * Gets the value of the signatures property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signatures property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignatures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlSignature }
     * 
     * 
     */
    public List<XmlSignature> getSignatures() {
        if (signatures == null) {
            signatures = new ArrayList<XmlSignature>();
        }
        return this.signatures;
    }

    /**
     * Gets the value of the basicBuildingBlocks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the basicBuildingBlocks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBasicBuildingBlocks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlBasicBuildingBlocks }
     * 
     * 
     */
    public List<XmlBasicBuildingBlocks> getBasicBuildingBlocks() {
        if (basicBuildingBlocks == null) {
            basicBuildingBlocks = new ArrayList<XmlBasicBuildingBlocks>();
        }
        return this.basicBuildingBlocks;
    }

    /**
     * Gets the value of the article32Block property.
     * 
     * @return
     *     possible object is
     *     {@link XmlArticle32Block }
     *     
     */
    public XmlArticle32Block getArticle32Block() {
        return article32Block;
    }

    /**
     * Sets the value of the article32Block property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlArticle32Block }
     *     
     */
    public void setArticle32Block(XmlArticle32Block value) {
        this.article32Block = value;
    }

}
