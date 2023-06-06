package golovin.store.gusli.service;

import golovin.store.gusli.dto.ReviewDto;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.mapper.ReviewMapper;
import golovin.store.gusli.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    @Transactional
    public ReviewDto saveReview(Long userId, Long productId, ReviewDto dto) {
        if (reviewRepository.existReview(userId, productId)) {
            throw new RuntimeException("There can be only one product review for each user");
        }
        User user = userService.getById(userId);
        Product product = productService.getById(productId);
        return reviewMapper.toDto(reviewRepository.save(reviewMapper.toEntity(dto, user, product)));
    }
}
