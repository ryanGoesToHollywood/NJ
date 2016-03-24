package com.nj;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="problem")
public class Problem {
	
	@Id @GeneratedValue
	@Column(name="problem_id")
	int problem_id;
	
	@Column(unique=true)
	String problem;
	
	@Column
	String problemCategory1;
	
	@Column
	String problemCategory2;
	
	@Column
	String problemCategory3;
	
	@Column
	String problemCategory4;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="problem", cascade=CascadeType.ALL)
	Set<Solution> solutions;

	public int getProblem_id() {
		return problem_id;
	}

	public void setProblem_id(int problem_id) {
		this.problem_id = problem_id;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getProblemCategory1() {
		return problemCategory1;
	}

	public void setProblemCategory1(String problemCategory1) {
		this.problemCategory1 = problemCategory1;
	}

	public String getProblemCategory2() {
		return problemCategory2;
	}

	public void setProblemCategory2(String problemCategory2) {
		this.problemCategory2 = problemCategory2;
	}

	public String getProblemCategory3() {
		return problemCategory3;
	}

	public void setProblemCategory3(String problemCategory3) {
		this.problemCategory3 = problemCategory3;
	}
	
	public String getProblemCategory4() {
		return problemCategory4;
	}

	public void setProblemCategory4(String problemCategory4) {
		this.problemCategory4 = problemCategory4;
	}
	
	public Set<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(Set<Solution> solutions) {
		this.solutions = solutions;
	}
}


