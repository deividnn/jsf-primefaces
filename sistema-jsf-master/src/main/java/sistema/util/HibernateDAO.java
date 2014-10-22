/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema.util;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author DeividnN
 */
public class HibernateDAO<T> implements DAO<T>{

    
    private Session sessao;
    private Class<T> classe;

    public HibernateDAO(Session sessao, Class<T> classe) {
        super();
        this.sessao = sessao;
        this.classe = classe;
    }
    
    
    
    @Override
    public void salvar(T t) {
        this.sessao.save(t);
    }

    @Override
    public void atualizar(T t) {
        this.sessao.merge(t);
    }

    @Override
    public void excluir(T t) {
        this.sessao.delete(t);
    }

    @Override
    public List<T> listar(String hql) {
        return this.sessao.createQuery(hql).list();
    }

    @Override
    public T carregar(String hql) {
    return   (T) this.sessao.createQuery(hql).setMaxResults(1).uniqueResult();
        
    }
    
}
