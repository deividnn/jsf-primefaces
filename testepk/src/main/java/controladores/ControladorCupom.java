/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Cupom;
import java.util.List;
import util.HibernateDAO;

/**
 *
 * @author Deivid
 */
public class ControladorCupom {

    private HibernateDAO<Cupom> dao;

    public ControladorCupom() {
        this.dao = new HibernateDAO<>(util.Util.pegarSessao(), Cupom.class);
    }

    public boolean salvar(Cupom cupom) {
        System.out.println("vai salvar");
        try {
            this.dao.salvar(cupom);
            util.Util.aviso("cupom salva");
            return true;
        } catch (Exception e) {
            util.Util.aviso("erro: " + e.getCause().toString());
            return false;
        }

    }

    public List<Cupom> listar(String hql) {
        return this.dao.listar(hql);
    }

    public Cupom carregar(String hql) {
        return this.dao.carregar(hql);
    }

    public int executarSQL(String sql, List<Object> parametros) {
        return this.dao.executarSQL(sql, parametros);
    }
}
