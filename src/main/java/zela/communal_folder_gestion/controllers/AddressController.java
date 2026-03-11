package zela.communal_folder_gestion.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.dto.address.AddressCreationDto;
import zela.communal_folder_gestion.services.AddressService;

@AllArgsConstructor
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService service;

    @PutMapping("/{id}")
    public void updateAddress(@PathVariable Long id, @RequestBody AddressCreationDto dto) {
        service.updateAddress(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePending(@PathVariable Long id) {
        service.deleteAddress(id);
    }

    @DeleteMapping
    public void deleteMultiAddress(@RequestBody Long[] ids) {
        for (Long id : ids) {
            service.deleteAddress(id);
        }
    }

    // POST /pending-addresses/{id}/cancel → annule une modification draft
    @PostMapping("/pending-addresses/{id}/cancel")
    public void cancelPending(@PathVariable Long id) {
        service.cancelPendingByFolder(id);
    }

}
