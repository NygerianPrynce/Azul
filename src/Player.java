
 class Player {
 int playerNumber; 
 boolean isStarter;
 PlayerWall pW;
 PlayerLine pL; 
Scoring score;
    public Player(int pNumber){
        playerNumber = pNumber;
        pW = new PlayerWall();
        pL = new PlayerLine(); 
        if(isStarter){
            pL.addStarterTile();
        }
        score = new Scoring(this);
    }
    //get player wall
    public PlayerWall getPlayerWall(){
        return pW;
    }
    //get player line
    public PlayerLine getPlayerLine(){
        return pL;
    }
    //get score
    public Scoring getScore(){
        return score;
    }
    //get player number
    public int getPlayerNumber(){
        return playerNumber;
    }
    //make starter
    public void makeStarter(){
        isStarter = true;
    }
    //check if starter
    public boolean isStarter(){
        return isStarter;
    }
}
