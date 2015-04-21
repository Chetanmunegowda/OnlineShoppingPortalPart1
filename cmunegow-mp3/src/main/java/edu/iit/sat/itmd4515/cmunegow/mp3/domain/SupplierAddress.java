package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * It contains shipper address attributes
 * @author Chetan Munegowda
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "SupplierAddress.findById", query = "SELECT sa FROM SupplierAddress sa WHERE sa.supAddressId = :supAddrId")
})
public class SupplierAddress implements Serializable{

    // ======================================
    // =             Attributes             =
    // =====================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supAddressId;

    @Column(name = "supAddrStreet1", nullable = false)
    private String Street1;
    @Column(name = "supAddrStreet2")
    private String street2;
    @Column(name = "supAddrCity", nullable = false)
    private String city;
    @Column(name = "supAddrState", nullable = false)
    private String state;
    @Column(name = "supAddrZipCode", nullable = false)
    private String zipcode;
    @Column(name = "supAddrCountry", nullable = false)
    private String country;

    // ======================================
    // =            Constructors            =
    // ======================================

    public SupplierAddress() {
    }

    public SupplierAddress(String Street1, String street2, String city, String state, String zipcode, String country) {
        this.Street1 = Street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }
    
    
    
    
    
     // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Long getSupAddressId() {
        return supAddressId;
    }

    public void setSupAddressId(Long supAddressId) {
        this.supAddressId = supAddressId;
    }

    public String getStreet1() {
        return Street1;
    }

    public void setStreet1(String Street1) {
        this.Street1 = Street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "SupplierAddress{" + "supAddressId=" + supAddressId + ", Street1=" + Street1 + ", street2=" + street2 + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", country=" + country + '}';
    }
    
    

}
