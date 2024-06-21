package dev.dhanush.FileSharing.Service;

import dev.dhanush.FileSharing.Model.FileEntity;
import dev.dhanush.FileSharing.Model.Version;
import dev.dhanush.FileSharing.Repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VersionService {

    @Autowired
    private VersionRepository versionRepository;

    public void saveVersion(FileEntity fileEntity, MultipartFile file, String versionName) throws IOException {
        Version version = new Version();
        version.setFileEntity(fileEntity);
        version.setVersionName(versionName);
        version.setData(file.getBytes());
        versionRepository.save(version);
    }

    public List<Version> getVersionsByFile(FileEntity fileEntity) {
        return versionRepository.findByFileEntity(fileEntity);
    }
}