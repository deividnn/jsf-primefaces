/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Deivid
 */
@Named
@SessionScoped
public class User4 implements Serializable {

    @Inject
    private Profession4 profession;

    public Profession4 getProfession() {
        return profession;
    }

    public void setProfession(Profession4 profession) {
        this.profession = profession;
    }

}
