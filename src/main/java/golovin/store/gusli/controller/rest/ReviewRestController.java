package golovin.store.gusli.controller.rest;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.ReviewDto;
import golovin.store.gusli.entity.type.ReviewFilterType;
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

    @GetMapping("/product/{productId}")
    public ResponseEntity<PageableResponse<ReviewDto>> getReviewsByProduct(@PathVariable @Positive(message = "productId must be positive") Long productId,
                                                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(reviewService.getReviews(productId, ReviewFilterType.PRODUCT, pageable));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PageableResponse<ReviewDto>> getReviewsByUser(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                                        @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(reviewService.getReviews(userId, ReviewFilterType.USER, pageable));
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<ReviewDto> createReview(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                   @PathVariable @Positive(message = "productId must be positive") Long productId,
                                                   @RequestBody @Valid ReviewDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReview(userId, productId, dto));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable @Positive(message = "reviewId must be positive") Long reviewId,
                                                    @RequestBody @Valid ReviewDto dto) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, dto));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable @Positive(message = "reviewId must be positive") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
