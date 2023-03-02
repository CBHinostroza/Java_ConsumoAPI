/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.daoimpl;

import com.api.clases.ClaseGlobal;
import com.api.conexion.Conexion;
import com.api.dao.UsuarioDao;
import com.api.generic.APIGeneric;
import com.api.herramientas.ClaseCifrado;
import com.api.models.Usuario;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Bryan
 */
public class UsuarioDaoImpl extends APIGeneric implements UsuarioDao {

    Conexion cnx;
    String epActualizar = "";

    public UsuarioDaoImpl() {

        if (ClaseGlobal.Conexion.equals("Empresa")) {
            epActualizar = "Usuariosapi/Actualizar";
        } else {
            epActualizar = "Usuariosapi/Actualizar";
        }
    }

    @Override
    public boolean Guardar(Object modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Actualizar(Object modelo) {
        cnx = new Conexion();
        List<Usuario> lista;
        ClaseCifrado ep = new ClaseCifrado("wYb3R");
        try {
            ps = cnx.getConecction().prepareStatement("SELECT \n"
                    + "DISTINCT\n"
                    + "TBM_USUAR.COD_USUAR,\n"
                    + "TBM_USUAR.NOM_CLAVE,\n"
                    + "TBM_USUAR.NOM_USUAR,\n"
                    + "ISNULL(TBR_USUAR_TRABA.COD_TRABA,'')COD_TRABA\n"
                    + "FROM TBM_USUAR LEFT JOIN TBR_USUAR_TRABA \n"
                    + "ON TBM_USUAR.COD_USUAR = TBR_USUAR_TRABA.COD_USUAR\n"
                    + "WHERE TBM_USUAR.EST_ACTIV = 'S'");
            rs = ps.executeQuery();
            lista = new LinkedList<>();

            System.out.println("Usuario \t Contraseña");
            while (rs.next()) {
                Usuario objeto = new Usuario();
                objeto.setUsername(rs.getString(1));
                objeto.setPassword(ep.decrypt(rs.getString(2)));
                objeto.setEmail(rs.getString(1));
                objeto.setCodtraba(rs.getString(4));
                objeto.setNombreusuario(rs.getString(3));
                lista.add(objeto);
                System.out.println(rs.getString(1) + " \t " + ep.decrypt(rs.getString(2)));
            }

            Map<Object, Object> map = new HashMap<>();
            map.put("lista", lista);
            return this.Actualizar(map, epActualizar);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } finally {
            cnx.DevolverConexion();
        }
    }

    @Override
    public boolean Eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> Listar(Object modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
