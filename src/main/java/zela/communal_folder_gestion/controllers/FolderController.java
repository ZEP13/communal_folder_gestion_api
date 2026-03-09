package zela.communal_folder_gestion.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import zela.communal_folder_gestion.dto.folder.FolderCreationDto;
import zela.communal_folder_gestion.services.FolderService;

@AllArgsConstructor
@RestController
@RequestMapping("/folders")
public class FolderController {

    private final FolderService service;

    @PostMapping("/save")
    public void save(FolderCreationDto dto) {
        service.save(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        service.deleteFolder(id);
    }
}
