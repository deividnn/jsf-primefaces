/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import entidades.Loja;
import entidades.Movimento;
import java.util.List;
import util.HibernateDAO;

/**
 *
 * @author Deivid
 */
public class ControladorMovimento {
    
    private HibernateDAO<Movimento> dao;

    public ControladorMovimento() {
        this.dao = new HibernateDAO<>(util.Util.pegarSessao(), Movimento.class);
    }

    public void salvar(Movimento movimento) {
        try {
            this.dao.salvar(movimento);
            util.Util.aviso("movimento salva");
        } catch (Exception e) {
            util.Util.aviso("erro: " + e.getCause().toString());
        }

    }

    public List<Movimento> listar(String hql) {
        return this.dao.listar(hql);
    }
    
      public Movimento carregar(String hql) {
        return this.dao.carregar(hql);
    }
}
