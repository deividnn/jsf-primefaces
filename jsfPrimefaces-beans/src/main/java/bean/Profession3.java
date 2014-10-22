/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.NoneScoped;

/**
 *
 * @author Deivid
 */
@ManagedBean(name = "profession3")
@NoneScoped
public class Profession3 implements Serializable{
    
    @ManagedProperty(value = "Software Engineer")
    private String title;
    
    @ManagedProperty(value = "TI")
    private String industry;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    
}
