package dev.dhanush.FileSharing.Controller;

import dev.dhanush.FileSharing.Model.FileEntity;
import dev.dhanush.FileSharing.Model.Version;
import dev.dhanush.FileSharing.Service.FileService;
import dev.dhanush.FileSharing.Service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private VersionService versionService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Assuming you add a method to fetch all files for the logged-in user
        List<FileEntity> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        return "dashboard";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("version") String version) throws IOException {
        FileEntity fileEntity = fileService.saveFile(file);
        versionService.saveVersion(fileEntity, file, version);
        return "redirect:/dashboard";
    }

    @GetMapping("/download/{fileId}")
    public String download(@PathVariable Long fileId, Model model) {
        FileEntity fileEntity = fileService.getFile(fileId);
        model.addAttribute("file", fileEntity);
        List<Version> versions = versionService.getVersionsByFile(fileEntity);
        model.addAttribute("versions", versions);
        return "download";
    }

    @GetMapping("/share/{fileId}")
    public String share(@PathVariable Long fileId, Model model) {
        FileEntity fileEntity = fileService.getFile(fileId);
        model.addAttribute("file", fileEntity);
        return "share";
    }
}