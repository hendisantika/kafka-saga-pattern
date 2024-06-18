package id.my.hendisantika.kafkasagapattern.orderservice.entity;

import id.my.hendisantika.kafkasagapattern.model.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 09.49
 * To change this template use File | Settings | File Templates.
 */
@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus status;
}
