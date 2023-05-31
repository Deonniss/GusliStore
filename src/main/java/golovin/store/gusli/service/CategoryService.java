package golovin.store.gusli.service;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.CategoryDto;
import golovin.store.gusli.entity.Category;
import golovin.store.gusli.mapper.CategoryMapper;
import golovin.store.gusli.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @SneakyThrows
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @SneakyThrows
    public CategoryDto getCategory(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow());
    }

    @SneakyThrows
    public PageableResponse<CategoryDto> getCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return new PageableResponse<CategoryDto>().toBuilder()
                .items(categoryMapper.toDtos(categories.getContent()))
                .total(categories.getTotalElements())
                .build();
    }

    @SneakyThrows
    @Transactional
    public CategoryDto saveCategory(CategoryDto dto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(dto)));
    }

    @SneakyThrows
    @Transactional
    public CategoryDto updateProduct(Long categoryId, CategoryDto dto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.updateEntity(category, dto)));
    }

    @SneakyThrows
    @Transactional
    public void deleteProduct(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
