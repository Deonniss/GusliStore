package golovin.store.gusli.service;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.UserDto;
import golovin.store.gusli.entity.Role;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.entity.type.RoleType;
import golovin.store.gusli.mapper.UserMapper;
import golovin.store.gusli.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CartService cartService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @SneakyThrows
    public UserDto registerUser(UserDto dto) {
        User user = userRepository.save(userMapper.toEntity(dto, passwordEncoder.encode(dto.getPassword()), getRoleSetForUser()));
        cartService.createCart(user);
        return userMapper.toDto(user);
    }

    @Override
    @SneakyThrows
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    @SneakyThrows
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @SneakyThrows
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @SneakyThrows
    public UserDto getUser(Long userId) {
        return userMapper.toDto(userRepository.findById(userId).orElseThrow());
    }

    @SneakyThrows
    public PageableResponse<UserDto> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return new PageableResponse<UserDto>().toBuilder()
                .items(userMapper.toDtos(users.getContent()))
                .total(users.getTotalElements())
                .build();
    }
    @SneakyThrows
    private Set<Role> getRoleSetForUser() {
        return Set.of(roleService.getRole(RoleType.USER_ROLE));
    }
}
