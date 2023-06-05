package mapper;

import golovin.store.gusli.dto.OrderDto;
import golovin.store.gusli.entity.Order;
import golovin.store.gusli.entity.OrderItem;
import golovin.store.gusli.entity.Status;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.entity.type.StatusType;
import golovin.store.gusli.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderMapperTest {

    private static final Timestamp timestamp = Timestamp.from(Instant.now());

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    public void testToEntity() {

        User user = User.builder()
                .id(1L)
                .email("email@gmail.com")
                .password("pass")
                .firstName("Denis")
                .lastName("Golovin")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Status status = Status.builder()
                .id(1L)
                .name("Created")
                .type(StatusType.CREATED)
                .description("will create")
                .build();

        OrderDto dto = OrderDto.builder()
                .totalCost(200D)
                .totalQuantity(2)
                .build();

        Order order = orderMapper.toEntity(dto, user, status);

        assertNull(order.getId());
        assertEquals(order.getTotalCost(), dto.getTotalCost());
        assertEquals(order.getTotalQuantity(), dto.getTotalQuantity());
        assertEquals(order.getStatus(), status);
        assertEquals(order.getUser(), user);
        assertNull(order.getUpdatedAt());
        assertNull(order.getCreatedAt());
    }

    @Test
    public void testPerformAfterMapping() {

        OrderItem orderItem = OrderItem.builder()
                .id(1L)
                .price(100D)
                .quantity(2)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);


        User user = User.builder()
                .id(1L)
                .email("email@gmail.com")
                .password("pass")
                .firstName("Denis")
                .lastName("Golovin")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Status status = Status.builder()
                .id(1L)
                .name("Created")
                .type(StatusType.CREATED)
                .description("will create")
                .build();

        Order order = Order.builder()
                .user(user)
                .status(status)
                .items(orderItems)
                .totalCost(100D)
                .totalQuantity(2)
                .build();

        Order performOrder = orderMapper.performAfterMapping(order);



        assertNull(performOrder.getId());
        assertEquals(performOrder.getTotalCost(), 100D);
        assertEquals(performOrder.getTotalQuantity(), 2);
        assertEquals(performOrder.getStatus(), status);
        assertEquals(performOrder.getUser(), user);
        assertEquals(performOrder.getItems(), orderItems);
        assertNull(performOrder.getUpdatedAt());
        assertNull(performOrder.getCreatedAt());

        for (OrderItem item: performOrder.getItems()) {
            assertEquals(item.getOrder(), performOrder);
        }
    }

    @Test
    public void testToDto() {

        Status status = Status.builder()
                .id(1L)
                .name("Created")
                .type(StatusType.CREATED)
                .description("will create")
                .build();

        Order order = Order.builder()
                .id(1L)
                .status(status)
                .totalCost(100D)
                .totalQuantity(2)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        OrderDto dto = orderMapper.toDto(order);

        assertEquals(dto.getId(), order.getId());
        assertEquals(dto.getTotalQuantity(), order.getTotalQuantity());
        assertEquals(dto.getTotalCost(), order.getTotalCost());
        assertEquals(dto.getStatus(), status);
        assertEquals(dto.getUpdatedAt(), timestamp);
        assertEquals(dto.getCreatedAt(), timestamp);
    }

    @Test
    public void testToDtos() {

        Status status = Status.builder()
                .id(1L)
                .name("Created")
                .type(StatusType.CREATED)
                .description("will create")
                .build();

        Order order1 = Order.builder()
                .id(1L)
                .status(status)
                .totalCost(100D)
                .totalQuantity(2)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Order order2 = Order.builder()
                .id(1L)
                .status(status)
                .totalCost(100D)
                .totalQuantity(2)
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        List<OrderDto> dtos = orderMapper.toDtos(orders);

        for (OrderDto dto: dtos) {
            assertEquals(dto.getId(), order1.getId());
            assertEquals(dto.getTotalQuantity(), order1.getTotalQuantity());
            assertEquals(dto.getTotalCost(), order1.getTotalCost());
            assertEquals(dto.getStatus(), status);
            assertEquals(dto.getUpdatedAt(), timestamp);
            assertEquals(dto.getCreatedAt(), timestamp);
        }
    }
}
