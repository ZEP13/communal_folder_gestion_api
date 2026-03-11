package zela.communal_folder_gestion.services;

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

    public AddressDto savePending(AddressCreationDto dto) {
        if (!folderService.existsById(dto.folderId())) {
            throw new FolderNotFoundException(dto.folderId());
        }
        FolderEntity folder = folderService.getById(dto.folderId());

        AddressEntity entity = mapper.toEntityFromCreationDto(dto);
        entity.setFolder(folder);
        AddressEntity saved = repo.save(entity);
        return mapper.toDto(saved);
    }

    public List<AddressDto> confirmSaveByFolder(Long folderId) {
        if (!folderService.existsById(folderId)) {
            throw new FolderNotFoundException(folderId);
        }

        List<AddressEntity> pendingList = repo.findByFolderIdAndStatus(folderId, "PENDING");

        pendingList.forEach(entity -> entity.setStatus("CONFIRMED"));
        repo.saveAll(pendingList);

        return pendingList.stream()
                .map(mapper::toDto)
                .toList();
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
        List<AddressEntity> pendingList = repo.findByFolderIdAndStatus(folderId, "PENDING");
        repo.deleteAll(pendingList);
    }
}
