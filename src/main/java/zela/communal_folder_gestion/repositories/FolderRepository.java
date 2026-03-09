package zela.communal_folder_gestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zela.communal_folder_gestion.entities.FolderEntity;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Long> {

}
