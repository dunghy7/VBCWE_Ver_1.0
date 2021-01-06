package com.dtsvn.vbcwe.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeManager;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

@Component
public class OfficeManagerInstance {
	private static OfficeManager INSTANCE = null;

	public static synchronized void start() {
		officeManagerStart();
	}

	@PostConstruct
	private void init() {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
			String[] portNumbers = properties.getProperty("portNumbers", "").split(",");
			int[] ports = new int[portNumbers.length];

			for (int i = 0; i < portNumbers.length; i++) {
				ports[i] = Integer.parseInt(portNumbers[i]);
			}

			LocalOfficeManager.Builder builder = LocalOfficeManager.builder().install();
			builder.officeHome(properties.getProperty("libreOfficeHome", ""));
			builder.portNumbers(ports);
			builder.taskExecutionTimeout(
					Integer.parseInt(properties.getProperty("taskExecutionTimeoutMinutes", "")) * 1000 * 60); // minute
			builder.taskQueueTimeout(
					Integer.parseInt(properties.getProperty("taskQueueTimeoutHours", "")) * 1000 * 60 * 60); // hour

			INSTANCE = builder.build();
			officeManagerStart();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void officeManagerStart() {
		if (INSTANCE.isRunning()) {
			return;
		}

		try {
			INSTANCE.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}