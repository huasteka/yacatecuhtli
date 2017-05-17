package br.com.yacatecuhtli.core.io;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.io.Files.copy;
import static com.google.common.io.Files.getFileExtension;

@ToString
@EqualsAndHashCode
public class FileOperationHandler {

    private static final Logger LOGGER = Logger.getLogger(FileOperationHandler.class);

    protected File directory;

    public FileOperationHandler(String directoryPath) {
        this.directory = new File(directoryPath);
    }

    public File openFile(String fileNameWithExtension) {
        return new File(getDirectoryPath() + "/" + fileNameWithExtension);
    }

    public File openFile(String fileName, String fileExtension) {
        return openFile(fileName + "." + fileExtension);
    }

    public Path createTemporaryFile(String prefix, String suffix) {
        try {
            return Files.createTempFile(prefix, suffix);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public Path createTemporaryFile(String extension) {
        return createTemporaryFile("", extension);
    }

    public void writeToFile(byte[] source, String fileName) {
        writeToFile(source, openFile(fileName));
    }

    public void writeToFile(byte[] source, File target) {
        try {
            Files.write(target.toPath(), source);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void overwriteFile(byte[] source, File target) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(target, false);
            outputStream.write(source);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void overwriteFile(byte[] source, String fileName) {
        overwriteFile(source, openFile(fileName));
    }

    public byte[] readFromFile(String fileName) {
        try {
            return Files.readAllBytes(openFile(fileName).toPath());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public File copyToTemporaryFile(File source) {
        try {
            File target = createTemporaryFile(getFileExtension(source.getName())).toFile();
            copy(source, target);
            return target;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<File> readDirectory() {
        return Optional.ofNullable(this.directory.listFiles())
                .map(Arrays::asList)
                .orElseGet(ArrayList::new)
                .stream()
                .filter((file) -> file.isFile() && file.canRead())
                .collect(Collectors.toList());
    }

    public String getDirectoryPath() {
        return this.directory.getPath();
    }

}
