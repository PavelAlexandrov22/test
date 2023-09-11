package it.academy.jd2.firstapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Main1 {
    public static void main(String[] args) {
        try {
            // Запрос фамилии и имени у пользователя
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Введите фамилию: ");
            String surname = reader.readLine();

            System.out.print("Введите имя: ");
            String name = reader.readLine();


            String urlWithQueryString = "http://localhost:8080/firstapp/hello_with_name" +"?firstName="
                    + URLEncoder.encode(name, StandardCharsets.UTF_8)
                    + "&lastName=" + URLEncoder.encode(surname, StandardCharsets.UTF_8);
            URL url = new URL(urlWithQueryString);


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                System.out.println("Ответ сервера: " + response);
            } else {
                System.out.println("Ошибка HTTP-запроса: " + responseCode);
            }


            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
