/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controladores.ControladorCupom;
import controladores.ControladorLoja;
import controladores.ControladorMovimento;
import entidades.Cupom;
import entidades.CupomPK;
import entidades.Loja;
import entidades.Movimento;
import entidades.MovimentoPK;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.HibernateUtil;

/**
 *
 * @author Deivid
 */
@ManagedBean
@ViewScoped
public class CupomBean implements Serializable {
    
    private Cupom cupom;
    private List<Cupom> lista;
    private Loja loja;
    private Movimento movimento;
    private List<Movimento> listaMovimento;
    
    @PostConstruct
    public void init() {
        this.loja = new Loja();
        this.movimento = new Movimento();
        this.movimento.setMovimentoPK(new MovimentoPK());
        this.listaMovimento = new ArrayList<>();
        this.cupom = new Cupom();
        this.cupom.setCupomPK(new CupomPK());
    }
    
    public List<Loja> listaLojas() {
        String hql = "SELECT vo FROM Loja vo"
                + " ORDER BY vo.id DESC";
        return new ControladorLoja().listar(hql);
    }
    
    public void listaMovimentos() {
        if (this.loja != null) {
            String hql = "SELECT vo FROM Movimento vo"
                    + " WHERE vo.loja =" + this.loja.getId() + "";
            this.listaMovimento = new ControladorMovimento().listar(hql);
        }
    }
    
    public void salvar() {
        
        String hql = "SELECT vo FROM Loja vo"
                + " WHERE vo.id=" + this.loja.getId() + "";
        
        Loja loja2 = new ControladorLoja().carregar(hql);
        this.cupom.setLoja(loja2);
        this.cupom.getCupomPK().setLojaFk(loja2.getId());
        
        String hqlm2 = "SELECT vo FROM Movimento vo"
                + " WHERE vo.movimentoPK.id=" + this.movimento.getMovimentoPK().getId() + ""
                + " AND vo.loja = " + this.loja.getId() + "";
        
        Movimento movimento2 = new ControladorMovimento().carregar(hqlm2);
        this.cupom.setMovimento(movimento2);
        System.out.println(this.cupom.getMovimento());
        
        String hqlm = "SELECT vo FROM Cupom vo"
                + " ORDER BY vo.cupomPK.id DESC";
        
        Cupom cupom2 = new ControladorCupom().carregar(hqlm);
        
        if (cupom2 == null) {
            this.cupom.getCupomPK().setId(1);
        } else {
            this.cupom.getCupomPK().setId(cupom2.getCupomPK().getId() + 1);
        }
        
        Long mov = this.cupom.getMovimento().getMovimentoPK().getId();
        Long id = this.cupom.getCupomPK().getId();
        Long loja = this.cupom.getLoja().getId();
        String serie = this.cupom.getSerie();
        
        String sql = "insert into cupom "
                + " (id,loja_fk,movimento_fk,serie)"
                + " values"
                + "(?,?,?,?)";
        
        List<Object> parametros = new ArrayList<>();
        parametros.add(id);
        parametros.add(loja);
        parametros.add(mov);
        parametros.add(serie);
        
        if (new ControladorCupom().executarSQL(sql, parametros) == 1) {
            util.Util.aviso("cupom salvo");
        } else {
            util.Util.aviso("erro ao salvar cupom");
        }
        
        init();
        this.lista = null;
        
    }
    
    public Cupom getCupom() {
        return cupom;
    }
    
    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }
    
    public List<Cupom> getLista() {
        if (this.lista == null) {
            String hql = "SELECT vo FROM Cupom vo"
                    + " ORDER BY vo.id DESC";
            this.lista = new ControladorCupom().listar(hql);
        }
        return lista;
    }
    
    public Loja getLoja() {
        return loja;
    }
    
    public void setLoja(Loja loja) {
        this.loja = loja;
    }
    
    public Movimento getMovimento() {
        return movimento;
    }
    
    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }
    
    public List<Movimento> getListaMovimento() {
        return listaMovimento;
    }
    
}
