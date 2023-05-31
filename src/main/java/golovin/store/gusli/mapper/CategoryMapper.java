package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.CategoryDto;
import golovin.store.gusli.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Category updateEntity(@MappingTarget Category entity, CategoryDto dto);
}
