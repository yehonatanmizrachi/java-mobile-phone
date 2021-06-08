package blackjack.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
public class User extends Player {
	private int id;
	private double money;
	private int wins; // the amount of wins in game
	private int totalGames;
	final double initial_money = 1000;
	
	public User(int id)
	{
		super();
		this.sumOfCards = 0;
		this.id = id;
		try {
		      File myObj = new File("./app/blackjack/players/" + "player" + id + ".txt");
		      Scanner myReader = new Scanner(myObj);
		      int k = 0;
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        if (k == 0)
		        {
		        	this.money = Double.parseDouble(data);
		        	k++;
		        }
		        else if (k == 1)
		        {
		        	this.wins = Integer.parseInt(data);
		        	k++;
		        }
		        else
		        	this.totalGames = Integer.parseInt(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		    	File yourFile = new File("./app/blackjack/players/" + "player" + id + ".txt");
		    	try {
					yourFile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // if file already exists will do nothing 
		    	try {
					FileOutputStream oFile = new FileOutputStream(yourFile, false);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	this.wins = 0;
		    	this.money = initial_money;
		    	this.totalGames = 0;
		    	try {
				      FileWriter myWriter = new FileWriter("./app/blackjack/players/" + "player" + 1 + ".txt");
				      myWriter.write(this.money + "\n" + this.wins + "\n" + this.totalGames);
				      myWriter.close();
		    	} catch (IOException e1) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				}
		    }
	}
	
	public double getMoney()
	{
		return this.money;
	}
	
	public int getWins()
	{
		return this.wins;
	}
	
	public void setMoney(double money)
	{
		this.money = money;
	}
	public int getID() {
		return this.id;
	}

	@Override
	public void makeMove(DeckOfCards DC, int evSum) {
		this.addCard(DC.getCard());
		this.sumCards();
	}

	public int getTotalGames() {
		return this.totalGames;
	}
	
	public void incTotalGames()
	{
		this.totalGames++;
	}
	
}
