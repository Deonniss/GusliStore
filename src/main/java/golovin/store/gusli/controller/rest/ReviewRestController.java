package golovin.store.gusli.controller.rest;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.ReviewDto;
import golovin.store.gusli.service.ReviewService;
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
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    @GetMapping("/{userId}/{productId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                               @PathVariable @Positive(message = "productId must be positive") Long productId) {
        return ResponseEntity.ok(reviewService.getReview(userId, productId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<PageableResponse<ReviewDto>> getReviewsByProduct(@PathVariable @Positive(message = "productId must be positive") Long productId,
                                                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(reviewService.getReviews(productId, pageable));
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<ReviewDto> createProduct(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                   @PathVariable @Positive(message = "productId must be positive") Long productId,
                                                   @RequestBody @Valid ReviewDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReview(userId, productId, dto));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteProduct(@PathVariable @Positive(message = "reviewId must be positive") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
