package br.com.yacatecuhtli.core.io;

import br.com.yacatecuhtli.core.service.DateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    private static final Logger LOGGER = Logger.getLogger(UploadService.class);

    @Autowired
    protected DateService dateService;

    public boolean uploadFile(MultipartFile multipartFile, String bucket) {
        try {
            String fileName = DigestUtils.md5DigestAsHex((multipartFile.getOriginalFilename() + dateService.getNow().getTime()).getBytes());
            new FileOperationHandler(bucket).writeToFile(multipartFile.getBytes(), fileName);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean updateFile(MultipartFile multipartFile, String bucket, String fileName) {
        try {
            new FileOperationHandler(bucket).overwriteFile(multipartFile.getBytes(), fileName);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
