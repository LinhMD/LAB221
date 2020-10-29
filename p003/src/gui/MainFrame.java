/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import dao.EmployeeDAO;
import dto.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class MainFrame extends javax.swing.JFrame {
    private Vector<Employee> employees = new Vector<>();
    private boolean isForNew;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
       this.initComponents();
       this.loadTable();
       this.changeState(true);
    }

    /*
    * create new table model and set header
    * get all employee whose isDelete == false
    * add each employee.toVector as a row to table model
    * then set table model
    * */
    private void loadTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(Employee.getHeader());
//        EmpListSingleton.getInstance().stream().filter(e -> !e.isDelete()).forEach(e -> model.addRow(e.toVector()));
        employees.stream().filter(e -> !e.isDelete()).forEach(e -> model.addRow(e.toVector()));
        table.setModel(model);
    }

    /*
    * get selected employee
    * then display
    * */
    private void tableClick(){
        int selectedRow = table.getSelectedRow();
        Employee employee = (Employee) table.getModel().getValueAt(selectedRow, 0);
        this.displayEmp(employee);
        this.changeState(false);
    }

    /*
    * from user input in display panel
    * create new Employee
    * sins thy committed null thy shall get(also an display error)
    * */
    private Employee getEmployee(){
        String empID = this.txtEmpID.getText();
        String fullName = this.txtEmpFullName.getText();
        String address = this.txtEmpAddress.getText();
        String phone = this.txtEmpPhone.getText();
        String email = this.txtEmpEmail.getText();
        String dob = this.txtEmpDOB.getText();
        try{
            return new Employee(empID,fullName,address,phone,email,dob);
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }
    /*
    * take para as an employee(nullable)
    * then display it to display panel
    * */
    private void displayEmp(Employee employee){
        //always null checking
        if(employee == null) return;

        this.txtEmpID.setText(employee.getEmpID());
        this.txtEmpFullName.setText(employee.getFullName());
        this.txtEmpAddress.setText(employee.getAddress());
        this.txtEmpPhone.setText(employee.getPhone());
        this.txtEmpEmail.setText(employee.getEmail());
        this.txtEmpDOB.setText(employee.getDOB());
    }

    /*
    * change state save for new(true)/update(false)
    * */
    private void changeState(boolean state){
        this.txtEmpID.setEnabled(state);
        this.isForNew = state;
    }

    /*
    * when click new btn
    * change state to save for new
    * and clear display panel by throw at it a half-baked Employee
    * */
    private void btnNewClick(ActionEvent event){
        this.changeState(true);
        this.displayEmp(new Employee());
    }

    private void btnSaveClick(ActionEvent event){
        if(this.isForNew)
            saveNew();
        else
            saveUpdate();
    }

    private void btnFindEMpByIDClick(ActionEvent event){
        Employee employee = EmployeeDAO.findEmployee(this.txtEmpID.getText());
        if(employee != null){
            if(!employees.contains(employee)){
                employees.add(employee);
            }else {
                int index = employees.indexOf(employee);
                table.setRowSelectionInterval(index, index);
            }
            this.displayEmp(employee);
        }
    }

    private void saveNew(){
        Employee employee = this.getEmployee();
        if(employee == null) return;
        if(EmployeeDAO.findEmployee(employee.getEmpID()) == null){//not found
            if(EmployeeDAO.insertEmployee(employee)){
                this.employees.add(employee);
                JOptionPane.showMessageDialog(null, "Add " + employee + " successfully.");
                this.loadTable();
            }else //somehow add failed?
                JOptionPane.showMessageDialog(null, "Add employee" + employee + " failed!!!");
        }else //found
            JOptionPane.showMessageDialog(null, "EmpID duplicate!!!");
    }

    private void saveUpdate(){
        Employee employee = this.getEmployee();
        if(employee == null) return;
        if(EmployeeDAO.updateEmployee(employee)){
            this.employees.set(employees.indexOf(employee), employee);
            JOptionPane.showMessageDialog(null, "Update " + employee + " successfully.");
        }else
            JOptionPane.showMessageDialog(null, "Update " + employee + " Failed!!!");

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
        txtEmpEmail = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnFindEmpByID = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEmpAddress = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtEmpID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmpFullName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmpPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmpDOB = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Employee Management");

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
                tableClick();
            }
        });
        btnGetAll.setText("Get All");

        jLabel4.setText("Email");

        btnCreate.setText("Create");

        btnSave.setText("Save");

        btnDelete.setText("Delete");

        btnFindEmpByID.setText("Find by EmpID");

        jLabel5.setText("Address");

        txtEmpAddress.setColumns(20);
        txtEmpAddress.setRows(5);
        jScrollPane2.setViewportView(txtEmpAddress);

        jLabel2.setText("EmpID");

        jLabel3.setText("Full Name");

        jLabel6.setText("Phone");

        jLabel8.setText("DOB");

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
                            .addComponent(txtEmpFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnFindEmpByID))
                            .addComponent(txtEmpPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmpDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtEmpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFindEmpByID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmpID))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmpFullName)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtEmpPhone))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmpEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnSave)
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
                .addGap(0, 33, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
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





   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFindEmpByID;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnSave;
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
    private javax.swing.JTextArea txtEmpAddress;
    private javax.swing.JTextField txtEmpDOB;
    private javax.swing.JTextField txtEmpEmail;
    private javax.swing.JTextField txtEmpFullName;
    private javax.swing.JTextField txtEmpID;
    private javax.swing.JTextField txtEmpPhone;
    // End of variables declaration//GEN-END:variables
}
