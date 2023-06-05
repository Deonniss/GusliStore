package golovin.store.gusli.service;

import golovin.store.gusli.dto.CartDto;
import golovin.store.gusli.dto.CartItemDto;
import golovin.store.gusli.entity.Cart;
import golovin.store.gusli.entity.CartItem;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.mapper.CartItemMapper;
import golovin.store.gusli.mapper.CartMapper;
import golovin.store.gusli.repository.CartItemRepository;
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
    private final CartItemRepository cartItemRepository;

    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    private final ProductService productService;

    @SneakyThrows
    public CartDto getCart(Long userId) {
        return cartMapper.toDto(cartRepository.findByUserId(userId));
    }

    @SneakyThrows
    @Transactional
    public CartDto addCartItem(Long cartId, CartItemDto dto) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        CartItem cartItem = saveCartItem(cart, dto);
        fillCart(cart, cartItem);
        return cartMapper.toDto(cart);
    }

    @SneakyThrows
    private void fillCart(Cart cart, CartItem cartItem) {
        cart.addItem(cartItem);
        cart.addQuantity(cartItem.getQuantity());
        cart.addCost(cartItem.getPrice());
    }

    @SneakyThrows
    private CartItem saveCartItem(Cart cart, CartItemDto dto) {
        CartItem cartItem = cartItemMapper.toEntity(dto);
        cartItem.setCart(cart);
        Product product = productService.getById(dto.getProductId());
        cartItem.setProduct(product);
        cartItem.setPrice(dto.getQuantity() * product.getPrice());
        cartItemRepository.save(cartItem);
        return cartItem;
    }


    /**
     * TODO Корзина создается только при создании пользователя !!!
     *
     * @param user
     */
    public void createCart(User user) {

    }


}
