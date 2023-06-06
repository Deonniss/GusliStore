package golovin.store.gusli.controller.rest;

import golovin.store.gusli.dto.ReviewDto;
import golovin.store.gusli.service.ReviewService;
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
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

//    @GetMapping("/{reviewId}")
//    public ResponseEntity<ReviewDto> getReview(@PathVariable @Positive(message = "reviewId must be positive") Long reviewId) {
//        return ResponseEntity.ok(reviewService.getReview(reviewId));
//    }


    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<ReviewDto> createProduct(@PathVariable @Positive(message = "userId must be positive") Long userId,
                                                    @PathVariable @Positive(message = "productId must be positive") Long productId,
                                                    @RequestBody @Valid ReviewDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReview(userId, productId, dto));
    }
    
}
