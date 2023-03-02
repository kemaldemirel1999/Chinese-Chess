
public abstract class AbstractBoard implements BoardInterface{
	
	Item [] items;

	public AbstractBoard(){
		items = new Item[]{
				new Soldier("g1","P", 1),
				new Soldier("g3","P", 1),
				new Soldier("g5","P", 1),
				new Soldier("g7","P", 1),
				new Soldier("g9","P", 1),
				new Horse("j2","A", 4),
				new Horse("j8","A", 4),
				new Chariot("j1","K", 9),
				new Chariot("j9","K", 9),
				new Elephant("j3","F", 2),
				new Elephant("j7","F", 2),
				new Cannon("h2","T", 4.5F),
				new Cannon("h8","T", 4.5F),
				new Advisor("j4","V", 2),
				new Advisor("j6","V", 2),
				new General("j5","Ş", 0),

				new Soldier("d1","p", 1),
				new Soldier("d3","p", 1),
				new Soldier("d5","p", 1),
				new Soldier("d7","p", 1),
				new Soldier("d9","p", 1),
				new Horse("a2","a", 4),
				new Horse("a8","a", 4),
				new Chariot("a1","k", 9),
				new Chariot("a9","k", 9),
				new Elephant("a3","f", 2),
				new Elephant("a7","f", 2),
				new Cannon("c2","t", 4.5F),
				new Cannon("c8","t", 4.5F),
				new Advisor("a4","v", 2),
				new Advisor("a6","v", 2),
				new General("a5","ş", 0),

		};

	}

	
}
