package dev.dhanush.FileSharing.Repository;

import dev.dhanush.FileSharing.Model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}