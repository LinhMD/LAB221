package util;

import dto.Employee;

import java.io.Serializable;
import java.util.ArrayList;

public class EmpListSingleton extends ArrayList<Employee> implements Serializable {
	private static volatile EmpListSingleton instance;

	private EmpListSingleton(){
	}

	public static synchronized EmpListSingleton getInstance(){
		if(instance == null)
			return instance = new EmpListSingleton();
		return instance;
	}
}
