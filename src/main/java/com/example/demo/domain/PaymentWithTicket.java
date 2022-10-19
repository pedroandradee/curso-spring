package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.example.demo.enums.PaymentState;

@Entity
public class PaymentWithTicket extends Payment {
    private static final long serialVersionUID = 1L;
    
    private Date expireDate;
    private Date paymentDate;

    public PaymentWithTicket() {}

    public PaymentWithTicket(Integer id, PaymentState state, Order order, Date expireDate, Date paymentDate) {
        super(id, state, order);
        this.expireDate = expireDate;
        this.paymentDate = paymentDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    
}