package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.entity.Category;
import golovin.store.gusli.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "category", target = "category")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductDto dto, Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product updateEntity(@MappingTarget Product entity, ProductDto dto);
}
