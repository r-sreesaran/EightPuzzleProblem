/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzleproblem;

import static eightpuzzleproblem.Board.blocks;


/**
 *
 * @author sree
 */
public class Solver {
   Board intial;
    public Solver(Board initial) // find a solution to the initial board (using the A* algorithm) 
    {
       this.intial =  intial; 
       
       
    }

    public boolean isSolvable() // is the initial board solvable? 
    {  
        int single[] = new int[intial.dimension()*intial.dimension()];
        for(int row =0,tracker=0;row<intial.dimension();row++) {
         for(int column =0; column<intial.dimension();column++,tracker++) {
             single[tracker] = blocks[row][column];
         }
        }
        int temp=0;
        for(int count=0;count<intial.dimension()*intial.dimension();count++) {
          for(int counter=count+1;counter<intial.dimension()*intial.dimension();counter++) 
          {
            if(single[count]<single[counter]) {
              temp++;
            }
          }
        }
        if(temp%2==0) 
            return true;
        return false;
    }

    public int moves() // min number of moves to solve initial board; -1 if unsolvable 
    {
        return 0;
    }

    public Iterable<Board> solution() // sequence of boards in a shortest solution; null if unsolvable 
    {
        return null;
    }

    public static void main(String[] args) // solve a slider puzzle (given below) 
    {
    }
}
