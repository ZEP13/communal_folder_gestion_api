package zela.communal_folder_gestion.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.dto.address.AddressCreationDto;
import zela.communal_folder_gestion.dto.address.AddressDto;
import zela.communal_folder_gestion.dto.folder.FolderCreationDto;
import zela.communal_folder_gestion.services.AddressService;
import zela.communal_folder_gestion.services.FolderService;

@AllArgsConstructor
@RestController
@RequestMapping("/folders")
public class FolderController {

    private final FolderService service;
    private final AddressService addressService;

    @PostMapping("/save")
    public void save(@RequestBody FolderCreationDto dto) {
        service.save(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        service.deleteFolder(id);
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<Page<?>> getAddressesByFolderId(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(addressService.getAllAddressesByFolder(id, page, size));
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<?> saveAddressToFolder(@PathVariable Long id, @RequestBody AddressCreationDto dto) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        addressService.savePending(id, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/save")
    public ResponseEntity<List<AddressDto>> saveAddressFolder(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressService.confirmSaveByFolder(id));
    }
}
