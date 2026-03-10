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
        service.updatePending(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePending(@PathVariable Long id) {
        service.deletePending(id);
    }

    @DeleteMapping
    public void deleteMultiPending(@RequestBody Long[] ids) {
        for (Long id : ids) {
            service.deletePending(id);
        }
    }

    @PostMapping("/pending-addresses/{id}/cancel")
    public void cancelPending(@PathVariable Long id) {
        service.deletePending(id);

    }
}
