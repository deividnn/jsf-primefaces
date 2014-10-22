/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controladores.ControladorLoja;
import entidades.Loja;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deivid
 */
@ManagedBean
@ViewScoped
public class LojaBean implements Serializable {

    private Loja loja;
    private List<Loja> lista;

    @PostConstruct
    public void init() {
        this.loja = new Loja();
    }

    public void salvar() {
        new ControladorLoja().salvar(this.loja);
        init();
        this.lista=null;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public List<Loja> getLista() {
        if (this.lista == null) {
            String hql = "SELECT vo FROM Loja vo"
                    + " ORDER BY vo.id DESC";
            this.lista = new ControladorLoja().listar(hql);
        }
        return lista;
    }

    public void setLista(List<Loja> lista) {
        this.lista = lista;
    }

}
