package com.instagram.instagram.mapper;

import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.dto.CreatePostDTO;
import com.instagram.instagram.dto.NotificationGetDTO;
import com.instagram.instagram.dto.NotificationSentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface NotificationMapper {
    NotificationMapper NOTIFICATION_MAPPER = Mappers.getMapper(NotificationMapper.class);


//    @Mapping(source = "message", target = "message")
//    @Mapping(source = "toUser", target = "toUser")
//    @Mapping(source = "type", target = "type")
//    NotificationGetDTO mapToGetDto(Notification notification);
//
//    @Mapping(source = "message", target = "message")
//    @Mapping(source = "toUser", target = "toUser")
//    @Mapping(source = "type", target = "type")
//    NotificationSentDTO mapToSetDto(Notification notification);
//
//
//
//    // You can also define reverse mapping if needed
//
//    NotificationGetDTO getdto(Notification dto);
//    NotificationSentDTO setdto(Notification dto);
}
