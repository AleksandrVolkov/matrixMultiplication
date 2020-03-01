package cashbox;

import sun.util.resources.LocaleData;


public class Client {
    private Actions actions;
    private Long money;
    private int serviceTimeMills;

    public Client(Actions actions, Long money, int serviceTimeMills) {
        this.actions = actions;
        this.money = money;
        this.serviceTimeMills = serviceTimeMills;
    }

    public Actions getActions() {
        return actions;
    }

//    public void setActions(Actions actions) {
//        this.actions = actions;
//    }

    public Long getMoney() {
        return money;
    }

//    public void setAmountOfMoney(Long amount) {
//        this.money = amount;
//    }

    public int getServiceTimeMills() {
        return serviceTimeMills;
    }

//    public void setServiceTimeMills(int serviceTimeMills) {
//        this.serviceTimeMills = serviceTimeMills;
//    }
}
