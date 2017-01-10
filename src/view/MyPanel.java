package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import control.Controller;

public class MyPanel extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	Controller controller;
	
	
	// Konstruktor
	public MyPanel(Controller controller)
	{
		this.controller = controller;
		this.controller.getModel().getPlayfield().addObserver(this);
		
		this.addMouseListener(new MouseAdapter() 
		{
			@Override
            public void mousePressed(MouseEvent e) 
			{
				// Position of mouse
				double frameX = controller.getView().getLocationOnScreen().getX();
				double frameY = controller.getView().getLocationOnScreen().getY();	
				
				double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
				double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
				
				int x = (int) (mouseX - frameX) / 11;
				int y = (int) ((mouseY - frameY) / 11) - 2;
				
				// clicked on cell set to alive
				controller.getModel().getPlayfield().born( x, (int) y);
            }
        });
	}
	
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, controller.getView().getWidth(), controller.getView().getHeight());	
		
		for (int y = 0; y < controller.getModel().getPlayfield().getField().length; y++)
		{
			for (int x = 0; x < controller.getModel().getPlayfield().getField()[y].length; x++)
			{	
				if (controller.getModel().getPlayfield().isAlive(x, y))
				{
					g.setColor(Color.white);
					g.fillRect(x + (x * 10), y + (y * 10), 10, 10);	
				}
				else 
				{
					g.setColor(Color.black);
					g.fillRect(x + (x * 10), y + (y * 10), 10, 10);	
				}
			}
		} 
	}

	@Override
	public void update(Observable o, Object arg)
	{
		revalidate();
		repaint();
	}	
}
