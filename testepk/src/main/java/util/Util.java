/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionFactoryImpl;

/**
 *
 * @author Deivid
 */
public class Util {

    public static Session pegarSessao() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public static void aviso(String texto) {
        FacesMessage msg = new FacesMessage(texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }

    public static Connection conexao() {
        try {

            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/testepk", "deivid", "deivid");

        } catch (SQLException e) {
            System.out.println(e.getCause().toString());
        }
        return null;
    }

    public static void atualizarPK(Long id, Long movimento, Long loja, String serie) {

        Session sessao = Util.pegarSessao();

        Query query = sessao.createSQLQuery(
                "insert into cupom"
                + " (id,loja_fk,movimento_fk,serie)"
                + " values"
                + "(?,?,?,?)")
                .setParameter(0, id)
                .setParameter(1, loja)
                .setParameter(2, movimento)
                .setParameter(3, serie);

        if (query.executeUpdate() == 1) {
            util.Util.aviso("cupom salva");
        } else {
            util.Util.aviso("erro ao inserir cupom");
        }
    }

}
