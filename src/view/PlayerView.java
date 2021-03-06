package view;

import controller.PlayerController;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

/**
 * the interface of player
 */
public class PlayerView extends JFrame {
	private JPanel panel = null;
	private JTextField jt1, jt2, jt3, jt4, jt10, jt11,jt12,jt13;
	private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl13,jl14,jl15;
	private JButton service1, service2, service3, service4,serviceFind1, back,jb1, jb2, jb3, jb4,jb5,jb6,jb7,jb8,jb9,jb10,jb11;
	private Vector rowData, columnName;
	private JTable table1,table2,table3;
	private JScrollPane JSP;
	/**
	 * Constructor used to create an EnglishClock View.
	 *
	 * @param panel JPanel of the main application.
	 */
	public PlayerView(JPanel panel) {
		this.panel = panel;
		initialize();
	}

	/**
	 * Method used to initialize the view.
	 */
	public void initialize() {
		jl1 = new JLabel("Select a service");
		jl1.setPreferredSize(new Dimension(200, 100));
		jl1.setFont(new Font("Dialog", 1, 15));
		panel.setLayout(new FlowLayout());
		service1 = new JButton("search");
		service2 = new JButton("add");
		service3 = new JButton("delete");
		service4 = new JButton("get information");
		serviceFind1 = new JButton("funtion question1");
		panel.add(jl1);
		panel.add(service1);
		panel.add(service2);
		panel.add(service3);
		panel.add(service4);
		panel.add(serviceFind1);
		initevent();
	}

