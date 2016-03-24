package com.nj;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
@Entity
@Table(name="solution")
public class Solution {
	
	@Id @GeneratedValue
	@Column(name="id")
	int id;
	
	@Column(unique=false) //false since same solution could occur for more than one problem
	String solution;
	
	@ManyToOne (fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="problem_id")
	Problem problem;
	
	@Column
	int solutionGood;
	
	@Column
	int solutionBad;
	
	@Column
	String exploreURL;
	
	@Column
	String urlDescription;	
	
	@Column
	int urlGood;
	
	@Column
	int urlBad;
	
	@Column
	int solutionRank;
	
	@Column
	int urlRank;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	@JsonIgnore  //stops infinite recursion
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public int getSolutionGood() {
		return solutionGood;
	}

	public void setSolutionGood(int solutionGood) {
		this.solutionGood = solutionGood;
	}

	public int getSolutionBad() {
		return solutionBad;
	}

	public void setSolutionBad(int solutionBad) {
		this.solutionBad = solutionBad;
	}

	public String getExploreURL() {
		return exploreURL;
	}

	public void setExploreURL(String exploreURL) {
		this.exploreURL = exploreURL;
	}

	public String getUrlDescription() {
		return urlDescription;
	}

	public void setUrlDescription(String urlDescription) {
		this.urlDescription = urlDescription;
	}

	public int getUrlGood() {
		return urlGood;
	}

	public void setUrlGood(int urlGood) {
		this.urlGood = urlGood;
	}

	public int getUrlBad() {
		return urlBad;
	}

	public void setUrlBad(int urlBad) {
		this.urlBad = urlBad;
	}

	public int getSolutionRank() {
		return solutionRank;
	}

	public void setSolutionRank(int solutionRank) {
		this.solutionRank = solutionRank;
	}

	public int getUrlRank() {
		return urlRank;
	}

	public void setUrlRank(int urlRank) {
		this.urlRank = urlRank;
	}
	    
}