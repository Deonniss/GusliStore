package golovin.store.gusli.service;

import golovin.store.gusli.dto.CartDto;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.mapper.CartMapper;
import golovin.store.gusli.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @SneakyThrows
    public CartDto getCart(Long userId) {
        return cartMapper.toDto(cartRepository.findByUserId(userId));
    }

    /**
     * TODO Корзина создается только при создании пользователя !!!
     * @param user
     */
    public void createCart(User user) {

    }
}
