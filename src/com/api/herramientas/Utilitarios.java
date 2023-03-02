/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.herramientas;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Bryan
 */
public class Utilitarios {

    public void colocarBotonRefrescar(JButton btnAdd) {
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource(RutasArchivo.rutaImagenesArchivo() + "btnRefrescar.png")));
    }
    
    public boolean RespuestasHttpBoolean(int statusCode) {

        boolean respuesta;

        switch (statusCode) {
            case 200 & 201:
                respuesta = true;
                break;
            case 307:
                respuesta = false;
                break;
            case 400:
                respuesta = false;
                break;
            case 404:
                respuesta = false;
                break;
            default:
                respuesta = false;
                break;
        }
        
        System.out.println(RespuestasHttpString(statusCode));
        JOptionPane.showMessageDialog(null, RespuestasHttpString(statusCode), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        return respuesta;
    }
    
    private String RespuestasHttpString(int statusCode) {

        String respuesta;

        switch (statusCode) {
            case 200 & 201:
                respuesta = statusCode + ": Respuesta con exito";
                break;
            case 307:
                respuesta = statusCode + ": Error en la redirección HTTP/HTTPS no es correcta";
                break;
            case 400:
                respuesta = statusCode + ": Error de validación en el body";
                break;
            case 404:
                respuesta = statusCode + ": Error Recurso no encontrado";
                break;
            default:
                respuesta = statusCode + ": Respuesta no mapeado en el metodo RespuestasHttp"
                            + " de la clase "+this.getClass().getName();
                break;
        }
        return respuesta;
    }
}
