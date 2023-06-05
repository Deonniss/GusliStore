package mapper;

import golovin.store.gusli.dto.CategoryDto;
import golovin.store.gusli.entity.Category;
import golovin.store.gusli.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategoryMapperTest {

    private static final Timestamp timestamp = Timestamp.from(Instant.now());

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    public void testToEntity() {

        CategoryDto dto = CategoryDto
                .builder()
                .name("name")
                .build();

        Category category = categoryMapper.toEntity(dto);

        assertNull(dto.getId());
        assertEquals(dto.getName(), category.getName());
        assertNull(dto.getCreatedAt());
        assertNull(dto.getUpdatedAt());
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

        CategoryDto dto = categoryMapper.toDto(category);

        assertEquals(dto.getId(), category.getId());
        assertEquals(dto.getName(), category.getName());
        assertEquals(dto.getCreatedAt(), category.getCreatedAt());
        assertEquals(dto.getUpdatedAt(), category.getCreatedAt());
    }


}
