package com.walmart.hackday.service;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.walmart.hackday.entity.Profile;

@Path("/profile/{id}")
public class ProfileResource extends BaseResource{
	private static Logger logger = Logger.getLogger(ProfileResource.class);

	// The Java method will process HTTP GET requests
	@GET
	@Produces("application/json")
	public Profile getSearchResults(@PathParam("id") String id) throws IOException {
		Profile profile = new Profile();
		EntityManagerFactory EMF = null;
		EntityManager EM = null;
		try {
			//profile.setId(id);
			profile.setEmail("test1@walmart.com"); 
			profile.setPassword("testpassword");
			profile.setFirstName("testFN");
			profile.setLastName("testLN");
			profile.setPictureURL("testpictureURL");
			EMF = Persistence.createEntityManagerFactory("hackday");
			EM = EMF.createEntityManager();	
			EntityTransaction et = EM.getTransaction();

			et.begin();
			EM.persist(profile);
			et.commit();
			
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		finally {
			EM.close();
			EMF.close();
		}
		return profile;
	}
}
