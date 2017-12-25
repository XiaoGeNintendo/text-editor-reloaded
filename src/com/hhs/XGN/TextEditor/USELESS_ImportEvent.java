package com.hhs.XGN.TextEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class USELESS_ImportEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AppMain main=new AppMain();

		main.ta.setText("Hello,World");
	}

}
