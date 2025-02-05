package org.telemedicine.serverside.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.telemedicine.serverside.dto.mail.SendEmailDto;
import org.telemedicine.serverside.exception.AppException;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String systemEmail;

    public void sendEmail(SendEmailDto emailPayload) {
        var message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailPayload.getTo());
            helper.setSubject(emailPayload.getSubject());
            helper.setText(emailPayload.getText(), true);
            helper.setFrom(systemEmail);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.print(e);
            throw new AppException(HttpStatus.BAD_REQUEST, "Send mail fail");
        }
    }
    public void sendEmailToVerifyRegister(String toEmail, String verificationCode) {
        String verifyUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/auth/register/verify/{verificationCode}")
                .buildAndExpand(verificationCode)
                .toUriString();
        String emailText = String.format(
                """
                <body style="margin: 0; padding: 0; background-color: #f0f4fc; font-family: Arial, sans-serif;">
                    <table width="100%%" cellspacing="0" cellpadding="0" style="background-color: #f0f4fc; padding: 20px;">
                        <tr>
                            <td align="center">
                                <table width="600" cellspacing="0" cellpadding="20" style="background-color: #e0e7ff; border-radius: 8px;">
                                    <tr>
                                        <td align="center" style="color: #1a1a1a; font-size: 24px; font-weight: bold;">
                                            Xác thực tài khoản
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center" style="color: #444444; font-size: 16px; line-height: 1.6;">
                                            Vui lòng nhấn vào nút bên dưới để xác thực địa chỉ email và hoàn thành đăng ký tài khoản. Đường dẫn có giá trị trong 5 phút.
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <!-- Confirm Button -->
                                            <a href="%s" style="display: inline-block; padding: 12px 24px; background-color: #1e40af; color: #ffffff; text-decoration: none; font-size: 16px; font-weight: bold; border-radius: 5px;">
                                                Xác thực
                                            </a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </body>
                """, verifyUrl
        );
        SendEmailDto emailPayload = SendEmailDto.builder()
                .to(toEmail)
                .subject("Verity email to register TELEMEDICINE")
                .text(emailText)
                .isHtml(true)
                .build();
        sendEmail(emailPayload);
    }

    public void sendEmailToWelcome(String email) {
        
    }
}
