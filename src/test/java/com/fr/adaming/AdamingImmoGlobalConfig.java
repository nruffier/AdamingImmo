package com.fr.adaming;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;
/**
 * @author Nicolas RUFFIER & Mehdi :)
 *
 */
@Configuration
public class AdamingImmoGlobalConfig {

	@PostConstruct
	public void init() {
		Server webServer;
		try {
			webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8072");
			webServer.start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
