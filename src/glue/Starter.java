package glue;
import control.Controller;
import view.View;
import model.Model;


public class Starter 
{
	public Starter()
	{
		Model myModel = new Model();
		Controller myController = new Controller(myModel);	
		
		@SuppressWarnings("unused")
		View myView = new View(myController);			
	}
	
	public static void main(String[] args) 
	{
		new Starter();
	}
}
