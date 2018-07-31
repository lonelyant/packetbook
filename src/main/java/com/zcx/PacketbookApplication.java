package com.zcx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zcx.dao")
public class PacketbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacketbookApplication.class, args);
	}
}
