/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import dao.HibernateDAO;
import dao.UsuarioDAO;
import entidades.Usuario;
import java.util.List;

/**
 *
 * @author Deivid
 */
public class UsuarioRN {
    
    private final HibernateDAO<Usuario> dao;
    
    public UsuarioRN() {
        this.dao = new UsuarioDAO();
    }
 
    
    public void salvar(Usuario usuario) {
        
        String sql = "SELECT u FROM Usuario u"
                + " WHERE u.nome='" + usuario.getNome() + "'";
        
        Usuario verificaUsuario = this.dao.buscar(sql);
        
        if (verificaUsuario == null) {
            this.dao.salvar(usuario);
            util.Util.criarAlerta("usuario salvo");
        } else {
            util.Util.criarAlerta("usuario ja existe");
        }
    }
    
    public List<Usuario> lista() {
        return this.dao.listar();
    }
    
}
