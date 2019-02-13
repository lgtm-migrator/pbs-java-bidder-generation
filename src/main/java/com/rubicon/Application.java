package com.rubicon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class Application {

    private static final String HOMEPAGE = "http://localhost:8080/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        launchBrowser();
    }

    private static void launchBrowser() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(HOMEPAGE));
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + HOMEPAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
