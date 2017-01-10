package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import control.Controller;


public class View extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private MyPanel panel;
	
	
	public View(Controller controller)
	{
		this.controller = controller;
		
		controller.setView(this);
		
		JButton button = new JButton("Wenn du eine Startformation gewählt hast, klicke hier um die Simulation zu starten.");
		button.addActionListener(this);
		
		panel = new MyPanel(controller);
		
		this.getContentPane() .add(BorderLayout.CENTER, panel);
		this.getContentPane() .add(BorderLayout.SOUTH, button);
		
	    this.setSize(1100, 1150);
	    this.setTitle("Game of Life");
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);	  
	}
	
	
	// Getter und Setter – Controller
	public Controller getController()
	{
		return controller;
	}

	public void setController(Controller controller)
	{
		this.controller = controller;
	}

	
	// Getter und Setter – Panel
	public MyPanel getPanel()
	{
		return panel;
	}

	public void setPanel(MyPanel panel)
	{
		this.panel = panel;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			this.controller.getModel().getPlayfield().startSimulation();
		} 
		catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
	}
}
