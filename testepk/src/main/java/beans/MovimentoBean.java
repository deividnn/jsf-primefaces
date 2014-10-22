/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controladores.ControladorLoja;
import controladores.ControladorMovimento;
import entidades.Loja;
import entidades.Movimento;
import entidades.MovimentoPK;
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
public class MovimentoBean {

    private Movimento movimento;
    private List<Movimento> lista;
    private Loja loja;

    @PostConstruct
    public void init() {
        this.loja = new Loja();
        this.movimento = new Movimento();
        this.movimento.setMovimentoPK(new MovimentoPK());
    }

    public List<Loja> listaLojas() {
        String hql = "SELECT vo FROM Loja vo"
                + " ORDER BY vo.id DESC";
        return new ControladorLoja().listar(hql);
    }

    public void salvar() {

        String hql = "SELECT vo FROM Loja vo"
                + " WHERE vo.id=" + this.loja.getId() + "";

        Loja loja2 = new ControladorLoja().carregar(hql);

        this.movimento.setLoja(loja2);
        this.movimento.getMovimentoPK().setLojaFk(loja2.getId());

        String hqlm = "SELECT vo FROM Movimento vo"
                + " ORDER BY vo.movimentoPK.id DESC";

        Movimento movimento2 = new ControladorMovimento().carregar(hqlm);

        if (movimento2 == null) {
            this.movimento.getMovimentoPK().setId(1);
        } else {
            this.movimento.getMovimentoPK().setId(movimento2.getMovimentoPK().getId() + 1);
        }

        new ControladorMovimento().salvar(this.movimento);
        init();
        this.lista = null;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    public List<Movimento> getLista() {
        if (this.lista == null) {
            String hql = "SELECT vo FROM Movimento vo"
                    + " ORDER BY vo.id DESC";
            this.lista = new ControladorMovimento().listar(hql);
        }
        return lista;
    }

    public void setLista(List<Movimento> lista) {
        this.lista = lista;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

}
