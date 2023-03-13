
public class Player {

	private String player_name = null;
	private float puan; // her taş yedikçe oyuncunun puanı taşın puanına göre artar.

	private boolean isWinner;

	public Player(String player_name){
		this.player_name = player_name;
		this.puan = 0;
		this.isWinner = false;
	}

	public boolean isWinner() {return isWinner;}
	public void setWinner(boolean winner) {isWinner = winner;}
	public float getPuan() {
		return puan;
	}
	public void setPuan(float puan) {
		this.puan = puan;
	}
	public String getPlayer_name() {
		return this.player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

}
