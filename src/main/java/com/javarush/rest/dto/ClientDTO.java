package com.javarush.rest.dto;

import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


public class ClientDTO {

    private UUID id;
    @NotBlank(message = "Имя обязательно")
    private String name;

    @Email(message = "Email должен быть действительным")
    private String email;

    @NotBlank(message = "Телефон обязателен")
    private String phone;

    public UUID getId() {
        return id = UUID.randomUUID();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
