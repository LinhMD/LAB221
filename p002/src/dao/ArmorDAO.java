package dao;

import util.ArmorListSingleton;

import java.io.*;

public class ArmorDAO {
	public static final String FILE_NAME = "ArmorData.txt";

	public static void writeFile(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			out.writeObject(ArmorListSingleton.getInstance());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArmorListSingleton readFile(){
		ArmorListSingleton armorListSingleton = null;
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
			armorListSingleton = (ArmorListSingleton) in.readObject();
			in.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return armorListSingleton == null? new ArmorListSingleton(): armorListSingleton;
	}

}
