package zela.communal_folder_gestion.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pending_address")
public class PendingAddressEntity extends AddressEntity {
}
