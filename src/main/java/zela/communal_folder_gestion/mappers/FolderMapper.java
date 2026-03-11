package zela.communal_folder_gestion.mappers;

import org.springframework.stereotype.Component;

import zela.communal_folder_gestion.dto.folder.FolderCreationDto;
import zela.communal_folder_gestion.dto.folder.FolderDto;
import zela.communal_folder_gestion.entities.FolderEntity;

@Component
public class FolderMapper {
    public FolderDto toFolderDto(FolderEntity folder) {
        return new FolderDto(folder.getId(), folder.getName());
    }

    public FolderEntity toEntity(FolderCreationDto folder) {
        if (folder == null) {
            return null;
        }

        FolderEntity folderEntity = new FolderEntity();
        folderEntity.setName(folder.name());

        return folderEntity;
    }

}
