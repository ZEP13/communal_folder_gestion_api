package zela.communal_folder_gestion.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.dto.address.AddressCreationDto;
import zela.communal_folder_gestion.dto.address.AddressDto;
import zela.communal_folder_gestion.entities.FinalAddressEntity;
import zela.communal_folder_gestion.entities.PendingAddressEntity;
import zela.communal_folder_gestion.mappers.AddressMapper;
import zela.communal_folder_gestion.repositories.FinalAddressRepository;
import zela.communal_folder_gestion.repositories.PendingAddressRepository;

@Service
@AllArgsConstructor
public class AddressService {

    private final FinalAddressRepository finalRepo;
    private final PendingAddressRepository pendingRepo;
    private final AddressMapper mapper;

    public AddressDto savePending(AddressCreationDto dto) {
        PendingAddressEntity entity = mapper.toEntityFromCreationDto(dto);
        PendingAddressEntity saved = pendingRepo.save(entity);
        return mapper.toDtoFromPending(saved);
    }

    public List<AddressDto> confirmSave() {

        List<PendingAddressEntity> pendingList = pendingRepo.findAll();

        List<FinalAddressEntity> finalEntities = pendingList.stream()
                .map(mapper::toEntityFromPending)
                .toList();

        finalRepo.saveAll(finalEntities);
        pendingRepo.deleteAll(pendingList);

        return finalEntities.stream()
                .map(mapper::toDto)
                .toList();
    }

    public void deletePending() {
        pendingRepo.deleteAll();
    }

    public List<?> getAllAddresses() {
        List<FinalAddressEntity> finalEntities = finalRepo.findAll();
        List<PendingAddressEntity> pendingEntities = pendingRepo.findAll();

        List<AddressDto> finalDtos = finalEntities.stream()
                .map(mapper::toDto)
                .toList();

        List<AddressDto> pendingDtos = pendingEntities.stream()
                .map(mapper::toDtoFromPending)
                .toList();

        return List.of(finalDtos, pendingDtos);
    }
}
