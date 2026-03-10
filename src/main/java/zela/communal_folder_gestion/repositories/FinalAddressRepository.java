package zela.communal_folder_gestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zela.communal_folder_gestion.entities.FinalAddressEntity;

@Repository
public interface FinalAddressRepository extends JpaRepository<FinalAddressEntity, Long> {
    List<FinalAddressEntity> findByFolderId(Long folderId);

}
