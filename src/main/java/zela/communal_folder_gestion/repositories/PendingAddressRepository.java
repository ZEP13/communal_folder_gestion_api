package zela.communal_folder_gestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zela.communal_folder_gestion.entities.PendingAddressEntity;

@Repository
public interface PendingAddressRepository extends JpaRepository<PendingAddressEntity, Long> {

    List<PendingAddressEntity> findByFolderId(Long folderId);

}
