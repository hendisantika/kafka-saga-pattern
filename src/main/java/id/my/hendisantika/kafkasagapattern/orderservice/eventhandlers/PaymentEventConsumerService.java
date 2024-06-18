package id.my.hendisantika.kafkasagapattern.orderservice.eventhandlers;

import id.my.hendisantika.kafkasagapattern.model.enums.OrderStatus;
import id.my.hendisantika.kafkasagapattern.model.enums.PaymentStatus;
import id.my.hendisantika.kafkasagapattern.model.event.PaymentEvent;
import id.my.hendisantika.kafkasagapattern.orderservice.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 09.53
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PaymentEventConsumerService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public void consumePaymentEvent(PaymentEvent paymentEvent) {
        this.purchaseOrderRepository.findById(paymentEvent.getOrderId())
                .ifPresent(purchaseOrder -> {
                    purchaseOrder.setStatus(paymentEvent.getStatus().equals(PaymentStatus.APPROVED) ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED);
                    this.purchaseOrderRepository.save(purchaseOrder);
                });
    }
}
