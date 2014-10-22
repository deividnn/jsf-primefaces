/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 *
 * @author Deivid
 */
@Named
public class Profession4 implements Serializable {

    private String title;

    private String industry;

    @PostConstruct
    public void init() {
        this.title = "Software Architect";
        this.industry = "TI";
    }

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
