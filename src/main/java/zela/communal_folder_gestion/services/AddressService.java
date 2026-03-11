package zela.communal_folder_gestion.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.dto.address.AddressCreationDto;
import zela.communal_folder_gestion.dto.address.AddressDto;
import zela.communal_folder_gestion.entities.AddressEntity;
import zela.communal_folder_gestion.entities.FolderEntity;
import zela.communal_folder_gestion.exceptions.FolderNotFoundException;
import zela.communal_folder_gestion.mappers.AddressMapper;
import zela.communal_folder_gestion.repositories.AddressRepository;

@AllArgsConstructor
@Service
public class AddressService {

    private final AddressRepository repo;
    private final FolderService folderService;
    private final AddressMapper mapper;

    public AddressDto savePending(Long id, AddressCreationDto dto) {
        if (!folderService.existsById(id)) {
            throw new FolderNotFoundException(id);
        }
        FolderEntity folder = folderService.getById(id);

        AddressEntity entity = mapper.toEntityFromCreationDto(dto);
        entity.setFolder(folder);
        AddressEntity saved = repo.save(entity);
        return mapper.toDto(saved);
    }

    public List<AddressDto> confirmSaveByFolder(Long folderId) {
        List<AddressEntity> pendingNew = repo.findByFolderIdAndStatus(folderId, "PENDING");
        List<AddressEntity> pendingUpdate = repo.findByFolderIdAndStatus(folderId, "PENDING_UPDATE");

        List<AddressEntity> allPending = new ArrayList<>();
        allPending.addAll(pendingNew);
        allPending.addAll(pendingUpdate);

        allPending.forEach(entity -> entity.setStatus("CONFIRMED"));
        repo.saveAll(allPending);

        return allPending.stream().map(mapper::toDto).toList();
    }

    public void updateAddress(Long id, AddressCreationDto dto) {
        AddressEntity entity = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address with id " + id + " does not exist."));

        entity.setFullUserName(dto.fullUserName());
        entity.setCity(dto.city());
        entity.setStreet(dto.street());
        entity.setPostalCode(dto.postalCode());
        entity.setNumber(dto.number());
        entity.setStatus("PENDING_UPDATE");

        repo.save(entity);

    }

    public void deleteAddress(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Address with id " + id + " does not exist.");
        }
        repo.deleteById(id);
    }

    public Page<AddressDto> getAllAddressesByFolder(Long folderId, int page, int size) {
        if (!folderService.existsById(folderId)) {
            throw new FolderNotFoundException(folderId);
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<AddressEntity> addressPage = repo.findByFolderId(folderId, pageable);
        List<AddressDto> dtos = addressPage.getContent().stream()
                .map(mapper::toDto)
                .toList();
        return new PageImpl<>(dtos, pageable, addressPage.getTotalElements());
    }

    public void cancelPendingByFolder(Long folderId) {
        if (!folderService.existsById(folderId)) {
            throw new FolderNotFoundException(folderId);
        }

        List<AddressEntity> pendingNew = repo.findByFolderIdAndStatus(folderId, "PENDING");
        repo.deleteAll(pendingNew);

        List<AddressEntity> pendingUpdate = repo.findByFolderIdAndStatus(folderId, "PENDING_UPDATE");
        pendingUpdate.forEach(entity -> entity.setStatus("CONFIRMED"));
        repo.saveAll(pendingUpdate);
    }
}
