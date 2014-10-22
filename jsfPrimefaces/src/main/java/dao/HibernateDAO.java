/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.Session;

/**
 *
 * @author Deivid
 * @param <T>
 */
public class HibernateDAO<T> implements DAO<T> {

    private final Session sessao;
    private final Class classe;

    public HibernateDAO(Session sessao, Class classe) {
        super();
        this.sessao = sessao;
        this.classe = classe;
    }

    @Override
    public void salvar(T t) {

        this.sessao.save(t);
    }

    @Override
    public T buscar(String sql) {
        return (T) this.sessao.createQuery(sql).uniqueResult();
    }

    @Override
    public List<T> listar() {
        return this.sessao.createCriteria(classe).list();
    }

}
