package id.my.hendisantika.kafkasagapattern.model.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 09.41
 * To change this template use File | Settings | File Templates.
 */
@Data
public class OrderRequestDTO {
    private Integer userId;
    private Integer productId;
}
