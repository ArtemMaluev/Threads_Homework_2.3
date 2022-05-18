package maluevArtem;

public class Main {

    static final int NUMBER_VISITORS = 4;
    static final int ACTIONS_TIME = 3000;

    public static void main(String[] args) throws InterruptedException {

        final Restaurant restaurant = new Restaurant();

        Thread waiter1 = new Thread(restaurant::waiter, "Официант1");
        Thread waiter2 = new Thread(restaurant::waiter, "Официант2");
        Thread waiter3 = new Thread(restaurant::waiter, "Официант3");
        waiter1.start();
        waiter2.start();
        waiter3.start();

        for (int i = 1; i <= NUMBER_VISITORS; i++) {
            Thread.sleep(ACTIONS_TIME);
            new Thread(restaurant::visitor, "Посетитель" + i).start();
        }
    }
}
