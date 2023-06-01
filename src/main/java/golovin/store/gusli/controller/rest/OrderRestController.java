package golovin.store.gusli.controller.rest;

import golovin.store.gusli.dto.OrderDto;
import golovin.store.gusli.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
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

//    @GetMapping("/{orderId}")
//    public ResponseEntity<OrderDto> getOrder(@PathVariable @Positive(message = "orderId must be positive") Long orderId) {
//        return ResponseEntity.ok(orderService.getOrder(orderId));
//    }
//
//    @GetMapping
//    public ResponseEntity<PageableResponse<OrderDto>> getOrders(@PageableDefault Pageable pageable) {
//        return ResponseEntity.ok(orderService.getOrders(pageable));
//    }

    @PostMapping("/{userId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                @RequestBody @Valid OrderDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.registerOrder(userId, dto));
    }

//    @PutMapping("/{orderId}")
//    public ResponseEntity<OrderDto> updateProduct(@PathVariable @Positive(message = "orderId must be positive") Long orderId,
//                                                     @RequestBody @Valid OrderDto dto) {
//        return ResponseEntity.ok(orderService.updateProduct(orderId, dto));
//    }
//
//    @DeleteMapping("/{orderId}")
//    public ResponseEntity<?> deleteProduct(@PathVariable @Positive(message = "orderId must be positive") Long orderId) {
//        orderService.deleteProduct(orderId);
//        return ResponseEntity.noContent().build();
//    }
}
