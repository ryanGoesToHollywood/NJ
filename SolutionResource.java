package com.nj;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service //SpringDI component(of service type).
@Path("/solution")
public class SolutionResource {

	private SolutionDao solutionService;
	private ProblemDao problemResource;
	
	@Autowired
	public SolutionResource(SolutionDao solutionService, ProblemDao problemResource){
		this.solutionService = solutionService;
		this.problemResource = problemResource;
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Solution getSolution(@QueryParam("id") String id) { 
		Solution solution = solutionService.getSolution(id);
		return solution;
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createSolution(Solution poststuff) {  
		
		try {
			poststuff.setProblem(problemResource.getProblem(poststuff.problem.problem));
			solutionService.insertSolution(poststuff);}
		catch (RuntimeException f) {};
	}	
	
	@DELETE
	@Path("/delete")
	public void deleteSolution(@QueryParam("solution")String solution) {  
		solutionService.deleteSolution(solution);
	}	
	
	@GET
	@Path("/getSet")
	@Produces(MediaType.APPLICATION_JSON)
	public Solution getSolutionSet(@QueryParam("id") String id) { 
		Solution solutionMain = solutionService.getSolution(id);
		return solutionMain;
	}

	@GET
	@Path("/sweetSol")
	public void incrementSolutionSweet(@QueryParam("id") int id) { 
		solutionService.incrementSolutionSweet(id);
	}	
	
	@GET
	@Path("/sourSol")
	public void incrementSolutionSour(@QueryParam("id") int id) { 
		solutionService.incrementSolutionSour(id);
	}	
	
	@GET
	@Path("/flagSol")
	public void incrementSolutionRed(@QueryParam("id") int id) { 
		solutionService.incrementSolutionRed(id);
	}	
}