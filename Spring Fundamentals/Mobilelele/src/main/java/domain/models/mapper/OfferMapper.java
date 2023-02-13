package domain.models.mapper;

import domain.models.dtos.AddOfferDTO;
import domain.models.entities.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    Offer addOfferDtoToOffer(AddOfferDTO addOfferDTO);
}
