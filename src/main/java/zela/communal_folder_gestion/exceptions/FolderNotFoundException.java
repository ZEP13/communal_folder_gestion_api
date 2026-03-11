package zela.communal_folder_gestion.exceptions;

public class FolderNotFoundException extends RuntimeException {
    public FolderNotFoundException(Long folderId) {
        super("Folder with id " + folderId + " does not exist.");
    }

}
