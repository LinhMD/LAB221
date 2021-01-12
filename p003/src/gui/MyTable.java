package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MyTable extends JTable {
	public MyTable() {
		this.setModel(new DefaultTableModel(new Vector<Vector<String>>(4),new Vector<>(4) ){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		this.setColumnSelectionAllowed(false);
		this.getTableHeader().setReorderingAllowed(false);
		this.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}
}
