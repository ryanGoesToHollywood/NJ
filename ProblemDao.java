package com.nj;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

@Service
public class ProblemDao {
	
	private Session session;
	
	public ProblemDao() { 	
		session = HibernateUtil.getSessionFactory().openSession();
	}  
	
	public void insertProblem(Problem problem){
		Transaction trns = null;
        try {
            trns = session.beginTransaction();
            session.save(problem);
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
	public Problem getProblem(String id2) {
		Criteria crit = session.createCriteria(Problem.class);
		crit.add(Restrictions.eq("problem", id2));
		List list = crit.list();
		if(!list.isEmpty()){
			session.flush();
	        session.close();
			return (Problem) list.get(0);
		}
		session.flush();
        session.close();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List getProblemCats(String id2) {
		Criteria crit = session.createCriteria(Problem.class);
		crit.add(Restrictions.eq("problemCategory1", id2));
		crit.setProjection(Projections.projectionList() //only want one field returned
				.add(Projections.property("problem"), "problem"))  
			    .setResultTransformer(Transformers.aliasToBean(Problem.class));
		List list = crit.list();
		if(!list.isEmpty()){
			session.flush();
	        session.close();
			return list;
		}
		session.flush();
        session.close();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List getProblemCats2(String id2) {
		Criteria crit = session.createCriteria(Problem.class);
		crit.add(Restrictions.eq("problemCategory2", id2));
		crit.setProjection(Projections.distinct(Projections.projectionList() //Distinct and only specified fields
				.add(Projections.property("problemCategory1"), "problemCategory1")))
			    .setResultTransformer(Transformers.aliasToBean(Problem.class));
		List list = crit.list();
		if(!list.isEmpty()){
			session.flush();
	        session.close();
			return list;
		}
		session.flush();
        session.close();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List getProblemCats3(String id2) {
		Criteria crit = session.createCriteria(Problem.class);
		crit.add(Restrictions.eq("problemCategory3", id2));
		crit.setProjection(Projections.distinct(Projections.projectionList() //Distinct and only specified fields
				.add(Projections.property("problemCategory2"), "problemCategory2")))
			    .setResultTransformer(Transformers.aliasToBean(Problem.class));
		List list = crit.list();
		if(!list.isEmpty()){
			session.flush();
	        session.close();
			return list;
		}
		session.flush();
        session.close();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List getProblemCats4(String id2) {
		Criteria crit = session.createCriteria(Problem.class);
		crit.add(Restrictions.eq("problemCategory4", id2));
		crit.setProjection(Projections.distinct(Projections.projectionList() //Distinct and only specified fields
				.add(Projections.property("problemCategory3"), "problemCategory3")))
			    .setResultTransformer(Transformers.aliasToBean(Problem.class));
		List list = crit.list();
		if(!list.isEmpty()){
			session.flush();
	        session.close();
			return list;
		}
		session.flush();
        session.close();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteProblem(String problem){
		Criteria crit = session.createCriteria(Problem.class);
		crit.add(Restrictions.eq("problem", problem));
		List list = crit.list(); 
		session.beginTransaction();
		session.delete((Problem) list.get(0));
		session.getTransaction().commit();
		session.flush();
        session.close();
	}
}
