package golovin.store.gusli.controller.rest;

import golovin.store.gusli.dto.CartDto;
import golovin.store.gusli.dto.CartItemDto;
import golovin.store.gusli.service.CartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/cart")
public class CartRestController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable @Positive(message = "userId must be positive") Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/addItem/{cartId}")
    public ResponseEntity<CartDto> createProduct(@PathVariable @Positive(message = "cartId must be positive") Long cartId,
                                                 @RequestBody @Valid CartItemDto dto) {
        return ResponseEntity.ok(cartService.addCartItem(cartId, dto));
    }
}
