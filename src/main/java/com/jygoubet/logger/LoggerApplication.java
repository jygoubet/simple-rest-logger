package com.jygoubet.logger;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class LoggerApplication {

    private static final String ANSI_RESET = "\u001B[0m";

    private static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final Map<String, String> COLOR_MAP = new HashMap<>();
    static {
        COLOR_MAP.put("debug", "\u001B[37m");
        COLOR_MAP.put("info", "\u001B[34m");
        COLOR_MAP.put("success", "\u001B[32m");
        COLOR_MAP.put("warning", "\u001B[33m");
        COLOR_MAP.put("error", "\u001B[31m");
        COLOR_MAP.put("fatal", "\u001B[35m");
    }

    @PostMapping(path = "/log", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postFoo(@RequestBody String body) {
        JSONObject json = new JSONObject(body);
        String level = "debug";
        if (json.has("level")) {
            level = json.getString("level").toLowerCase();
            if (!COLOR_MAP.containsKey(level)) {
                level = "debug";
            }
        }
        if (json.has("data")) {
            log(level, json.getString("data"));
        }
        return "";
    }

    private static void log(String level, String data) {
        System.out.println(COLOR_MAP.get(level) + LOG_DATE_FORMAT.format(Calendar.getInstance().getTime()) + " [" + level.toUpperCase() + "] " + data + ANSI_RESET);
    }

    public static void main(String[] args)  {
        SpringApplication.run(LoggerApplication.class, args);
        System.out.println("\n\n");
        log("debug", "  ============= Log DEBUG =============");
        log("info", "   ============= Log INFO =============");
        log("success", "============= Log SUCCESS =============");
        log("warning", "============= Log WARNING =============");
        log("error", "  ============= Log ERROR =============");
        log("fatal", "  ============= Log FATAL =============");
        System.out.println("\n\n");
    }
}