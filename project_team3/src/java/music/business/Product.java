/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.business;

import java.text.NumberFormat;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author William
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
    
    @Id
    @Column (name = "Product_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;    
    
    @Column (name = "Product_code")
    private String code;
    @Column (name = "Product_Description")
    private String description;
    @Column (name = "Product_Price")
    private double price;
    @Column (name = "Product_CoverURL")
    private String coverURL = "";

    public Product() {}

    public Long getId() {
        return productId;
    }

    public void setId(Long productId) {
        this.productId = productId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        if(code == null) return "";
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        if(description == null) return "";
        return description;
    }

    public String getArtistName() {
        try {
            String artistName = 
                    description.substring(0, description.indexOf(" - "));
            return artistName;
        
        } catch(StringIndexOutOfBoundsException e)  {
                return "";
        }
    }

    public String getAlbumName() {
        try {
            String albumName = 
                description.substring(description.indexOf(" - ") + 3);
            return albumName;
        } catch(StringIndexOutOfBoundsException e)  {
                return "";
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
    
    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
    
    public String getCoverURL() {
        return coverURL;
    }
    
    public String getImageURL() {
        String imageURL = "../musicStore/images/" + productId.toString() + "_cover.jpg";
        return imageURL;
    }

    public String getProductType() {
        return "Audio CD";
    }
}