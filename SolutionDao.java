package com.nj;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;
@Service
public class SolutionDao {
	
	private Session session;
	
	public SolutionDao() { 	
		session = HibernateUtil.getSessionFactory().openSession();
	}  
	
	public void insertSolution(Solution solution){
		Transaction trns = null;
        try {
            trns = session.beginTransaction();
            session.save(solution);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();  //if no rollback then data row will get locked
            }
            //e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
	}

	@SuppressWarnings("rawtypes")
	public Solution getSolution(String id2) {
		Criteria crit = session.createCriteria(Solution.class);
		crit.add(Restrictions.eq("solution", id2));
		List list = crit.list();
		if(!list.isEmpty()){
			session.flush();
	        session.close();
			return (Solution) list.get(0);
		}
		session.flush();
        session.close();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public void incrementSolutionSweet(int id2) {
		Transaction trns = null;
		Criteria crit = session.createCriteria(Solution.class);
		crit.add(Restrictions.eq("id", id2));
		List list = crit.list();
		Solution s = (Solution) list.get(0);
		int goodCounter = s.getSolutionGood();
		s.setSolutionGood(++goodCounter);
        try {
            trns = session.beginTransaction();
            session.update(s);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            //e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
	}	
	
	@SuppressWarnings("rawtypes")
	public void incrementSolutionSour(int id2) {
		Transaction trns = null;
		Criteria crit = session.createCriteria(Solution.class);
		crit.add(Restrictions.eq("id", id2));
		List list = crit.list();
		Solution s = (Solution) list.get(0);
		int badCounter = s.getSolutionBad();
		s.setSolutionBad(++badCounter);
        try {
            trns = session.beginTransaction();
            session.update(s);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            //e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
	}	
	
	//@SuppressWarnings("rawtypes")
	public void incrementSolutionRed(int id2) {
		//add after adding to Solution object
	}	
	
	@SuppressWarnings("rawtypes")
	public void deleteSolution(String solution){
		Criteria crit = session.createCriteria(Solution.class);
		crit.add(Restrictions.eq("solution", solution));
		List list = crit.list(); 
		session.beginTransaction();
		session.delete((Solution) list.get(0));
		session.getTransaction().commit();
		session.flush();
        session.close();
	}
	
}