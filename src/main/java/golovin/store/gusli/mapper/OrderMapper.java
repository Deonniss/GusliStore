package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.OrderDto;
import golovin.store.gusli.entity.Order;
import golovin.store.gusli.entity.OrderItem;
import golovin.store.gusli.entity.Status;
import golovin.store.gusli.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper extends EntityMapper<OrderDto, Order> {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "user", target = "user")
    @Mapping(source = "status", target = "status")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Order toEntity(OrderDto orderDto, User user, Status status);

    default Order performAfterMapping(Order order) {
        Set<OrderItem> orderItems = order.getItems();
        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                item.setOrder(order);
            }
        }
        return order;
    }
}