	public void initevent() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		service1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clickserach(e);
			}
		});
		service2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clickadd(e);
			}
		});
		service3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Clickdelete(e);
			}
		});
		service4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {ClickGetInfo(e);}
		});
		serviceFind1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClickFuntion1(e);
			}
		});
	}

	public void Clickserach(ActionEvent event) {
		panel.removeAll();
		panel.repaint();
		jl14 = new JLabel("please enter the name of player");
		jt1 = new JTextField(15);
		jb1 = new JButton("search");
		back = new JButton("back");
		panel.add(jl14);
		panel.add(jt1);
		panel.add(jb1);
		panel.add(back);
		listenAct();
	}

	public void Clickadd(ActionEvent event) {
		panel.removeAll();
		panel.repaint();
		GridBagLayout g1 = new GridBagLayout();
		panel.setLayout(g1);
		GridBagConstraints constraints = new GridBagConstraints();
		//constraints.fill = GridBagConstraints.BOTH;

		jl5 = new JLabel("namePlayer");
		jl6 = new JLabel("nbKoctets");
		jl7 = new JLabel("nameFormat");
		jt2 = new JTextField(15);
		jt3 = new JTextField(15);
		jt4 = new JTextField(15);
		jb3 = new JButton("addFormat");
		jb4 = new JButton("addCodec");
		jb5 = new JButton("back");
		jb2 = new JButton("add");
		g1.setConstraints(jl5, constraints);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		g1.setConstraints(jt2, constraints);

		constraints.gridwidth = 1;
		g1.setConstraints(jl6, constraints);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		g1.setConstraints(jt3, constraints);

		constraints.gridwidth = 1;
		g1.setConstraints(jl7, constraints);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		g1.setConstraints(jt4, constraints);
		g1.setConstraints(jb3, constraints);

		g1.setConstraints(jb4, constraints);

		constraints.gridwidth = 1;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		jb2 = new JButton("add");

		panel.add(jl5);
		panel.add(jt2);
		panel.add(jl6);
		panel.add(jt3);
		panel.add(jl7);
		panel.add(jt4);
		panel.add(jb3);


		panel.add(jb2);
		panel.add(jb5);
		initListenAddAct();
	}

	public void Clickdelete(ActionEvent event){
		panel.removeAll();
		panel.repaint();
		jl13 = new JLabel("please enter the name of the player");
		jt12 = new JTextField(15);
		jb6 = new JButton("delete");
		jb7 = new JButton("back");
		panel.add(jl13);
		panel.add(jt12);
		panel.add(jb6);
		panel.add(jb7);
		initListenDelAct();
	}

	public void ClickGetInfo(ActionEvent event){
		List<Player> list = PlayerController.ListAllPlayers();
		columnName = new Vector();
		columnName.add("name of the player");
		columnName.add("nbKoctets");
		rowData = new Vector();
		for(int i = 0; i < list.size(); i++){
			Vector ligne = new Vector();
			if(list.get(i) != null){
				ligne.add(list.get(i).getNamePlayer());
				ligne.add(list.get(i).getNbKoctets());
				rowData.add(ligne);
			}
			else
				ligne.add("Empty!");
		}
		table2 = new JTable(rowData,columnName);
		JSP = new JScrollPane(table2);
		panel.removeAll();
		panel.repaint();
		jb8 = new JButton("back");
		panel.add(JSP);
		panel.add(jb8);
		initListenGetInfoAct();
	}

	public void ClickFuntion1(ActionEvent event){
		jl15 = new JLabel("please enter the name of format");
		jt13 = new JTextField(15);
		jb9 = new JButton("search");
		jb10 = new JButton("back");
		panel.removeAll();
		panel.repaint();
		panel.add(jl15);
		panel.add(jt13);
		panel.add(jb9);
		panel.add(jb10);
		initFuntion1Act();
	}

	public void initFuntion1Act(){
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		jb9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnFunction1Click(e);
			}
		});
		jb10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backClick(e);
			}
		});
	}

	public void btnFunction1Click(ActionEvent event){
		String name = jt13.getText();
		List<String> result = PlayerController.FindPlayerByForamt(name);
		columnName = new Vector();
		columnName.add("format");
		columnName.add("player");
		rowData = new Vector();
		for(int i = 0; i < result.size(); i++){
			Vector ligne = new Vector();
			if(result.get(i) != null){
				ligne.add(name);
				ligne.add(result.get(i));
				rowData.add(ligne);
			}
		}
		table3 = new JTable(rowData,columnName);
		JSP = new JScrollPane(table3);
		jb11 = new JButton("back");
		panel.removeAll();
		panel.repaint();
		panel.add(JSP);
		panel.add(jb11);
		listenToback();
	}

	public void listenToback(){
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		jb11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backClick(e);
			}
		});
	}

	public void listenAct() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchClick(e);
			}
		});
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backClick(e);
			}
		});
	}

	public void btnSearchClick(ActionEvent event) {
		Player infoPlayer = PlayerController.FindPlayerByName(jt1.getText());
		columnName = new Vector();
		columnName.add("name");
		columnName.add("nbKoctets");
		rowData = new Vector();
		Vector ligne = new Vector();
		ligne.add(infoPlayer.getNamePlayer());
		ligne.add(infoPlayer.getNbKoctets());
		rowData.add(ligne);
		table1 = new JTable(rowData,columnName);
		JSP = new JScrollPane(table1);
		panel.removeAll();
		panel.repaint();
		panel.add(JSP);
		panel.add(back);
	}

	public void backClick(ActionEvent event) {
		panel.removeAll();
		panel.repaint();
		new PlayerView(this.panel);
	}

	public void initListenDelAct(){
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		jb6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteClick(e);
			}
		});
		jb7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backClick(e);
			}
		});
	}

	public void deleteClick(ActionEvent event){
		String namePlayer = jt12.getText();
		PlayerController.DelPlayer(namePlayer);
	}
	public void initListenAddAct() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddPlayer(e);
			}
		});

		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddFormatClick(e);
			}
		});
		jb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backClick(e);
			}
		});

	}

	public void initListenGetInfoAct(){
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		jb8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backClick(e);
			}
		});
	}

	public void AddPlayer(ActionEvent event) {
		String namePlayer = jt2.getText();
		String nbKoctetsString = jt3.getText();
		String nameFormat = jt4.getText();
		PlayerController.AddPlayer(namePlayer, nbKoctetsString, nameFormat);
	}

	public void AddFormatClick(ActionEvent event){

	}
}

