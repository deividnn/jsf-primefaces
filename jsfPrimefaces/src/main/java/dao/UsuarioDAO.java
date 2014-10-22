/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entidades.Usuario;
import util.HibernateUtil;

/**
 *
 * @author Deivid
 */
public class UsuarioDAO extends HibernateDAO<Usuario>{

    public UsuarioDAO() {
        super(HibernateUtil.getSessionFactory().getCurrentSession(),
                Usuario.class);
    }
    
}
