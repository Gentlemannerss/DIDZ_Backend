package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.exceptions.BadRequestException;
import com.digicoachindezorg.didz_backend.services.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadprofilepic/{userId}")
    public ResponseEntity<Object> singleFileUpload(@PathVariable Long userId, @RequestParam("file") MultipartFile file) throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadprofilepic/").path(Objects.requireNonNull(userId.toString())).toUriString();
        String fileName = fileService.storeFile(file, url, userId);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/downloadprofilepic/{userId}")
    public ResponseEntity<Object> downloadProfilePic(@PathVariable Long userId, HttpServletRequest request) {
        Resource resource = fileService.downLoadFile(userId);
        MediaType contentType = MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

    @DeleteMapping("/deleteprofilepic/{userId}")
    public ResponseEntity<Object> deleteProfilePic(@PathVariable Long userId) {
        if (fileService.deleteProfilePic(userId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (fileService.deleteProfilePic(userId)) {
            return ResponseEntity.ok("Profile picture of user with ID : " + userId + " is deleted");
        } else {
            throw new BadRequestException("file does not exist in the system");
        }
    }
}

