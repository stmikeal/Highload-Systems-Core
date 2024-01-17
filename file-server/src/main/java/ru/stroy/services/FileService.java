package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.FileAttachment;
import ru.stroy.entity.datasource.Login;
import ru.stroy.exceptions.FileNotFoundException;
import ru.stroy.exceptions.LoginNotFoundException;
import ru.stroy.repositories.FileRepository;
import ru.stroy.repositories.security.LoginRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final LoginRepository loginRepository;
    private final FileRepository fileRepository;

    public Optional<FileAttachment> getFileWithPriority(Long id, String username) {
        Long accountId = loginRepository
                .findByUsername(username)
                .orElseThrow(() -> new LoginNotFoundException("Can't find user with username: " + username))
                .getId();
        return fileRepository
                .findById(id)
                .filter(file -> file.getAuthor().getId().equals(accountId));
    }

    public byte[] loadFile(Long id, String username) {
        return getFileWithPriority(id, username)
                .map(FileAttachment::getBytes)
                .orElseThrow(() -> new FileNotFoundException("Can't find file with id: " + id));
    }

    public String getFilename(Long id, String username) {
        return getFileWithPriority(id, username)
                .map(FileAttachment::getName)
                .orElseThrow(() -> new FileNotFoundException("Can't find file with id: " + id));
    }

    public Long saveFile(byte[] bytes, String name, String username) {
        FileAttachment file = createFile(bytes, name, username);
        return fileRepository.save(file).getId();
    }

    private FileAttachment createFile(byte[] bytes, String name, String username) {
        FileAttachment file = new FileAttachment();
        file.setName(name);
        file.setBytes(bytes);
        file.setAuthor(getAccountByUsername(username));
        return file;
    }

    private Account getAccountByUsername(String username) {
        return loginRepository
                .findByUsername(username)
                .map(Login::getAccount)
                .orElseThrow(() -> new LoginNotFoundException("Not found user with username: " + username));
    }

}
