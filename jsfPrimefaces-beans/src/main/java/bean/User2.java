/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Deivid
 */
@ManagedBean
@SessionScoped
public class User2 implements Serializable {
    
    @ManagedProperty(value = "Teste")
    private String username;
    
    private String password;
    
    @PostConstruct
    public void init(){
        System.out.println("iniciando o bean, username tem valor "+username);
    }
    
    @PreDestroy
    public void clean(){
        System.out.println("destruindo o bean");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
