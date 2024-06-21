package dev.dhanush.FileSharing.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] data;

    @OneToMany(mappedBy = "fileEntity", cascade = CascadeType.ALL)
    private List<Version> versions;
}