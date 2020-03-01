package cashbox;

public class Cashbox {
    private Long money = 1000L;

    public Cashbox() {
    }

    public Cashbox(Long money) {
        this.money = money;
    }

    public synchronized Long getMoney() {
        return money;
    }

    public void setMoney(Long amountOfMoney) {
        this.money = amountOfMoney;
    }

    public synchronized void addMoney(Long amountOfMoney) {
        this.money += amountOfMoney;
        System.out.println(Thread.currentThread().getName() + " положила: " + amountOfMoney + "руб.\n" +
                "В кассе сейчас: " + money);
    }

    public synchronized void removeMoney(Long amountOfMoney) {
        this.money -= amountOfMoney;
        System.out.println(Thread.currentThread().getName() + " сняла: " + amountOfMoney + "руб.\n" +
                "В кассе сейчас: " + money);
    }
}
