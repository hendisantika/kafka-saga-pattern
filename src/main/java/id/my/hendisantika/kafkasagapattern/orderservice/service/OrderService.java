package id.my.hendisantika.kafkasagapattern.orderservice.service;

import id.my.hendisantika.kafkasagapattern.model.dto.OrderRequestDTO;
import id.my.hendisantika.kafkasagapattern.model.dto.OrderResponseDTO;
import id.my.hendisantika.kafkasagapattern.orderservice.entity.PurchaseOrder;
import id.my.hendisantika.kafkasagapattern.orderservice.eventhandlers.OrderEventPublisherService;
import id.my.hendisantika.kafkasagapattern.orderservice.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-saga-pattern
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/06/24
 * Time: 09.55
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderService {

    // product price map
    private static final Map<Integer, Integer> PRODUCT_PRICE = Map.of(
            1, 100,
            2, 200,
            3, 300
    );

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final OrderEventPublisherService eventPublisherService;

    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDTO) {
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.save(this.dtoToEntity(orderRequestDTO));
        this.eventPublisherService.raiseOrderCreatedEvent(purchaseOrder);
        return purchaseOrder;
    }

    public List<OrderResponseDTO> getAll() {
        return this.purchaseOrderRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
