package golovin.store.gusli.service;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.ReviewDto;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.entity.Review;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.mapper.ReviewMapper;
import golovin.store.gusli.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserService userService;
    private final ProductService productService;

    private final ReviewMapper reviewMapper;

    @SneakyThrows
    public ReviewDto getReview(Long userId, Long productId) {
        return reviewMapper.toDto(reviewRepository.findByUserIdAndProductId(userId, productId));
    }

    @SneakyThrows
    public PageableResponse<ReviewDto> getReviews(Long productId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAllByProductId(productId, pageable);
        return new PageableResponse<ReviewDto>().toBuilder()
                .items(reviewMapper.toDtos(reviews.getContent()))
                .total(reviews.getTotalElements())
                .build();
    }

    @SneakyThrows
    @Transactional
    public ReviewDto saveReview(Long userId, Long productId, ReviewDto dto) {
        if (reviewRepository.existReview(userId, productId)) {
            throw new RuntimeException("There can be only one product review for each user");
        }
        User user = userService.getById(userId);
        Product product = productService.getById(productId);
        product.addReview(dto.getRating());
        productService.saveProduct(product);
        return reviewMapper.toDto(reviewRepository.save(reviewMapper.toEntity(dto, user, product)));
    }

    @SneakyThrows
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review =  reviewRepository.findById(reviewId).orElseThrow();
        Product product = review.getProduct();
        product.minusReview(review.getRating());
        reviewRepository.delete(review);
    }
}
