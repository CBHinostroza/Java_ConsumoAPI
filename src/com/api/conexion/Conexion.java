/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.conexion;

import com.api.clases.ClaseGlobal;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Bryan
 */
public class Conexion {

    private Connection conecction = null;

    public Conexion() {

        SQLServerDataSource ds = new SQLServerDataSource();

        String ipHost = "";
        int port = 0;
        String baseDatos = "";
        String usuario = "";
        String contrasena = "";

        switch (ClaseGlobal.Conexion) {
            case "LocalHost":
                ipHost = "LocalHost";
                port = 1433;
                baseDatos = "MyperSSoma";
                usuario = "sa";
                contrasena = "1234";
                break;
            case "Empresa":
                ipHost = "172.16.10.10";
                port = 1433;
                baseDatos = "MYPERSSOMA";
                usuario = "MYPER";
                contrasena = "MyperSac$2022";
                break;
        }

        try {
            ds.setIntegratedSecurity(false);
            ds.setServerName(ipHost);
            ds.setPortNumber(port);
            ds.setUser(usuario);
            ds.setPassword(contrasena);
            ds.setDatabaseName(baseDatos);
            conecction = ds.getConnection();
            System.out.println("Conectado correctamente");
        } catch (SQLException ex) {
            System.out.println("Error en la conexion" + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public Connection getConecction() {
        return conecction;
    }

    public void DevolverConexion() {
        if (conecction != null) {
            try {
                conecction.close();
                conecction = null;
            } catch (SQLException ex) {
                System.out.println("Error al Cerrar la conexion" + ex.getMessage());
            }
        }
    }
}
