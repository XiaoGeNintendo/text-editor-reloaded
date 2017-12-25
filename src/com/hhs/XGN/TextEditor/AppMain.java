package com.hhs.XGN.TextEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.Border;

public class AppMain {
	public Frame fr;
	public TextArea ta;

	public void RunDll32() {
		// Set values
		Frame fr = new Frame("XGN Text Editor");
		// Menu Set
		MenuBar mb = new MenuBar();
		Menu m1 = new Menu("Files");
		Menu m2 = new Menu("Edit");
		Menu m3 = new Menu("Help");
		MenuItem m11 = new MenuItem("Import");
		m11.addActionListener(new ImportEvent());
		MenuItem m12 = new MenuItem("Export");
		m12.addActionListener(new ExportEvent());
		MenuItem m13 = new MenuItem("exit");
		m13.addActionListener(new ExitEvent());
		MenuItem m21 = new MenuItem("Clear all");
		m21.addActionListener(new ClearEvent());
		MenuItem m22 = new MenuItem("Count word");
		m22.addActionListener(new CountEvent());
		MenuItem m31 = new MenuItem("Show Helps");
		m31.addActionListener(new HelpEvent());
		MenuItem m32 = new MenuItem("About");
		m32.addActionListener(new AboutEvent());
		m1.add(m11);
		m1.add(m12);
		m1.addSeparator();
		m1.add(m13);
		m2.add(m21);
		m2.add(m22);
		m3.add(m31);
		m3.addSeparator();
		m3.add(m32);
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		fr.setLayout(new BorderLayout());
		fr.setMenuBar(mb);
		// TextArea Set
		ta = new TextArea();
		ta.setText("Welcome to XGN Text Editor");
		fr.add("Center", ta);
		// Show Frame
		fr.pack();
		fr.setVisible(true);
	}

	public static void main(String[] args) {
		AppMain fr = new AppMain();
		fr.RunDll32();
	}

	// Import Event
	class ImportEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			FileDialog fd = new FileDialog(fr, "Import from...", FileDialog.LOAD);
			fd.setVisible(true);
			if (ta.getText() != "" && fd.getFile() != null) {
				if (JOptionPane.showConfirmDialog(null, "The textarea isn't empty.Are you going to go on?", "Question",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					try {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(new FileInputStream(fd.getFile())));
						ta.setText("");
						String s = null;
						while ((s = br.readLine()) != null) {
							ta.setText(ta.getText() + "\n" + s);
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "ERROR:File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						JOptionPane.showMessageDialog(null, "ERROR:Cannot read file", "ERROR",
								JOptionPane.ERROR_MESSAGE);

					} catch (NullPointerException e) {
						// JOptionPane.showMessageDialog(null, "ERROR:You must
						// import a file!", "ERROR", JOptionPane.ERROR_MESSAGE);

					}
				}

			} else if (fd.getFile() != null) {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fd.getFile())));
					ta.setText("");
					String s = null;
					while ((s = br.readLine()) != null) {
						ta.setText(ta.getText() + "\n" + s);
					}
					br.close();
					JOptionPane.showMessageDialog(null, "Finish!", "Import", JOptionPane.INFORMATION_MESSAGE);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ERROR:File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR:Cannot read file", "ERROR", JOptionPane.ERROR_MESSAGE);

				} catch (NullPointerException e) {
					// JOptionPane.showMessageDialog(null, "ERROR:You must
					// import a file!", "ERROR", JOptionPane.ERROR_MESSAGE);

				}
			}
		}

	}

	// Export Event
	class ExportEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			FileDialog fd = new FileDialog(fr, "Export to...", FileDialog.SAVE);
			fd.setVisible(true);
			System.out.println(fd.getDirectory() + fd.getFile());
			try {
				if (fd.getFile() != null) {
					PrintWriter pw = new PrintWriter(new FileOutputStream(fd.getDirectory() + fd.getFile()));
					pw.println(ta.getText());
					pw.flush();
					pw.close();
					JOptionPane.showMessageDialog(null, "Finish!", "Export", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "ERROR:File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	// Exit Event
	class ExitEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (ta.getText() != "") {
				if (JOptionPane.showConfirmDialog(null, "The textarea isn't empty.Are you going to go on?", "Question",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					System.exit(0);
				}
			} else {
				System.exit(0);
			}
		}

	}

	// Clear Event
	class ClearEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (ta.getText() != "") {
				if (JOptionPane.showConfirmDialog(null, "Are you sure to clean the textarea?", "Question",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					ta.setText("");
				}
			}

		}

	}

	class HelpEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"Helps:\nJust like all the notepad,it's easy to use.\nFirst,import a file or write one\nNext,write something\n Then,export it!!!\n(P.s.:Use 'Edit->Clean' can clean all the textarea)",
					"HELP", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	class AboutEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"About XGN's Easy Notepad:\nBy HellHole Studios 2016 XGN\nMade In Java!!\nThank to java.awt and javax.swing!",
					"HELP", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	class CountEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Word Count:" + ta.getText().length(), "Word Count",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
