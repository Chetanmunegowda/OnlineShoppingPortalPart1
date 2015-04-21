package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Customer Address contains the address fields of customer
 * @author Chetan Munegowda
 */
@Entity
@NamedQuery(name = "CustomerAddress.findById", query = "SELECT ca FROM CustomerAddress ca WHERE ca.custAddressId = :custAddrId")
@Table(name = "CustomerAddress")
public class CustomerAddress implements Serializable{

    // ======================================
    // =             Attributes             =
    // =====================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long custAddressId;

    @Column(name = "custAddrStreet1", nullable = false)
    private String Street1;
    @Column(name = "custAddrStreet2")
    private String street2;
    @Column(name = "custAddrCity", nullable = false)
    private String city;
    @Column(name = "custAddrState", nullable = false)
    private String state;
    @Column(name = "custAddrZipCode", nullable = false)
    private String zipcode;
    @Column(name = "custAddrCountry", nullable = false)
    private String country;

     // ======================================
    // =            Constructors            =
    // ======================================

    public CustomerAddress() {
    }

    public CustomerAddress(String Street1, String street2, String city, String state, String zipcode, String country) {
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
    public Long getCustAddressId() {
        return custAddressId;
    }

    public void setCustAddressId(Long custAddressId) {
        this.custAddressId = custAddressId;
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
        return "CustomerAddress{" + "custAddressId=" + custAddressId + ", Street1=" + Street1 + ", street2=" + street2 + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", country=" + country + '}';
    }
    
    

}
