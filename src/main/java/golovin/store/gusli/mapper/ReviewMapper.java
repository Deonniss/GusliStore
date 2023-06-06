package golovin.store.gusli.mapper;

import golovin.store.gusli.dto.ReviewDto;
import golovin.store.gusli.entity.Product;
import golovin.store.gusli.entity.Review;
import golovin.store.gusli.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends EntityMapper<ReviewDto, Review> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "product")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Review toEntity(ReviewDto dto, User user, Product product);


    @Mapping(target = "authorName", source = "user.username")
    ReviewDto toDto(Review entity);
}
