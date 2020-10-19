package client;

import dto.ArmorDTO;
import util.ArmorInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
	public static void main(String[] args) {
		try {
			ArmorInterface armorInterface = (ArmorInterface) Naming.lookup("rmi://127.0.0.1:1097/remoteArmor");
			System.out.println(armorInterface.findAllArmor());
			armorInterface.updateArmor(new ArmorDTO("asd"));
		} catch (NotBoundException | RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
