package com.hoonziAPItest.txtDownload.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class apiController {

    private final String apiUrl = "api-주소";

    @PostMapping("/apiCall")
    @ResponseBody
    public String filePathReturn(@RequestParam("date") String date) {

        if(date.contains("-")) { date = date.replace("-",""); }

        String targetUrl = apiUrl+date;
        System.out.println(targetUrl);
        URL url = null;
        try {
            url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            int code = conn.getResponseCode();
            System.out.println("response code : "+ code);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            StringBuilder sb = new StringBuilder();
            String result = "";
            for(int i = 1; (result = br.readLine()) != null; i++){
                sb.append(result);
            }

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("./txt").getFile() + "/sample.txt");
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

            FileWriter fw = new FileWriter(file);
            fw.write(sb.toString());
            fw.close();

            return file.getAbsolutePath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
