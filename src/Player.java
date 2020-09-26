
public class Player {
	
	private String name= "";
	private int points = 0;
	private int rank = 0;
	public Player(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}
	
	

}
