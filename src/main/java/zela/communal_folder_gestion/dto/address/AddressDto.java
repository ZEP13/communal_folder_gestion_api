package zela.communal_folder_gestion.dto.address;

public record AddressDto(
        Long id,
        String fullUserName,
        String street,
        String postalCode,
        String city,
        String number,
        String status,
        Long folderId) {
}
