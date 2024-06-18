package id.my.hendisantika.kafkasagapattern.orderservice.controller;

import id.my.hendisantika.kafkasagapattern.model.dto.OrderRequestDTO;
import id.my.hendisantika.kafkasagapattern.model.dto.OrderResponseDTO;
import id.my.hendisantika.kafkasagapattern.orderservice.entity.PurchaseOrder;
import id.my.hendisantika.kafkasagapattern.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 09.57
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("order")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO dto) {
        return this.orderService.createOrder(dto);
    }

    @GetMapping("/all")
    public List<OrderResponseDTO> getOrders() {
        return this.orderService.getAll();
    }
}
