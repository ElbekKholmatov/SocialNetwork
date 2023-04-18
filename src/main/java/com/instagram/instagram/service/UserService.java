package com.instagram.instagram.service;

import com.instagram.instagram.criteria.FollowersCriteria;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.User;
import com.instagram.instagram.repository.AuthUserRepository;
import com.instagram.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;

    public AuthUser getUser(String username) {
        return authUserRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
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
}

