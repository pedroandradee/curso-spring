package com.example.demo.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.domain.PaymentWithTicket;

@Service
public class TicketService {
    
    public void fillPaymentWithTicket(PaymentWithTicket pg, Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pg.setExpireDate(cal.getTime());
    }
}
