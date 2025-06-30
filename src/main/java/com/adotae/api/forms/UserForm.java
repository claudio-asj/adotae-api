package com.adotae.api.forms;

import com.adotae.api.models.User;
import com.adotae.api.repositories.UserRepository;
import com.adotae.api.utils.HashUtil;

public class UserForm {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String city;
    private String cpf;
    private byte[] image;

    public User create(){
        User response = new User();
        response.setName(this.name);
        response.setEmail(this.email);
        response.setPassword(HashUtil.sha256(this.password));
        response.setPhone(this.phone);
        response.setCity(this.city);
        response.setCpf(this.cpf);
        response.setImage(this.image);

        return response;
    }

    public User update(Long id, UserRepository userRepository) {
        User user = userRepository.getReferenceById(id);

        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(HashUtil.sha256(this.password));
        user.setPhone(this.phone);
        user.setCity(this.city);
        user.setCpf(this.cpf);
        user.setImage(this.image);

        return user;
    }
}
