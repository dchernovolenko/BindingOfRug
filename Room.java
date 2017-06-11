import java.util.Scanner;
//import java.util.Stack;
import lib.*;
public class Room implements Comparable{
	//Instance variables
	private Tile[][] _room;
	private Tile _playerTile;
	private int _row;
	private int _col;
	private Player _player;
	private boolean _isDone; //to check if room is completed.
	private ArrayQueue<Monster> _monsterQueue; //a queue of multiple or one monster

	//constructor for a not same dimension room (length != width)
	public Room(int row, int column){
		//creates new player
		_player = new Player();
		_playerTile = new Tile(_player, 0,0);
		//create a new monster(s)
		_monsterQueue = new ArrayQueue<Monster>();
		for (int i = 0; i <  (1 + (int) (Math.random() * 3)); i++){
			Monster enemy = new Monster();
			_monsterQueue.enqueue(enemy);
		}
		//creates the room
		_room = new Tile[row][column];
		//just some holder variables
		_row = row;
		_col = column;
		_isDone = false;

		//creates the room
		for(int r = 0; r < _room.length; r++){
			for(int c = 0; c < _room[r].length; c++){
				_room[r][c] = new Tile(r,c);
			}
		}
	}

	//constructor for a square room
	public Room(int square){
		//creates new player
		_player = new Player();
		_playerTile = new Tile(_player, 0,0);
		//create a new monster
		_monsterQueue = new ArrayQueue<Monster>(1 + (int) (Math.random() * 3));
		//creates the queue of monsters
		for (int i = 0; i < _monsterQueue.size(); i++){
			Monster enemy = new Monster();
			_monsterQueue.enqueue(enemy);
		}
		//some holder variables
		_row = square;
		_col = square;
		_room = new Tile[square][square];
		_isDone = false;
		//put Tiles into the _room
		for(int r = 0; r < _room.length; r++){
			for(int c = 0; c < _room[r].length; c++){
				_room[r][c] = new Tile(r,c);
			}
		}
	}

	//displays the room
	public void print(){
		for(Tile[] row : _room){
			for(Tile pos: row){
				System.out.print(pos.getChar());
			}
			System.out.println();
		}
	}

	//get player
	public Player getPlayer(){
		return _player;
	}
	//update the current Player stats into the room.
	public void updatePlayer(Player player){
		_player = player;
	}

	//return if the Room is done.
	public Boolean checkDone(){
		return _isDone;
	}

	public void setDone(Boolean value){
		_isDone = value;
	}
	//asks a player to move
	public void askPlayerMove(){
		Scanner s = new Scanner(System.in);
		System.out.println("Input a direction");
		String input = s.next();
		//Check if a direction is inputed.
		//If not loop again3
		while ("wasd".indexOf(input) < 0){
			System.out.println("Invalid direction. Input a direction as w,a,s,or d.");
			input = s.next();;
		}
		checkMove(input);
	}

	/*
	CHANGE TILES THING
	*/
	public void message(String message){
		System.out.println(message);
	}

	public Boolean isValidMove(String input){
		int row = _playerTile.getRow();
		int col = _playerTile.getCol();
		Tile destination = _playerTile;

		if (input.equals("w")){
			if (row - 1 < 0)
			return false;
			//destination =  _room[row - 1][col];
		}
		if (input.equals("a")){
			//destination = _room[row][col - 1];
			if (col - 1 < 0)
			return false;
		}
		if(input.equals("s")){
			//destination = _room[row + 1][col];
			if (row + 1 > _row - 1)
			return false;
		}
		if(input.equals("d")){
			//destination = _room[row][col + 1];
			if (col + 1 > _col - 1)
			return false;
		}
		if (destination.getRow() < 0 || destination.getRow() > _row ||
		destination.getCol() < 0 || destination.getCol() > _col){
			return false;
		}
		return true;
	}
	public void checkMove(String input){
		String dir = _playerTile.getChar();
		int row = _playerTile.getRow();
		int col = _playerTile.getCol();
		if(input.indexOf("x") == 0){
			//((Player)(_room[row][col].getEntity())).checkAttack();
			input = input.substring(1,2);
			if (!isValidMove(input)){
				message("You cannot move there");
			}
			else if (input.equals("w")){
				attack(_room[row][col], _room[row - 1][col]);
			}
			else if (input.equals("a")){
				attack(_room[row][col], _room[row][col - 1]);
			}
			else if(input.equals("s")){
				attack(_room[row][col], _room[row + 1][col]);
			}
			else{ //when input is "d"
			attack(_room[row][col], _room[row][col + 1]);
		}
	}
	else if (!isValidMove(input)){
		message("You cannot move there");
	}
	else if (input.equals("w")){
		move(_room[row][col], _room[row - 1][col]);
	}
	else if (input.equals("a")){
		move(_room[row][col], _room[row][col - 1]);
	}
	else if(input.equals("s")){
		move(_room[row][col], _room[row + 1][col]);
	}
	else{ //when input is "d"
	move(_room[row][col], _room[row][col + 1]);
}
}

public void move(Tile origin, Tile destination){
	//GOAL: swap the tiles + check walls + check if out of bounds

	//Check if out of bound
	if (destination.getRow() < 0 || destination.getRow() > _row ||
	destination.getCol() < 0 || destination.getCol() > _col){
		return;
	}

	//Check if destination is empty
	if(destination.getChar() == "_"){
		//SWAP ENTITIES
		int oRow = origin.getRow();
		int oCol = origin.getCol();
		int dRow = destination.getRow();
		int dCol = destination.getCol();

		Creature temp = origin.getEntity();
		_room[oRow][oCol].setEntity(destination.getEntity());
		_room[dRow][dCol].setEntity(temp);
		/*
		origin.setEntity(destination.getEntity());
		destination.setEntity(temp);
		*/
		_playerTile = destination;
	}

	//Check if destination is a wall
	else if(destination.getChar() == "█"){
		System.out.println("You cannot move there");
		return;
	}

	//do stuff if destination has an entity
	else{
		//ATTACK HERE <NOT DONE>
		attack(origin, destination);
		return; //PROBABLY CHANGE
	}
}

public void attack(Tile attacker, Tile receiver){
	int aRow = attacker.getRow();
	int aCol = attacker.getCol();
	int rRow = receiver.getRow();
	int rCol = receiver.getCol();
	_room[rRow][rCol].getEntity().takeDamage(attacker.getEntity().dealDamage());
	if (!checkDeath(receiver)){
		_room[aRow][aCol].getEntity().takeDamage(attacker.getEntity().dealDamage());
		checkDeath(attacker);
	}

}

public boolean checkDeath(Tile origin){
	if(origin.getEntity().isDead()){
		_room[origin.getRow()][origin.getCol()].setEntity(null);
		return true;
	}
	return false;
}
//asks the Monster to move

public boolean checkMonsterClear(){
	return (_monsterQueue == null);
}

public void askMonsterMove(){
	//NOT DONE
}

public ArrayQueue<Monster> getMonsters(){
	return _monsterQueue;
}
//Comparable
public int compareTo(Object otherObject){
	Room otherRoom = (Room)otherObject;
	if(_monsterQueue.size() < otherRoom.getMonsters().size())
	return -1;
	else if(_monsterQueue.size() == otherRoom.getMonsters().size())
	return 0;
	return 1;
}

}
