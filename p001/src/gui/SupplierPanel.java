/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ItemDAO;
import dao.SupplierDAO;
import dtos.Item;
import dtos.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class SupplierPanel extends javax.swing.JPanel {

    /**
     * Creates new form SupplierPanel
     */

    public static Vector<Supplier> SUPPLIER =  SupplierDAO.getSuppliers();
    boolean isForNew = true;

    public SupplierPanel() {
        initComponents();
        loadTable();
    }

    public void loadTable() {
        DefaultTableModel model = new DefaultTableModel();
        Vector<String> identifiers = new Vector<>();
        identifiers.add("Code");
        identifiers.add("Name");
        identifiers.add("Address");
        model.setColumnIdentifiers(identifiers);
        for (Supplier supplier : SUPPLIER) {
            Vector<String> vector = new Vector<>();
            vector.add(supplier.getCode());
            vector.add(supplier.getName());
            vector.add(supplier.getAddress());
            model.addRow(vector);
        }
        this.table.setModel(model);
        this.newSupplier(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        Address = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        btnCollab = new javax.swing.JRadioButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setText("Code");

        jLabel2.setText("Name");

        txtName.addActionListener(this::txtNameActionPerformed);

        Address.setText("Address");

        btnCollab.setText("Collaborating");

        btnNew.setText("Add New");
        btnNew.addActionListener(this::newSupplier);

        btnSave.setText("Save");
        btnSave.addActionListener(this::save);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::deleteSupplier);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCode))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddress))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCollab)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNew)
                                .addGap(18, 18, 18)
                                .addComponent(btnSave)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCollab)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addContainerGap(60, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteSupplier(ActionEvent actionEvent) {
        int row = this.table.getSelectedRow();
        if(row >= 0 &&  row < SUPPLIER.size()){
            Supplier supplier = SUPPLIER.get(row);
            Vector<Item> deleteItems = new Vector<>();
            for (Item item : ItemPanel.ITEMS)
                if (item.getSupplier().equals(supplier))
                    deleteItems.add(item);
            confirmAndDelete(supplier, deleteItems);
        }
    }

    private void confirmAndDelete(Supplier supplier, List<Item> items) {
        int confirmDialog;
        if(items.size() > 0)
            confirmDialog = JOptionPane.showConfirmDialog(null,
                "There are " + items.size() + " item(s) " + supplier.getName() + " still supplying.\n" +
                        "Do you want to delete this supplier?");
        else
            confirmDialog = JOptionPane.showConfirmDialog(null,  "Do you want to delete this supplier?");

        if(confirmDialog == JOptionPane.YES_OPTION){
            for (Item item : items) ItemDAO.deleteItem(item);
            ItemPanel.ITEMS = ItemDAO.getAllItem();
            if(SupplierDAO.deleteSupplier(supplier)){
                SUPPLIER.remove(supplier);
                JOptionPane.showMessageDialog(null, "Delete " + supplier.getName() + " supplier successfully");
            }
            loadTable();
        }
    }

    private void save(ActionEvent actionEvent) {
        if(isForNew){
            saveNew();
        }else{
            saveUpdate();
        }
    }

    private void saveUpdate() {
        try{
            Supplier supplier = SUPPLIER.get(table.getSelectedRow());
            if(supplier != null){
                String code = txtCode.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();
                boolean isCollaborating = btnCollab.isSelected();
                Supplier newSupplier = new Supplier(code, name, address, isCollaborating);
                if(SupplierDAO.updateSupplier(newSupplier)){
                    supplier.setAddress(newSupplier.getAddress());
                    supplier.setName(newSupplier.getName());
                    supplier.setCollaborating(newSupplier.isCollaborating());
                    JOptionPane.showMessageDialog(null, "Update supplier successfully!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Update item failed!!");
                }
                loadTable();
            }
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void saveNew() {
        try{
            String code = this.txtCode.getText();
            if(SUPPLIER.stream().anyMatch(s -> s.getCode().equals(code))){
                JOptionPane.showMessageDialog(null, "Supplier code  duplicated!!");
                return;
            }

            Supplier supplier = new Supplier(code , this.txtName.getText(), this.txtAddress.getText(), btnCollab.isSelected());
            if(SupplierDAO.insertSupplier(supplier)){
                SUPPLIER.add(supplier);
                JOptionPane.showMessageDialog(null, "Add new "+ supplier.getName() +"supplier successfully!!");
            }
            this.loadTable();
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void newSupplier(ActionEvent actionEvent) {
        txtCode.setEnabled(true);
        txtCode.setText("");
        txtCode.requestFocus();
        txtName.setText("");
        txtAddress.setText("");
        this.btnCollab.setSelected(true);
        this.isForNew = true;
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int pos = table.getSelectedRow();
        if(pos >= 0){
            txtCode.setEnabled(false);
            Supplier supplier = SUPPLIER.get(pos);
            this.txtName.setText(supplier.getName());
            this.txtName.requestFocus();
            this.txtCode.setText(supplier.getCode());
            this.txtAddress.setText(supplier.getAddress());
            this.btnCollab.setSelected(supplier.isCollaborating());
        }
        this.isForNew = false;
    }//GEN-LAST:event_tableMouseClicked

    private void txtNameActionPerformed(ActionEvent evt) {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()-> {
            Frame supplier = new Frame("supplier");
            supplier.add(new SupplierPanel());
            supplier.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address;
    private javax.swing.JRadioButton btnCollab;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
