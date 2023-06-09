package com.instagram.instagram.service;

import com.instagram.instagram.criteria.FollowersCriteria;
import com.instagram.instagram.domains.Follow;
import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.domains.basic.User;
import com.instagram.instagram.repository.NotificationRepository;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final NotificationRepository notificationRepository;
    private final UserService userService;


    public void save(AuthUser from, AuthUser to) {
        followRepository.save(new Follow(from,to, LocalDateTime.now()));
        log.info("User with id {} is following user with id {}",from.getId(),to.getId());
        notificationRepository.save(
                Notification.childBuilder()
                        .message(from.getUsername()+" has started following you!")
                        .seen(false)
                        .from(from)
                        .to(List.of(to))
                        .type(Notification.NotificationType.FOLLOW)
                        .build());
        log.info("Notification sent to user with id {} about following",to.getId());
    }

    public void unfollow(AuthUser from, AuthUser to) {
        followRepository.delete(from.getId(),to.getId());
        log.info("User with id {} is following user with id {}",from.getId(),to.getId());
        notificationRepository.save(
                Notification.childBuilder()
                        .message(from.getUsername()+" has stopped following you!")
                        .seen(false)
                        .from(from)
                        .to(List.of(to))
                        .type(Notification.NotificationType.UNFOLLOW)
                        .build()
        );
        log.info("Notification sent to user with id {} about unfollowing",to.getId());
    }

    public List<User> findAll(FollowersCriteria criteria) {
        AuthUser user = userService.getUser(criteria.getUsername());
        Sort sort =null;
        if(criteria.getSortKey()== FollowersCriteria.SortKey.DATE && criteria.getOrder()==FollowersCriteria.Order.ASC){
            sort = Sort.by(Sort.Direction.ASC, "createdAt");
        }else if(criteria.getSortKey()== FollowersCriteria.SortKey.DATE) {
            sort = Sort.by(Sort.Direction.DESC, "createdAt");
        }
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize(), sort);
        Page<Follow> follows = followRepository.findAllByFollowing(user,pageable);
        List<Long> ids = follows.stream().map(e -> e.getFollower().getId()).toList();
        List<User> users = userService.getUsers(ids, criteria);
        if(criteria.getSortKey() == FollowersCriteria.SortKey.NAME){
            if(criteria.getOrder()== FollowersCriteria.Order.ASC){
                users = users.stream().sorted(Comparator.comparing(User::getFullName)).toList();
            }else {
                users = users.stream().sorted(Comparator.comparing(User::getFullName).reversed()).toList();
            }
        }
        log.info("Followers of {} are requested",criteria.getUsername());
        return users;
    }

    public List<Long> findFollowers(Long id) {
        return followRepository.findAllMyFollowers(id);
    }
}
