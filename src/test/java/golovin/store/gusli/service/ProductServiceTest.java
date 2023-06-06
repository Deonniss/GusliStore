package golovin.store.gusli.service;

import golovin.store.gusli.dto.CategoryDto;
import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.entity.Category;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.mapper.ProductMapper;
import golovin.store.gusli.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        CategoryDto categoryDto = CategoryDto
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

        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("name")
                .price(100D)
                .description("description")
                .avgRating(1.5D)
                .totalReview(2)
                .category(categoryDto)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .name("name")
                .price(100D)
                .description("description")
                .avgRating(1.5D)
                .totalReview(2)
                .category(category)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        ProductDto productDto2 = ProductDto.builder()
                .id(2L)
                .name("name2")
                .price(100D)
                .description("description")
                .avgRating(1.5D)
                .totalReview(2)
                .category(categoryDto)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        ProductDto productDtoUpdate3 = ProductDto.builder()
                .name("name3")
                .price(300d)
                .description("description3")
                .avgRating(3.5D)
                .totalReview(3)
                .build();

        Product productUpdated = Product.builder()
                .id(1L)
                .name("name3")
                .price(300d)
                .description("description3")
                .avgRating(3.5D)
                .totalReview(3)
                .category(category)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        ProductDto productDtoUpdated = ProductDto.builder()
                .id(1L)
                .name("name3")
                .price(300d)
                .description("description3")
                .avgRating(3.5D)
                .totalReview(3)
                .category(categoryDto)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();


        Product productSave = Product.builder()
                .name("name")
                .price(100D)
                .description("description")
                .avgRating(1.5D)
                .totalReview(2)
                .category(category)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        ProductDto productDtoSave = ProductDto.builder()
                .name("name")
                .price(100D)
                .description("description")
                .avgRating(1.5D)
                .totalReview(2)
                .build();

        Product productSaved = Product.builder()
                .name("name")
                .price(100D)
                .description("description")
                .category(category)
                .avgRating(1.5D)
                .totalReview(2)
                .build();


        Pageable pageable = Pageable.ofSize(10);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);
        productDtoList.add(productDto2);

        Page<Product> products = new PageImpl<>(productList);
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        when(productRepository.findAll(pageable)).thenReturn(products);
        when(productRepository.save(productSave)).thenReturn(product);
        when(productRepository.save(productSaved)).thenReturn(product);
        when(productRepository.save(productUpdated)).thenReturn(productUpdated);

        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productMapper.toDto(productUpdated)).thenReturn(productDtoUpdated);
        when(productMapper.toDtos(productList)).thenReturn(productDtoList);
        when(productMapper.updateEntity(product, productDtoUpdate3)).thenReturn(productUpdated);
        when(productMapper.toEntity(productDtoSave, category)).thenReturn(productSaved);

        when(categoryService.getById(1L)).thenReturn(category);
    }

    @Test
    public void testGetById() {

        Product product = productService.getById(1L);

        assertEquals(product.getId(), 1L);
        assertEquals(product.getName(), "name");
        assertEquals(product.getPrice(), 100D);
        assertEquals(product.getDescription(), "description");
        assertEquals(product.getAvgRating(), 1.5D);
        assertEquals(product.getTotalReview(), 2);
        assertEquals(product.getCategory().getId(), 1L);
        assertEquals(product.getCategory().getName(), "name");
        assertEquals(product.getCategory().getCreatedAt(), timestamp);
        assertEquals(product.getCategory().getUpdatedAt(), timestamp);
        assertEquals(product.getUpdatedAt(), timestamp);
        assertEquals(product.getCreatedAt(), timestamp);
    }

    @Test
    public void testGetProduct() {

        ProductDto dto = productService.getProduct(1L);

        assertEquals(dto.getId(), 1L);
        assertEquals(dto.getName(), "name");
        assertEquals(dto.getPrice(), 100D);
        assertEquals(dto.getDescription(), "description");
        assertEquals(dto.getAvgRating(), 1.5D);
        assertEquals(dto.getTotalReview(), 2);
        assertEquals(dto.getCategory().getId(), 1L);
        assertEquals(dto.getCategory().getName(), "name");
        assertEquals(dto.getCategory().getCreatedAt(), timestamp);
        assertEquals(dto.getCategory().getUpdatedAt(), timestamp);
        assertEquals(dto.getUpdatedAt(), timestamp);
        assertEquals(dto.getCreatedAt(), timestamp);
    }

    @Test
    public void testGetProducts() {

        Pageable pageable = Pageable.ofSize(10);

        List<ProductDto> dtos = productService.getProducts(pageable).getItems();

        ProductDto dto = dtos.get(0);

        assertEquals(dto.getId(), 1L);
        assertEquals(dto.getName(), "name");
        assertEquals(dto.getPrice(), 100D);
        assertEquals(dto.getDescription(), "description");
        assertEquals(dto.getAvgRating(), 1.5D);
        assertEquals(dto.getTotalReview(), 2);
        assertEquals(dto.getCategory().getId(), 1L);
        assertEquals(dto.getCategory().getName(), "name");
        assertEquals(dto.getCategory().getCreatedAt(), timestamp);
        assertEquals(dto.getCategory().getUpdatedAt(), timestamp);
        assertEquals(dto.getUpdatedAt(), timestamp);
        assertEquals(dto.getCreatedAt(), timestamp);

        ProductDto dto1 = dtos.get(1);

        assertEquals(dto1.getId(), 2L);
        assertEquals(dto1.getName(), "name2");
        assertEquals(dto1.getPrice(), 100D);
        assertEquals(dto1.getDescription(), "description");
        assertEquals(dto1.getAvgRating(), 1.5D);
        assertEquals(dto1.getTotalReview(), 2);
        assertEquals(dto1.getCategory().getId(), 1L);
        assertEquals(dto1.getCategory().getName(), "name");
        assertEquals(dto1.getCategory().getCreatedAt(), timestamp);
        assertEquals(dto1.getCategory().getUpdatedAt(), timestamp);
        assertEquals(dto1.getUpdatedAt(), timestamp);
        assertEquals(dto1.getCreatedAt(), timestamp);
    }

    @Test
    public void testSaveProduct() {

        ProductDto productDtoSave = ProductDto.builder()
                .name("name")
                .price(100D)
                .description("description")
                .avgRating(1.5D)
                .totalReview(2)
                .build();

        ProductDto dto = productService.saveProduct(1L, productDtoSave);

        assertEquals(dto.getId(), 1L);
        assertEquals(dto.getName(), "name");
        assertEquals(dto.getPrice(), 100D);
        assertEquals(dto.getDescription(), "description");
        assertEquals(dto.getAvgRating(), 1.5D);
        assertEquals(dto.getTotalReview(), 2);
        assertEquals(dto.getCategory().getId(), 1L);
        assertEquals(dto.getCategory().getName(), "name");
        assertEquals(dto.getCategory().getCreatedAt(), timestamp);
        assertEquals(dto.getCategory().getUpdatedAt(), timestamp);
        assertEquals(dto.getUpdatedAt(), timestamp);
        assertEquals(dto.getCreatedAt(), timestamp);
    }

    @Test
    public void testUpdateProduct() {

        ProductDto productDtoUpdate = ProductDto.builder()
                .name("name3")
                .price(300d)
                .description("description3")
                .avgRating(3.5D)
                .totalReview(3)
                .build();

        ProductDto dto = productService.updateProduct(1L, productDtoUpdate);

        assertEquals(dto.getId(), 1L);
        assertEquals(dto.getName(), "name3");
        assertEquals(dto.getPrice(), 300d);
        assertEquals(dto.getDescription(), "description3");
        assertEquals(dto.getAvgRating(), 3.5D);
        assertEquals(dto.getTotalReview(), 3);
        assertEquals(dto.getCategory().getId(), 1L);
        assertEquals(dto.getCategory().getName(), "name");
        assertEquals(dto.getCategory().getCreatedAt(), timestamp);
        assertEquals(dto.getCategory().getUpdatedAt(), timestamp);
        assertEquals(dto.getUpdatedAt(), timestamp);
        assertEquals(dto.getCreatedAt(), timestamp);
    }

}
