package service.desk.airport.servicedesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import service.desk.airport.servicedesk.security.dao.DepartmentRepository;
import service.desk.airport.servicedesk.security.dao.PasswordResetTokenRepository;
import service.desk.airport.servicedesk.security.dao.RoleRepository;
import service.desk.airport.servicedesk.security.dao.UserRepository;
import service.desk.airport.servicedesk.security.entity.Department;
import service.desk.airport.servicedesk.security.entity.PasswordResetToken;
import service.desk.airport.servicedesk.security.entity.Role;
import service.desk.airport.servicedesk.security.entity.User;
import service.desk.airport.servicedesk.security.token.TokenRepository;

import java.time.LocalDateTime;

@EnableJpaRepositories(basePackages = { "service.desk.airport.servicedesk.dao","service.desk.airport.servicedesk.security.dao","service.desk.airport.servicedesk.security.token"})
@EntityScan(basePackages = {"service.desk.airport.servicedesk.entity","service.desk.airport.servicedesk.security.entity","service.desk.airport.servicedesk.security.token"})
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@EnableScheduling
public class ServicedeskApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	TokenRepository tokenRepository;


	public static void main(String[] args) {
		SpringApplication.run(ServicedeskApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception
	{
		deleteExpiredTokens();
		//deleteExpiredPasswordResetTokens();
		//cleanup();
		//startingData();
	}
	private void cleanup() {
		userRepository.deleteAll();
	}

	private void deleteExpiredTokens() {
		tokenRepository.deleteInvalidTokens();
	}

	private void deleteExpiredPasswordResetTokens() {
		passwordResetTokenRepository.deleteExpiredTokens(LocalDateTime.now());
	}

	@Scheduled(cron = "0 0 * * * *") // Cron expression for running every hour
	public void performTask() {
		System.out.println("Scheduled deletion of expired tokens.");
		deleteExpiredTokens();
		deleteExpiredPasswordResetTokens();
	}


}
