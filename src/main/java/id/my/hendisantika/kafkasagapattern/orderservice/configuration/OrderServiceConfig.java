package id.my.hendisantika.kafkasagapattern.orderservice.configuration;

import id.my.hendisantika.kafkasagapattern.model.event.OrderEvent;
import id.my.hendisantika.kafkasagapattern.orderservice.eventhandlers.PaymentEventConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxSink;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 09.47
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderServiceConfig {

    private final PaymentEventConsumerService consumerService;

    @Bean
    public DirectProcessor<OrderEvent> getFlux() {
        return DirectProcessor.create();
    }

    @Bean
    public FluxSink<OrderEvent> orderEventChannel(DirectProcessor<OrderEvent> processor) {
        return processor.sink();
    }
}
