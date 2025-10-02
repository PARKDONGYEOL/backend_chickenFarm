package com.backend.chickenFarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //매 자정에 나이 증가 쿼리 실행을 위한 어노테이션(스케줄 기능 활성화)
public class ChickenFarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChickenFarmApplication.class, args);
	}

}
