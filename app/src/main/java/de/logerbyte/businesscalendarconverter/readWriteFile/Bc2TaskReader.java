package de.logerbyte.businesscalendarconverter.readWriteFile;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bc2TaskReader {
    public Bc2TaskReader() {
    }

    public Bc2TaskJson[] createBc2tJsonFromFile(String pathFile) {
        Gson gson = new Gson();
        String convertedText = replaceLineSeparator(pathFile);
        JsonReader jsonReader = new JsonReader(new StringReader(convertedText));

        return gson.fromJson(jsonReader, Bc2TaskJson[].class);
    }

    private String replaceLineSeparator(String pathname) {

        String textRAW = null;
        try {
            // TODO: 2019-05-28 resolve min sdk errors
            textRAW = new String(Files.readAllBytes(Paths.get(pathname)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textRAW.replace(System.lineSeparator(), "");    }
}
