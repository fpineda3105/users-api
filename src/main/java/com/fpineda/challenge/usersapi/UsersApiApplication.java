package com.fpineda.challenge.usersapi;

import java.time.LocalDate;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.AddressEntity;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.entity.UserEntity;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.JpaAddressPersistenceAdapter;
import com.fpineda.challenge.usersapi.infrastructure.adapter.persistence.repository.JpaUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.fpineda.challenge.usersapi.config"})
public class UsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApiApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner populateUsers(JpaUserPersistenceAdapter userPersistence,
	// 		JpaAddressPersistenceAdapter addressPersistence) {

	// 	return args -> {
	// 		var user = new UserEntity();
	// 		user.setName("Fernando");
	// 		user.setEmail("fpineda@gmail.com");
	// 		user.setBirthDate(LocalDate.of(1988, 05, 31));

	// 		var address = new AddressEntity();
	// 		address.setCity("Caracas");
	// 		address.setCountry("Venezuela");
	// 		address.setState("Dtt. Capital");
	// 		address.setZipCode("37123");


	// 		user.setAddress(address);

	// 		user = userPersistence.save(user);

	// 		log.info("user saved: {}", user);

	// 		log.info("address saved: {}", addressPersistence.findAll().get(0));
			
		

	// 	};
	// }

}
