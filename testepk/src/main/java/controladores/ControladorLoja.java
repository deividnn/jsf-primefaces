/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Loja;
import java.util.List;
import util.HibernateDAO;

/**
 *
 * @author Deivid
 */
public class ControladorLoja {

    private HibernateDAO<Loja> dao;

    public ControladorLoja() {
        this.dao = new HibernateDAO<>(util.Util.pegarSessao(), Loja.class);
    }

    public void salvar(Loja loja) {
        try {
            this.dao.salvar(loja);
            util.Util.aviso("loja salva");
        } catch (Exception e) {
            util.Util.aviso("erro: " + e.getCause().toString());
        }

    }

    public List<Loja> listar(String hql) {
        return this.dao.listar(hql);
    }

    public Loja carregar(String hql) {
        return this.dao.carregar(hql);
    }
}
