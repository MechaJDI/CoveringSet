
import java.util.Scanner;


public class Test
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a number for the dimensions of the board.");
        int x = scanner.nextInt();
        
        System.out.println("Enter the number of queens to be placed on the board.");
        int y = scanner.nextInt();
        CoveringSet c = new CoveringSet(x,y); // 11x11 board, 5 queens
        c.play();
    }
}