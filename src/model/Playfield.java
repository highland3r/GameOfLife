package model;

import java.util.Observable;

public class Playfield extends Observable
{
	private boolean[][] field;
	private boolean[] neighbours = new boolean[8];
	
	
	// Konstruktor
	public Playfield (int size)
	{
		this.field = new boolean[size][size];
	}
	
	public void startSimulation() throws InterruptedException
	{
		// Endlosschleife in der nach jeder Runde das Spielfeld neu geladen wird.
		// while(true)
		for (int i = 0; i < 100; i++)
		{
			refreshField();
		}
	}
	
	// Erneuert das Spielfeld und gibt ein neues zurück
	public void refreshField()
	{
		for (int y = 0; y < this.field.length; y++)
		{
			for (int x = 0; x < this.field[y].length; x++)
			{	
				if (y != 0 || x != 0 || y != this.field.length-1 || x != this.field.length-1)
				{
					chooseAction(x, y);	
				}
			}
		} 	
	}
	
	
	// Wählt Aktion um Zelle zu töten oder wiederzubeleben
	public void chooseAction (int x, int y)
	{
		int counter = 0;
		
		for (boolean b : getNeighbours(x, y))
		{
			if (b == true)
			{
				counter++;
			}	
		}
		
		if (this.field[y][x] == true)
		{
			if (counter < 2)
			{
				lonliness(x, y);
			}
			else if (counter > 3)
			{
				overpopulation (x, y);
			}
		}
		else 
		{
			if ( counter == 3) 
			{
				reproduction(x, y);
			}
		}		
	}
		
	
	// Get neighbours
	public boolean[] getNeighbours (int x, int y)
	{
		try 
		{ 
			this.neighbours[0] = this.field[y - 1][x - 1];
			this.neighbours[1] = this.field[y - 1][x];
			this.neighbours[2] = this.field[y - 1][x + 1];
			
			this.neighbours[3] = this.field[y][x - 1];
			this.neighbours[4] = this.field[y][x + 1];
			
			this.neighbours[5] = this.field[y + 1][x - 1];
			this.neighbours[6] = this.field[y + 1][x];
			this.neighbours[7] = this.field[y + 1][x + 1];
		}
		catch (Exception e)
		{
			// System.err.println("Spielfeld zu klein.");
		}		
		return this.neighbours;
	}
	
	
	// Methoden um zu ermitteln, ob die Zelle lebt oder stirbt.
	public void reproduction (int x, int y)
	{
		this.born(x, y);
	}
	
	public void lonliness (int x, int y)
	{
		this.kill(x, y);		
	}
	
	public void overpopulation (int x, int y)
	{
		this.kill(x, y);	
	}	
	
	public boolean isAlive(int x, int y)
	{
		if (field[y][x] == false) 
		{
			return false;
		} 
		return true;			
	}
	
	// Zelle töten oder wiederbeleben
	public void born (int x, int y)
	{
		this.field[y][x] = true;
		customNotifyAll();
		
	}
	
	public void kill (int x, int y)
	{
		this.field[y][x] = false;
		customNotifyAll();
		
	}
		
	// Getter und Setter
	public boolean[][] getField()
	{
		return field;
	}	
	
	public void setField(boolean[][] field)
	{
		this.field = field;
	}
	
	public void customNotifyAll()
	{
		setChanged();
		notifyObservers(this);
	}
}
