package de.logerbyte.businesscalendarconverter.readWriteFile;

import de.logerbyte.businesscalendarconverter.readWriteFile.Bc2File;
import de.logerbyte.businesscalendarconverter.readWriteFile.Bc2TaskJson;
import de.logerbyte.businesscalendarconverter.readWriteFile.Bc2TaskReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bc2FileTest {

    @Test
    public void test_delete_lineSeparator() throws IOException {
        String string = new String(Files. readAllBytes(Paths.get("bc2t_test_file" + File.separator + "fake_bc2_file.txt")), StandardCharsets.UTF_8);

        Assert.assertTrue(string.contains(System.lineSeparator()));
        String newString = string.replace(System.lineSeparator(), " ");
        Assert.assertFalse(newString.contains(System.lineSeparator()));
        Assert.assertTrue(newString.contains("/n"));
    }

    @Test
    public void test_create_and_write_into_file() throws IOException {
        String writeContent = "That is a test file creation.";
        String pathName = "bc2t_test_file" + File.separator + "test_wrote_into files";
        Files.write(Paths.get(pathName), writeContent.getBytes());

        File file = new File(pathName);
        Assert.assertTrue(file.exists());
        String string = new String(Files. readAllBytes(Paths.get(pathName)), StandardCharsets.UTF_8);
        Assert.assertTrue(string.contains(writeContent));
        Assert.assertTrue(Files.deleteIfExists(Paths.get(pathName)));
    }

    @Test
    public void test_create_icalFile() {
        Bc2TaskJson[] bc2tJsonFromFile = new Bc2TaskReader().createJson("bc2t_test_file" + File.separator + "bc2t_test.txt");
        Bc2File.createICS(bc2tJsonFromFile);
    }
}