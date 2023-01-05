package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.Order;
import com.example.shopping_mall.entity.OrderItem;
import com.example.shopping_mall.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final MemberRepository memberRepository;

    public void mailSend(List<OrderItem> orderItems, String email, Order order) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        String name = memberRepository.findByEmail(email).getName();

        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(name + "님의 주문 내역 확인서");
        message.setText(setContext(orderItems, order), "UTF-8", "html");

        mailSender.send(message);
    }

    public String sendVerificationCode(String email) throws Exception {
        String code = createKey();

        String setFrom = "shoppingmall0710@gmail.com";
        String setTo = email;
        String title = "이메일 인증 코드입니다";
        String content = "홈페이지를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + code + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(setFrom);
            helper.setTo(setTo);
            helper.setSubject(title);
            helper.setText(content);
            mailSender.send(message);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return code;
    }

    private String setContext(List<OrderItem> orderItems, Order order) {
        Context context = new Context();
        context.setVariable("orderItems", orderItems);
        context.setVariable("order", order);
        return templateEngine.process("mail", context);
    }

    private String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("emailVerification", context);
    }

    // 인증번호 생성
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < 8; i++) {
            int idx = random.nextInt(3);

            switch(idx) {
                case 0:
                    // a~z
                    key.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
                case 1:
                    // A~Z
                    key.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0~9
                    key.append(random.nextInt(10));
                    break;
            }
        }
        return key.toString();
    }
}
