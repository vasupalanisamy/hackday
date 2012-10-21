package com.walmart.hackday.service;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.walmart.hackday.entity.Associate;

@Path("/associate")
public class AssociateResource extends BaseResource{
	private static Logger logger = Logger.getLogger(AssociateResource.class);

	// The Java method will process HTTP GET requests
	@GET
	@Produces("application/json")
	public Associate findAssociate(@QueryParam("email") String email, @QueryParam("password") String password) throws IOException {

		Associate associate = new Associate();
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hackday");
			em = emf.createEntityManager();	
			Query query = em.createQuery("SELECT user FROM " + Associate.class.getName() + " user WHERE email = :email");
			query.setParameter("email", email);
			associate = (Associate)query.getSingleResult();
			if (associate != null) {
				logger.info("findAssociate(): Associate found for " + email);
				if(!associate.getPassword().equals(password)) {
					logger.info("findAssociate(): password not matching for " + email);
					associate = new Associate();
				} 
			} else {
				logger.info("findAssociate(): Associate not found for " + email);
			}
			
		} catch (Exception exp) {
			logger.error("findAssociate(): error while finding the associate ", exp);
		}
		finally {
			em.close();
			emf.close();
		}
		return associate;
	}
}
