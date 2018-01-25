package br.edu.ifpe.garanhuns.provapc.model.dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.edu.ifpe.garanhuns.provapc.model.Aluno;
import br.edu.ifpe.garanhuns.provapc.model.Professor;
import br.edu.ifpe.garanhuns.provapc.model.Prova;
import br.edu.ifpe.garanhuns.provapc.model.RespostaProva;

/**
 *
 * @author Eduardo
 */
public class DaoManagerHiber{

    private static DaoManagerHiber myself = null;
    private SessionFactory sessionFactory;
    private Session s;

    private DaoManagerHiber() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            s = sessionFactory.openSession();
        } catch (Throwable th) {
            System.err.println("Enitial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
    }

    public static DaoManagerHiber getInstance() {
        if (myself == null) myself = new DaoManagerHiber();

        return myself;
    }

    public void persist(Object o) {
        Transaction tr = null;
        try {
            s = sessionFactory.openSession();
            tr = s.beginTransaction();
            s.save(o);
           
            tr.commit();
        } catch (RuntimeException e) {
            if (tr != null) tr.rollback();
            throw e;
        } finally {
            s.close();
        }
    }

    public List recover(String hql) {
        Transaction tr = null;
        List l = null;
        try {
            s = sessionFactory.openSession();
            tr = s.beginTransaction();
            Query query = s.createQuery(hql);
            tr.commit();
            s.flush();
            l = query.list();
            return l;
        } catch (RuntimeException e) {
            if (tr != null) tr.rollback();
            throw e;
        } finally {
            s.close();
        }
    }

    
    public List recover(String hql, long id) {
        Transaction tr = null;
        List l = null;
        try {
            s = sessionFactory.openSession();
            tr = s.beginTransaction();
            Query q = s.createQuery(hql);
            q.setLong("id", id);
            tr.commit();
            s.flush();
            l = q.list();
            return l;
        } catch (RuntimeException e) {
            if (tr != null) tr.rollback();
            throw e;
        } finally {
            s.close();
        }
    }

    public List recoverSQL(String sql) {
        Transaction tr = null;
        List l = null;
        try {
            s = sessionFactory.openSession();
            tr = s.beginTransaction();
            Query query = s.createSQLQuery(sql);
            tr.commit();
            s.flush();
            l = query.list();
            return l;
        } catch (RuntimeException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        } finally {
            s.close();
        }
    }

    public void update(Object o) {
        Transaction tr = null;
        try {
            s = sessionFactory.openSession();
            tr = s.beginTransaction();
            s.merge(o);
            tr.commit();
            s.flush();
        } catch (RuntimeException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        } finally {
            s.close();
        }
    }

    public void delete(Object o) {
        Transaction tr = null;
        try {
            s = sessionFactory.openSession();
            tr = s.beginTransaction();
            s.delete(o);
            tr.commit();
            s.flush();
        } catch (RuntimeException e) {
            if (tr != null) tr.rollback();
            throw e;
        } finally {
            s.close();
        }
    }
    public boolean recuperarProfessorLogin(String login, String senha){
		try {

			System.out.println("login "+login);
			System.out.println("senha "+senha);
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Professor where login=:login and senha=:senha");
			query.setString("login",login);
			query.setString("senha",senha);
			
			List list=query.list();
			System.out.println("list size "+list.size());
			if(list.size()==1){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
    public boolean recuperarAlunoLogin(String login, String senha){
		try {

			System.out.println("login "+login);
			System.out.println("senha "+senha);
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Aluno where login=:login and senha=:senha");
			query.setString("login",login);
			query.setString("senha",senha);
			List list=query.list();
			System.out.println("list size "+list.size());
			if(list.size()==1){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
    public List recuperarRespostaProvaPeloLogin(Prova p){
		Aluno aluno = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("alunoLogado");
		System.out.println("loginAluno "+aluno.getLogin());
		System.out.println("idProva "+p.getId());
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from RespostaProva where prova.id="+p.getId());
		List list=query.list();
		return list;
    }
    public static void main(String args[]) {
        DaoManagerHiber.getInstance();
    }

}
