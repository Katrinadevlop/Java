import java.util.Random;

public class Main {
    public static final int SIZE = 8;

    public static void main(String[] args) {
        Random random = new Random();
        int[][] randomColors = new int[SIZE][SIZE];
        int[][] rotatedColors = new int[SIZE][SIZE];

        fillArray(randomColors, random);
        printArray(randomColors);
        System.out.println();

        System.out.println("Поворот на 90 градусов");
        rotateArray90Degrees(randomColors, rotatedColors);
        printArray(rotatedColors);
        System.out.println();

        System.out.println("Поворот на 180 градусов");
        rotateArray180Degrees(randomColors, rotatedColors);
        printArray(rotatedColors);
        System.out.println();

        System.out.println("Поворот на 270 градусов");
        rotateArray270Degrees(randomColors, rotatedColors);
        printArray(rotatedColors);
        System.out.println();
    }

    private static void fillArray(int[][] randomColors, Random random) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                randomColors[row][cells] = random.nextInt(255);
            }
        }
    }

    private static void rotateArray90Degrees(int[][] randomColors, int[][] rotatedColors) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                rotatedColors[row][cells] = randomColors[SIZE - 1 - cells][row];
            }
        }
    }

    private static void rotateArray180Degrees(int[][] randomColors, int[][] rotatedColors) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                rotatedColors[row][cells] = randomColors[SIZE - 1 - row][SIZE - 1 - cells];
            }
        }
    }

    private static void rotateArray270Degrees(int[][] randomColors, int[][] rotatedColors) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                rotatedColors[row][cells] = randomColors[cells][SIZE - 1 - row];
            }
        }
    }

    private static void printArray(int[][] arrayRandomNumbers) {
        for (int row = 0; row < SIZE; row++) {
            for (int cells = 0; cells < SIZE; cells++) {
                System.out.format("%4d", arrayRandomNumbers[row][cells]);
            }
            System.out.println();
        }
    }
}