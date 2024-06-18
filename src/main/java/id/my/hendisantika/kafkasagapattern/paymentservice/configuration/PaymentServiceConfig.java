package id.my.hendisantika.kafkasagapattern.paymentservice.configuration;

import id.my.hendisantika.kafkasagapattern.model.event.OrderEvent;
import id.my.hendisantika.kafkasagapattern.model.event.PaymentEvent;
import id.my.hendisantika.kafkasagapattern.paymentservice.eventhandlers.OrderEventProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 10.00
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PaymentServiceConfig {

    private final OrderEventProcessorService orderEventProcessorService;

    @Bean
    public Function<OrderEvent, PaymentEvent> orderEventProcessor() {
        return orderEventProcessorService::processOrderEvent;
    }
}
