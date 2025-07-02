package com.adotae.api.dtos;

import com.adotae.api.models.User;
import lombok.Data;

import java.util.Base64;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String phone;
    private String city;
    private String cpf;
    private String imageBase64;

    public UserDTO(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.city = user.getCity();
        this.cpf = user.getCpf();
        byte[] imageBytes = user.getImage();
        if (imageBytes != null && imageBytes.length > 0) {
            this.imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        } else {
            this.imageBase64 = null;
        }
    }



}
