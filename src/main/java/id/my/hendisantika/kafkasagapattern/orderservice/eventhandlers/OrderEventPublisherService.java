package id.my.hendisantika.kafkasagapattern.orderservice.eventhandlers;

import id.my.hendisantika.kafkasagapattern.model.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 09.52
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderEventPublisherService {

    private final FluxSink<OrderEvent> orderEventChannel;
}
