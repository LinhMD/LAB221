/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0004.ui;

import p0004.dao.BookDAO;
import p0004.dto.Book;
import p0004.error.BookInvalidException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.Objects;
import java.util.Vector;

import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

/**
 *
 * @author USER
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private final BookDAO dao = new BookDAO();
    private Vector<Book> books = new Vector<>();

    private boolean isForNew = true;

    public MainFrame() {
        this.setTitle("Book Management");
        this.setResizable(false);
        initComponents();
        loadTable();
        this.publishedYear.setModel(new SpinnerNumberModel(getInstance().get(YEAR), 0, getInstance().get(YEAR), 1));
    }

    private void loadTable(){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(Book.getHeaderData());
        books.forEach(f -> model.addRow(f.toVector()));
        this.table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void getAllButtonClick(ActionEvent event){
        try{
            this.books = dao.getAllBook();
            this.sortBySelected(event);
            this.loadTable();
        }catch(BookInvalidException b){
            JOptionPane.showMessageDialog(null, b.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
    }

    public void showBook(Book book){
        if(book == null) return;
        this.txtBookID.setText(book.getId());
        this.txtBookName.setText(book.getName());
        this.txtAuthor.setText(book.getAuthor());
        this.txtPublisher.setText(book.getPublisher());
        this.publishedYear.setValue(book.getPublishedYear());
        this.forRentCheckBox.setSelected(book.isForRent());
    }

    public void setState(boolean state){//true mean for new, false mean for update
        this.isForNew = state;
        this.txtBookID.setEnabled(state);
    }

    public void tableClick(){
        TableModel model = this.table.getModel();
        Book book = (Book) model.getValueAt(table.getSelectedRow(), 0);
        this.setState(false);
        this.showBook(book);
    }

    public void clearView(){
        this.txtBookID.setText("");
        this.txtBookName.setText("");
        this.txtAuthor.setText("");
        this.txtPublisher.setText("");
        this.publishedYear.setValue(getInstance().get(YEAR));
        this.forRentCheckBox.setSelected(true);
    }

    private Book getBookFromDisplay(){
        String id = this.txtBookID.getText();
        String name = this.txtBookName.getText();
        String author = this.txtAuthor.getText();
        String publisher = this.txtPublisher.getText();
        int year = ((int) this.publishedYear.getValue());
        boolean forRent = this.forRentCheckBox.isSelected();
//        System.out.println("here");
        try {
            return new Book(id, name, author, publisher, year +"", forRent? "1": "0");
        } catch (BookInvalidException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }

    public void addNewButtonClick(ActionEvent event){
        this.clearView();
        this.setState(true);
    }

    public void saveButtonClick(ActionEvent event){
        if(this.isForNew){
            saveNew();
        }else {
            saveUpdate();
        }
    }

    public void saveNew(){
        Book book = this.getBookFromDisplay();
        if(book == null) return;
        try{
            if(dao.insertBook(book))
                if(this.books.add(book))
                    JOptionPane.showMessageDialog(null, "Add book " + book + " successfully!");
            else JOptionPane.showMessageDialog(null, "Add book " + book + " failed!!!");
            loadTable();
        }catch(BookInvalidException b){
            JOptionPane.showMessageDialog(null, b.toString());
        }catch(Exception e){
            if(e.getMessage().contains("duplicate"))
                JOptionPane.showMessageDialog(null, "Book id: " + book.getId() + "duplicate!!!");
            else
                JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    public void saveUpdate(){
        Book book = this.getBookFromDisplay();
        if(book == null) return;
        try{
            if(dao.updateBook(book))
                if(this.books.set(books.indexOf(book), book) != null)
                    JOptionPane.showMessageDialog(null, "Update book " + book + " successfully!");
            else JOptionPane.showMessageDialog(null, "Update book " + book + " failed!!!");
            loadTable();
        }catch(BookInvalidException b){
            JOptionPane.showMessageDialog(null, b.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    
    public void findByIDClick(ActionEvent event){
        String id = this.txtBookID.getText();
        try{
            Book book = dao.findBookByID(id);
            if(book != null){
                displayFoundBook(book);
            }else{
                JOptionPane.showMessageDialog(null, "Book id: " + id +" not found!!!" );
            }
        }catch(BookInvalidException b){
            JOptionPane.showMessageDialog(null, b.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    public void findByNameClick(ActionEvent event){
        String name = this.txtSearchByName.getText();
        try{
            Book book = dao.findBookByName(name);
            if(book != null){
                displayFoundBook(book);
            }else{
                JOptionPane.showMessageDialog(null, "Book name: " + name +" not found!!!" );
            }
        }catch(BookInvalidException b){
            JOptionPane.showMessageDialog(null, b.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    private void displayFoundBook(Book book) {
        this.showBook(book);
        if(!this.books.contains(book)){
            this.books.add(book);
        }
        this.sortBySelected(null);
        this.loadTable();
        int i = books.indexOf(book);
        this.table.setRowSelectionInterval(i, i);
        this.setState(false);
    }

    public void sortBySelected(ActionEvent event){
        String sortBy = (String) sortByName.getSelectedItem();
        if(Objects.equals(sortBy, "Ascending")){
            this.books.sort(Comparator.comparing(Book::getName));
        }else
            this.books.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
        loadTable();
        
    }

    public void deleteButtonClick(ActionEvent event){
        if(this.isForNew) {
            JOptionPane.showMessageDialog(null, "Please choose a book to delete!!!");
            return;
        }
        String bookID = this.txtBookID.getText();
        if(bookID == null || bookID.trim().isEmpty()) return;
        int option = JOptionPane.showConfirmDialog(null, "Do you want to delete " + bookID + "?");
        if(JOptionPane.YES_OPTION == option){
            try{
                if (dao.deleteBookByID(bookID)){
                    this.books.remove(new Book(bookID));
                    JOptionPane.showMessageDialog(null, "Delete book ID: " + bookID + " successfully!");
                    loadTable();
                }   
                else
                    JOptionPane.showMessageDialog(null, "Delete book ID: " + bookID + " failed!");
            }catch(BookInvalidException b){
                JOptionPane.showMessageDialog(null, b.toString());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        sortByName = new javax.swing.JComboBox<>();
        txtSearchByName = new javax.swing.JTextField();
        btnSearchByName = new javax.swing.JButton();
        btnGetAll = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBookID = new javax.swing.JTextField();
        btnFindByID = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtBookName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        txtPublisher = new javax.swing.JTextField();
        publishedYear = new javax.swing.JSpinner();
        forRentCheckBox = new javax.swing.JCheckBox();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Book management");

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.setColumnSelectionAllowed(true);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tableClick();
            }
        });
        jLabel2.setText("Sort by name:");

        sortByName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        sortByName.addActionListener(this::sortBySelected);

        btnSearchByName.setText("Search By Name");
        btnSearchByName.addActionListener(this::findByNameClick);

        btnGetAll.setText("Get All Book");
        btnGetAll.addActionListener(this::getAllButtonClick);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(sortByName, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearchByName)
                .addGap(12, 12, 12)
                .addComponent(txtSearchByName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(btnGetAll, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sortByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchByName)))
                .addGap(18, 18, 18)
                .addComponent(btnGetAll)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel3.setText("Book ID");

        btnFindByID.setText("Find By ID");
        btnFindByID.addActionListener(this::findByIDClick);

        jLabel4.setText("Book Name");

        jLabel5.setText("Author");

        jLabel6.setText("Publisher");

        jLabel7.setText("Published year");

        

        forRentCheckBox.setText("For rent");
        

        btnAddNew.setText("Add new");
        btnAddNew.addActionListener(this::addNewButtonClick);

        btnSave.setText("Save");
        btnSave.addActionListener(this::saveButtonClick);

        btnRemove.setText("Remove");
        btnRemove.addActionListener(this::deleteButtonClick);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(forRentCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddNew)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBookName)
                    .addComponent(txtAuthor)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(publishedYear, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemove)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtBookID, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFindByID, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPublisher))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFindByID)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBookName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publishedYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(forRentCheckBox)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew)
                    .addComponent(btnSave)
                    .addComponent(btnRemove))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindByID;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchByName;
    private javax.swing.JButton btnAddNew;
    private javax.swing.JCheckBox forRentCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JSpinner publishedYear;
    private javax.swing.JComboBox<String> sortByName;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtBookID;
    private javax.swing.JTextField txtBookName;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtSearchByName;
    // End of variables declaration//GEN-END:variables
}
