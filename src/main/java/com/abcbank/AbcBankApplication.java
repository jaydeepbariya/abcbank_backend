package com.abcbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AbcBankApplication implements Runnable{

	Logger logger = LogManager.getLogger(AbcBankApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AbcBankApplication.class, args);
	}

	@Override
	public void run() {
			logger.info("Info level log example");
			logger.debug("Debug level log example");
			logger.error("Error level log example", new Exception("Example exception"));
		}
	}

