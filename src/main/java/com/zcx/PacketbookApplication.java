package com.zcx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.zcx.dao")
@EnableTransactionManagement
public class PacketbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacketbookApplication.class, args);
	}
}
