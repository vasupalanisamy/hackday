package com.walmart.hackday.service;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.walmart.hackday.dao.DAO;
import com.walmart.hackday.entity.Order;
import com.walmart.hackday.entity.Profile;

@Path("/profile")
public class ProfileResource extends BaseResource{
	private static Logger logger = Logger.getLogger(ProfileResource.class);

	// The Java method will process HTTP GET requests
	@GET
	@Produces("application/json")
	public Profile findProfile(@QueryParam("email") String email, @QueryParam("password") String password) throws IOException {

		Profile profile = new Profile();
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hackday");
			em = emf.createEntityManager();	
			Query query = em.createQuery("SELECT user FROM " + Profile.class.getName() + " user WHERE email = :email");
			query.setParameter("email", email);
			profile = (Profile)query.getSingleResult();
			if (profile != null) {
				logger.info("findProfile(): Profile found for " + email);
				if(!profile.getPassword().equals(password)) {
					logger.info("findProfile(): password not matching for " + email);
					profile = new Profile();
				} 
			} else {
				logger.info("findProfile(): Profile not found for " + email);
			}
			
		} catch (Exception exp) {
			logger.error("findProfile(): error while finding the profile ", exp);
		}
		finally {
			em.close();
			emf.close();
		}
		return profile;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile handlePost(Profile profile){
		DAO dao = null;
		Profile profileResp = null;
		try{
			dao = new DAO();
			profileResp = dao.get(Profile.class, profile.getId());
			if (profileResp != null){
				if(profile.getFirstName() != null) {
					profileResp.setFirstName(profile.getFirstName());
				}
				if(profile.getLastName() != null) {
					profileResp.setLastName(profile.getLastName());
				}				
				if(profile.getPictureURL() != null) {
					profileResp.setPictureURL(profile.getPictureURL());
				}
				dao.update(profileResp);
			} else {
				profileResp = new Profile();
			}
				
		} catch (Exception exp) {
			logger.error("handlePost(): error while updating the profile - " + profile.getId(), exp);
		} finally {
			dao.close();
		}
		return profileResp;
	}
}
