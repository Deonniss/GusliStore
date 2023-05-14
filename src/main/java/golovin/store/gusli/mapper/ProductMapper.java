package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {
}
