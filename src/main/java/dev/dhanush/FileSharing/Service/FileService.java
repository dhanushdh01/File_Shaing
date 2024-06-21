package dev.dhanush.FileSharing.Service;

import dev.dhanush.FileSharing.Model.FileEntity;
import dev.dhanush.FileSharing.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileEntity saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setData(file.getBytes());
        return fileRepository.save(fileEntity);
    }

    public FileEntity getFile(Long fileId) {
        return fileRepository.findById(fileId).orElse(null);
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
}