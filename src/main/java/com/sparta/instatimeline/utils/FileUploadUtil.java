package com.sparta.instatimeline.utils;

import com.sparta.instatimeline.controller.PostForm;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {

    private static Path uploadDir = Paths.get("images/");

    public static void saveFile(PostForm form) throws IOException {

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String username = form.getUsername();
        MultipartFile image = form.getImage();

        InputStream inputStream = image.getInputStream();
        BufferedImage inputImage = ImageIO.read(inputStream);
        BufferedImage outputImage = new BufferedImage(220, 160, inputImage.getType());

        Graphics2D graphics = outputImage.createGraphics();
        graphics.drawImage(inputImage, 0, 0, 220, 160, null);
        graphics.dispose();

        Path filePath = uploadDir.resolve(username + ".jpg");

        ImageIO.write(outputImage, "jpg", new File(String.valueOf(filePath)));

    }

}
