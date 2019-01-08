package com.revature.eval.java.core;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EvaluationService evalServ = new EvaluationService();
		Object[] answers = {
				evalServ.reverse("string"),
				evalServ.acronym("Standard and Poor")
		};
		for (Object o : answers) {
			System.out.println(o);
		}
	}

}
