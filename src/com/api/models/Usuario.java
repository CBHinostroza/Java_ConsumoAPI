/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.models;

import java.io.Serializable;

/**
 *
 * @author Bryan
 */
public class Usuario implements Serializable {

    private String username;
    private String password;
    private String email;
    private String codtraba;
    private String nombreusuario;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodtraba() {
        return codtraba;
    }

    public void setCodtraba(String codtraba) {
        this.codtraba = codtraba;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

}
