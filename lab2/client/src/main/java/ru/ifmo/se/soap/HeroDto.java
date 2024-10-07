
package ru.ifmo.se.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for heroDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="heroDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="armor" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="damage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="mainAttribute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="melee" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="moveSpeed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "heroDto", propOrder = {
    "armor",
    "damage",
    "id",
    "mainAttribute",
    "melee",
    "moveSpeed",
    "name"
})
public class HeroDto {

    protected Double armor;
    protected Integer damage;
    protected Long id;
    protected String mainAttribute;
    protected Boolean melee;
    protected Integer moveSpeed;
    protected String name;

    /**
     * Gets the value of the armor property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getArmor() {
        return armor;
    }

    /**
     * Sets the value of the armor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setArmor(Double value) {
        this.armor = value;
    }

    /**
     * Gets the value of the damage property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDamage() {
        return damage;
    }

    /**
     * Sets the value of the damage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDamage(Integer value) {
        this.damage = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the mainAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainAttribute() {
        return mainAttribute;
    }

    /**
     * Sets the value of the mainAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainAttribute(String value) {
        this.mainAttribute = value;
    }

    /**
     * Gets the value of the melee property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMelee() {
        return melee;
    }

    /**
     * Sets the value of the melee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMelee(Boolean value) {
        this.melee = value;
    }

    /**
     * Gets the value of the moveSpeed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMoveSpeed() {
        return moveSpeed;
    }

    /**
     * Sets the value of the moveSpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMoveSpeed(Integer value) {
        this.moveSpeed = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
