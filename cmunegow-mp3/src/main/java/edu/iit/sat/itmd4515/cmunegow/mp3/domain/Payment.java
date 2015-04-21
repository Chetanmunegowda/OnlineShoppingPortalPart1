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
 *Payment contains the payment mode
 * @author Chetan Munegowda
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Payment.findById", query = "SELECT p FROM Payment p WHERE p.paymentId = :pId"),
    @NamedQuery(name = "Payment.findByMode", query = "SELECT p FROM Payment p WHERE p.pMode = :pMode"),
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
})
public class Payment implements Serializable{

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    @Column(unique = true)
    private String pMode;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Payment() {
    }

    public Payment(String pMode) {
        this.pMode = pMode;
    }

    
    
    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getpMode() {
        return pMode;
    }

    public void setpMode(String pMode) {
        this.pMode = pMode;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", pMode=" + pMode + '}';
    }
    
    

}
