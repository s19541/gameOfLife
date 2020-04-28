public class GameOfLife {
    boolean[][] board;
    //czas pomiedzy generacjami w sekundach
    double breakTime=1;
    GameOfLife(boolean[][]boardTab){
        this.board =boardTab;
        gameOfLoop();
    }
    void gameOfLoop(){
        new Thread(()->{
            while(true){
                Gui.refreshButtons(board);
                board=createNextGeneration();
                try {
                    Thread.sleep((int) (breakTime * 1000));
                }catch(InterruptedException e){}
            }
        }).start();
    }
    void showBoardOnConsole(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(!board[i][j])
                    System.out.print("O");
                else
                    System.out.print("X");
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private boolean[][] createNextGeneration(){
        boolean[][]newBoard=new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int neighobors=neighborCount(i,j);
                if(!board[i][j]){
                    if(neighobors==3)
                        newBoard[i][j]=true;
                    else
                        newBoard[i][j]=false;
                }else{
                    if(neighobors==3||neighobors==2)
                        newBoard[i][j]=true;
                    else
                        newBoard[i][j]=false;
                }
            }
        }
        return newBoard;
    }
    private int neighborCount(int i,int j){
        int neighborCounter=0;
        for (int k = i-1; k < i+2; k++) {
            for (int l = j-1; l < j+2; l++) {
                if(k!=i||l!=j){
                    try{
                        if(board[k][l])
                            neighborCounter++;
                    }catch(IndexOutOfBoundsException e){}
                }
            }
        }
        return neighborCounter;
    }
}
