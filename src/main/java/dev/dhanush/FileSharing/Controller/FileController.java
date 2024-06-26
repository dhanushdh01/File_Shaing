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

    // Get the dashboard page with the list of files
    // Postman: GET request to http://localhost:8080/dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<FileEntity> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        return "dashboard";
    }

    // Get the upload page
    // Postman: GET request to http://localhost:8080/upload
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    // Handle file upload
    // Postman: POST request to http://localhost:8080/upload
    // Body: form-data with key "file" (type: file) and key "version" (type: text)
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("version") String version) throws IOException {
        FileEntity fileEntity = fileService.saveFile(file);
        versionService.saveVersion(fileEntity, file, version);
        return "redirect:/dashboard";
    }

    // Get the download page for a specific file
    // Postman: GET request to http://localhost:8080/download/{fileId}
    @GetMapping("/download/{fileId}")
    public String download(@PathVariable Long fileId, Model model) {
        FileEntity fileEntity = fileService.getFile(fileId);
        model.addAttribute("file", fileEntity);
        List<Version> versions = versionService.getVersionsByFile(fileEntity);
        model.addAttribute("versions", versions);
        return "download";
    }

    // Get the share page for a specific file
    // Postman: GET request to http://localhost:8080/share/{fileId}
    @GetMapping("/share/{fileId}")
    public String share(@PathVariable Long fileId, Model model) {
        FileEntity fileEntity = fileService.getFile(fileId);
        model.addAttribute("file", fileEntity);
        return "share";
    }
}
