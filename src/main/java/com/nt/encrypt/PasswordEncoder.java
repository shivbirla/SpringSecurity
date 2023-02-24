package com.nt.encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String pwd1 = encoder.encode("punasiya");
		String pwd2 = encoder.encode("Birla");

		System.out.println(pwd1);
		System.out.println(pwd2);

	}

}
