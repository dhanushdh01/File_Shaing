package dev.dhanush.FileSharing.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String versionName;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "file_entity_id")
    private FileEntity fileEntity;
}