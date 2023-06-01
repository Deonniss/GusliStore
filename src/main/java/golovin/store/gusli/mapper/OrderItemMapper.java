package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.OrderItemDto;
import golovin.store.gusli.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends EntityMapper<OrderItemDto, OrderItem> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrderItem toEntity(OrderItemDto orderDto);

}
