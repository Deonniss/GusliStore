package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.CartItemDto;
import golovin.store.gusli.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper extends EntityMapper<CartItemDto, CartItem> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    CartItem toEntity(CartItemDto dto);
}
