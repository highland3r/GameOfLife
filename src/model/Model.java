package model;

import java.util.Observable;

public class Model extends Observable
{
	private Playfield field;
	
	// Konstruktor
	public Model()
	{
		this.field = new Playfield(100);
	}

	// Getter und Setter
	public Playfield getPlayfield()
	{
		return field;
	}
	
}
