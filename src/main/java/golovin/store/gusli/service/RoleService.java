package golovin.store.gusli.service;

import golovin.store.gusli.common.PageableResponse;
import golovin.store.gusli.dto.RoleDto;
import golovin.store.gusli.entity.Role;
import golovin.store.gusli.entity.type.RoleType;
import golovin.store.gusli.mapper.RoleMapper;
import golovin.store.gusli.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @SneakyThrows
    public Role getRole(RoleType type) {
        return roleRepository.findByName(type);
    }

    @SneakyThrows
    public PageableResponse<RoleDto> getRolesByUserId(Long userId, Pageable pageable) {
        Page<Role> roles = roleRepository.findAllByUserId(userId, pageable);
        return new PageableResponse<RoleDto>().toBuilder()
                .items(roleMapper.toDtos(roles.getContent()))
                .total(roles.getTotalElements())
                .build();

    }

    @SneakyThrows
    public PageableResponse<RoleDto> getRoles(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        return new PageableResponse<RoleDto>().toBuilder()
                .items(roleMapper.toDtos(roles.getContent()))
                .total(roles.getTotalElements())
                .build();
    }
}
