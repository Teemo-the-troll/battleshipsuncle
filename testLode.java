/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
//package app;

import java.util.Scanner;


public class Main
{
	public static void main(String[] args) {
		testLode t = new testLode();
		t.Run();
	}
}	

class testLode {

    void Run()
    {

        int gameWidth = 10;
        int gameHeight = 10;
        int gameShipLimit = 3;
          
        Player player1 = new Player("p1", gameShipLimit, gameWidth, gameHeight);
        Player player2 = new Player("p2", gameShipLimit, gameWidth, gameHeight);
    
        player1.AddShipToGame();
        player1.ShowShips();
        
        player1.ShowDef();
        player2.ShowDef();
        
        
        Scanner sc = new Scanner(System.in);
        //System.out.println("Which boat do you want to create? k for kriznik, l for letadlova, c for clun, p for parnik. There must be enough room for the ship!");
        char g = sc.next().charAt(0);
        System.out.println("Key: " + g);     

    }

    void ShowDesc()
    {
        System.out.println("A");
    }
    
    class Player
    {
        public String nickname;
        public GameBoard gameBoard;
        public Ship[] ships;
  
        public Player(String nickname, int shipsLimit, int gamebWidth, int gamebHeight){
            this.nickname = nickname;
            ships = new Ship[shipsLimit];
            gameBoard = new GameBoard(gamebWidth, gamebHeight);
        }

        public void AddShipToGame(){
            for(int i=0; i<ships.length; i++){
                Scanner sc = new Scanner(System.in);
                System.out.println(i + ". BOAT: Which boat do you want to create? k for kriznik, l for letadlova, c for clun, p for parnik. There must be enough room for the ship!");
                char g = sc.next().charAt(0);
                switch (g){
                    case 'k':  
                        while(true){
                            Steamer s = new Steamer(gameBoard);
                            if (s.created){
                                ships[i] = s;
                                break;
                            }
                            else{
                                System.out.println("Set new position for ship:");   
                            }
                        }
                        break;
                    //case 'l':  createLetadlova(x,y);System.out.println("Creating letadlova at x:" + x + " y:" + y); drawAim();  break;
                    //case 'c':  createClun(x,y); System.out.println("Creating clun at x:" + x + " y:" + y); drawAim(); break;
                    //case 'p':  createParnik(x, y); System.out.println("Creating parnik at x:" + x + " y:" + y); drawAim(); break;
                }
            }
        }
        
        public void ShowShips(){
            
            for(int i = 0; i < ships.length; i++){
                System.out.println("Ship " + i + ", type: " + ships[i].name + ", position: " + ships[i].position.x + "," + ships[i].position.y);   
            }
        }
      
        public void Play(){
      
        }
        
        public void ShowDef(){
            System.out.println("Player nickname:" + nickname);
        }
    }
   
    class GameBoard 
    {
        public boolean[][] board;
        public boolean[][] shoots;
        
        public GameBoard(int width, int height) 
        {
            board = new boolean[width][height]; 
            shoots = new boolean[width][height]; 
        }
        
        public int getWidth() 
        {
          return board[0].length;
        }
        
        public int getHeight()
        {
          return board[1].length;
        }
        
        public void Draw(){
            
        }
    }
   
    class Position
    {
        public int x;
        public int y;
        
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    abstract class Ship
    {
        String name;
        GameBoard gameBoard;
        Position position;
        boolean [][] shipDefinition;
        
        public void SetPosition()
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Insert new boat X position: ");
            char x = sc.next().charAt(0);
            System.out.println("Insert new boat Y position: ");
            char y = sc.next().charAt(0);
            
            //TODO Check input
            position = new Position(Character.getNumericValue(x), Character.getNumericValue(y));
        }
        
        public void DrawShip()
        {
            //TODO check shipDefinition not null
            if(IsInsideBoard() && IsFreeSpace())
            {
                for (int i = 0; i < shipDefinition[0].length; i++) {
                    for (int j = 0; j < shipDefinition[0].length; j++) {
                        gameBoard.board[position.x+i][position.y+j] = shipDefinition[i][j];
                    }
                }
            }
            else
            {
                //error handling
            }
        }
      
        boolean IsInsideBoard()
        {
            if (position.x < 0)
                return false;
          
            if (position.y < 0)
                return false;
            
            if (shipDefinition[0].length + position.x > gameBoard.getWidth())
                return false;
            
            if (shipDefinition[1].length + position.y > gameBoard.getHeight())
                return false;
          
            return true;
        }
      
        boolean IsFreeSpace()
        {
            //TODO game logic - can ships touch each other, if no then you have to change ship definition, 
            //  maybe you could switch from boolean to int (0- ship neigborhoud, 1- ship, 2- free water)
            for (int i = 0; i < shipDefinition[0].length; i++) {
                for (int j = 0; j < shipDefinition[0].length; j++) {
                    if (gameBoard.board[position.x+i][position.y+j] == true)
                        return false;            
                }
            }
         
            return true;
        }
    }
   
    class Steamer extends Ship
    {
        public boolean created = false;

        //Use Factory pattern
        public Steamer(GameBoard gameBoard) 
        {  
            this.name = "Steamer"; 
            shipDefinition = new boolean[][] {{false, true}, {true, true}, {true, true}, {false, true}};

            this.gameBoard = gameBoard;
            
            this.SetPosition();
            
            if (!this.IsInsideBoard()){
                System.out.println("Ship is outside board.");
                return;
            }
            
            if (!this.IsFreeSpace()){
                System.out.println("Not enought space to create ship.");
                return;
            }
            
            System.out.println("Creating Steamer at " + this.position.x + ", " + this.position.y);
            this.created = true;
        } 
    }
   
    class Cruiser
    { 
        //TODO def like Steamer
    }
   
    class Boat
    {
        //TODO def like Steamer
    }

}



