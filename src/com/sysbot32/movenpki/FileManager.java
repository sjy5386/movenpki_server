package com.sysbot32.movenpki;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    public byte[] read(String filename) {
        byte[] data;
        try {
            data = Files.readAllBytes(Paths.get(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    public void write(String filename, byte[] data) {
        try {
            Files.write(Paths.get(filename), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
