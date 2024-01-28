package com.dutn.be_do_an_vat.utility;

import com.dutn.be_do_an_vat.exception.FileFormatEcception;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

public class ConstFile {
    private static Optional updoadLoadFile(MultipartFile imag) throws IOException {
        String duoi = imag.getOriginalFilename();
        if (!StringUtils.containsAny(duoi, ".png", ".jpg"))
            throw new FileFormatEcception(Const.FILE_FORMAT);

        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");

        if (Files.exists(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(imag.getOriginalFilename())))
            return null;
        if (!Files.exists(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = Const.CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(imag.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(imag.getBytes());
        }
        return null;
    }

    public static void updateLoadFile(MultipartFile[] multipartFiles) {
        Arrays.stream(multipartFiles).forEach(i -> {
            try {
                updoadLoadFile(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
