package golovin.store.gusli.controller.rest;

import golovin.store.gusli.dto.CategoryDto;
import golovin.store.gusli.service.CategoryService;
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
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable @Positive(message = "categoryId must be positive") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }
}
