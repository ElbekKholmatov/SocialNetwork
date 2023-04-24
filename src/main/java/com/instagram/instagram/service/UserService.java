package com.instagram.instagram.service;

import com.instagram.instagram.config.security.JwtUtils;
import com.instagram.instagram.criteria.FollowersCriteria;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.domains.basic.User;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.UpdateUserDTO;
import com.instagram.instagram.dto.auth.TokenResponse;
import com.instagram.instagram.repository.AuthUserRepository;
import com.instagram.instagram.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;
    private final DocumentService documentService;
    private final JwtUtils jwtUtils;

    public AuthUser getUser(String username) {
        return authUserRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }

    public AuthUser getUser(Long id) {
        return authUserRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }

    public List<User> getUsers(List<Long> ids, FollowersCriteria criteria) {
        List<User> users = userRepository.findAllByAuthUserId(ids);
        if(criteria.getSortKey()== FollowersCriteria.SortKey.NAME){
            if(criteria.getOrder()== FollowersCriteria.Order.ASC){
                users = users.stream().sorted(Comparator.comparing(User::getFullName)).toList();
            }else {
                users = users.stream().sorted(Comparator.comparing(User::getFullName).reversed()).toList();
            }
        }
        return users;
    }

    public void saveUserByAuthId(String username, Long authId) {
        User user = User.builder()
                .authUserId(authId)
                .fullName(username)
                .bio("")
                .gender(User.Gender.NOT_GIVEN)
                .picture(null)
                .build();

        System.out.println("User Service    34:  =>  user saving process      ");
        System.out.println(user.toString());
        userRepository.save(user);
    }

    public GetTokenDTO update(@NonNull UpdateUserDTO dto) {

        userRepository.update(dto.id(), dto.fullName(), dto.bio(), dto.gender());

        TokenResponse tokenResponse = jwtUtils.generateToken(authUserRepository.findAuthUsernameById(dto.id()));
        String jwtToken = tokenResponse.getAccessToken();

        return GetTokenDTO.builder()
                .token(jwtToken)
                .build();
    }

    public GetTokenDTO updateProfilePicture(MultipartFile file, Long id) {
        Document picture = documentService.saveDocument(file);
        userRepository.updateProfilePicture(picture, id);
        TokenResponse tokenResponse = jwtUtils.generateToken(authUserRepository.findAuthUsernameById(id));

        return GetTokenDTO.builder()
                .token(tokenResponse.getAccessToken())
                .build();
    }
}

