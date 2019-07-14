package de.logerbyte.businesscalendarconverter;

import de.logerbyte.businesscalendarconverter.readWriteFile.Bc2TaskJson;
import de.logerbyte.businesscalendarconverter.readWriteFile.Bc2TaskReader;
import org.junit.Test;

import java.io.File;

public class MainControllerTest {

    public MainControllerTest() {}

    @Test
    public void testCreateJson() {
        new Bc2TaskReader().createJson("bc2t_test_file" + File.separator + "bc2t_test.txt");
    }
}