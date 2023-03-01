
public abstract class AbstractBoard implements BoardInterface{
	
	Item [] items;

	public AbstractBoard(){
		items = new Item[]{
				new Soldier("g1","E"),
				new Soldier("g3","E"),
				new Soldier("g5","E"),
				new Soldier("g7","E"),
				new Soldier("g9","E"),
				new Horse("j2","A"),
				new Horse("j8","A"),
				new Chariot("j1","K"),
				new Chariot("j9","K"),
				new Elephant("j3","F"),
				new Elephant("j7","F"),
				new Cannon("h2","T"),
				new Cannon("h8","T"),
				new Advisor("j4","V"),
				new Advisor("j6","V"),
				new General("j5","Ş"),

				new Soldier("d1","e"),
				new Soldier("d3","e"),
				new Soldier("d5","e"),
				new Soldier("d7","e"),
				new Soldier("d9","e"),
				new Horse("a2","a"),
				new Horse("a8","a"),
				new Chariot("a1","k"),
				new Chariot("a9","k"),
				new Elephant("a3","f"),
				new Elephant("a7","f"),
				new Cannon("c2","t"),
				new Cannon("c8","t"),
				new Advisor("a4","v"),
				new Advisor("a6","v"),
				new General("a5","ş"),

		};

	}

	
}
