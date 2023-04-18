package com.instagram.instagram.controller;

import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.UpdateUserDTO;
import com.instagram.instagram.dto.UpdateUserProfilePictureDTO;
import com.instagram.instagram.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<GetTokenDTO> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody UpdateUserDTO dto
    ) {
        return ResponseEntity.ok(userService.update(dto));
    }

    @PutMapping(path = "/updatePhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GetTokenDTO> update(
            @RequestPart("file") MultipartFile file,
            @Valid Long id
    ) {
        return ResponseEntity.ok(
                userService.updateProfilePicture(file, id)
        );
    }
}
