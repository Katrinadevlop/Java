import java.util.Random;

public class Main {
    public static final int SIZE = 8;

    public static void main(String[] args) {
        Random random = new Random();
        int[][] randomColors = new int[SIZE][SIZE];
        int[][] rotatedColors = new int[SIZE][SIZE];

        FillingArray(randomColors, random);
        PrintArray(randomColors);
        System.out.println();

        System.out.println("Поворот на 90 градусов");
        RotateArrayOn90Degrees(randomColors, rotatedColors);
        PrintArray(rotatedColors);
        System.out.println();

        System.out.println("Поворот на 180 градусов");
        RotateArrayOn180Degrees(randomColors, rotatedColors);
        PrintArray(rotatedColors);
        System.out.println();

        System.out.println("Поворот на 270 градусов");
        RotateArrayOn270Degrees(randomColors, rotatedColors);
        PrintArray(rotatedColors);
        System.out.println();
    }

    private static void FillingArray(int[][] randomColors, Random random) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                randomColors[row][cells] = random.nextInt(255);
            }
        }
    }

    private static void RotateArrayOn90Degrees(int[][] randomColors, int[][] rotatedColors) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                rotatedColors[row][cells] = randomColors[SIZE - 1 - cells][row];
            }
        }
    }

    private static void RotateArrayOn180Degrees(int[][] randomColors, int[][] rotatedColors) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                rotatedColors[row][cells] = randomColors[SIZE - 1 - row][SIZE - 1 - cells];
            }
        }
    }

    private static void RotateArrayOn270Degrees(int[][] randomColors, int[][] rotatedColors) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                rotatedColors[row][cells] = randomColors[cells][SIZE - 1 - row];
            }
        }
    }

    private static void PrintArray(int[][] arrayRandomNumbers) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                System.out.format("%4d", arrayRandomNumbers[row][cells]);
            }
            System.out.println();
        }
    }
}