package com.bateman.richard;

import org.json.JSONArray;
import org.json.JSONObject;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Ruinous Mission test project running.");

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("test.json");
        if(inputStream == null) throw new InvalidStateException("inputStream is null");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String inputStr;
        while((inputStr = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputStr);
        }
        String data = stringBuilder.toString();

        JSONObject jsonData = new JSONObject(data); // (String data)
        JSONArray itemsArray = jsonData.getJSONArray("items"); // assumes the json file contains an “items” object

        for(int i = 0; i < itemsArray.length(); i++) {
            JSONObject jsonPhoto = itemsArray.getJSONObject(i);
            int age = jsonPhoto.getInt("age");
            String name = jsonPhoto.getString("name");
            System.out.println("Age is: " + age);
            System.out.println("Name is: " + name);
            JSONArray messagesArray = jsonPhoto.getJSONArray("messages");
            for(int messageIndex = 0; messageIndex < messagesArray.length(); messageIndex++) {
                System.out.println("Message " + messageIndex + ": " + messagesArray.getString(messageIndex));
            }
        }

    }
}
