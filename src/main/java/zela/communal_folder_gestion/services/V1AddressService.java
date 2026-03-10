// package zela.communal_folder_gestion.services;
//
// import java.util.List;
//
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;
//
// import lombok.AllArgsConstructor;
// import zela.communal_folder_gestion.dto.address.AddressCreationDto;
// import zela.communal_folder_gestion.dto.address.AddressDto;
// import zela.communal_folder_gestion.entities.FinalAddressEntity;
// import zela.communal_folder_gestion.entities.FolderEntity;
// import zela.communal_folder_gestion.entities.PendingAddressEntity;
// import zela.communal_folder_gestion.mappers.AddressMapper;
// import zela.communal_folder_gestion.repositories.FinalAddressRepository;
// import zela.communal_folder_gestion.repositories.PendingAddressRepository;
//
// ///v1 ou j'utilise la methode demande avec 2 tables sql pour les addresses
// ///sevice obselete, juste pour montrer une autre approche que celle final
// /// Address : les adresses validées (table principale)
// /// PendingAddress : les modifications draft
// /// MergedAddress : mappée sur la vue SQL représentant la liste finale
//
// @AllArgsConstructor
// @Service
// public class V1AddressService {
//
// private final FinalAddressRepository finalRepo;
// private final PendingAddressRepository pendingRepo;
// private final FolderService folderService;
// private final AddressMapper mapper;
//
// public AddressDto savePending(AddressCreationDto dto) {
// if (!folderService.existsById(dto.folderId())) {
// throw new IllegalArgumentException("Folder with id " + dto.folderId() + "
// does not exist.");
// }
// FolderEntity folder = folderService.getById(dto.folderId());
//
// PendingAddressEntity entity = mapper.toEntityFromCreationDto(dto);
// entity.setFolder(folder);
//
// PendingAddressEntity saved = pendingRepo.save(entity);
// return mapper.toDtoFromPending(saved);
// }
//
// public List<AddressDto> confirmSaveByFolder(Long folderId) {
//
// List<PendingAddressEntity> pendingList =
// pendingRepo.findByFolderId(folderId);
//
// List<FinalAddressEntity> finalEntities = pendingList.stream()
// .map(mapper::toEntityFromPending)
// .toList();
//
// finalRepo.saveAll(finalEntities);
// pendingRepo.deleteAll(pendingList);
//
// return finalEntities.stream()
// .map(mapper::toDto)
// .toList();
// }
//
// public Page<AddressDto> getAllAddressesByFolderId(Long folderId, int page,
// int size) {
//
// Pageable pageable = PageRequest.of(page, size);
//
// List<FinalAddressEntity> finalEntities = finalRepo.findByFolderId(folderId,
// pageable);
// List<PendingAddressEntity> pendingEntities =
// pendingRepo.findByFolderIdPageable(folderId, pageable);
//
// List<AddressDto> finalDtos = finalEntities.stream()
// .map(mapper::toDto)
// .toList();
//
// List<AddressDto> pendingDtos = pendingEntities.stream()
// .map(mapper::toDtoFromPending)
// .toList();
//
// List<AddressDto> allDtos = List.of(finalDtos,
// pendingDtos).stream().flatMap(List::stream).toList();
//
// return new PageImpl<>(allDtos, pageable, allDtos.size());
// }
//
// public void deletePending(Long id) {
// pendingRepo.deleteById(id);
// }
//
// public void updatePending(Long id, AddressCreationDto dto) {
// if (!pendingRepo.existsById(id)) {
// throw new IllegalArgumentException("Pending address with id " + id + " does
// not exist.");
// }
// if (!folderService.existsById(dto.folderId())) {
// throw new IllegalArgumentException("Folder with id " + dto.folderId() + "
// does not exist.");
// }
// FolderEntity folder = folderService.getById(dto.folderId());
//
// PendingAddressEntity entity = mapper.toEntityFromCreationDto(dto);
// entity.setId(id);
// entity.setFolder(folder);
//
// pendingRepo.save(entity);
// }
//
// public List<?> getAllAddresses() {
// List<FinalAddressEntity> finalEntities = finalRepo.findAll();
// List<PendingAddressEntity> pendingEntities = pendingRepo.findAll();
//
// List<AddressDto> finalDtos = finalEntities.stream()
// .map(mapper::toDto)
// .toList();
//
// List<AddressDto> pendingDtos = pendingEntities.stream()
// .map(mapper::toDtoFromPending)
// .toList();
//
// return List.of(finalDtos, pendingDtos);
// }
// }
