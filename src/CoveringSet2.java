/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JDI
 */
public class CoveringSet2
{
    //queen object containing coordinates of its location
    public class Position
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
/**
            if(this.x == n-1) // if position is on the last row
                if(this.y)
                p = new Position(0,0);
            else
*/
            /*
            if(this.y == n-1) // if position is on last space of column (n-1)
                if(this.x != n-1) // if position is not on the last space of row
                    p = new Position(this.x+1,0);

            else
                p = new Position(this.x,this.y+1);
             */
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
    public CoveringSet2(int n, int k)
    {
        this.n = n;
        this.k = k; // number of queens
        this.board = new int[n][n];
        clearBoard(); // fill board with zeroes




    }

    // next test
    public void test()
        {
            //Position p = new Position(0,0);
            queens.push(new Position(0,0));
            System.out.println("Queen at "+queens.peek());
            for(int i = 40; i != 0; i--) // for the number of queens
            {
                if(queens.peek().next(n) != null)
                {
                    queens.push(queens.peek().next(n)); //push a position(queen) to next available space
                    System.out.println("Queen at "+queens.peek());
                }
            }
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
                //System.out.println("Space ("+i+", "+j+" )  cleared."); // test purposes
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
                //System.out.println("Cell ("+i+","+j+") = "+board[i][j]);

        return answer;
    }

    // place 1s in spaces that queen/position dominates
    public void dominate(int[][] b, Position p)
    {
        //get coordinates of position/queen
        //int row = p.getRow();
        //System.out.println(p+"*");
        //System.out.println("Column = "+p.getCol());
        int col = p.getCol();
        //int k = 0;
        for(int m = 0 ; m < n; m++) // fill in spaces below & above queen
            b[m][col] = 1;
            //System.out.println("Space ("+k+", "+col+" )  dominated.");
        //for(int i = row; i < n; i++)
        //    b[i][col] = 1; // fill in column below

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
            //System.out.println("Space ("+i+", "+j+" )  dominated.");
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
            //System.out.println("Space ("+i+", "+j+" )  dominated.");


        }
        
    }

    //sets up the board to represent domination of queens
    public void domination(Stack <Position> q)
    {
        clearBoard(); // clear board first
        Stack <Position> p = new Stack <Position>();
        while(!q.isEmpty())
        {
            //System.out.println("Peek is"+queens.peek());
            dominate(board,q.peek()); // check domination of top
            p.push(q.pop()); //push into new stack
        }
        while(!p.isEmpty())
            q.push(p.pop()); //push positions back to original stack
    }

    public void play()
    {
        System.out.println("Play method started.");
        System.out.println("Solution:");
        // when board is full, null sent to be pushed out

        Position p = new Position(0,0); // position of first queen
        queens.push(p); // push queen onto stack
        //System.out.println("Queen at "+queens.peek());
        //dominate(board,p); // take spaces dominate by queen
        for(int i = k-1; i != 0; i--) // for the number of queens
        {
            if(queens.peek().next(n) != null)
            {
                queens.push(queens.peek().next(n)); //push a position(queen) to next available space
                //System.out.println("Queen at "+queens.peek());
            }
            //dominate(board,queens.peek()); // take spaces dominated by queen
        }

        if(k >= n) // if there are as many queens as there are rows
        {
            while(!queens.isEmpty()) // output positions of queens
                System.out.println(queens.pop());
        }
        else
        {
            domination(queens); // set up board for domination of queens



                //p = queens.peek(); // change position that calls the next position method
            /*
            // contents of stack testing
            for(int i = k; i != 0; i--)
                System.out.println(queens.pop());
                //System.out.println("Queen placed at ("+p.getRow()+", "+p.getCol()+" )  dominated.");
             */
            //fillBoard();
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
                        //System.out.println("Top is null." + top);
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

                domination(queens);
                if(checkBoard())
                {
                    System.out.println("Solution:");
                    Stack <Position> t = new Stack <Position>();
                    while(!queens.isEmpty())
                    {
                        System.out.println(queens.peek());
                        t.push(queens.pop()); //push into new stack
                    }
                    while(!t.isEmpty())
                        queens.push(t.pop()); //push positions back to original stack
                }
            }
            if(queens.isEmpty())
                System.out.println("No solution.");
            else
                if(!done)
                    while(!queens.isEmpty())
                        System.out.println(queens.pop());
        }
        System.out.println("Game completed.");
    }
    
}

