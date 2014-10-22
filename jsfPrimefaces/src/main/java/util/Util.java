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
 *
 * @author Deivid
 */
public class Util {
    
    public static void criarAlerta(String aviso) {
        FacesMessage msg = new FacesMessage(aviso);
        FacesContext.getCurrentInstance().addMessage(aviso, msg);
    }
    
    public static void atualizarComponente(String id) {
        RequestContext.getCurrentInstance().update(id);
    }
    
    public void executarFuncaoJavaScript(String funcao) {
        RequestContext.getCurrentInstance().execute(funcao);
    }
}
