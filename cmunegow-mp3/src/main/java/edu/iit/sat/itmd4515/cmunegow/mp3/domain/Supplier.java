package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User can be Supplier. Supplier can supply many items and 
 * has many shippers
 *
 * @author Chetan Munegowda
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s")
})
@Table(name = "Suppliers")
public class Supplier extends Users implements  Serializable{

    // ======================================
    // =             Attributes             =
    // ======================================
    @Column(nullable = false, unique = true, length = 20)
    private String supPhone;
    @Column(nullable = false, unique = true, length = 45)
    private String supEmail;
    
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "supAddrId", unique = true, nullable = false)
    private SupplierAddress supAddress;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "Suplr_Items", joinColumns = @JoinColumn(name = "supId"), inverseJoinColumns = @JoinColumn(name = "itemId"))
    private List<Items> supItems;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "Suplr_Shipper", joinColumns = @JoinColumn(name = "supId"), inverseJoinColumns = @JoinColumn(name = "shipperId"))
    private List<Shipper> shippers;
            
    
    
    // ======================================
    // =            Constructors            =
    // ======================================
    public Supplier() {
    }

    public Supplier(String userName, String userPassword,String supPhone, String supEmail) {
        super(userName, userPassword);
        this.supPhone = supPhone;
        this.supEmail = supEmail;
    }
    
    

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public String getSupPhone() {
        return supPhone;
    }

    public void setSupPhone(String supPhone) {
        this.supPhone = supPhone;
    }

    public SupplierAddress getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(SupplierAddress supAddress) {
        this.supAddress = supAddress;
    }

    public List<Items> getSupItems() {
        return supItems;
    }

    public void setSupItems(List<Items> supItems) {
        this.supItems = supItems;
    }

    public List<Shipper> getShippers() {
        return shippers;
    }

    public void setShippers(List<Shipper> shippers) {
        this.shippers = shippers;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }
    
    

    @Override
    public String toString() {
        return "Supplier{" + "supPhone=" + supPhone + ", supEmail=" + supEmail + '}';
    }
    
}
