package id.my.hendisantika.kafkasagapattern.paymentservice.eventhandlers;

import id.my.hendisantika.kafkasagapattern.model.enums.PaymentStatus;
import id.my.hendisantika.kafkasagapattern.model.event.OrderEvent;
import id.my.hendisantika.kafkasagapattern.model.event.PaymentEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 10.01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OrderEventProcessorService {

    // user - credit limit
    public static final Map<Integer, Integer> userMap = new HashMap<>();

    static {
        userMap.put(1, 1000);
        userMap.put(2, 1000);
        userMap.put(3, 1000);
        userMap.put(4, 1000);
        userMap.put(5, 1000);
    }

    public PaymentEvent processOrderEvent(OrderEvent orderEvent) {
        var price = orderEvent.getPrice();
        var creditLimit = userMap.get(orderEvent.getUserId());
        PaymentEvent paymentEvent = new PaymentEvent(orderEvent.getOrderId());
        if (creditLimit >= price) {
            paymentEvent.setStatus(PaymentStatus.APPROVED);
            userMap.computeIfPresent(orderEvent.getUserId(), (k, v) -> v - price);
        } else {
            paymentEvent.setStatus(PaymentStatus.REJECTED);
        }
        return paymentEvent;
    }
}
