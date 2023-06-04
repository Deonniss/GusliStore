package golovin.store.gusli.controller.rest;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.OrderDto;
import golovin.store.gusli.dto.OrderItemDto;
import golovin.store.gusli.entity.type.StatusType;
import golovin.store.gusli.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable @Positive(message = "orderId must be positive") Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PageableResponse<OrderDto>> getOrders(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                                @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrders(userId, pageable));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                @RequestBody @Valid OrderDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.registerOrder(userId, dto));
    }

    @PostMapping("/addItem/{orderId}")
    public ResponseEntity<OrderDto> addOrderItem(@PathVariable @Positive(message = "orderId must be positive") Long orderId,
                                                 @RequestBody @Valid OrderItemDto dto) {
        return ResponseEntity.ok(orderService.addOrderItem(orderId, dto));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderDto> changeOrderStatus(@PathVariable @Positive(message = "orderId must be positive") Long orderId,
                                                      @RequestParam @NotNull StatusType status) {
        return ResponseEntity.ok(orderService.changeOrderStatus(orderId, status));
    }

    @DeleteMapping("/deleteItem/{orderItemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable @Positive(message = "orderItemId must be positive") Long orderItemId) {
        orderService.deleteOrderItem(orderItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable @Positive(message = "orderId must be positive") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
