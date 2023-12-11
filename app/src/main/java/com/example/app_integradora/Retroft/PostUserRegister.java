package com.example.app_integradora.Retroft;

public class PostUserRegister {

    private String name;
    private String lastname;
    private String email;
    private String departament;
    private String phone;
    private String matricula;
    private String password;
    private String password_confirmation;

    public PostUserRegister(String name, String lastname, String email, String departament, String phone, String matricula, String password, String password_confirmation) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.departament = departament;
        this.phone = phone;
        this.matricula = matricula;
        this.password = password;
        this.password_confirmation = password_confirmation;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}