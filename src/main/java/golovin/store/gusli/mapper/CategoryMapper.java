package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.CategoryDto;
import golovin.store.gusli.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {
}
