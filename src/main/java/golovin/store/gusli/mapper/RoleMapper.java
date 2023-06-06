package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.RoleDto;
import golovin.store.gusli.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
}
