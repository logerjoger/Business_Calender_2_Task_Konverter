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

    public Bc2TaskJson[] createBc2tJsonFromFile(String arg) {
        Gson gson = new Gson();
        Bc2File bc2TaskBc2File = new Bc2File();
        bc2TaskBc2File.setPathName(arg);


        String replacedString = replaceLineSeparator(arg);

        JsonReader jsonReader = new JsonReader(new StringReader(replacedString));
        return gson.fromJson(jsonReader, Bc2TaskJson[].class);
    }

    private String replaceLineSeparator(String pathname) {

        String string = null;
        try {
            string = new String(Files.readAllBytes(Paths.get(pathname)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string.replace(System.lineSeparator(), "");    }
}
