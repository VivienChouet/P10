package com.bibliotheque.API.Entity.Mapper;

import com.bibliotheque.API.Entity.Dto.EditionDTO;
import com.bibliotheque.API.Entity.Edition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditionMapper  extends EntityMapper<EditionDTO, Edition> {
}
