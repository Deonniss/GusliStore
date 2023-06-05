package mapper;

import golovin.store.gusli.dto.OrderItemDto;
import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.entity.Category;
import golovin.store.gusli.entity.OrderItem;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.mapper.OrderItemMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderItemMapperTest {

    private static final Timestamp timestamp = Timestamp.from(Instant.now());

    private final OrderItemMapper orderItemMapper = Mappers.getMapper(OrderItemMapper.class);

    @Test
    public void testToEntity() {

        ProductDto productDto = ProductDto
                .builder()
                .name("new name")
                .price(10_000d)
                .description("new description ...")
                .build();

        OrderItemDto dto = OrderItemDto.builder()
                .quantity(2)
                .product(productDto)
                .price(productDto.getPrice() * 2)
                .build();

        OrderItem orderItem = orderItemMapper.toEntity(dto);

        assertNull(orderItem.getId());
        assertEquals(orderItem.getQuantity(), dto.getQuantity());
        assertEquals(orderItem.getPrice(), dto.getPrice());
        assertEquals(orderItem.getProduct().getName(), "new name");
        assertEquals(orderItem.getProduct().getPrice(), 10_000d);
        assertEquals(orderItem.getProduct().getDescription(), "new description ...");
        assertNull(orderItem.getUpdatedAt());
        assertNull(orderItem.getCreatedAt());
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

        OrderItem orderItem = OrderItem.builder()
                .id(1L)
                .quantity(2)
                .product(product)
                .price(product.getPrice() * 2)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        OrderItemDto dto = orderItemMapper.toDto(orderItem);

        assertEquals(dto.getId(), orderItem.getId());
        assertEquals(dto.getQuantity(), orderItem.getQuantity());
        assertEquals(dto.getPrice(), orderItem.getPrice());
        assertEquals(dto.getUpdatedAt(), orderItem.getUpdatedAt());
        assertEquals(dto.getCreatedAt(), orderItem.getCreatedAt());

        assertEquals(dto.getProduct().getId(), orderItem.getProduct().getId());
        assertEquals(dto.getProduct().getName(), orderItem.getProduct().getName());
        assertEquals(dto.getProduct().getUpdatedAt(), orderItem.getProduct().getUpdatedAt());
        assertEquals(dto.getProduct().getCreatedAt(), orderItem.getProduct().getCreatedAt());

        assertEquals(dto.getProduct().getCategory().getId(), orderItem.getProduct().getCategory().getId());
        assertEquals(dto.getProduct().getCategory().getName(), orderItem.getProduct().getCategory().getName());
        assertEquals(dto.getProduct().getCategory().getUpdatedAt(), orderItem.getProduct().getCategory().getUpdatedAt());
        assertEquals(dto.getProduct().getCategory().getCreatedAt(), orderItem.getProduct().getCategory().getCreatedAt());
    }

}
