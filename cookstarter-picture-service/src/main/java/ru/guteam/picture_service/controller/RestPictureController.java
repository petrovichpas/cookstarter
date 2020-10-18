package ru.guteam.picture_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.guteam.cookstarter.api.dto.StatusResponse;
import ru.guteam.picture_service.controller.util.StatusResponseBuilder;
import ru.guteam.picture_service.service.PictureService;

import java.io.IOException;

import static ru.guteam.cookstarter.api.dto.RequestMessageHeaders.JWT_TOKEN_HEADER;

@RestController
@RequestMapping("/picture")
@RequiredArgsConstructor
public class RestPictureController {
    private final PictureService pictureService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StatusResponse> addPicture(@RequestBody MultipartFile multipartFile,
                                                     @RequestHeader(JWT_TOKEN_HEADER) String token) throws IOException {
        Long id = pictureService.insert(multipartFile);
        return StatusResponseBuilder.okWithId(String.valueOf(id));
    }

    @PostMapping(value = "/update/{pictureId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StatusResponse> updatePicture(@RequestBody MultipartFile multipartFile,
                                                        @RequestHeader(JWT_TOKEN_HEADER) String token,
                                                        @PathVariable("pictureId") Long pictureId) throws IOException {
        pictureService.update(multipartFile, pictureId);
        return StatusResponseBuilder.ok();
    }

    @GetMapping("/detele/{id}")
    public ResponseEntity<StatusResponse> deletePicture(@PathVariable("id") Long id,
                                                        @RequestHeader(JWT_TOKEN_HEADER) String token) {
        pictureService.deletePicture(id);
        return StatusResponseBuilder.ok();
    }
}
