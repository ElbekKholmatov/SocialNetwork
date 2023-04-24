package com.instagram.instagram.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
