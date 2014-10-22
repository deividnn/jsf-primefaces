/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Deivid
 */
@ManagedBean
@SessionScoped
public class Bean implements Serializable{
    
    public User3 getUser(){
    
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ELContext el = context.getELContext();
        ExpressionFactory ef = application.getExpressionFactory();
        ValueExpression value = ef.createValueExpression(el,"#{user3}",User3.class);
        User3 user = (User3) value.getValue(el);
        
        return user;
    }
}
