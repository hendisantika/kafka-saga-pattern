package id.my.hendisantika.kafkasagapattern.orderservice.controller;

import id.my.hendisantika.kafkasagapattern.model.dto.OrderRequestDTO;
import id.my.hendisantika.kafkasagapattern.model.dto.OrderResponseDTO;
import id.my.hendisantika.kafkasagapattern.orderservice.entity.PurchaseOrder;
import id.my.hendisantika.kafkasagapattern.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Order", description = "Order CRUD API with documentation annotations")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @Operation(
            summary = "Create Purchase Order Data",
            description = "Create Purchase Order Data.",
            tags = {"Tutorial"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            PurchaseOrder.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO dto) {
        return this.orderService.createOrder(dto);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get All Purchase Orders Data",
            description = "Get All Purchase Orders Data.",
            tags = {"Tutorial"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            OrderResponseDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public List<OrderResponseDTO> getOrders() {
        return this.orderService.getAll();
    }
}
