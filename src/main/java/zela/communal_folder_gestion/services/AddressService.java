package zela.communal_folder_gestion.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.mappers.AddressMapper;
import zela.communal_folder_gestion.repositories.FinalAddressRepository;
import zela.communal_folder_gestion.repositories.PendingAddressRepository;

@AllArgsConstructor
@Service
public class AddressService {

    private final FinalAddressRepository finalRepo;
    private final PendingAddressRepository pendingRepo;
    private final FolderService folderService;
    private final AddressMapper mapper;

}
