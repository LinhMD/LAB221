/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dto.ArmorDTO;
import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author USER
 */
public interface ArmorInterface extends Remote{
    boolean createArmor(ArmorDTO armorDTO);
    ArmorDTO findByArmorID(String id);
    List<ArmorDTO> findAllArmor();
    boolean removeArmor(String id);
    boolean updateArmor(ArmorDTO armorDTO);
}
