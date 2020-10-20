package ru.guteam.picture_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.guteam.picture_service.model.Picture;
import ru.guteam.picture_service.repo.PictureRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public void viewPicture(Long id, HttpServletResponse response) throws IOException {
        Optional<Picture> picture = pictureRepository.findById(id);
        if (picture.isPresent()) {
            Path image = Paths.get(picture.get().getPath());
            byte[] bytes = Files.readAllBytes(image);
            response.setContentType("image");
            response.getOutputStream().write(bytes);
        }
    }

    @Transactional
    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }

    @Transactional
    public Long insert(MultipartFile multipartFile) throws IOException {
        Path picFile = Paths.get("Pictures/" + multipartFile.getOriginalFilename());
        Files.copy(multipartFile.getInputStream(), picFile.resolve(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        Picture picture = Picture.builder()
                .path(picFile.toString())
                .build();
        pictureRepository.save(picture);
        return pictureRepository.findByPath(picFile.toString()).getId();
    }

    @Transactional
    public void update(MultipartFile multipartFile, Long pictureId) throws IOException {
        Path picFile = Paths.get("Pictures/" + multipartFile.getOriginalFilename());
        String oldFile = pictureRepository.findByPath(picFile.toString()).getPath();
        Path oldPic = Paths.get(oldFile);
        Files.delete(oldPic);
        Files.copy(multipartFile.getInputStream(), picFile.resolve(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        Picture picture = Picture.builder()
                .id(pictureId)
                .path(picFile.toString())
                .build();
        pictureRepository.save(picture);
    }
}
