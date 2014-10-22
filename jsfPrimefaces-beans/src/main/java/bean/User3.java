/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Deivid
 */
@ManagedBean
@SessionScoped
public class User3 implements Serializable {
    
    @ManagedProperty(value = "#{profession3}")
    private Profession3 profession;

    public Profession3 getProfession() {
        return profession;
    }

    public void setProfession(Profession3 profession) {
        this.profession = profession;
    }
    
    
    
   
}
