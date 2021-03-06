package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DAO.CodecDao;
import DAO.DllDao;
import DAO.FormatDao;
import DAO.VideoDao;
import controller.VideoController;
import model.Codec;
import model.Format;
import model.Video;

/**
 * the interface of video
 */
public class VideoView extends JFrame{
	private JPanel panel;
	private JTextField textfield, textfield2, textfield3;
	private JLabel label, label2, label3, jl1;
	private JButton btnSearch = new JButton("Search Video");
	private JButton btnAdd = new JButton("Add Video");
	private JButton btnDelete = new JButton("Delete Video");
	private JButton btnGetInfo = new JButton("Get Video Information");
	private JButton btnBack = new JButton("Back");

	private VideoDao videodao= new VideoDao();
	Vector rowData,columnName;
	private JTable table;
	private JScrollPane JSP;

	/**
	 * Constructor used to create an Video View.
	 * 
	 * @param panel JPanel of the main application.
	 */
	public VideoView(JPanel panel, JScrollPane JSP) {
		this.panel = panel;
		panel.add(JSP);
		panel.add(btnBack);
		initEvent_btn();
	}
	
	public VideoView(JPanel panel) {
		this.panel = panel;
		panel.setLayout(new FlowLayout());
		jl1 = new JLabel("Select a service");
		jl1.setPreferredSize(new Dimension(150, 100));
		jl1.setFont(new java.awt.Font("Dialog", 1, 15));
		panel.add(jl1);
	    panel.add(btnSearch);
	    panel.add(btnAdd);
	    panel.add(btnDelete);
	    panel.add(btnGetInfo);   
		initEvent();
	}
	public VideoView(JPanel panel, String btnlabel) {
		this.panel = panel;
		panel.setLayout(new FlowLayout());
		GridBagLayout g1 = new GridBagLayout();
		panel.setLayout(g1);
		GridBagConstraints constraints = new GridBagConstraints();
		label = new JLabel("Video Name");
		textfield = new JTextField(15);
		label2 = new JLabel("Format name");
		textfield2 = new JTextField(15);
		label3 = new JLabel("Extension");
		textfield3 = new JTextField(15);

		panel.add(label);
		panel.add(textfield);
		switch(btnlabel) {
			case "Search Video":
				panel.add(btnSearch);
				break;
			case "Add Video":
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

				panel.add(label2);
				panel.add(textfield2);
				panel.add(label3);
				panel.add(textfield3);
				panel.add(btnAdd);
				break;
			case "Delete Video":
				panel.add(label3);
				panel.add(textfield3);
				panel.add(btnDelete);
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
				new VideoView(panel, "Search Video");
				panel.updateUI();
	      }
	    });

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				new VideoView(panel, "Add Video");
				panel.updateUI();
	      }
	    });
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				new VideoView(panel, "Delete Video");
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
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnDeleteClick(e);
	      }
	    });
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new VideoView(panel);
			panel.updateUI();
	      }
	    });
	  }
	  
	  private void btnSearchClick(ActionEvent evt){
		  List<Video> list = VideoController.FindVideoBYNameVideo(textfield.getText());
		  columnName = new Vector();
		  columnName.add("idVideo");
		  columnName.add("Video Name");
		  columnName.add("Format Name");
		  columnName.add("Extension");
		  rowData = new Vector();
		  for(int i = 0; i<list.size(); i++){
			  Vector ligne = new Vector();
			  if(list.get(i) != null){
				  ligne.add(list.get(i).getId());
				  ligne.add(list.get(i).getNameVideo());
				  ligne.add(list.get(i).getNameFormat().getNameFormat());
				  ligne.add(list.get(i).getNameFormat().getExtension());
				  rowData.add(ligne);
				}
			}
		  table = new JTable(rowData,columnName);
		  JSP = new JScrollPane(table);
		  JSP.setPreferredSize(new Dimension(600,200));
		  panel.removeAll();
		  new VideoView(panel, JSP);
		  panel.updateUI();
	  }
	  
	  private void btnAddClick(ActionEvent evt){
		  System.out.println("testing1");
		  String status = videodao.AddVideoBYFormatInfo(textfield.getText(), textfield2.getText());
		  switch (status){
		  case "Add Succeed!":
			  System.out.println("testing2");
			  JOptionPane.showMessageDialog(null, status);
			  break;
		  case "Format dosen't exist!":
			  System.out.println("testing3");
			  String dllname = JOptionPane.showInputDialog("Please add a Dll name: ");
			  if(dllname == null)
				  JOptionPane.showMessageDialog(null, "Dllname is empty!");
			  else {
				  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sgbd");
				  EntityManager em = entityManagerFactory.createEntityManager();
				  DllDao dlldao = new DllDao();
				  CodecDao codecdao = new CodecDao();
				  FormatDao formatdao = new FormatDao();
				  formatdao.AddFormat2(textfield2.getText(), textfield3.getText());
				  Format format = new Format(textfield2.getText(), textfield3.getText());
				  dlldao.AddDll2(dllname, format);
				  String codecname = JOptionPane.showInputDialog("Please add a Codec name: ");
				  if(codecname == null)
					  JOptionPane.showMessageDialog(null, "Codecname is empty!");
				  else {
					  String InfoCommercial = JOptionPane.showInputDialog("Please add InfoCommercial of Codec: ");
					  if(InfoCommercial == null)
						  JOptionPane.showMessageDialog(null, "InfoCommercial is empty!");
					  else {
						  em.getTransaction().begin();
						  Video new_video = new Video(textfield.getText(), format);
						  em.persist(new_video);
						  em.getTransaction().commit();
						  boolean test = codecdao.AddCodec(codecname, dllname, textfield2.getText(), textfield3.getText(), InfoCommercial);
						  if(test == true)
							  JOptionPane.showMessageDialog(null, "Add Succeed!");
						  else
							  JOptionPane.showMessageDialog(null, "Add Failed!");
					  }
					  	
				  }
				  break;
			  }
		  }
	  }
	  
	  private void btnDeleteClick(ActionEvent evt){
		String status = VideoController.DelVideo(textfield.getText(), textfield3.getText());
		  JOptionPane.showMessageDialog(null, status);
	  }

	  private void btnGetInfoClick(ActionEvent evt){
		  List<Video> list = VideoController.ListAll();
		  columnName = new Vector();
		  columnName.add("idVideo");
		  columnName.add("Video Name");
		  columnName.add("Format Name");
		  columnName.add("Extension");
		  rowData = new Vector();
		  for(int i = 0; i<list.size(); i++){
			  Vector ligne = new Vector();
			  if(list.get(i) != null){
				  ligne.add(list.get(i).getId());
				  ligne.add(list.get(i).getNameVideo());
				  ligne.add(list.get(i).getNameFormat().getNameFormat());
				  ligne.add(list.get(i).getNameFormat().getExtension());
				  rowData.add(ligne);
				}
			}
		  table = new JTable(rowData,columnName);
		  JSP = new JScrollPane(table);
		  JSP.setPreferredSize(new Dimension(600,200));
		  panel.removeAll();
		  new VideoView(panel, JSP);
		  panel.updateUI();
	  }
}
