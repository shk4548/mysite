package com.poscoict.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySiteApplication.class, args);  // 웹에서는 이렇게 사용  try catch 는 콘솔용에서는 쓰지만 웹에서 사용할때는 쓰지 않음
	}

}
