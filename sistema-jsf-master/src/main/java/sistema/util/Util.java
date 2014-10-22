/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DeividnN
 */
public class Util {

    public static Session pegarSessao() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public static void criarAviso(String texto){
        FacesMessage msg = new FacesMessage(texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }
    
    public static void resetarFormulario(String idForm){
        RequestContext.getCurrentInstance().reset(idForm);
    }
}
