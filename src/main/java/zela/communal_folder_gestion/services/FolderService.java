package zela.communal_folder_gestion.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.dto.folder.FolderCreationDto;
import zela.communal_folder_gestion.entities.FolderEntity;
import zela.communal_folder_gestion.mappers.FolderMapper;
import zela.communal_folder_gestion.repositories.FolderRepository;

@Service
@AllArgsConstructor
public class FolderService {

    private final FolderRepository repo;
    private final FolderMapper mapper;

    public void deleteFolder(long id) {
        repo.deleteById(id);
    }

    public void save(FolderCreationDto dto) {
        FolderEntity entity = mapper.toEntity(dto);
        repo.save(entity);
    }
}
