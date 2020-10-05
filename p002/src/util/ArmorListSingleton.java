package util;

import dto.ArmorDTO;

import java.io.Serializable;
import java.util.Vector;

public class ArmorListSingleton extends Vector<ArmorDTO> implements Serializable {
	private static ArmorListSingleton ARMOR_LIST = null;

	public static ArmorListSingleton getInstance(){
		if(ARMOR_LIST != null){
			return ARMOR_LIST;
		} else
			return ARMOR_LIST = new ArmorListSingleton();
	}
}
