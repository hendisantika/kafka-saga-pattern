package id.my.hendisantika.kafkasagapattern.model.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 09.43
 * To change this template use File | Settings | File Templates.
 */
@Data
public class OrderResponseDTO {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus status;
}
