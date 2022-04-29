/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author carlos
 */
@Entity
@Table (name="products")
public class Products implements Serializable{
    
    @Id
    @Column(name="UPC")
    private String upc;
 
    @Column(name="ItemNo")
    private int itemNo;

    @Column(name="itemType")
    private String itemType;

    @Column(name="priceprefix")
    private String priceprefix;

    @Column(name="pricewholeNumber")
    private float pricewholeNumber;

    @Column(name="priceseparator")
    private String priceseparator;

    @Column(name="pricedecimals")
    private int pricedecimals;

    @Column(name="pricesuffix")
    private String pricesuffix;

    @Column(name="priceisRegularCurrency")
    private String priceisRegularCurrency;

    @Column(name="contextualImageUrl")
    private String contextualImageUrl;

    @Column(name="mainImageAlt")
    private String mainImageAlt;

    @Column(name="breathTaking")
    private String breathTaking;

    @Column(name="discount")
    private String discount;

    @Column(name="name")
    private String name;

    @Column(name="typeName")
    private String typeName;

    @Column(name="itemMeasureReferenceText")
    private String itemMeasureReferenceText;

    @Column(name="mainImageUrl")
    private String mainImageUrl;

    @Column(name="pipUrl")
    private String pipUrl;

    @Column(name="itemNoGlobal")
    private int itemNoGlobal;
    
    @Column(name="onlineSellable")
    private String onlineSellable;
    
    @Column(name="lastChance")
    private String lastChance;
    
    @Column(name="colors")
    private String colors;
    
    @Column(name="priceNumeral")
    private float priceNumeral;
    
    @Column(name="currencyCode")
    private String currencyCode;
    
    @Column(name="tag")
    private String tag;
    
    @JoinColumn(name="nombreCategoria")
    @ManyToOne
    private Subcategorias subcategoria;
    
    @Column(name="variants")
    private String variants;
    
    @Column(name="tagText")
    private String tagText;
    
    @Column(name="availabilityDisclaimer")
    private String availabilityDisclaimer;
    
    @Column(name="priceUnit")
    private String priceUnit;

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getPriceprefix() {
        return priceprefix;
    }

    public void setPriceprefix(String priceprefix) {
        this.priceprefix = priceprefix;
    }

    public float getPricewholeNumber() {
        return pricewholeNumber;
    }

    public void setPricewholeNumber(float pricewholeNumber) {
        this.pricewholeNumber = pricewholeNumber;
    }

    public String getPriceseparator() {
        return priceseparator;
    }

    public void setPriceseparator(String priceseparator) {
        this.priceseparator = priceseparator;
    }

    public int getPricedecimals() {
        return pricedecimals;
    }

    public void setPricedecimals(int pricedecimals) {
        this.pricedecimals = pricedecimals;
    }

    public String getPricesuffix() {
        return pricesuffix;
    }

    public void setPricesuffix(String pricesuffix) {
        this.pricesuffix = pricesuffix;
    }

    public String getPriceisRegularCurrency() {
        return priceisRegularCurrency;
    }

    public void setPriceisRegularCurrency(String priceisRegularCurrency) {
        this.priceisRegularCurrency = priceisRegularCurrency;
    }

    public String getContextualImageUrl() {
        return contextualImageUrl;
    }

    public void setContextualImageUrl(String contextualImageUrl) {
        this.contextualImageUrl = contextualImageUrl;
    }

    public String getMainImageAlt() {
        return mainImageAlt;
    }

    public void setMainImageAlt(String mainImageAlt) {
        this.mainImageAlt = mainImageAlt;
    }

    public String getBreathTaking() {
        return breathTaking;
    }

    public void setBreathTaking(String breathTaking) {
        this.breathTaking = breathTaking;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getItemMeasureReferenceText() {
        return itemMeasureReferenceText;
    }

    public void setItemMeasureReferenceText(String itemMeasureReferenceText) {
        this.itemMeasureReferenceText = itemMeasureReferenceText;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getPipUrl() {
        return pipUrl;
    }

    public void setPipUrl(String pipUrl) {
        this.pipUrl = pipUrl;
    }

    public int getItemNoGlobal() {
        return itemNoGlobal;
    }

    public void setItemNoGlobal(int itemNoGlobal) {
        this.itemNoGlobal = itemNoGlobal;
    }

    public String getOnlineSellable() {
        return onlineSellable;
    }

    public void setOnlineSellable(String onlineSellable) {
        this.onlineSellable = onlineSellable;
    }

    public String getLastChance() {
        return lastChance;
    }

    public void setLastChance(String lastChance) {
        this.lastChance = lastChance;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public float getPriceNumeral() {
        return priceNumeral;
    }

    public void setPriceNumeral(float priceNumeral) {
        this.priceNumeral = priceNumeral;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Subcategorias getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategorias subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getVariants() {
        return variants;
    }

    public void setVariants(String variants) {
        this.variants = variants;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public String getAvailabilityDisclaimer() {
        return availabilityDisclaimer;
    }

    public void setAvailabilityDisclaimer(String availabilityDisclaimer) {
        this.availabilityDisclaimer = availabilityDisclaimer;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }   
}
