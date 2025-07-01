package com.adotae.api.dtos;

import com.adotae.api.models.User;

public class UserDto {
    private String name;
    private String email;
    private String phone;
    private String city;
    private String cpf;
    private byte[] image;

    public UserDto(User user){
        this.name = user.getName();
        this.email = user.getName();
        this.phone = user.getPhone();
        this.city = user.getCity();
        this.cpf = user.getCpf();
        this.image = user.getImage();
    }

}
