package com.instagram.instagram.mapper;

import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper POST_MAPPER = Mappers.getMapper(PostMapper.class);

    Post toEntity(PostDto dto);
}
