/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rn.UsuarioRN;

/**
 *
 * @author Deivid
 */
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

    @Inject
    private Usuario usuario;

    private List<Usuario> lista;

    
    
    public void salvar() {
        try {
            new UsuarioRN().salvar(this.usuario);
            this.usuario = new Usuario();
            this.lista = null;
        } catch (Exception e) {
            util.Util.criarAlerta(e.toString());
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        if (this.lista == null) {
         this.lista =  new UsuarioRN().lista();
        }
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

}
