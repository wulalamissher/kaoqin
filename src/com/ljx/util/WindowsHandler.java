package com.ljx.util;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowsHandler extends WindowAdapter {

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowActivated(e);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowClosed(e);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowDeactivated(e);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowDeiconified(e);
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowGainedFocus(e);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowIconified(e);
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowLostFocus(e);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowOpened(e);
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowStateChanged(e);
	}	
}
