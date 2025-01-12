package ru.gb.task2_3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[][] status = getStatus();
        printStatus(status);
        
        System.out.println(convertStatusToInt(status));

        byte[] out = convertStatusToBytes(status);
        System.out.println(Arrays.toString(out));

        writeBytesToFile(out, "bytes.file");
        
        byte[] in = readFromFileToBytes("bytes.file");
        System.out.println(Arrays.toString(in));
        
        int[][] status1 = convertFromBytesToStatus(in);
        printStatus(status1);
    }

    private static final int ROWS = 3;
    private static final int COLUMNS = 3;

    // создание матрицы из 9 чисел в диапазоне [0, 3]
    private static int[][] getStatus() {
        Random r = new Random();
        int[][] status = new int[ROWS][];
        for (int i = 0; i < status.length; i++) {
            status[i] = r.ints(COLUMNS, 0, 4).toArray();
        }
        return status;
    }

    private static void printStatus(int[][] status) {
        for (int[] item : status) {
            System.out.println(Arrays.toString(item));
        }
    }

    // преобразование матрицы в одно число (int)
    private static int convertStatusToInt(int[][] status) {
        int res = 0, k = 0;
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = COLUMNS - 1; j >= 0; j--) {
                res += status[i][j] * Math.pow(10, k++);
            } 
        }
        return res;
    }

    // преобразование матрицы в массив байт
    private static byte[] convertStatusToBytes(int[][] status) {
        byte[] res = new byte[ROWS];
        for (int i = 0; i < ROWS; i++) {
            byte b = 0;
            for (int j = 0; j < COLUMNS; j++) {
                b += (byte) (status[i][j] << (COLUMNS - 1 - j) * 2);
            }
            res[i] = b;
        }
        return res;
    }

    // преобразование массива байт в матрицу
    private static int[][] convertFromBytesToStatus(byte[] res) {
        int[][] status = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            int b = res[i];
            for (int j = 0; j < COLUMNS; j++) {
                status[i][j] = b >> (COLUMNS - 1 - j) * 2;
                b -= status[i][j] << (COLUMNS - 1 - j) * 2;
            }
        }
        return status;
    }

    private static void writeBytesToFile(byte[] res, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(res);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    private static byte[] readFromFileToBytes(String filename) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return fis.readAllBytes();
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла: " + e.getMessage());
            return null;
        }
    }
}
