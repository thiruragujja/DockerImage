package com.mindtree.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mindtree.pojo.Employee;
import com.mindtree.pojo.Project;

@Repository("projectTaskDao")
public class ProjectTaskDaoImpl extends CustomHibernateDaoSupport implements ProjectTaskDao {
	
	private static final Logger logger = LogManager.getLogger(ProjectTaskDaoImpl.class);
	
	@Override
	public void save(Project project) {
		
		logger.debug("Inside save method of project");
		try {
			getHibernateTemplate().save(project);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void save(Employee employee) {
		
		logger.debug("Inside save method of employee");
		try {
			getHibernateTemplate().save(employee);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> projectList() {
		
		logger.debug("Inside projectList method");
		List<Project> projectList = new ArrayList<>();
		try {
            String query = "from Project";
			projectList = (List<Project>) getHibernateTemplate().find(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("projectList: " +projectList);
		return projectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> employeeList(String nameOfProject) {
		
		logger.debug("Inside employeeList method");
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			Object[] param = { nameOfProject };
			String query = "from Employee where project.projectName=? and taskDescription = NULL";
			employeeList = (List<Employee>) getHibernateTemplate().find(query, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("employeeList: " +employeeList);
		return employeeList;
	}

	@SuppressWarnings("unchecked")
	public List<Project> projectDetails(String nameOfProject) {
		
		logger.debug("Inside projectDetails method");
		List<Project> projectList = new ArrayList<>();
		Session session = null;
		
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Project.class);
			criteria.add(Restrictions.eq("projectName", nameOfProject));
			projectList = criteria.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if(session != null)
				session.close();
		}
		
		logger.debug("projectList: " +projectList);
		return projectList;
	}
	
	
	@Override
	public boolean updateProjectDetails(Employee employee) {

		logger.debug("Inside updateProjectDetails method");
		Session session = null;
		Transaction transaction = null;
		String hqlQuery = "";
       
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			hqlQuery = "UPDATE Employee" + " SET endTask = :endTask," + "startTask = :startTask,"
					+ "taskDescription = :taskDescription" + " WHERE employeeId = :employeeId";
			Query query = session.createQuery(hqlQuery);
			query.setDate("endTask", employee.getEndDate());
			query.setDate("startTask", employee.getStartDate());
			query.setString("taskDescription", employee.getTaskDescription());
			query.setInteger("employeeId", employee.getEmpId());
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		} finally {
			if(session != null)
				session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> employeeDescription(String empDescription) {
		
		logger.debug("Inside employeeDescription method");
		List<Employee> list = new ArrayList<>();
		
		try {
			Object[] param = { empDescription };
			String query = "from Employee where taskDescription=?";
			list = (List<Employee>) getHibernateTemplate().find(query, param);
			return list;
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("list: " +list);
		return list;
	}
}