package com.bibliotheque.API.Entity.Dto;

import lombok.Data;

@Data
public class ReservationResearchDTO {

    private Integer id;
    private EditionDTO edition;
    private Integer userid;

}
