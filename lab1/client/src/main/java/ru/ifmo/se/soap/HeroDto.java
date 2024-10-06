
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
 *         &lt;element name="armor" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="damage" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="mainAttribute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="melee" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="moveSpeed" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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

    protected double armor;
    protected int damage;
    protected long id;
    protected String mainAttribute;
    protected boolean melee;
    protected int moveSpeed;
    protected String name;

    /**
     * Gets the value of the armor property.
     * 
     */
    public double getArmor() {
        return armor;
    }

    /**
     * Sets the value of the armor property.
     * 
     */
    public void setArmor(double value) {
        this.armor = value;
    }

    /**
     * Gets the value of the damage property.
     * 
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the value of the damage property.
     * 
     */
    public void setDamage(int value) {
        this.damage = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
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
     */
    public boolean isMelee() {
        return melee;
    }

    /**
     * Sets the value of the melee property.
     * 
     */
    public void setMelee(boolean value) {
        this.melee = value;
    }

    /**
     * Gets the value of the moveSpeed property.
     * 
     */
    public int getMoveSpeed() {
        return moveSpeed;
    }

    /**
     * Sets the value of the moveSpeed property.
     * 
     */
    public void setMoveSpeed(int value) {
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
