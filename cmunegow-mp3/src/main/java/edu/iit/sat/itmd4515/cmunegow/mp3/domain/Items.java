package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * ********************************
 * Suppler supplies many Items. Its many to many relationship
 * *******************************
 */
@Entity
@Table(name = "Items")
@NamedQueries({
    @NamedQuery(name = "Items.findById", query = "SELECT i FROM Items i WHERE i.itemId = :ItemId"),
    @NamedQuery(name = "Items.findByName", query = "SELECT i FROM Items i WHERE i.itemName = :ItemName"),
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i")
})
public class Items implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    @Column(nullable = false, length = 30, unique = true)
    private String itemName;
    @Column(nullable = false)
    private Integer itemCost;
    @Column(nullable = false)
    private Integer itemQuantity;
    @Column(nullable = false)
    private Boolean itemAvailability;

    @ManyToMany(mappedBy = "supItems")
    private List<Supplier> itmSuppliers;
    

    // ======================================
    // =            Constructors            =
    // ======================================
    public Items() {
    }

    public Items(String itemName, Integer itemCost, Integer itemQuantity, Boolean itemAvailability) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemQuantity = itemQuantity;
        this.itemAvailability = itemAvailability;
    }
    
    
    

     // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemCost() {
        return itemCost;
    }

    public void setItemCost(Integer itemCost) {
        this.itemCost = itemCost;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Boolean getItemAvailability() {
        return itemAvailability;
    }

    public void setItemAvailability(Boolean itemAvailability) {
        this.itemAvailability = itemAvailability;
    }

    public List<Supplier> getItmSuppliers() {
        return itmSuppliers;
    }

    public void setItmSuppliers(List<Supplier> itmSuppliers) {
        this.itmSuppliers = itmSuppliers;
    }

    @Override
    public String toString() {
        return "Items{" + "itemId=" + itemId + ", itemName=" + itemName + ", itemCost=" + itemCost + ", itemQuantity=" + itemQuantity + ", itemAvailability=" + itemAvailability + '}';
    }
    
    
}
