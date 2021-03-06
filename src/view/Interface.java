package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class Interface{

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Video Management System");
		frame.setResizable(false);
		frame.setSize(800,400);
//		frame.setBounds(500, 300, 400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		/** Video View **/
		JPanel video = new JPanel();
		new VideoView(video);
		tabbedPane.addTab("Video", null, video, null);
		
		/** Player View **/
		JPanel player = new JPanel();
		new PlayerView(player);
		tabbedPane.addTab("Player", null, player, null);
		
		/** Codec View **/
		JPanel codec = new JPanel();
		new CodecView(codec);
		tabbedPane.addTab("Codec", null, codec, null);
		
		frame.setVisible(true);
	}
}
