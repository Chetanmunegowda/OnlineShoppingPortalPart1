package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User can be Customer who uses the Online Portal System
 *
 * @author Chetan Munegowda
 */
@Entity
@Table(name = "Customers")
@NamedQueries({
    @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.custName = :name"),
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
})

public class Customer extends Users implements Serializable{

    // ======================================
    // =             Attributes             =
    // ======================================
    @Column(nullable = false, unique = true, length= 40)
    private String custName;
    @Column(nullable = false, unique = true, length = 20)
    private String custPhone;
    @Column(nullable = false, unique = true, length = 45)
    private String custEmail;
    @Column
    private Integer custAge;
    @Column(unique = true, length = 20)
    private String cardNumber;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "custAddressId", unique = true, nullable = false)
    private CustomerAddress custAddress;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private List<Orders> order;
    

    // ======================================
    // =            Constructors            =
    // ======================================
    public Customer() {
    }

    public Customer(String userName, String userPassword, String custName, String custPhone, String custEmail, Integer custAge, String cardNumber) {
        super(userName, userPassword);
        this.custName = custName;
        this.custPhone = custPhone;
        this.custEmail = custEmail;
        this.custAge = custAge;
        this.cardNumber = cardNumber;
    }
    
    
    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Integer getCustAge() {
        return custAge;
    }

    public void setCustAge(Integer custAge) {
        this.custAge = custAge;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustName() {
        return custName;
    }

    public CustomerAddress getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(CustomerAddress custAddress) {
        this.custAddress = custAddress;
    }
    
    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<Orders> getOrder() {
        return order;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Customer{" + "custName=" + custName + ", custPhone=" + custPhone + ", custEmail=" + custEmail + ", custAge=" + custAge + ", cardNumber=" + cardNumber + ", custAddress=" + custAddress + '}';
    }
    
    
    
}
