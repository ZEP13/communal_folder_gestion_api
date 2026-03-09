package zela.communal_folder_gestion.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.services.AddressService;

@AllArgsConstructor
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService service;

}
