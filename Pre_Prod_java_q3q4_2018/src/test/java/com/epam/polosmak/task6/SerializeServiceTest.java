package com.epam.polosmak.task6;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.util.SerializationUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SerializeServiceTest {

    private static final String NAME = "Shorts";
    private static final String COLOR = "red";
    private static final int PRICE = 100;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File testFile;
    private File testGzip;
    private Sportswear sportswear;
    private Map<Sportswear, Integer> map;
    private SerializationUtil serializeService;

    @Before
    public void init() throws IOException {
        serializeService = new SerializationUtil();
        sportswear = new Sportswear(NAME, PRICE, COLOR);
        map = new HashMap<>();
        map.put(sportswear, 0);
        testFile = folder.newFile("testSave.bin");
        testGzip = folder.newFile("testGzip.bin");
    }

    @Test
    public void shouldSaveMapToFile() {
        SerializationUtil.serialize(map, testFile.getPath());
        assertTrue(testFile.exists());
    }

    @Test
    public void shouldReturnDeSerializedMap() {
        SerializationUtil.serialize(map, testFile.getPath());
        Map<Sportswear, Integer> loaded = (Map<Sportswear, Integer>) SerializationUtil.deserialize(testFile.getPath());
        assertEquals(map, loaded);
    }

    @Test
    public void shouldReturnEmptyMap_whenFileNotExists() {
        Map<Sportswear, Integer> loaded2 = (Map<Sportswear, Integer>) SerializationUtil.deserialize(testFile.getPath());
        assertTrue(loaded2.isEmpty());
    }

    @Test
    public void archivedFileShouldBeSmallerThenNonArchived() throws Exception {
        SerializationUtil.serialize(map, testFile.getPath());
        try (FileOutputStream fos = new FileOutputStream(testGzip)) {
            GZIPOutputStream gz = new GZIPOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(gz);
            oos.writeObject(map);
            oos.flush();
            oos.close();
        }
        assertTrue(testFile.length() > testGzip.length());
    }
}
