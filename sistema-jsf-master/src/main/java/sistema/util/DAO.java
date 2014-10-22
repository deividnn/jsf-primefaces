/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema.util;

import java.util.List;

/**
 *
 * @author DeividnN
 * @param <T>
 */
public interface DAO<T> {
    
    public void salvar(T t);
    
    public void atualizar(T t);
    
    public void excluir(T t);
    
    public List<T> listar(String hql);
    
    public T carregar(String hql);
    
}
