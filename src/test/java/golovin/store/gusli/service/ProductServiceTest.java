package golovin.store.gusli.service;

import golovin.store.gusli.entity.Category;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.mapper.ProductMapper;
import golovin.store.gusli.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {

    private static final Timestamp timestamp = Timestamp.from(Instant.now());

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private CategoryService categoryService;

    private ProductService productService;

    @BeforeEach
    public void generateProductService() {

        productService = new ProductService(productRepository, productMapper, categoryService);

        Category category = Category
                .builder()
                .id(1L)
                .name("name")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("name")
                .price(100D)
                .description("description")
                .avgRating(1.5D)
                .totalReview(2)
                .category(category)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        //TODO мокировать все методы МОК сервисов
    }

}
