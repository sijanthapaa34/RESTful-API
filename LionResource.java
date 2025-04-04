package com.sijan.demorest;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("lions")
public class LionResource {
	
	LionRepository repo = new LionRepository();
	
	//fetching resources
	@GET 
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Lion> getLions() {
		System.out.println("getLion is called");

		return repo.getLions();
	}
	
	@GET
	@Path("lion/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Lion getLion(@PathParam ("id") int id) {
	    Lion lion = repo.getLion(id);

	    return lion;
	}
	  
	@POST //creating resources
	@Path("lion")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Lion createLion(Lion l1) {
		System.out.println(l1);
		repo.create(l1);
		return l1;
	}
	
	  
	@PUT //updating resources
	@Path("lion")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Lion updateLion(Lion l1) {
		System.out.println(l1);
		if(repo.getLion(l1.getId())== null) {
			repo.create(l1);
		}else {
		repo.update(l1);
		}
		return l1;
	}
	
	@DELETE
	@Path("lion/{id}")
	public Lion killLion(@PathParam("id") int id) {
		Lion l = repo.getLion(id);
		if(l.getId()!=0) {
			repo.delete(id); 
		}
		return l;
	}
}
