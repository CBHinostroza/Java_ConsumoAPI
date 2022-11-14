/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.dao;

import java.util.List;

/**
 *
 * @author Bryan
 */
public interface GenericDao {

    public boolean Guardar(Object modelo);

    public boolean Actualizar(Object modelo);
    
    public List<?> Listar(Object modelo);

    public boolean Eliminar(int id);
    

    
}
