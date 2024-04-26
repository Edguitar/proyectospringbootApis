package com.curso.java.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TokenTest {

	public static void main(String [] arg) {
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}
}
