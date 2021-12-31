package com.bibliotheque.API.Entity.Dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class AttenteDTO {
    public int id;
    public Date dateMail;
    public Boolean mail;
    public EditionDTO edition;
    private UserDTO user;
}
