package golovin.store.gusli.controller.rest;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.service.*;
import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable @Positive(message = "productId must be positive") Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getProducts(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productService.getProducts(pageable));
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<ProductDto> createProduct(@PathVariable @Positive(message = "categoryId must be positive") Long categoryId,
                                                    @RequestBody @Valid ProductDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(categoryId, dto));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable @Positive(message = "productId must be positive") Long productId,
                                                    @RequestBody @Valid ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(productId, dto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable @Positive(message = "productId must be positive") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
