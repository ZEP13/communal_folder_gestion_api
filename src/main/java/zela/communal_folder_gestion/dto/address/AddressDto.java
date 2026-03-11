package zela.communal_folder_gestion.dto.address;

public record AddressDto(
        long id,
        String fullUserName,
        String street,
        String postalCode,
        String city,
        String number,
        String status,
        Long folderId) {
}
