package zela.communal_folder_gestion.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import zela.communal_folder_gestion.entities.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findByFolderIdAndStatus(Long folderId, String status);

    Page<AddressEntity> findByFolderId(Long folderId, Pageable pageable);

}
