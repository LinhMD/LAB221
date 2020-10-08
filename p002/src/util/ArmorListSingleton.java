package util;

import dao.ArmorDAO;
import dto.ArmorDTO;

import java.io.Serializable;
import java.util.Vector;

public class ArmorListSingleton extends Vector<ArmorDTO> implements Serializable {
	private static ArmorListSingleton ARMOR_LIST = null;

	public static ArmorListSingleton getInstance(){
		System.out.println(ARMOR_LIST);
		if(ARMOR_LIST != null){
			return ARMOR_LIST;
		} else
			return ARMOR_LIST = ArmorDAO.readFile();
	}
}
