package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.UserDto;
import golovin.store.gusli.entity.Role;
import golovin.store.gusli.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserDto dto, String password, Set<Role> roles);
}
