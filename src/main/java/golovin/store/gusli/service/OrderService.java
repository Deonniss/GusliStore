package golovin.store.gusli.service;

import golovin.store.gusli.dto.OrderDto;
import golovin.store.gusli.entity.Status;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.mapper.OrderMapper;
import golovin.store.gusli.mapper.ProductMapper;
import golovin.store.gusli.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static golovin.store.gusli.entity.type.StatusType.CREATED;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final StatusService statusService;
    private final UserService userService;

    @SneakyThrows
    @Transactional
    public OrderDto registerOrder(Long userId, OrderDto dto) {
        User user = userService.getById(userId);
        Status status = statusService.getByType(CREATED);
        fillProducts(dto);
        return orderMapper.toDto(orderRepository.save(orderMapper.performAfterMapping(orderMapper.toEntity(dto, user, status))));
    }

    private void fillProducts(OrderDto dto) {
        dto.getItems().forEach(i -> i.setProduct(productMapper.toDto(productService.getById(i.getProductId()))));
    }
}
