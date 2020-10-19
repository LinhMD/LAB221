/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dto.ArmorDTO;
import util.ArmorInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

import static dto.ArmorDTO.getHeaderInfo;

/**
 *
 * @author USER
 */
public class MainFrame extends javax.swing.JFrame {

    private Vector<ArmorDTO> armorList = new Vector<>();
    private boolean isForNew = true;
    ArmorInterface armorI;
    /**
     * Creates new form MainFrame
     */
    public MainFrame(ArmorInterface armorI) {
        initComponents();
        this.armorI = armorI;
        loadTable();
//        try {
//            this.armorI = armorI;
//            armorList = (Vector<ArmorDTO>) this.armorI.findAllArmor();
//        } catch ( RemoteException e) {
//            e.printStackTrace();
//        }
    }

    /*
    * reset table display
    * */
    private void loadTable() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(getHeaderInfo());
        armorList.forEach((a) -> tableModel.addRow(a.toVector()));
        table.setModel(tableModel);
    }

    private void displayArmor(ArmorDTO armor){
        if(armor == null) return;

        this.txtID.setText(armor.getArmorID());
        this.txtClassification.setText(armor.getClassification());
        this.txtDefence.setText(armor.getDefence() +"");
        this.txtStatus.setText(armor.getStatus());
        this.txtDescription.setText(armor.getDescription());
        this.txtTimeOfCreate.setText(armor.getTimeOfCreate());
    }

    private ArmorDTO getArmor(){
        try{
            String id = this.txtID.getText();
            String classification = this.txtClassification.getText();
            String description = this.txtDescription.getText();
            String timeOfCreate = this.txtTimeOfCreate.getText();
            String status = this.txtStatus.getText();
            String defence = this.txtDefence.getText();
            return new ArmorDTO(id, classification, description, status, timeOfCreate, defence);
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    private void deleteArmor(ActionEvent actionEvent){
        String id = this.txtID.getText();

        int option = JOptionPane.showConfirmDialog(null, "Do you want to delete armor " + id + "?");
        if(option == JOptionPane.YES_OPTION){
            try{
                if(armorI.removeArmor(id)){
                    this.armorList.remove(new ArmorDTO(id));
                    JOptionPane.showMessageDialog(null, "Delete armor " + id + " successfully!");
                    loadTable();
                } else
                    JOptionPane.showMessageDialog(null, "Delete armor " + id + " failed!!!");
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private void updateArmor(ActionEvent event) {
        ArmorDTO updatedArmor = getArmor();
        if(updatedArmor == null) return;

        try{
            if(armorI.updateArmor(updatedArmor)){
                this.armorList.set(this.table.getSelectedRow(), updatedArmor);
                JOptionPane.showMessageDialog(null, "Update armor " + updatedArmor.getArmorID() + " successfully!");
                loadTable();
            } else
                JOptionPane.showMessageDialog(null, "Update armor " + updatedArmor.getArmorID() + " failed!!!");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void saveNewArmor() {
        ArmorDTO armor = getArmor();
        if(armor == null) return;

        try{
            if(armorI.createArmor(armor)){
                this.armorList.add(armor);
                JOptionPane.showMessageDialog(null, "Add armor " + armor.getArmorID() + " successfully!");
                loadTable();
            } else
                JOptionPane.showMessageDialog(null, "Add armor " + armor.getArmorID() + " failed!!!");
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void newClick(ActionEvent actionEvent){
        if(!isForNew){
            isForNew = true;
            //display a completely empty armor obj
            this.displayArmor(new ArmorDTO());
            this.txtID.setEnabled(true);
        }else {
            this.saveNewArmor();
            isForNew = false;
        }
    }

    private void tableClick() {
        ArmorDTO armorDTO = this.armorList.get(table.getSelectedRow());
        this.displayArmor(armorDTO);
        this.isForNew = false;
    }

    private void getAllClick(ActionEvent actionEvent) {
        try {
            this.armorList = (Vector<ArmorDTO>) armorI.findAllArmor();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        loadTable();
    }

    private void findArmor(ActionEvent actionEvent) {
        String armorID = this.txtID.getText();
        try {
            ArmorDTO armor = armorI.findByArmorID(armorID);
            if(!this.armorList.contains(armor))
                armorList.add(armor);
            this.displayArmor(armor);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnGetAll = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        txtDefence = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnFindArmorByID = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClassification = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTimeOfCreate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Armor Client");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setColumnSelectionAllowed(true);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tableClick();
            }
        });
        btnGetAll.setText("Get All");
        btnGetAll.addActionListener(this::getAllClick);

        jLabel4.setText("Defence");

        btnCreate.setText("Create");
        btnCreate.addActionListener(this::newClick);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::updateArmor);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::deleteArmor);

        btnFindArmorByID.setText("Find Armor by ID");
        btnFindArmorByID.addActionListener(this::findArmor);
        jLabel5.setText("Description");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        jLabel2.setText("ArmorID");

        jLabel3.setText("Classification");

        jLabel6.setText("Time Of Create");

        jLabel8.setText("Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClassification, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnFindArmorByID))
                            .addComponent(txtTimeOfCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtDefence, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFindArmorByID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtID))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtClassification)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtTimeOfCreate))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDefence, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btnGetAll)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGetAll)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new MainFrame((ArmorInterface) Naming.lookup("rmi://127.0.0.1:1097/remoteArmor")).setVisible(true);
            } catch (NotBoundException | RemoteException | MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFindArmorByID;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtClassification;
    private javax.swing.JTextField txtDefence;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTimeOfCreate;
    // End of variables declaration//GEN-END:variables
}
