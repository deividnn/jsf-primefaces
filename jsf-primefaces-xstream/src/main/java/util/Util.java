/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * classe com funcoes gerais
 *
 * @author Deivid
 */
public class Util {

    /**
     * limpa os campos do formulario
     *
     * @param id do formulario
     */
    public static void resetarFormulario(String id) {
        RequestContext.getCurrentInstance().reset(id);
    }

    /**
     * cria uma mensagem que sera exibida no p:growl
     *
     * @param texto da mensagem
     */
    public static void criarMensagem(String texto) {
        FacesMessage msg = new FacesMessage(texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }

    /**
     * executa um comando do javascript dentro de um metodo java
     *
     * @param comando js
     */
    public static void executarJavascript(String comando) {
        RequestContext.getCurrentInstance().execute(comando);
    }
}
