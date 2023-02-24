/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.clases;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Bryan
 */
public class ClaseGlobal {

    public static String Conexion = "Empresa";
//    protected String api = "https://apimyperssoma.azurewebsites.net/api/";
    protected String api = "http://200.48.233.66:8099/api/";
    protected PreparedStatement ps;
    protected CallableStatement cs;
    protected ResultSet rs;
}
