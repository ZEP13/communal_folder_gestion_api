package zela.communal_folder_gestion.mappers;

import zela.communal_folder_gestion.dto.address.AddressCreationDto;
import zela.communal_folder_gestion.dto.address.AddressDto;
import zela.communal_folder_gestion.entities.AddressEntity;

public class AddressMapper {

    public AddressDto toDto(AddressEntity entity) {
        if (entity == null) {
            return null;

        }
        return new AddressDto(
                entity.getId(),
                entity.getFullUserName(),
                entity.getStreet(),
                entity.getPostalCode(),
                entity.getCity(),
                entity.getNumber(),
                entity.getStatus(),
                entity.getFolder().getId());
    }

    public AddressEntity toEntityFromCreationDto(AddressCreationDto dto) {
        if (dto == null) {
            return null;
        }
        AddressEntity entity = new AddressEntity();
        entity.setFullUserName(dto.fullUserName());
        entity.setStreet(dto.street());
        entity.setPostalCode(dto.postalCode());
        entity.setCity(dto.city());
        entity.setNumber(dto.number());
        entity.setStatus("PENDING");
        return entity;
    }

}
