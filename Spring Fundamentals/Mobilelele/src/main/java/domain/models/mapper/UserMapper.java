package domain.models.mapper;

import domain.models.dtos.UserRegisterDTO;
import domain.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    User userDtoToUser(UserRegisterDTO userRegisterDTO);
}
