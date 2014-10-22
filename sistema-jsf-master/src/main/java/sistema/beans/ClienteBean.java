/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sistema.controladores.ControladorCliente;
import sistema.entidades.Cliente;
import sistema.util.Util;

/**
 *
 * @author DeividnN
 */
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private Cliente cliente;
    private List<Cliente> lista;
    private String textBusca;
    private String colunaBusca;

    @PostConstruct
    public void init() {
        this.cliente = new Cliente();
        this.colunaBusca = "nome";
    }

    public void salvar() {
        if (new ControladorCliente().salvar(this.cliente)) {
            this.lista = null;
            this.cliente = new Cliente();
        }
    }

    public void excluir(Cliente cliente) {
        new ControladorCliente().excluir(cliente);
        this.lista = null;
    }

    public void editar(Cliente cliente) {
        this.cliente = cliente;
        resetarForm();
    }

    public void cancelar() {
        this.cliente = new Cliente();
        resetarForm();
    }

    public void resetarForm() {
        Util.resetarFormulario("form");
    }

    public void pesquisar() {
        String hql = "SELECT vo FROM Cliente vo"
                + " WHERE CAST(vo." + this.colunaBusca + " AS text) "
                + "LIKE '" + this.textBusca + "%'";

        this.lista = new ControladorCliente().listar(hql);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getLista() {
        if (this.lista == null) {
            String hql = "SELECT vo FROM Cliente vo"
                    + " ORDER BY vo.id DESC";
            this.lista = new ControladorCliente().listar(hql);
        }
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public String getTextBusca() {
        return textBusca;
    }

    public void setTextBusca(String textBusca) {
        this.textBusca = textBusca;
    }

    public String getColunaBusca() {
        return colunaBusca;
    }

    public void setColunaBusca(String colunaBusca) {
        this.colunaBusca = colunaBusca;
    }

}
