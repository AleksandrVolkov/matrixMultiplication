import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class MultMatrixInThread2 {
    public static Integer[][] fillMatrix(int n, int m) {
        Integer[][] A = new Integer[n][m];
        Random rnd = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                A[i][j] = rnd.nextInt(100);
        return A;
    }
    private static Integer[][] result;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        Integer[][] m1 = fillMatrix(n, m);
        Integer[][] m2 = fillMatrix(m, n);

        if (m1.length != m2[0].length || m1[0].length != m2.length) {
            System.out.println("Wrong matrix length");
            return;
        }
        result = new Integer[m1.length][m1.length];
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(new Task("Task one", m1, m2, result, (i) -> i % 3 == 0));
        es.execute(new Task("Task two", m1, m2, result, (i) -> i % 3 == 1));
        es.execute(new Task("Task three", m1, m2, result, (i) -> i % 3 == 2));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error : InterruptedException\n" + e.getMessage());
        }
        es.shutdown();
        System.out.println("\nResult :\n");
        for (Integer[] l : result) System.out.println(Arrays.toString(l));
    }

}

class Task implements Runnable {

    public String name;
    public Integer[][] m1;
    public Integer[][] m2;
    public Integer[][] result;
    public IntPredicate p;

    public Task(String name, Integer[][] m1, Integer[][] m2, Integer[][] result, IntPredicate predicate) {
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
        this.result = result;
        this.p = predicate;
        System.out.println("Start Thred " + name);
    }

    @Override
    public void run() {
        IntStream.range(0, m1.length).forEach(i -> {
            IntStream.range(0, m1.length).filter(p).forEach(j -> {
                result[i][j] = IntStream.range(0, m1[0].length)
                        .map(e -> m1[i][e] * m2[e][j]).sum();
            });
        });
        System.out.println("Thread " + name + " is finished!");
    }
}
