package com.nj;

//import java.util.HashSet;
//import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/problem")
public class ProblemResource {

	private ProblemDao problemService;

	public ProblemResource(){
		problemService = new ProblemDao();
	}
	
	@GET  //main get from a basic query
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Problem getProblem(@QueryParam("id") String id) { 
		Problem problem = problemService.getProblem(id);
		return problem;
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createProblem(Problem poststuff) {
		try {problemService.insertProblem(poststuff);}
		catch (RuntimeException e){};
	}	
	
	//@DELETE
	//@Path("/delete")
	//	public void deleteProblem(@QueryParam("problem")String problem) {  
	//		problemService.deleteProblem(problem);
	//}	
	
	@GET
	@Path("/getCat1")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("rawtypes")
	public String[] getProblemCat1(@QueryParam("id") String id) { 
		List list = problemService.getProblemCats(id);
		String[] problemslist = new String[list.size()];
		int index = 0;
		while (index < list.size()) {
			Problem p = (Problem) list.get(index);
			problemslist[index] = p.getProblem();
			index++;
		}
		return problemslist;
	}
	
	@GET
	@Path("/getCat2")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("rawtypes")
	public String[] getProblemCat2(@QueryParam("id") String id) { 
		List list = problemService.getProblemCats2(id);
		String[] problemslist = new String[list.size()];
		int index = 0;
		while (index < list.size()) {
			Problem p = (Problem) list.get(index);
			problemslist[index] = p.getProblemCategory1();
			index++;
		}
		return problemslist;
	}
	
	@GET
	@Path("/getCat3")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("rawtypes")
	public String[] getProblemCat3(@QueryParam("id") String id) { 
		List list = problemService.getProblemCats3(id);
		String[] problemslist = new String[list.size()];
		int index = 0;
		while (index < list.size()) {
			Problem p = (Problem) list.get(index);
			problemslist[index] = p.getProblemCategory2();
			index++;
		}
		return problemslist;
	}
		
	@GET
	@Path("/getCat4")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("rawtypes")
	public String[] getProblemCat4(@QueryParam("id") String id) { 
		List list = problemService.getProblemCats4(id);
		String[] problemslist = new String[list.size()];
		int index = 0;
		while (index < list.size()) {
			Problem p = (Problem) list.get(index);
			problemslist[index] = p.getProblemCategory3();
			index++;
		}
		return problemslist;
	}	
}	
