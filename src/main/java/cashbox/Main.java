package cashbox;

import java.util.Random;

public class Main {
    static Cashbox cashbox = new Cashbox();

    static ClerkThread clerk1 = new ClerkThread(cashbox);
    static ClerkThread clerk2 = new ClerkThread(cashbox);
    static ClerkThread clerk3 = new ClerkThread(cashbox);

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static void main(String[] args) {
        clerk1.start();
        clerk1.setName("Операционистка 1");
        clerk2.start();
        clerk2.setName("Операционистка 2");
        clerk3.start();
        clerk3.setName("Операционистка 3");
        Random rnd = new Random();
        long money = 0;
        while (true) {
            money = Long.parseLong(String.valueOf(rnd.nextInt(1000)));
            if (rnd.nextInt(100) % 3 == 0 || clerk1.getSizeDeque() == 0)
                if (rnd.nextInt(100) % 2 == 0)
                    clerk1.pushClient(new Client(Actions.PUT, money, rnd(1000, 3000)));
                else
                    clerk1.pushClient(new Client(Actions.WITHDRAW, money, rnd(1000, 3500)));
            if (rnd.nextInt(100) % 3 == 1 || clerk2.getSizeDeque() == 0)
                if (rnd.nextInt(100) % 2 == 0)
                    clerk2.pushClient(new Client(Actions.PUT, money, rnd(1000, 3500)));
                else
                    clerk2.pushClient(new Client(Actions.WITHDRAW, money, rnd(1000, 3500)));
            if (rnd.nextInt(100) % 3 == 2 || clerk3.getSizeDeque() == 0)
                if (rnd.nextInt(100) % 2 == 0)
                    clerk3.pushClient(new Client(Actions.PUT, money, rnd(1000, 3500)));
                else
                    clerk3.pushClient(new Client(Actions.WITHDRAW, money, rnd(1000, 3500)));

        }
    }
}
