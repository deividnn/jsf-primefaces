/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Deivid
 */
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private Cliente cliente;
    private List<Cliente> listaClientes = new ArrayList<>();

    @PostConstruct
    public void init() {

        this.cliente = new Cliente();

        if (this.listaClientes.isEmpty()) {
            for (int i = 1; i <= 10; i++) {
                this.listaClientes.add(new Cliente((long) i, "deivid" + i, "deivid" + i + "@dot.com"));
            }
        }
    }

    public void abrirJanelaClientes() {
        RequestContext.getCurrentInstance().openDialog("clientes");
    }

    public void abrirJanelaClientesOpcoes() {
        /*
         Name       Default         Type        Description
         modal      0               Boolean     Controls modality of the dialog.
         resizable  1               Boolean     When enabled, makes dialog resizable.
         draggable  1               Boolean     When enabled, makes dialog draggable.
         width      auto            Integer     Width of the dialog.
         height     auto            Integer     Height of the dialog.
         contentWidth640            Integer     Width of the dialog content.
         contentHeightauto          Integer     Height of the dialog content.
         closable   true            Boolean     Whether the dialog can be closed or not.
         Data
         */
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("draggable", false);
        opcoes.put("resizable", false);
        opcoes.put("closable", true);

        RequestContext.getCurrentInstance().openDialog("clientes", opcoes, null);
    }

    public void selecionarCliente(Cliente cliente) {
        RequestContext.getCurrentInstance().closeDialog(cliente);

    }

    public void pegarClienteSelecionado(SelectEvent event) {
        this.cliente = (Cliente) event.getObject();
        mostrarMensagem("cliente selecionado");
    }

    public void removerCliente(Cliente cliente) {

        for (Iterator<Cliente> it = listaClientes.iterator(); it.hasNext();) {
            Cliente cliente1 = it.next();
            if (Objects.equals(cliente1.getId(), cliente.getId())) {
                it.remove();
                mostrarMensagem("cliente removido");
            }
        }

    }

    public void mostrarMensagem(String texto) {
        FacesMessage msg = new FacesMessage(texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

}
