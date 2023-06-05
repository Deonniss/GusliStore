package golovin.store.gusli.service;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.OrderDto;
import golovin.store.gusli.dto.OrderItemDto;
import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.entity.Order;
import golovin.store.gusli.entity.OrderItem;
import golovin.store.gusli.entity.Status;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.entity.type.StatusType;
import golovin.store.gusli.mapper.OrderItemMapper;
import golovin.store.gusli.mapper.OrderMapper;
import golovin.store.gusli.mapper.ProductMapper;
import golovin.store.gusli.repository.OrderItemRepository;
import golovin.store.gusli.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static golovin.store.gusli.entity.type.StatusType.CREATED;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ProductMapper productMapper;

    private final ProductService productService;
    private final StatusService statusService;
    private final UserService userService;

    @SneakyThrows
    public OrderDto getOrder(Long orderId) {
        return orderMapper.toDto(orderRepository.findById(orderId).orElseThrow());
    }

    @SneakyThrows
    public PageableResponse<OrderDto> getOrders(Long userId, Pageable pageable) {
        Page<Order> orders = orderRepository.getAllByUserId(userId, pageable);
        return new PageableResponse<OrderDto>().toBuilder()
                .items(orderMapper.toDtos(orders.getContent()))
                .total(orders.getTotalElements())
                .build();
    }

    @SneakyThrows
    @Transactional
    public OrderDto registerOrder(Long userId, OrderDto dto) {
        User user = userService.getById(userId);
        Status status = statusService.getByType(CREATED);
        fillProducts(dto);
        return orderMapper.toDto(orderRepository.save(orderMapper.performAfterMapping(orderMapper.toEntity(dto, user, status))));
    }

    @SneakyThrows
    @Transactional
    public OrderDto changeOrderStatus(Long orderId, StatusType status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(statusService.getByType(status));
        return orderMapper.toDto(orderRepository.save(order));
    }

    @SneakyThrows
    @Transactional
    public OrderDto addOrderItem(Long orderId, OrderItemDto dto) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        addOrderItemToList(order, dto);
        return orderMapper.toDto(orderRepository.save(orderMapper.performAfterMapping(order)));
    }

    @SneakyThrows
    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @SneakyThrows
    @Transactional
    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow();
        Order order = orderItem.getOrder();
        order.minusQuantity(orderItem.getQuantity());
        order.minusCost(orderItem.getPrice());
        orderItemRepository.deleteById(orderItemId);
    }

    @SneakyThrows
    private void fillProducts(OrderDto dto) {
        dto.getItems().forEach(i -> {
            ProductDto product = productMapper.toDto(productService.getById(i.getProductId()));
            i.setProduct(product);
            i.setPrice(product.getPrice() * i.getQuantity());
            dto.addCost(i.getPrice());
            dto.addQuantity(i.getQuantity());
        });
    }

    @SneakyThrows
    private void addOrderItemToList(Order order, OrderItemDto dto) {
        fillProduct(dto);
        order.getItems().add(orderItemMapper.toEntity(dto));
        order.addCost(dto.getPrice());
        order.addQuantity(dto.getQuantity());
    }

    @SneakyThrows
    private void fillProduct(OrderItemDto dto) {
        ProductDto product = productMapper.toDto(productService.getById(dto.getProductId()));
        dto.setProduct(product);
        dto.setPrice(product.getPrice() * dto.getQuantity());
    }
}
