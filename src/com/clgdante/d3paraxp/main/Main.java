package com.clgdante.d3paraxp.main;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.clgdante.d3paraxp.database.DataBase;
import com.clgdante.d3paraxp.gui.MainFrame;

public class Main {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int S_WIDTH = 1920;
	private static final int S_HEIGHT = 1080;
	/** Center the window on the second monitor (to the right of the primary) */
	public static final Point CSM = new Point(S_WIDTH + S_WIDTH / 2 - WIDTH / 2, S_HEIGHT
			/ 2 - HEIGHT / 2);

	public static void main(String args[]) {
		DataBase db = new DataBase();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final MainFrame mainFrame = new MainFrame(db);
				mainFrame.setTitle("Paragon Level Converter");
				mainFrame.setResizable(false);
				// mainFrame.setSize(new Dimension(WIDTH, HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				// mainFrame.setLocation(CSM);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});

		// System.out.println(db.lookupPLvl(198092759999L)); // returns 428
		// System.out.println(db.lookupPLvl(198092760000L)); // returns 429
		// System.out.println(db.lookupTotalExp(70)); // returns 4,060,800,000
		// System.out.println(db.lookupTotalExp(409)); // returns 172,980,360,000
	}
}
