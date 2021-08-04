package com.bibliotheque.API.Entity.Mapper;

import com.bibliotheque.API.Entity.Dto.AttenteDTO;
import com.bibliotheque.API.Entity.Attente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttenteMapper extends EntityMapper<AttenteDTO, Attente > {

}
