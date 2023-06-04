package mapper;

import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.entity.Category;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

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
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
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
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        Timestamp timestamp = Timestamp.from(Instant.now());

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

    }

    @Test
    public void testToDtos() {

    }

    @Test
    public void testToEntities() {

    }

}
