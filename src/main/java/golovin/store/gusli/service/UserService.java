package golovin.store.gusli.service;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.UserDto;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.mapper.UserMapper;
import golovin.store.gusli.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @SneakyThrows
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
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
}
