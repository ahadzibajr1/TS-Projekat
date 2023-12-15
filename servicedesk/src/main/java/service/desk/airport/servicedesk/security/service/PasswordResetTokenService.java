package service.desk.airport.servicedesk.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.desk.airport.servicedesk.security.dao.PasswordResetTokenRepository;
import service.desk.airport.servicedesk.security.dao.UserRepository;
import service.desk.airport.servicedesk.security.entity.PasswordResetToken;
import service.desk.airport.servicedesk.security.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;




    public void resetPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);

        passwordResetTokenRepository.deleteByUser(user);

        passwordResetTokenRepository.save(passwordResetToken);

        mailSenderService.constructResetTokenEmail(token,email);
    }

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);

        if(passToken !=null && passToken.getExpiryDate().isAfter(LocalDateTime.now())) {
            String email = passToken.getUser().getEmail();
            passwordResetTokenRepository.delete(passToken);
            return  email;
        }

        return null;
    }

}
