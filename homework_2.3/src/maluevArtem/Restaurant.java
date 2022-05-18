package maluevArtem;

import java.util.LinkedList;
import java.util.Queue;

public class Restaurant {

    private final Order order = new Order(this);
    protected static int counter;
    private final Queue<String> queueVisitor;

    public Restaurant() {
        System.out.println("Ресторан открыт!");
        queueVisitor = new LinkedList<>();
    }

    public Queue<String> getQueueVisitor() {
        return queueVisitor;
    }

    public void visitor() {
        order.makeOrder();
    }

    public void waiter() {
        while (counter != Main.NUMBER_VISITORS) {
            order.bringOrder();
        }

    }
}
