package maluevArtem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Order {

    private final int ACTIONS_TIME = 1000;             // время действия
    private final int MEAL_TIME = 3000;                //  время приема пищи
    private final int ORDER_COMPLETION_TIME = 2000;    //  время выполнения заказа

    private final Restaurant restaurant;
    private final Lock locker;
    private final Condition condition;


    public Order(Restaurant restaurant) {
        this.restaurant = restaurant;
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void makeOrder() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашёл в ресторан");
            try {
                Thread.sleep(ACTIONS_TIME);
                restaurant.getQueueVisitor().add(Thread.currentThread().getName());
                locker.lock();
                System.out.println(Thread.currentThread().getName() + " готов сделать заказ");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " начал есть");
            } finally {
                locker.unlock();
            }
            Thread.sleep(MEAL_TIME);
            System.out.println(Thread.currentThread().getName() + " покинул ресторан");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Restaurant.counter++;
        }
    }

    public void bringOrder() {
        try {
            boolean flag = true;
            System.out.println(Thread.currentThread().getName() + " готов к работе");
            while (flag) {
                Thread.sleep(ACTIONS_TIME);
                if (!restaurant.getQueueVisitor().isEmpty()) {
                    restaurant.getQueueVisitor().remove();
                    flag = false;
                }
                if (Restaurant.counter == Main.NUMBER_VISITORS) {
                    return;
                }
            }
            locker.lock();
            Thread.sleep(ACTIONS_TIME);
            System.out.println(Thread.currentThread().getName() + " принял заказ");
            Thread.sleep(ORDER_COMPLETION_TIME);
            System.out.println(Thread.currentThread().getName() + " принёс заказ");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                locker.unlock();  // Почему то выскакивает исключение IllegalMonitorStateException ????
            } catch (IllegalMonitorStateException i) {
            }
        }
    }
}
