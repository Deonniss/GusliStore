package golovin.store.gusli.service;

import golovin.store.gusli.entity.Category;
import golovin.store.gusli.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @SneakyThrows
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
