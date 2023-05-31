package golovin.store.gusli.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityMapper<D, E>  {

    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntities(List<D> dtoList);
    List <D> toDtos(List<E> entityList);

    @Mapping(target = "id", ignore = true)
    E updateEntity(@MappingTarget E entity, D dto);
}
