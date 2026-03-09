package zela.communal_folder_gestion.mappers;

import zela.communal_folder_gestion.dto.address.AddressCreationDto;
import zela.communal_folder_gestion.dto.address.AddressDto;
import zela.communal_folder_gestion.entities.AddressEntity;
import zela.communal_folder_gestion.entities.FinalAddressEntity;
import zela.communal_folder_gestion.entities.PendingAddressEntity;

public class AddressMapper {

    public AddressEntity toEntity(AddressDto dto) {
        if (dto == null) {
            return null;
        }
        AddressEntity entity = new AddressEntity();
        entity.setId(dto.id());
        entity.setFullUserName(dto.fullUserName());
        entity.setStreet(dto.street());
        entity.setPostalCode(dto.postalCode());
        entity.setCity(dto.city());
        entity.setNumber(dto.number());

        return entity;
    }

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
                entity.getFolder() != null ? entity.getFolder().getId() : 0);
    }

    public FinalAddressEntity toEntityFromPending(PendingAddressEntity entity) {
        if (entity == null) {
            return null;
        }
        FinalAddressEntity addressEntity = new FinalAddressEntity();
        addressEntity.setId(entity.getId());
        addressEntity.setFullUserName(entity.getFullUserName());
        addressEntity.setStreet(entity.getStreet());
        addressEntity.setPostalCode(entity.getPostalCode());
        addressEntity.setCity(entity.getCity());
        addressEntity.setNumber(entity.getNumber());

        return addressEntity;
    }

    public AddressDto toDtoFromPending(PendingAddressEntity entity) {
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
                entity.getFolder() != null ? entity.getFolder().getId() : 0);
    }

    public PendingAddressEntity toEntityFromCreationDto(AddressCreationDto dto) {
        if (dto == null) {
            return null;
        }
        PendingAddressEntity entity = new PendingAddressEntity();
        entity.setFullUserName(dto.fullUserName());
        entity.setStreet(dto.street());
        entity.setPostalCode(dto.postalCode());
        entity.setCity(dto.city());
        entity.setNumber(dto.number());

        return entity;
    }
}
