package cashbox;

import java.util.*;

public class ClerkThread extends Thread {
    private Deque<Client> clients = new ArrayDeque<>();
    private Cashbox cashbox;


    public ClerkThread(Cashbox cashbox) {
        this.cashbox = cashbox;
    }

    public Deque<Client> getClientDeque() {
        return clients;
    }

    public int getSizeDeque() {
        return clients.size();
    }

    public void pushClient(Client client) {
        this.clients.push(client);
    }

    public Client popClient() {
        return this.clients.pollFirst();
    }

    @Override
    public void run() {
        while (true) {
            Client client = popClient();
            if (client != null) {
                if (client.getActions() == Actions.PUT) {
                    try {
                        Thread.sleep(client.getServiceTimeMills());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cashbox.addMoney(client.getMoney());
                }
                if (client.getActions() == Actions.WITHDRAW)
                    try {
                        Thread.sleep(client.getServiceTimeMills());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                if (cashbox.getMoney() >= client.getMoney()) cashbox.removeMoney(client.getMoney());
                else System.out.println("В кассе недостаточно денег для снятия " + client.getMoney() + "руб.");
            }
        }
    }
}
