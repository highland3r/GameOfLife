package control;

import view.View;
import model.Model;


public class Controller 
{
	private View view;
	private Model model;
	
	public Controller(Model model)
	{
		this.model = model;
	}
	
	public void setView(View view)
	{
		this.view = view;
	}
	
	public View getView()
	{
		return this.view;
	}

	public Model getModel()
	{
		return model;
	}

	public void setModel(Model model)
	{
		this.model = model;
	}
}
