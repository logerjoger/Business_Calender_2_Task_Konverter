package de.logerbyte.businesscalendarconverter.readWriteFile;

import de.logerbyte.businesscalendarconverter.ical.IcalConstants;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Bc2File {
    public static final String REGEX_DATE = "\\d\\d.\\d\\d.\\d\\d\\d\\d";
    public static final String OUT_PATH = "bc2t_test_file" + File.separator + "out.ics";

    private String pathName;

    public static void createIcsFile(Bc2TaskJson[] jsonFile) {
        createNewFile(OUT_PATH);

        Path path = Paths.get(OUT_PATH);

        StringBuilder sb = new StringBuilder();
        sb.append(IcalConstants.createVcalHeader());

        for (int i = 0; i < jsonFile.length; i++) {
            Bc2TaskJson content = jsonFile[i];
            String startTime = parseTimeMMintoDateTime(content.getDtstart());
            String endTime = parseTimeMMintoDateTime(content.getDtend());
            String summary = content.getTitle();
            String description = content.getDescription();

            sb.append(IcalConstants.createEvent(startTime, endTime, summary, description));

            if (i == jsonFile.length - 1) {
                sb.append(IcalConstants.createEnd());
            }
        }

        try {
            Files.write(path, sb.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String parseTimeMMintoDateTime(Long dtstart) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        Date date = new Date(dtstart);
        return df.format(date);
    }

    private static void createNewFile(String path) {
        File file = new File(path);
        file.getParentFile().mkdirs();
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getPathName() {
        return pathName;
    }

    void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String readFileContent(String pathName) {
        try {
            return new String(Files. readAllBytes(Paths.get(pathName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] splitContent(String content, String regex) {
        return content.split(regex);
    }
}
