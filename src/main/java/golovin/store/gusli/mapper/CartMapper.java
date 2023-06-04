package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.CartDto;
import golovin.store.gusli.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDto, Cart> {
}
