package zela.communal_folder_gestion.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.repositories.FinalAddressRepository;
import zela.communal_folder_gestion.repositories.PendingAddressRepository;

@Service
@AllArgsConstructor
public class AddressService {

    private final FinalAddressRepository finalRepo;
    private final PendingAddressRepository pendingRepo;

}
