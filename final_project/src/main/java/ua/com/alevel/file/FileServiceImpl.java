package ua.com.alevel.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService{

    @Value("${app.upload.dir}")
    private String uploadsDir;

    @Override
    public String saveMultipartFile(String relPath, MultipartFile file) {
        ensureExternalResourcesDirExists();

        String originalFilename = file.getOriginalFilename();
        Path path = Paths.get(uploadsDir, relPath, originalFilename);
        String filePath = path.toString();
        File destinationFile = new File(filePath);
        try {
            file.transferTo(destinationFile);
        } catch (IOException e) {
            e.printStackTrace();  // Better to handle exceptions properly
        }
        return relPath + "/" + originalFilename;
    }

    private void ensureExternalResourcesDirExists() {
        File uploadDir = new File(uploadsDir);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create the directory if it doesn't exist
        }
    }
}
