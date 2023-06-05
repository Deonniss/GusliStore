package mapper;

import golovin.store.gusli.dto.CategoryDto;
import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.entity.Category;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private static final Timestamp timestamp = Timestamp.from(Instant.now());

    @Test
    public void testToEntity() {
        // Создание тестовых данных
        ProductDto productDto = ProductDto
                .builder()
                .name("name")
                .price(100.5d)
                .description("description ...")
                .build();

        Category category = Category
                .builder()
                .id(1L)
                .name("name")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Product product = productMapper.toEntity(productDto, category);

        assertNull(product.getId());
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getPrice(), productDto.getPrice());
        assertEquals(product.getDescription(), productDto.getDescription());
        assertEquals(product.getAvgRating(), 0.0d);
        assertEquals(product.getTotalReview(), 0);
        assertNull(product.getCreatedAt());
        assertNull(product.getUpdatedAt());
        assertEquals(product.getCategory().getId(), category.getId());
        assertEquals(product.getCategory().getName(), category.getName());
        assertEquals(product.getCategory().getCreatedAt(), category.getCreatedAt());
        assertEquals(product.getCategory().getUpdatedAt(), category.getUpdatedAt());
    }

    @Test
    public void testUpdateEntity() {
        // Создание тестовых данных

        ProductDto productDto = ProductDto
                .builder()
                .name("new name")
                .price(10_000d)
                .description("new description ...")
                .build();

        Category category = Category
                .builder()
                .id(1L)
                .name("name")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();


        Product product = Product
                .builder()
                .id(1L)
                .name("name")
                .price(100.5d)
                .description("description ...")
                .avgRating(1.5)
                .totalReview(2)
                .category(category)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Product productNew = productMapper.updateEntity(product, productDto);

        assertEquals(productNew.getId(), product.getId());
        assertEquals(productNew.getName(), productDto.getName());
        assertEquals(productNew.getPrice(), productDto.getPrice());
        assertEquals(productNew.getDescription(), productDto.getDescription());
        assertEquals(productNew.getAvgRating(), product.getAvgRating());
        assertEquals(productNew.getTotalReview(), product.getTotalReview());
        assertEquals(productNew.getCategory(), category);
        assertEquals(productNew.getUpdatedAt(), timestamp);
        assertEquals(productNew.getCreatedAt(), timestamp);
    }

    @Test
    public void testToDto() {

        Category category = Category
                .builder()
                .id(1L)
                .name("name")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Product product = Product
                .builder()
                .id(1L)
                .name("name")
                .price(100.5d)
                .description("description ...")
                .avgRating(1.5)
                .totalReview(2)
                .category(category)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        ProductDto productDto = productMapper.toDto(product);

        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getPrice(), product.getPrice());
        assertEquals(productDto.getDescription(), product.getDescription());
        assertEquals(productDto.getAvgRating(), product.getAvgRating());
        assertEquals(productDto.getTotalReview(), product.getTotalReview());
        assertEquals(productDto.getCategory().getId(), category.getId());
        assertEquals(productDto.getCategory().getName(), category.getName());
        assertEquals(productDto.getCategory().getUpdatedAt(), category.getUpdatedAt());
        assertEquals(productDto.getCategory().getCreatedAt(), category.getCreatedAt());
        assertEquals(productDto.getUpdatedAt(), timestamp);
        assertEquals(productDto.getCreatedAt(), timestamp);
    }

    @Test
    public void testToDtos() {

        Category category = Category
                .builder()
                .id(1L)
                .name("name")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Product product = Product
                .builder()
                .id(1L)
                .name("name")
                .price(100.5d)
                .description("description ...")
                .avgRating(1.5)
                .totalReview(2)
                .category(category)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        List<Product> products = new ArrayList<>();
        products.add(product);

        List<ProductDto> productDtos = productMapper.toDtos(products);
        ProductDto productDto = productDtos.get(0);

        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getPrice(), product.getPrice());
        assertEquals(productDto.getDescription(), product.getDescription());
        assertEquals(productDto.getAvgRating(), product.getAvgRating());
        assertEquals(productDto.getTotalReview(), product.getTotalReview());
        assertEquals(productDto.getCategory().getId(), category.getId());
        assertEquals(productDto.getCategory().getName(), category.getName());
        assertEquals(productDto.getCategory().getUpdatedAt(), category.getUpdatedAt());
        assertEquals(productDto.getCategory().getCreatedAt(), category.getCreatedAt());
        assertEquals(productDto.getUpdatedAt(), timestamp);
        assertEquals(productDto.getCreatedAt(), timestamp);

    }

    @Test
    public void testToEntities() {

        CategoryDto categoryDto = CategoryDto
                .builder()
                .name("name")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        ProductDto productDto = ProductDto
                .builder()
                .name("name")
                .price(100.5d)
                .description("description ...")
                .avgRating(1.5)
                .totalReview(2)
                .category(categoryDto)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);

        List<Product> products = productMapper.toEntities(productDtos);
        Product product = products.get(0);

        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getPrice(), product.getPrice());
        assertEquals(productDto.getDescription(), product.getDescription());
        assertEquals(productDto.getAvgRating(), product.getAvgRating());
        assertEquals(productDto.getTotalReview(), product.getTotalReview());
        assertEquals(productDto.getCategory().getId(), categoryDto.getId());
        assertEquals(productDto.getCategory().getName(), categoryDto.getName());
        assertEquals(productDto.getCategory().getUpdatedAt(), categoryDto.getUpdatedAt());
        assertEquals(productDto.getCategory().getCreatedAt(), categoryDto.getCreatedAt());
        assertEquals(productDto.getUpdatedAt(), timestamp);
        assertEquals(productDto.getCreatedAt(), timestamp);

    }

}
