package golovin.store.gusli.service;

import golovin.store.gusli.entity.Status;
import golovin.store.gusli.entity.type.StatusType;
import golovin.store.gusli.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    @SneakyThrows
    public Status getByType(StatusType type) {
        return statusRepository.findByType(type);
    }
}
