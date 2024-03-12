package com.example.demo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ConstFile {
    public static String updoadLoadFile(String imag) throws IOException {
//        if (!StringUtils.containsAny(duoi, ".png", ".jpg"))
//            throw new FileFormatEcception(Const.FILE_FORMAT);

        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");

        if (Files.exists(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(imag)))
            return null;
        if (!Files.exists(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = Const.CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(imag);
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(imag.getBytes());
        }
        return imag;
    }

//    public static void updateLoadFile(List<String> multipartFiles) {
//        multipartFiles.forEach(i -> {
//            try {
//                updoadLoadFile(i);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
}
