package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Items can belong various category.
 * @author Chetan Munegowda
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.catName = :catName"),
    @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.catId = :catId"),
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
})
public class Category implements Serializable{

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long catId;
    @Column(nullable = false, unique = true,length = 50)
    private String catName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Item_Category",
               joinColumns = 
                       @JoinColumn(name = "catId", referencedColumnName = "catId"),
               inverseJoinColumns =
                       @JoinColumn(name = "itemId", referencedColumnName = "itemId"))
    private List<Items> Items;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Category() {
    }

    public Category(String catName) {
        this.catName = catName;
    }
    
    

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<Items> getItems() {
        return Items;
    }

    public void setItems(List<Items> Items) {
        this.Items = Items;
    }
    
}
