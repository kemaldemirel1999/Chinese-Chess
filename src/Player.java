
public class Player {

	private String player_name = null;
	private float puan; // her taş yedikçe oyuncunun puanı taşın puanına göre artar.

	public Player(String player_name){
		this.player_name = player_name;
		this.puan = 0;
	}
	public float getPuan() {
		return puan;
	}
	public void setPuan(float puan) {
		this.puan = puan;
	}
	public void increaseScore(float puan){
		this.puan = this.puan + puan;
	}

	public String getPlayer_name() {
		return this.player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}






}
