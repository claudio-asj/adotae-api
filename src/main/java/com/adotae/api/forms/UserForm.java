package com.adotae.api.forms;

import com.adotae.api.models.User;
import com.adotae.api.repositories.UserRepository;
import com.adotae.api.utils.HashUtil;
import lombok.Data;

import java.util.Base64;

@Data
public class UserForm {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String city;
    private String cpf;
    private String imageBase64;  // Imagem em Base64 como String

    private byte[] decodeImage() {
        if (imageBase64 == null || imageBase64.isEmpty()) {
            return null;
        }
        return Base64.getDecoder().decode(imageBase64);
    }

    public User create() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);

        // Só tenta fazer hash se a senha não for nula
        if (this.password != null) {
            user.setPassword(HashUtil.sha256(this.password));
        } else {
            throw new IllegalArgumentException("Senha não pode ser null");
        }

        user.setPhone(this.phone);
        user.setCity(this.city);
        user.setCpf(this.cpf);
        user.setImage(this.decodeImage());

        return user;
    }

    public User update(Long id, UserRepository userRepository) {
        User user = userRepository.getReferenceById(id);

        user.setName(this.name);
        user.setEmail(this.email);

        // Atualiza a senha apenas se for informada (evita sobrescrever com null)
        if (this.password != null && !this.password.isEmpty()) {
            user.setPassword(HashUtil.sha256(this.password));
        }

        user.setPhone(this.phone);
        user.setCity(this.city);
        user.setCpf(this.cpf);
        user.setImage(decodeImage());

        return user;
    }
}
