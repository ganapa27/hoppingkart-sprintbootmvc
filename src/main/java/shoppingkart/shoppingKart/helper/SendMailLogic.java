package shoppingkart.shoppingKart.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import shoppingkart.shoppingKart.dto.Customer;
import shoppingkart.shoppingKart.dto.Merchant;

@Service
public class SendMailLogic {

    @Autowired
    JavaMailSender mailSender; //used for sending mail

    public void sendEmail(Merchant merchant) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

		try {
			messageHelper.setTo(merchant.getEmail());
			messageHelper.setFrom("E-Commerce");
			messageHelper.setSubject("OTP Verification");
			String body = "<h1 style='color:blue'>Hello " + merchant.getName() + ",<br>Your Otp is : "
					+ merchant.getOtp() + "</h1>";
			messageHelper.setText(body, true);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

    public void sendOtp(Customer customer) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

		try {
			messageHelper.setTo(customer.getEmail());
			messageHelper.setFrom("E-Commerce");
			messageHelper.setSubject("OTP Verification");
			String body = "<h1 style='color:blue'>Hello " + customer.getName() + ",<br>Your Otp is : "
					+ customer.getOtp() + "</h1>";
			messageHelper.setText(body, true);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
