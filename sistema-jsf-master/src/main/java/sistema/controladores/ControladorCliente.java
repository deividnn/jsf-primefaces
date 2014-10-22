/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.controladores;

import java.util.List;
import sistema.entidades.Cliente;
import sistema.util.HibernateDAO;
import sistema.util.Util;

/**
 *
 * @author DeividnN
 */
public class ControladorCliente {

    private HibernateDAO<Cliente> dao;

    public ControladorCliente() {
        this.dao = new HibernateDAO<>(Util.pegarSessao(), Cliente.class);
    }

    public boolean salvar(Cliente cliente) {
        try {
            if (cliente.getId() == null) {

                this.dao.salvar(cliente);
                Util.criarAviso("cliente salvo");
                return true;

            } else {

                String hql = "SELECT vo FROM Cliente vo"
                        + " WHERE vo.nome='" + cliente.getNome() + "'"
                        + " AND vo.id!=" + cliente.getId() + "";

                Cliente verifica = new ControladorCliente().carregar(hql);
                if (verifica == null) {

                    this.dao.atualizar(cliente);
                    Util.criarAviso("cliente atualizado");
                    return true;

                } else {
                    
                    String hql2 = "SELECT vo FROM Cliente vo"
                            + " WHERE vo.id="+cliente.getId()+"";
                    
                    Cliente clienteatual = new ControladorCliente().carregar(hql2);
                    
                    Util.criarAviso("cliente " + cliente.getNome() + " ja existe");
                    cliente.setNome(clienteatual.getNome());
                    return false;
                }
            }
            
        } catch (Exception e) {
            Util.criarAviso(e.getCause().toString());
            return false;
        }
    }

    public void excluir(Cliente cliente) {
        try {
            this.dao.excluir(cliente);
            Util.criarAviso("cliente excluido");
        } catch (Exception e) {
            Util.criarAviso(e.getCause().toString());
        }

    }

    public List<Cliente> listar(String hql) {
        return this.dao.listar(hql);
    }

    public Cliente carregar(String hql) {
        return this.dao.carregar(hql);
    }
}
