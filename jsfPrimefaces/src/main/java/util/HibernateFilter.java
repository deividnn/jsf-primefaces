/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 *
 * @author Deivid
 */
@WebFilter(filterName = "hibernateFilter", urlPatterns = "/*")
public class HibernateFilter implements Filter {

    private SessionFactory sf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.sf = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            this.sf.getCurrentSession().beginTransaction();
            chain.doFilter(request, response);
            this.sf.getCurrentSession().getTransaction().commit();
            this.sf.getCurrentSession().close();

        } catch (HibernateException | IOException | ServletException ex) {
            try {
                if (this.sf.getCurrentSession().getTransaction().isActive()) {

                    this.sf.getCurrentSession().getTransaction().rollback();

                }
            } catch (HibernateException t) {
                
                this.sf.getCurrentSession().close();
            }
            throw new SecurityException(ex);
        } finally {
            this.sf.getCurrentSession().close();
        }
    }

    @Override
    public void destroy() {
        this.sf.close();
    }
}
