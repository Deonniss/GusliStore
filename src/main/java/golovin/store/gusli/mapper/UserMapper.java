package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.UserDto;
import golovin.store.gusli.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
