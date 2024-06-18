package id.my.hendisantika.kafkasagapattern.orderservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
}
