package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DAO.CodecDao;
import controller.CodecController;
import model.Codec;
import model.Company;

/**
 * the interface of codec
 */
public class CodecView {
	private JPanel panel = null;
	private JTextField textfield, textfield2, textfield3, textfield4, textfield5;
	private JLabel label, label2, label3, label4, label5, jl1;
	private JButton btnSearch = new JButton("Search Codec");
	private JButton btnAdd = new JButton("Add Codec");
	private JButton btnDelete = new JButton("Delete Codec");
	private JButton btnGetType = new JButton("Get Codec Type");
	private JButton btnGetInfo = new JButton("Get Codec Information");
	private JButton btnBack = new JButton("Back");
	Vector rowData,columnName;
	private JTable table;
	private JScrollPane JSP;

	/**
	 * Constructor used to create a Codec View.
	 * 
	 * @param panel JPanel of the main application.
	 */
	public CodecView(JPanel panel, JScrollPane JSP) {
		this.panel = panel;
		panel.add(JSP);
		panel.add(btnBack);
		initEvent_btn();
	}

	public CodecView(JPanel panel) {
		this.panel = panel;
		panel.setLayout(new FlowLayout());
		jl1 = new JLabel("Select a service");
		jl1.setPreferredSize(new Dimension(150, 100));
		jl1.setFont(new java.awt.Font("Dialog", 1, 15));
		panel.add(jl1);
	    panel.add(btnSearch);
	    panel.add(btnAdd);
//	    panel.add(btnDelete);
	    panel.add(btnGetType);
	    panel.add(btnGetInfo);   
		initEvent(); 
	}

	public CodecView(JPanel panel, String btnlabel) {
		this.panel = panel;
		panel.setLayout(new FlowLayout());
		GridBagLayout g1 = new GridBagLayout();
		panel.setLayout(g1);
		GridBagConstraints constraints = new GridBagConstraints();

		label = new JLabel("Codec Name");
		label2 = new JLabel("Dll Name");
		label3 = new JLabel("Format Name");
		label4 = new JLabel("Extension");
		label5 = new JLabel("InfoCommercial");
		textfield = new JTextField(15);
		textfield2 = new JTextField(15);
		textfield3 = new JTextField(15);
		textfield4 = new JTextField(15);
		textfield5 = new JTextField(15);
		panel.add(label);
		panel.add(textfield);
		switch(btnlabel) {
			case "Search Codec":
				panel.add(btnSearch);
				break;
			case "Add Codec":
				g1.setConstraints(label, constraints);
				constraints.gridwidth = GridBagConstraints.REMAINDER;
				g1.setConstraints(textfield, constraints);

				constraints.gridwidth = 1;
				g1.setConstraints(label2, constraints);
				constraints.gridwidth = GridBagConstraints.REMAINDER;
				g1.setConstraints(textfield2, constraints);

				constraints.gridwidth = 1;
				g1.setConstraints(label3, constraints);
				constraints.gridwidth = GridBagConstraints.REMAINDER;
				g1.setConstraints(textfield3, constraints);

				constraints.gridwidth = 1;
				g1.setConstraints(label4, constraints);
				constraints.gridwidth = GridBagConstraints.REMAINDER;
				g1.setConstraints(textfield4, constraints);

				constraints.gridwidth = 1;
				g1.setConstraints(label5, constraints);
				constraints.gridwidth = GridBagConstraints.REMAINDER;
				g1.setConstraints(textfield5, constraints);

				panel.add(label2);
				panel.add(textfield2);
				panel.add(label3);
				panel.add(textfield3);
				panel.add(label4);
				panel.add(textfield4);
				panel.add(label5);
				panel.add(textfield5);
				panel.add(btnAdd);
				break;

			case "Get Type":
				panel.add(btnGetType);
				break;
		}
		panel.add(btnBack);
		initEvent_btn();
	}

	/**
	 * Method used to initialize the view.
	 */
	private void initEvent(){ 
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				new CodecView(panel, "Search Codec");
				panel.updateUI();
	      }
	    });

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				new CodecView(panel, "Add Codec");
				panel.updateUI();
	      }
	    });
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				new CodecView(panel, "Delete Codec");
				panel.updateUI();
	      }
	    });
		
		btnGetType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				new CodecView(panel, "Get Type");
				panel.updateUI();
	      }
	    });
		
		btnGetInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnGetInfoClick(e);
	      }
	    });
	  }
	
	private void initEvent_btn(){
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnSearchClick(e);
	      }
	    });

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnAddClick(e);
	      }
	    });
		
//		btnDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			btnDeleteClick(e);
//	      }
//	    });
		
		btnGetType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnGetTypeClick(e);
	      }
	    });
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new CodecView(panel);
			panel.updateUI();
	      }
	    });
	  }
	  
	  private void btnSearchClick(ActionEvent evt){
		  Codec codec= CodecController.FindCodecByName(textfield.getText());
		  columnName = new Vector();
		  columnName.add("Codec Name");
		  columnName.add("InfoCommercial");
		  columnName.add("Dll Name");
		  rowData = new Vector();
		  Vector ligne = new Vector();
		  ligne.add(codec.getNameCodec());
		  if (codec.getCompany() != null){
			  ligne.add(codec.getCompany().getNameCompany());
		  }else {
			  ligne.add("open source");
		  }
		  ligne.add(codec.getDll().getNameDocument());
		  rowData.add(ligne);
		  table = new JTable(rowData,columnName);
		  JSP = new JScrollPane(table);
		  JSP.setPreferredSize(new Dimension(300,100));
		  panel.removeAll();
		  new CodecView(panel, JSP);
		  panel.updateUI();
	  }
	  
	  private void btnAddClick(ActionEvent evt){
		 boolean status = CodecController.AddCodec(textfield.getText(), textfield2.getText(), textfield3.getText(), textfield4.getText(), textfield5.getText());
		  if(status == true)
			  JOptionPane.showMessageDialog(null, "Add Successed!");
		  else
			  JOptionPane.showMessageDialog(null, "Add Failed!");
	  }


	  private void btnGetInfoClick(ActionEvent evt){
		  List<Codec> list = CodecController.ListAll();
		  columnName = new Vector();
		  columnName.add("Codec Name");
		  columnName.add("InfoCommercial");
		  rowData = new Vector();
		  for(int i = 0; i < list.size(); i++){
			  Vector ligne = new Vector();
			  if(list.get(i) != null){
				  System.out.println("testing14" + list.get(i).getNameCodec());
				  //System.out.println(list.get(i).getCompany().getNameCompany());
				  ligne.add(list.get(i).getNameCodec());
				  Company company = list.get(i).getCompany();
				  if(company != null){
					  ligne.add(company.getNameCompany());
					  rowData.add(ligne);
				  }
				  else {
					  ligne.add("open source");
					  rowData.add(ligne);
				  }
				}
			  else
				  ligne.add("Empty!");
			}
		  table = new JTable(rowData,columnName);
		  JSP = new JScrollPane(table);
		  JSP.setPreferredSize(new Dimension(300,100));
		  panel.removeAll();
		  new CodecView(panel, JSP);
		  panel.updateUI();
	  }
	   
	  private void btnGetTypeClick(ActionEvent evt){
		  String infoCommercial = CodecController.FindCompanyByCodec(textfield.getText());
		  JOptionPane.showMessageDialog(null, infoCommercial); 
	  }
}
