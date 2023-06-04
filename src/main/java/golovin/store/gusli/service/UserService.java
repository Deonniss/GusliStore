package golovin.store.gusli.service;

import golovin.store.gusli.entity.User;
import golovin.store.gusli.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @SneakyThrows
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
