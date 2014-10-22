/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.List;

/**
 *
 * @author Deivid
 * @param <T>
 */
public interface DAO<T> {
 public void salvar(T t);  
 public List<T> listar(String hql);
 public T carregar(String hql);
 public int executarSQL(String sql,List<Object> parametros);

}
