package com.student.pantry.studentPantry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    private JavaMailSender javaMailSender;

    public void sendOrderConfirmation(String to, Long orderDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Order Confirmation" );
        message.setText("Dear User, \n\nOrder placed successfully...!!! \n\n\n\n\nRegards, \nStudents Pantry Management System");

        javaMailSender.send(message);
    }
    
    public void sendOrderUpdates(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Inventory Update Notification");
        message.setText("Dear User,\n\nWe hope this message finds you well."
        		+ " We have restocked/added new items to the inventory to enhance your pantry experience. "
        		+ "\n\nPlease feel free to explore the updated inventory within the Students Pantry Management System application. "
        		+ "\n\nThank you for choosing our Pantry Management System! \n\n\n\n\nRegards, \nStudents Pantry Management System");
        

        javaMailSender.send(message);
    }
	
}
