package util;

import dao.ArmorDAO;
import dto.ArmorDTO;

import java.io.Serializable;
import java.util.Vector;

public class ArmorListSingleton extends Vector<ArmorDTO> implements Serializable {
	private static ArmorListSingleton ARMOR_LIST = null;


	public static ArmorListSingleton getInstance(){
		if(ARMOR_LIST != null){
			return ARMOR_LIST;
		} else
			return ARMOR_LIST = ArmorDAO.readFile();
	}

	public static void main(String[] args) {
		ArmorListSingleton instance = getInstance();
		instance.add(new ArmorDTO("1", "as", "as", "As","", "2"));
		System.out.println(instance);
		System.out.println(instance.indexOf(new ArmorDTO("1")));

	}
}
