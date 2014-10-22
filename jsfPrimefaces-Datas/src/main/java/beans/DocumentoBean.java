/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Documento;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deivid
 */
@Named
@ViewScoped
public class DocumentoBean implements Serializable {

    @Inject
    private Documento doc;

    public void comparaDatas(String id) {

        if (this.doc.getDataEmissao().after(this.doc.getDataEntrada())) {
            alerta("Data de emissao nao pode ser maior que Data de entrada");
            executarJavascript("setarFocus('" + id + "');");
            atualizaComponente("form:info");
        }else{
            atualizaComponente("form");
        }
    }

    public void alerta(String texto) {

        FacesMessage msg = new FacesMessage(texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }

    public void executarJavascript(String funcao) {
        RequestContext.getCurrentInstance().execute(funcao);
    }

    public void atualizaComponente(String id) {
        RequestContext.getCurrentInstance().update(id);
    }

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }

}
