/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Deivid
 * @param <T>
 */
public class HibernateDAO<T> implements DAO<T> {

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
    public List<T> listar(String hql) {
        return this.sessao.createQuery(hql).list();
    }

    @Override
    public T carregar(String hql) {
        return (T) this.sessao.createQuery(hql).setMaxResults(1).uniqueResult();
    }

    @Override
    public int executarSQL(String sql,List<Object> parametros) {
        Query query = this.sessao.createSQLQuery(sql);
        
        if(!parametros.isEmpty()){
            int i = 0;
            for (Object object : parametros) {
                System.out.println(object);
                query.setParameter(i, object);
                i++;
            }
   
        }
        return query.executeUpdate();
    }

   

}
