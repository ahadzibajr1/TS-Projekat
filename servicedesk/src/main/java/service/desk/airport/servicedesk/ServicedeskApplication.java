package service.desk.airport.servicedesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.desk.airport.servicedesk.security.dao.DepartmentRepository;
import service.desk.airport.servicedesk.security.dao.RoleRepository;
import service.desk.airport.servicedesk.security.dao.UserRepository;
import service.desk.airport.servicedesk.security.entity.Department;
import service.desk.airport.servicedesk.security.entity.Role;
import service.desk.airport.servicedesk.security.entity.User;
import service.desk.airport.servicedesk.security.token.TokenRepository;

@EnableJpaRepositories(basePackages = { "service.desk.airport.servicedesk.dao","service.desk.airport.servicedesk.security.dao","service.desk.airport.servicedesk.security.token"})
@EntityScan(basePackages = {"service.desk.airport.servicedesk.entity","service.desk.airport.servicedesk.security.entity","service.desk.airport.servicedesk.security.token"})
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class ServicedeskApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	TokenRepository tokenRepository;


	public static void main(String[] args) {
		SpringApplication.run(ServicedeskApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception
	{
		deleteExpiredTokens();
		//cleanup();
		//startingData();
	}
	private void cleanup() {
		userRepository.deleteAll();
	}

	private void deleteExpiredTokens() {
		tokenRepository.deleteInvalidTokens();
	}

	/*
	private void startingData() {
		Department department1 = departmentRepository.findById(1).orElseThrow();
		Department department2 = departmentRepository.findById(2).orElseThrow();
		Department department3 = departmentRepository.findById(3).orElseThrow();
		Department department4 = departmentRepository.findById(4).orElseThrow();
		Department department5 = departmentRepository.findById(5).orElseThrow();

		Role role1 = roleRepository.findById(1).orElseThrow();
		Role role2 = roleRepository.findById(2).orElseThrow();

		User user = new User("Saša","Mrdović","sasamrdovic@gmail.com", "$2a$12$8aCjoFVN/tZQ6bfGYIyPx.UDoNBVOT2WqR/wjXIj0JH3aEit4b7sW", department1, role1);
		userRepository.save(user);

		 user = new User("Samra","Behić","sbehic@gmail.com", "$2a$12$BgWc3PCbWMRxYGC9/6OoP.iIrmP/gRD3apQPaVQnums5CCIio5O.i", department2, role2);
		userRepository.save(user);

		 user = new User("Elma","Polutan","pelma@gmail.com", "$2a$12$acu6c/zD36KQu726VJlekelQYn7OsBI2zSs/el1yXXjWogyxvuqYy", department3, role2);
		userRepository.save(user);

		 user = new User("Amila","Hadzibajramovic","ahadzibajr@gmail.com", "$2a$12$dB4IPLaqbfPup7V2wKvHbeoRXoEiby1dTwz8sGh8GT9cUZYTgSfl.", department4, role2);
		userRepository.save(user);

		 user = new User("Agent","Agent","agent007@gmail.com", "$2a$12$jcsMd/V2TpGYWUq00oF0cuosaxD9oTeUjJXrs7vyTNDXUOnBmXHMi", department5, role2);
		userRepository.save(user);
	}*/


}
