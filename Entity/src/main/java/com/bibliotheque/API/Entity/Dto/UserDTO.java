package com.bibliotheque.API.Entity.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;
    public String name ;
    public String email;
    public String password;
    private String token;
    public boolean admin;
}
