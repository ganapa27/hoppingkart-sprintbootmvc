package shoppingkart.shoppingKart.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import shoppingkart.shoppingKart.dto.Merchant;

@Service
public class SendMailLogic {

    @Autowired
    JavaMailSender mailSender; //used for sending mail

    public void sendEmail(Merchant merchant){
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        try {
            // logic for sending mail content
            messageHelper.setTo(merchant.getEmail());
            messageHelper.setSubject("OTP verification");
            String text = "<h1> Hello "+merchant.getName()+"</h1><h2> Your OTP is "+merchant.getOtp()+"</h2>";
            messageHelper.setText(text,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
