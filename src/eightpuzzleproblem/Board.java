/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzleproblem;

import java.io.InputStream;
import static java.lang.System.out;
import java.util.Scanner;
import javax.print.DocFlavor;

/**
 *
 * @author sree
 */
public class Board {
    private int[][] blocks;
    public Board(int[][] blocks) {
      this.blocks = blocks;
    }          // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)

    public int dimension() // board dimension N 
    {
        return blocks.length;
    }

    public int hamming() // number of blocks out of place
    {    int hammingVal=0;
        for(int row =0;row<blocks.length;row++)
        for(int column=0;column<blocks.length;column++) 
            
            if(!(row==3||column==3)&&blocks[row][column] !=Math.pow(blocks.length,row)+column+1) {
                hammingVal++;
            }
        return hammingVal;
    }

    public int manhattan() // sum of Manhattan distances between blocks and goal
    {
        return 0;
    }

    public boolean isGoal() // is this board the goal board? 
    {
        return false;
    }

    public Board twin() // a boadr that is obtained by exchanging two adjacent blocks in the same row
    {
        return null;
    }

    public boolean equals(Object y) // does this board equal y? 
    {
        return false;
    }

    public Iterable<Board> neighbors() // all neighboring boards 
    {

        return null;
    }

    public String toString() // string representation of this board (in the output format specified below) 
    {
        return "";
    }

    public static void main(String[] args) {

    // create initial board from file
    Scanner in =  new Scanner(System.in);
    int N = in.nextInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.nextInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        out.println("No solution possible");
    else {
        out.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            out.println(board);
    }
}

}
