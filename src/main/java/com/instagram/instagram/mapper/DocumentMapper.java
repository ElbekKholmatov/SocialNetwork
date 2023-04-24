package com.instagram.instagram.mapper;

import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.dto.ReturnDocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentMapper {
    DocumentMapper DOCUMENT_MAPPER = Mappers.getMapper(DocumentMapper.class);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "updatedBy", ignore = true)
//    @Mapping(target = "deleted", ignore = true)
    ReturnDocumentDTO mapToReturnDocumentDTO(Document document);

}
