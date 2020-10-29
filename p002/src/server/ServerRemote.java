package server;

import dao.ArmorDAO;
import dto.ArmorDTO;
import util.ArmorInterface;
import util.ArmorListSingleton;

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
		ArmorListSingleton armorDTOS = ArmorListSingleton.getInstance();
		if(!armorDTOS.contains(armorDTO)){
			boolean add = armorDTOS.add(armorDTO);
			save();
			return add;
		} else
			throw new RemoteException("ArmorID duplicate!!!");

	}

	@Override
	public ArmorDTO findByArmorID(String id) throws RemoteException {
		ArmorListSingleton armorDTOS = ArmorListSingleton.getInstance();
		try{
			return armorDTOS.get(armorDTOS.indexOf(new ArmorDTO(id)));
		}catch (ArrayIndexOutOfBoundsException ex){
			throw new RemoteException("Can not find armor "+ id +"!!!");
		}
	}

	@Override
	public List<ArmorDTO> findAllArmor() throws RemoteException {
		try{
			return ArmorListSingleton.getInstance();
		}catch (Exception ex){
			throw new RemoteException(ex.getMessage());
		}
	}

	@Override
	public boolean removeArmor(String id) throws RemoteException {
		ArmorListSingleton armorDTOS = ArmorListSingleton.getInstance();
		try{
			findByArmorID(id);
			return armorDTOS.remove(new ArmorDTO(id));
		}catch (RemoteException ex){
			throw ex;
		}catch (Exception ex){
			throw new RemoteException(ex.getMessage());
		}finally {
			save();
		}
	}

	@Override
	public boolean updateArmor(ArmorDTO armorDTO) throws RemoteException {
		ArmorListSingleton armorDTOS = ArmorListSingleton.getInstance();
		try{
			findByArmorID(armorDTO.getArmorID());
			return armorDTOS.set(armorDTOS.indexOf(armorDTO), armorDTO) != null;
		}catch (RemoteException ex){
			throw ex;
		}catch (Exception ex){
			throw new RemoteException(ex.getMessage());
		}finally {
			save();
		}
	}

	private void save(){
		ArmorDAO.writeFile();
	}

	public static void main(String[] args) {
		try {

//			ArmorInterface armorInterface = new ServerRemote();
//			Naming.rebind("rmi://127.0.0.1/remoteArmor", armorInterface);

			ArmorInterface serverRemote = new ServerRemote();
			LocateRegistry.createRegistry(1097);
			Naming.rebind("rmi://127.0.0.1:1097/remoteArmor", serverRemote);


		} catch (Exception exception) {
//			exception.printStackTrace();
		}
	}
}
