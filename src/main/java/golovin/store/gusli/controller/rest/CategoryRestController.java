package golovin.store.gusli.controller.rest;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.CategoryDto;
import golovin.store.gusli.service.CategoryService;
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
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable @Positive(message = "categoryId must be positive") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getCategories(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(categoryService.getCategories(pageable));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(dto));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateProduct(@PathVariable @Positive(message = "categoryId must be positive") Long categoryId,
                                                    @RequestBody @Valid CategoryDto dto) {
        return ResponseEntity.ok(categoryService.updateProduct(categoryId, dto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteProduct(@PathVariable @Positive(message = "categoryId must be positive") Long categoryId) {
        categoryService.deleteProduct(categoryId);
        return ResponseEntity.noContent().build();
    }
}
