package zela.communal_folder_gestion.dto.address;

public record AddressCreationDto(
        String fullUserName,
        String street,
        String postalCode,
        String city,
        String number,
        Long folderId) {
}
