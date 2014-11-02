/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzleproblem;

import static java.lang.System.out;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author sree
 */
public class Board {
    private int[][] blocks;
//  private int[][] goal;
    private Map<Integer,List<Integer>> goal; 
    
    
    public Board(int[][] blocks) {
      this.blocks = blocks;
      
      goal = new TreeMap<Integer, List<Integer>>();
      LinkedList list;
      
      for(int row =0 ; row<blocks.length;row++) {
        for(int column=0;column<blocks.length;column++) {
        //  goal[row][column] = (int) (Math.pow(blocks.length,row)+column+1);
            list = new LinkedList();
            list.add(row);
            list.add(column);
            
         goal.put((int) (Math.pow(blocks.length,row)+column+1),list);
        }
       }
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
            
            if(!(row==2||column==2)&&blocks[row][column] !=Math.pow(blocks.length,row)+column+1) {
                hammingVal++;
            }
        return hammingVal;
    }

    public int manhattan() // sum of Manhattan distances between blocks and goal
    {
        int val=0;
        for(int row =0;row<blocks.length;row++)
        for(int column=0;column<blocks.length;column++) 
        {
         if(!(row==2||column==2)&&blocks[row][column] !=Math.pow(blocks.length,row)+column+1) {
            
        List indexes = goal.get(Math.pow(blocks.length,row)+column+1);
        int index1 = (Integer)indexes.get(0);
        int index2 = (Integer)indexes.get(1);
        
         val = Math.abs(row-index1) + Math.abs(column-index2)+val;
         }
        }  
        return val;
    }

    public boolean isGoal() // is this board the goal board? 
    {
        return blocks.toString().equals(goal.keySet().toString());
    }

    public Board twin() // a boadr that is obtained by exchanging two adjacent blocks in the same row
    {   
        int anotherBlocks[][] =  blocks.clone();
        for(int row =0;row<blocks.length;row++)
        for(int column=0;column<blocks.length-1;column++) 
        {
            if(anotherBlocks[row][column] !=0 && anotherBlocks[row][column+1]!=0) {
               int temp = anotherBlocks[row][column];
               anotherBlocks[row][column] = anotherBlocks[row][column+1];
               anotherBlocks[row][column+1] = temp;
               break;
            }
         }
        return new Board(anotherBlocks);
    }

    public boolean equals(Object y) // does this board equal y? 
    {
        return y.toString() == this.toString();
    }

    public Iterable<Board> neighbors() // all neighboring boards 
    {
        Stack<Board> list = new Stack();
        int anotherBlocks[][] =  blocks.clone();
        for(int row =0;row<blocks.length;row++)
        for(int column=0;column<blocks.length-1;column++) 
        {
            if(anotherBlocks[row][column] !=0 && anotherBlocks[row][column+1]!=0) {
               int temp = anotherBlocks[row][column];
               anotherBlocks[row][column] = anotherBlocks[row][column+1];
               anotherBlocks[row][column+1] = temp;
            }
            list.add(this);
         }
             return list;
   
    }

    public String toString() // string representation of this board (in the output format specified below) 
    {
        StringBuilder s = new StringBuilder();
    s.append(blocks.length + "\n");
    for (int i = 0; i < blocks.length; i++) {
        for (int j = 0; j < blocks.length; j++) {
            s.append(String.format("%2d ", blocks[i][j]));
        }
        s.append("\n");
    }
    return s.toString();
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
