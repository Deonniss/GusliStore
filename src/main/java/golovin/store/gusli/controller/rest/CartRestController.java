package golovin.store.gusli.controller.rest;

import golovin.store.gusli.dto.CartDto;
import golovin.store.gusli.service.CartService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
