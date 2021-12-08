package sample.exeption.storage;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("\nResume " + uuid + " is  exist", uuid + "\n");
    }
}