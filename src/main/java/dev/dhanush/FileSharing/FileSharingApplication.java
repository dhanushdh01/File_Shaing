package dev.dhanush.FileSharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FileSharingApplication {

	public static void main(String[] args) {

		SpringApplication.run(FileSharingApplication.class, args);

		System.out.println(new BCryptPasswordEncoder().encode("admin"));
		System.out.println(new BCryptPasswordEncoder().encode("user"));

	}

}
