/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.cmunegow.mp3.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Id Class for the OrderItems Entity
 * @author Chetan Munegowda
 */
public class OrderItemId {

     // ======================================
    // =            Constructors            =
    // ======================================
    public OrderItemId() {
    }

    // ======================================
    // =             Attributes             =
    // =====================================
    private Long orderItemId;
    
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId", nullable = false)
    private Long itemId;


    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderItemId oiId = (OrderItemId) o;

        if (!itemId.equals(oiId.itemId)) {
            return false;
        }
        if (!orderItemId.equals(oiId.orderItemId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + orderItemId.hashCode();
        return result;
    }

}
