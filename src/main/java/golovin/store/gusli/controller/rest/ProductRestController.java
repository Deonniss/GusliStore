package golovin.store.gusli.controller.rest;

import golovin.store.gusli.service.*;
import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getProducts(@PageableDefault Pageable pageable, Integer a) {
        if (a == null) throw new RuntimeException("a is null");
        return ResponseEntity.ok(productService.getProducts(pageable));
    }
}
