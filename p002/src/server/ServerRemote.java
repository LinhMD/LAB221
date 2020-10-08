package server;

import dto.ArmorDTO;
import util.ArmorInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ServerRemote extends UnicastRemoteObject implements ArmorInterface {
	protected ServerRemote() throws RemoteException {
		super();
	}


	@Override
	public boolean createArmor(ArmorDTO armorDTO) throws RemoteException {
		return false;
	}

	@Override
	public ArmorDTO findByArmorID(String id) throws RemoteException {
		return null;
	}

	@Override
	public List<ArmorDTO> findAllArmor() throws RemoteException {
		return null;
	}

	@Override
	public boolean removeArmor(String id) throws RemoteException {
		return false;
	}

	@Override
	public boolean updateArmor(ArmorDTO armorDTO) throws RemoteException {
		return false;
	}

	public static void main(String[] args) {
		try {
			ArmorInterface serverRemote = new ServerRemote();
			LocateRegistry.createRegistry(1097);
			Naming.rebind("RemoteArmor", serverRemote);
			System.out.println("Server is running....");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
