/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam & Kai Mawakana
 * @version 04/08/2025
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Should be from start to end cells
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();

        Stack<MazeCell> stack = new Stack<>();

        MazeCell currentCell = maze.getEndCell();

        // Tracing from end to start using parent property
        while (currentCell != null)
        {
            stack.push(currentCell);
            currentCell = currentCell.getParent();
        }

        // This process will add the cells into the Arraylist from start to end
        while(!stack.isEmpty())
        {
            solution.add(stack.pop());
        }

        return solution;
    }


    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
       Stack<MazeCell> stack = new Stack<MazeCell>();
       MazeCell start = maze.getStartCell();
       MazeCell end = maze.getEndCell();

       stack.push(start);
       start.setExplored(true);

       while(!stack.isEmpty())
       {
           MazeCell current = stack.pop();

           // Returns the solution when the end cell is reached
           if (current == end)
           {
               return getSolution();
           }

           // Checks neighboring cells in NESW order
           checkSurroundingCells(stack, current);
       }

        return null; // If no solution is found
    }


    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        Queue<MazeCell> queue = new LinkedList<MazeCell>();
        MazeCell start = maze.getStartCell();
        MazeCell end = maze.getEndCell();

        // Cell 1 is starting cell
        queue.add(start);
        start.setExplored(true);

        // Making sure the Queue is not empty
        while(!queue.isEmpty())
        {
            MazeCell current = queue.remove();

            // Once the end is reached, return the solution
            if (current == end)
            {
                return getSolution();
            }

            // Will check surrounding cells in NESW order
            checkSurroundingCells(queue, current);
        }
        return null; // Returns null if no solution is found
    }

    public void checkSurroundingCells(Object obj, MazeCell cell)
    {
        int row = cell.getRow();
        int col = cell.getCol();

        // Creating a 2d array to check in each direction
        int[][] directions = {
                {-1, 0}, // North
                {0, 1}, // East
                {1, 0}, // South
                {0, -1} // West
        };

        for (int[] direction : directions)
        {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (maze.isValidCell(newRow, newCol))
            {
                MazeCell neighbor = maze.getCell(newRow, newCol);
                neighbor.setExplored(true);
                neighbor.setParent(cell);

                // If object is Stack then push
                if(obj instanceof Stack)
                {
                    ((Stack<MazeCell>) obj).push(neighbor); // DFS
                }

                // Else add to a queue
                else
                {
                    ((Queue<MazeCell>) obj).add(neighbor); // BFS
                }
            }
        }

    }


    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
