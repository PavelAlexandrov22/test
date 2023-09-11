package it.academy.jd2.firstapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите фамилию: ");
        String lastName = reader.readLine();
        System.out.print("Введите имя: ");
        String firstName = reader.readLine();

        String urlParameters = "?firstName=" + URLEncoder.encode(firstName, StandardCharsets.UTF_8)
                + "&lastName=" + URLEncoder.encode(lastName, StandardCharsets.UTF_8);
        String url = "http://localhost:8080/firstapp/hello_with_name" + urlParameters;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response);
    }
}
