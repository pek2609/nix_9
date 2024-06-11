package ua.com.alevel.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String saveMultipartFile(String relPath, MultipartFile file);
}
