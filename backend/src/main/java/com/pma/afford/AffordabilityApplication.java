package com.pma.afford;

import com.pma.afford.configuration.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import com.google.gson.Gson;
import com.pma.afford.configuration.RSAKeyProperties;
import com.pma.afford.controllers.UserController;
import com.pma.afford.entities.Product;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**

 This is the main class for the Affordability Application. It extends the WebScrapping class and uses Spring Boot to run the application.
 It also includes a TimerTask that runs the scrapCostcoWallmartSobeysAddToTable() method every 24 hours using a Timer.
 */
@EnableConfigurationProperties(RSAKeyProperties.class)
@SpringBootApplication
public class AffordabilityApplication {
	/**
	 * The main method for the Affordability Application. It runs the Spring Boot application and schedules a TimerTask to run every 24 hours.
	 * @param args an array of command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		long delayInMillis = 0L;
		long periodInMillis = 1000L * 86400L;
		SpringApplication.run(AffordabilityApplication.class, args);
		WebScrapping webScrapping=new WebScrapping();
		Timer timer = new Timer();
		TimerTask t = new TimerTask() {
			@Override
			public void run() {
				try {
				String tokenAuthorize = webScrapping.testAuth();
					webScrapping.scrapCostcoWallmartSobeysAddToTable(tokenAuthorize);

				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
		timer.schedule(t, delayInMillis, periodInMillis);
	}
}

