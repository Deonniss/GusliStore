package golovin.store.gusli.service;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.ProductDto;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.mapper.ProductMapper;
import golovin.store.gusli.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @SneakyThrows
    public PageableResponse<ProductDto> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return new PageableResponse<ProductDto>().toBuilder()
                .items(productMapper.toDtos(products.getContent()))
                .total(products.getTotalElements())
                .build();
    }
}
