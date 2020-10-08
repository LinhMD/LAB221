/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author USER
 */
public class ArmorDTO implements Serializable {
	public static final String ARMOR_ID_FORMAT = "^[a-zA-Z0-9]{1,10}$";
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	static {
		DATE_FORMAT.setLenient(false);
	}

    private String armorID;
    private String classification;
    private String description;
    private String status;
    private Date timeOfCreate;
    private int defence;

    public ArmorDTO(){
    	this.armorID = "";
    	this.classification = "";
    	this.description = "";
    	this.status = "";
    	this.defence = 0;
    	this.timeOfCreate = Calendar.getInstance().getTime();
    }

    public ArmorDTO(String armorID) throws IllegalArgumentException{
    	if(armorID != null && armorID.matches(ARMOR_ID_FORMAT))
	        this.armorID = "";
    	else
    		throw new IllegalArgumentException("ArmorID invalid!!!");
	    this.classification = "";
	    this.description = "";
	    this.status = "";
	    this.defence = 0;
	    this.timeOfCreate = Calendar.getInstance().getTime();
    }

	public ArmorDTO(String armorID, String classification, String description, String status, Date timeOfCreate, int defence)
		throws IllegalArgumentException {
		//check null
		if(armorID == null || classification == null || description == null || status == null){
			StringBuilder err = new StringBuilder();
			err.append("Armor ");
			if(armorID == null)             err.append("id, ");
			if(classification == null)      err.append("classification, ");
			if(description == null)         err.append("description, ");
			if(status == null)              err.append("status, ");
			err.deleteCharAt(err.lastIndexOf(","));
			err.append("invalid!!!");
			throw new IllegalArgumentException(err.toString());
		}
		//check blank
		if(armorID.isBlank() || classification.isBlank() || description.isBlank() || status.isBlank()){
			StringBuilder err = new StringBuilder();
			err.append("Armor ");
			if(armorID.isBlank())           err.append("id, ");
			if(classification.isBlank())    err.append("classification, ");
			if(description.isBlank())       err.append("description, ");
			if(status.isBlank())            err.append("status, ");
			err.deleteCharAt(err.lastIndexOf(","));
			err.append("can not be empty!!!");
			throw new IllegalArgumentException(err.toString());
		}
		//check length, scope, format
		if(!armorID.matches(ARMOR_ID_FORMAT) || classification.length() > 30 || description.length() > 300 || defence <= 0){
			StringBuilder err = new StringBuilder();
			err.append("Armor ");
			if(!armorID.matches(ARMOR_ID_FORMAT))   err.append("id(format:a-zA-Z0-9, max:10), ");
			if(classification.length() > 30)        err.append("classification(max:30), ");
			if(description.length() > 300)          err.append("description(max:300), ");
			if(defence <= 0)                        err.append("defence(> 0), ");
			err.deleteCharAt(err.lastIndexOf(","));
			err.append("must be corrected!!!");
			throw new IllegalArgumentException(err.toString());
		}
		//after all done assign attribute
		this.armorID = armorID;
		this.classification = classification;
		this.description = description;
		this.status = status;
		this.timeOfCreate = Objects.requireNonNullElseGet(timeOfCreate, () -> Calendar.getInstance().getTime());
		this.defence = defence;
	}

	public String getArmorID() {
		return armorID;
	}

	public String getClassification() {
		return classification;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public String getTimeOfCreate() {
		return DATE_FORMAT.format(this.timeOfCreate);
	}

	public int getDefence() {
		return defence;
	}

	public void setArmorID(String armorID) throws IllegalArgumentException{
    	if(armorID == null) throw new IllegalArgumentException("ArmorID invalid!!!");
    	if(!armorID.matches(ARMOR_ID_FORMAT)) throw new IllegalArgumentException("ArmorID(format:a-zA-Z0-9, max:10) must be correct!!!");
		this.armorID = armorID;
	}

	public void setClassification(String classification) {
    	if(classification == null) throw new IllegalArgumentException("Armor classification invalid!!!");
    	if(classification.isBlank()) throw new IllegalArgumentException("Armor classification can not be empty.");
    	if(classification.length() > 30) throw new IllegalArgumentException("Armor classification length must lower than 30.");
		this.classification = classification;
	}

	public void setDescription(String description) {
		if(description == null) throw new IllegalArgumentException("Armor description invalid!!!");
		if(description.isBlank()) throw new IllegalArgumentException("Armor description can not be empty.");
		if(description.length() > 300) throw new IllegalArgumentException("Armor description length must lower than 300.");
		this.description = description;
	}

	public void setStatus(String status) {
		if(status == null) throw new IllegalArgumentException("Armor status invalid!!!");
		if(status.isBlank()) throw new IllegalArgumentException("Armor status can not be empty.");
		this.status = status;
	}

	public void setTimeOfCreate(Date timeOfCreate) {
    	if(timeOfCreate == null) throw new IllegalArgumentException("Armor time of create invalid!!!");
		this.timeOfCreate = timeOfCreate;
	}

	public void setDefence(int defence) {
		if(defence <= 0) throw new IllegalArgumentException("Armor defence must bigger than 0.");
    	this.defence = defence;
	}

	/*
	 * return meta data for table header
	 * */
	public static Vector<String> getHeaderInfo(){
//		String [] headerInfo = new String[]{"ArmorID", "Classification", "Description", "Status", "Time Of Create", "Defence"};
		String [] headerInfo = new String[]{"ArmorID", "Classification", "Time Of Create", "Defence"};
		return new Vector<>(Arrays.asList(headerInfo));
	}

	public Vector<Object> toVector(){
		Vector<Object> vector = new Vector<>();
		vector.add(this.armorID);
		vector.add(this.classification);
//		vector.add(this.description);
//		vector.add(this.status);
		vector.add(DATE_FORMAT.format(this.timeOfCreate));
		vector.add(this.defence);
		return vector;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ArmorDTO armorDTO = (ArmorDTO) o;
		return Objects.equals(armorID, armorDTO.armorID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(armorID);
	}

	@Override
	public String toString() {
		return "ArmorDTO{" +
				"armorID='" + armorID + '\'' +
				", classification='" + classification + '\'' +
				", description='" + description + '\'' +
				", status='" + status + '\'' +
				", timeOfCreate=" + timeOfCreate +
				", defence=" + defence +
				'}';
	}
}
