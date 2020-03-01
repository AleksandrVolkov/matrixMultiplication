import java.util.Random;
import java.util.Scanner;


public class Multiplication {
    public static int[][] fillMatrix(int n, int m) {
        int[][] A = new int[n][m];
        Random rnd = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                A[i][j] = rnd.nextInt(100);
        return A;
    }

    private static void printArray(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            System.out.format("%6d ", arr[j]);
        }
        System.out.println();
    }

    private static void printMatrix(int[][] res) {
        for (int i = 0; i < res.length; i++) {
            printArray(res[i]);
        }
        System.out.println();
    }

    public static void singleThreadPrint(int[][] mA, int[][] mB) {
        int[][] res = new int[mA.length][mA.length];
        long start = System.currentTimeMillis();

        for (int i = 0; i < mA.length; i++)
            for (int j = 0; j < mB[0].length; j++)
                for (int k = 0; k < mB.length; k++)
                    res[i][j] += mA[i][k] * mB[k][j];

        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        printMatrix(res);
        System.out.println("Однопоточное выполнение - " + timeConsumedMillis);
    }

    public static void multiThreadPrint(int[][] mA, int[][] mB) {
        int[][] res = new int[mA.length][mA.length];
        long start = System.currentTimeMillis();
        int mid = mA.length / 2;

        new Thread(() -> {
            for (int i = mid; i < mA.length; i++)
                for (int j = 0; j < mB[0].length; j++)
                    for (int k = 0; k < mB.length; k++)
                        res[i][j] += mA[i][k] * mB[k][j];
        }).start();

        for (int i = 0; i < mid; i++)
            for (int j = 0; j < mB[0].length; j++)
                for (int k = 0; k < mB.length; k++)
                    res[i][j] += mA[i][k] * mB[k][j];


        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMatrix(res);
        System.out.println("Многопоточное выполнение - " + timeConsumedMillis);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] mA = fillMatrix(n, m);
        int[][] mB = fillMatrix(m, n);
        int[] shArray = new int[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        singleThreadPrint(mA, mB);
        multiThreadPrint(mA, mB);
        System.out.println("\n\n");

        long start = System.currentTimeMillis();
        sortShellSingle(shArray);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        printArray(shArray);
        System.out.println("\nОднопоточная сортировка Шелла заняла " + timeConsumedMillis);

        shArray = new int[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        start = System.currentTimeMillis();
        sortShellMult(shArray);
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;

        printArray(shArray);

        System.out.println("\nМногопоточная сортировка Шелла заняла " + timeConsumedMillis);


    }

    public static void sortShellSingle(int[] array) {
        int h = 1;
        while (h * 3 < array.length)
            h = h * 3 + 1;
        while (h >= 1) {
            hSort(array, h);
            h = h / 3;
        }
    }

    public static void sortShellMult(int[] array) {
        int h = 1;
        while (h * 3 < array.length)
            h = h * 3 + 1;

        int mid = h / 2;

        while (h >= 1) {
            if (mid < h) {
                int finalH = h;
                new Thread(() -> {
                    hSort(array, finalH);
                }).start();
            } else
                hSort(array, h);
            h = h / 3;
        }
    }

    private static void hSort(int[] array, int h) {
        for (int i = h; i < array.length; i++)
            for (int j = i; j >= h; j = j - h)
                if (array[j] < array[j - h])
                    swap(array, j, j - h);
                else
                    break;

    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
