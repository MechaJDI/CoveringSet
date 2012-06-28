
public class CoveringSet
{
    //queen object containing coordinates of its location
    private class Position
    {
        int x; // x coordinate
        int y; // y coordinate

        //constructor
        public Position(int x, int y)
        {
            this.x = x; // rows
            this.y = y; // columns
        }

        //returns next available position on board of size nxn
        //returns null if no next position
        public Position next(int n)
        {
            Position p = null;
            if(y < n-1)
                p = new Position(x, y+1);
            else
                if(x < n-1)
                    p = new Position(x+1,0);
            return p;
        }

        //set coordinates of queen
        public void setCoord(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        //return y coordinate, column
        public int getCol()
        {
            return this.y;
        }

        //return x coordinate, row
        public int getRow()
        {
            return this.x;
        }

        //display coordinates of queen
        public String toString()
        {
            return "("+this.getRow()+","+this.getCol()+")";
        }

        //checks if position coordinates are equal
        public boolean equals(Position p)
        {
            boolean answer = false;
            if(x == p.x && y == p.y)
                answer = true;
            return answer;
        }
    }

    int n; // nxn = board size
    int k; // number of queens
    int[][] board = new int[n][n]; // board
    boolean done = false;
    Stack<Position> queens = new Stack <Position>(k);

    //constructor
    public CoveringSet(int n, int k)
    {
        this.n = n;
        this.k = k; // number of queens
        this.board = new int[n][n];
        clearBoard(); // fill board with zeroes
    }

    //clears the board
    public void clearBoard()
    {
        int i = 0;
        int j = 0;
        for(i = 0; i < n; i++)
            for(j = 0; j < n; j++)
            {
                this.board[i][j] = 0;
            }
    }

    // fill board with ones, testing purposes
    public void fillBoard()
    {
        int i = 0;
        int j = 0;
        for(i = 0; i < n; i++)
            for(j = 0; j < n; j++)
                this.board[i][j] = 1;
    }

    //check board for zeroes
    public boolean checkBoard()
    {
        boolean answer = true;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
               if(this.board[i][j] == 0 && answer == true) // if any spaces on board isn't filled, return false
                   answer = false;
        return answer;
    }

    // place 1s in spaces that queen/position dominates
    public void dominate(int[][] b, Position p)
    {
        // fill in spaces below & above queen
        int col = p.getCol();
        for(int m = 0 ; m < n; m++) 
            b[m][col] = 1;

        //fill in spaces to left and right of queen
        for(int l = 0; l < n; l++)
            b[p.getRow()][l] = 1;

        //fill in spaces right below diagonals
        int i = p.getRow()+1;
        int j = p.getCol()+1;
        while(i < n && j < n)
        {
            b[i][j] = 1;
            i++;
            j++;
        }

        // fill in right above diagonals
        i = p.getRow()-1;
        j = p.getCol()+1;
        while(i >= 0 && j < n)
        {
            b[i][j] = 1;
            i--;
            j++;
        }

        // fill in left above diagonals
        i = p.getRow()-1;
        j = p.getCol()-1;
        while(i >= 0 && j >= 0)
        {
            b[i][j] = 1;
            i--;
            j--;
        }

        //fill in left below diagonals
        i = p.getRow()+1;
        j = p.getCol()-1;
        while(i < n && j >= 0)
        {
            b[i][j] = 1;
            i++;
            j--;
        }
    }

    //sets up the board to represent domination of queens
    public void domination(Stack <Position> q)
    {
        clearBoard(); 
        Stack <Position> p = new Stack <Position>();
        while(!q.isEmpty())
        {
            dominate(board,q.peek()); // check domination of top
            p.push(q.pop()); //push into new stack
        }
        while(!p.isEmpty())
            q.push(p.pop()); //push positions back to original stack
    }

    //checks if nxn size board can be dominated by k queens
    public void play()
    {
        System.out.println("Game started.");
        System.out.println("Solution:");
        Position p = new Position(0,0); // position of first queen
        queens.push(p); // push queen onto stack
        for(int i = k-1; i != 0; i--) // for the number of queens
        {
            if(queens.peek().next(n) != null)
            {
                queens.push(queens.peek().next(n)); //push a position(queen) to next available space
            }
        }
        if(k >= n) // if there are as many queens as there are rows
        {
            while(!queens.isEmpty()) // output positions of queens
                System.out.println(queens.pop());
        }
        else
        {
            domination(queens); // set up board for domination of queens
            while(!checkBoard() && !done) // if the board is not filled and game not done
            {
                Position top = queens.pop();
                if(top.next(n) != null)
                    queens.push(top.next(n));
                else
                {
                    while(!queens.isEmpty() && queens.peek().next(n).equals(top))
                        top = queens.pop();
                    if(!queens.isEmpty())
                    {
                        top = queens.pop();
                        queens.push(top.next(n));
                        while(queens.getSize() < k && !done)
                        {
                            if(queens.peek().next(n) == null)
                                done = true;
                            else
                                queens.push(queens.peek().next(n));
                        }
                    }
                    else
                        done = true; // no solution
                }
                domination(queens); // set domination of queens
            }
            if(!queens.isEmpty())
            {
                while(!queens.isEmpty()) 
                    System.out.println(queens.pop());
            }
            else
                System.out.println("No solution.");
        }
        System.out.println("Game completed.");
    }
}