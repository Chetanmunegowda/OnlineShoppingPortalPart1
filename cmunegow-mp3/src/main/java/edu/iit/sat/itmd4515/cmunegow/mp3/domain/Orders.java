package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ***************************
 * Customer can place one to many orders. Orders contains the Order Amount,
 * Order Status etc **************************
 */
@Entity
@Table(name = "Orders")
@NamedQueries({
    @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
})
public class Orders implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Column(nullable = false)
    private String ordStatus;

    @Column(nullable = false)
    private Integer ordTotAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date ordCreationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date ordCancelDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date ordDeliverDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private List<OrderItems> ordItems;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "paymentId", unique = true, nullable = false)
    private Payment pymt;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Orders() {
    }

    public Orders(String ordStatus, Integer ordTotAmount, Date ordCreationDate) {
        this.ordStatus = ordStatus;
        this.ordTotAmount = ordTotAmount;
        this.ordCreationDate = ordCreationDate;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrdCreationDate() {
        return ordCreationDate;
    }

    public void setOrdCreationDate(Date ordCreationDate) {
        this.ordCreationDate = ordCreationDate;
    }

    public String getOrdStatus() {
        return ordStatus;
    }

    public void setOrdStatus(String ordStatus) {
        this.ordStatus = ordStatus;
    }

    public Integer getOrdTotAmount() {
        return ordTotAmount;
    }

    public void setOrdTotAmount(Integer ordTotAmount) {
        this.ordTotAmount = ordTotAmount;
    }

    public Integer calculateOrderTotCost(List<OrderItems> oiList, Items item) {
        Integer orderCost = 0;

        return orderCost;
    }

    public Payment getPymt() {
        return pymt;
    }

    public void setPymt(Payment pymt) {
        this.pymt = pymt;
    }

    public List<OrderItems> getOrdItems() {
        return ordItems;
    }

    public void setOrdItems(List<OrderItems> ordItems) {
        this.ordItems = ordItems;
    }

    public Date getOrdCancelDate() {
        return ordCancelDate;
    }

    public void setOrdCancelDate(Date ordCancelDate) {
        this.ordCancelDate = ordCancelDate;
    }

    public Date getOrdDeliverDate() {
        return ordDeliverDate;
    }

    public void setOrdDeliverDate(Date ordDeliverDate) {
        this.ordDeliverDate = ordDeliverDate;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", ordStatus=" + ordStatus + ", ordTotAmount=" + ordTotAmount + ", ordCreationDate=" + ordCreationDate + ", ordCancelDate=" + ordCancelDate + ", ordDeliverDate=" + ordDeliverDate + '}';
    }
    
    

}
