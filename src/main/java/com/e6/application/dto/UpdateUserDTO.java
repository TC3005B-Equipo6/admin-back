package com.e6.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String firstName;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido paterno debe tener entre 2 y 50 caracteres")
    private String paternalSurname;

    @Size(max = 50, message = "El apellido materno debe tener máximo 50 caracteres")
    private String maternalSurname;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    private boolean active;

    public UpdateUserDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getPaternalSurname() {
        return paternalSurname;
    }

    public String getMaternalSurname() {
        return maternalSurname;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }
}