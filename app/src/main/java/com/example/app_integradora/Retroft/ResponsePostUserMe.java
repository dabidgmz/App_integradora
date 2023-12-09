package com.example.app_integradora.Retroft;

public class ResponsePostUserMe {
    private String id;
    private String name;
    private String lastname; // Agregado para que coincida con 'lastname' en PHP
    private String email;
    private String departament; // Agregado para que coincida con 'departament' en PHP
    private String phone; // Agregado para que coincida con 'phone' en PHP
    private String status; // Agregado para que coincida con 'status' en PHP
    private String password;

    // Agrega getters y setters según sea necesario

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
