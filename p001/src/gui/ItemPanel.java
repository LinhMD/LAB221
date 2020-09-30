/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAO;
import dtos.Item;
import dtos.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class ItemPanel extends javax.swing.JPanel {

    /**
     * Creates new form ItemPanel
     */
    public static Vector<Item> items = dao.DAO.getAllItem();
    boolean isForNew = true;

    public ItemPanel() {
        initComponents();
        loadTable();
        updateCbxSupplier();
    }
    /* update item table base on item list
     *
     *
     * */
    public void loadTable() {
        DefaultTableModel model = new DefaultTableModel();
        Vector<String> identifiers = new Vector<>();
        identifiers.add("Code");
        identifiers.add("Name");
        identifiers.add("Supplier");
        identifiers.add("Unit");
        identifiers.add("Price");
        identifiers.add("Supplying");
        model.setColumnIdentifiers(identifiers);
        for (Item item : items) {
            Vector<Object> row = new Vector<>();
            row.add(item.getCode());
            row.add(item.getName());
            row.add(item.getSupplier());
            row.add(item.getUnit());
            row.add(item.getPrice());
            row.add(item.isSupplying());
            model.addRow(row);
        }
        this.jTable1.setModel(model);
        this.newClick(null);
    }
    /*
    * update item display base on item selected in table
    * also update cbxSupplier
    * */
    private void tableClick(){
        int pos = jTable1.getSelectedRow();
        this.isForNew = false;
        if(pos >= 0 && pos < items.size()){
            Item item = items.get(pos);
            this.txtCode.setText(item.getCode());
            this.txtCode.setEnabled(false);
            this.txtName.setText(item.getName());
            this.txtUnit.setText(item.getUnit());
            this.txtPrice.setText(item.getPrice() +"");
            this.jRadioButton1.setSelected(item.isSupplying());
            Vector<Supplier> collabSupplier = updateCbxSupplier();
            if(collabSupplier.contains(item.getSupplier())){
                cbxSupplier.setSelectedItem(item.getSupplier());
            }
        }
    }
    /*
    * update supplier cbox and return the list of collab supplier
    *
    * */
    private Vector<Supplier> updateCbxSupplier() {
        Vector<Supplier> collabSupplier = new Vector<>();
        for (Supplier supplier : SupplierPanel.suppliers)
            if (supplier.isCollaborating())
                collabSupplier.add(supplier);
        this.cbxSupplier.setModel(new  DefaultComboBoxModel<>(collabSupplier));
        return collabSupplier;
    }

    /*
    * when new button click everything enable and clear for new info
    * */
    private void newClick(ActionEvent actionEvent) {
        this.txtCode.setEnabled(true);
        this.txtCode.requestFocus();
        this.txtCode.setText("");
        this.txtName.setText("");
        this.txtPrice.setText("");
        this.txtUnit.setText("");
        this.jRadioButton1.setSelected(true);
        this.isForNew = true;
        updateCbxSupplier();
    }

    private void save(ActionEvent actionEvent) {
        if(isForNew){
            insertNewItem();
        }else{
            updateItem();
        }
    }
    /*
    * update selected item
    * in case shit fuk up, update item list form DB
    * */
    private void updateItem() {
        try{
            Item newItem = this.getItem(this.txtCode.getText());
            if (DAO.updateItem(newItem)){
                items.set(this.jTable1.getSelectedRow(), newItem);
                JOptionPane.showMessageDialog(null, "Update item " + newItem.getName() + " successfully!!");
                this.loadTable();
            }
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch (Exception e){
            items = DAO.getAllItem();
            loadTable();
        }
    }
    /*
    * insert new item into DB and item list
    * in case fuk up, update item list base on DB
    * */
    private void insertNewItem() {
        String code = this.txtCode.getText();
        if (items.stream().anyMatch(item -> item.getCode().equals(code))){
            JOptionPane.showMessageDialog(null, "Item code duplicate!!!");
        }else {
            try{
                Item newItem = getItem(code);
                if(DAO.insertItem(newItem)) if (items.add(newItem))
                    JOptionPane.showMessageDialog(null, "Add item successfully!!");
                else
                    items = DAO.getAllItem();
                this.loadTable();
            }catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private void deleteItem(ActionEvent actionEvent) {
        int row = this.jTable1.getSelectedRow();
        if (row >= 0 && row < items.size()){
            Item deleteItem = items.get(row);
            int confirm_delete = JOptionPane.showConfirmDialog(null, "Do you want to delete item " + deleteItem.getName() + "?", "Confirm delete", JOptionPane.YES_NO_CANCEL_OPTION);
            if(confirm_delete == JOptionPane.YES_OPTION) if (DAO.deleteItem(deleteItem)) if (items.remove(deleteItem))
                JOptionPane.showMessageDialog(null, "Delete Item successfully");
            else
                items = DAO.getAllItem();
            this.loadTable();
        }
    }
    private Item getItem(String code) throws IllegalArgumentException{
        String name = this.txtName.getText();
        String unit = this.txtUnit.getText();
        double price = Double.parseDouble(this.txtPrice.getText());
        boolean supplying = this.jRadioButton1.isSelected();
        Supplier supplier = (Supplier) this.cbxSupplier.getSelectedItem();
        return new Item(code, name, unit, price, supplying, supplier);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUnit = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxSupplier = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableClick();
            }
        });

        jLabel1.setText("Item Code");

        jLabel2.setText("Item Name");


        jLabel4.setText("Unit");

        jLabel5.setText("Price");

        jLabel3.setText("Supplier");

        jRadioButton1.setText("Supplying");

        btnNew.setText("Add New");
        btnNew.addActionListener(this::newClick);

        btnSave.setText("Save");
        btnSave.addActionListener(this::save);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::deleteItem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtName)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbxSupplier)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUnit)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrice)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addContainerGap(89, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        EventQueue.invokeLater(()-> {
            Frame item = new Frame("item");
            item.add(new ItemPanel());
            item.setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<Supplier> cbxSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
