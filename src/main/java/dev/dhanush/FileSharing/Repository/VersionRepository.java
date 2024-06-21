package dev.dhanush.FileSharing.Repository;

import dev.dhanush.FileSharing.Model.FileEntity;
import dev.dhanush.FileSharing.Model.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VersionRepository extends JpaRepository<Version, Long> {
    List<Version> findByFileEntity(FileEntity fileEntity);
}