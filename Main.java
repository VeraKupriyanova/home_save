package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    private static String dirFirst = "C:\\Users\\Вера\\Games\\savegames\\save1.dat";
    private static String dirSecond = "C:\\Users\\Вера\\Games\\savegames\\save2.dat";
    private static String dirThird = "C:\\Users\\Вера\\Games\\savegames\\save3.dat";
    private static String dirZip = "C:\\Users\\Вера\\Games\\savegames\\result.zip";

    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(1, 2, 3, 4);
        GameProgress gameProgress2 = new GameProgress(5, 6, 7, 8);
        GameProgress gameProgress3 = new GameProgress(9, 10, 11, 12);

        saveGame(dirFirst, gameProgress1);
        saveGame(dirSecond, gameProgress2);
        saveGame(dirThird, gameProgress3);


        List<String> list = new ArrayList<>();
        list.add(dirFirst);
        list.add(dirSecond);
        list.add(dirThird);
        zipFiles(dirZip, list);
        File file1 = new File(dirFirst);
        File file2 = new File(dirSecond);
        File file3 = new File(dirThird);
        List<File> listFiles = new ArrayList<>();
        listFiles.add(file1);
        listFiles.add(file2);
        listFiles.add(file3);
        deleteFile(listFiles);
    }

    public static void saveGame(String src, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(src)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(gameProgress);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void zipFiles(String srcArchive, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(srcArchive))) {
            for (String value : list) {
                try (FileInputStream fis = new FileInputStream(value)) {
                    ZipEntry entry = new ZipEntry(value);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(List<File> listFiles) {
        for (File fileVal : listFiles) {
            if (fileVal.delete()) {
                System.out.println(fileVal.getName() + " - файл удален");
            }
        }
    }


}
