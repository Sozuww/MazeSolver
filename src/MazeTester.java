/**
 * A class to test our MazeSolver
 * @author: Nandhini Namasivayam & Kai Mawakana
 * @version: 04/08/2025
 */

import java.util.ArrayList;

public class MazeTester {

    /**
     * Tests the getSolution() function against a
     * hardcoded solution.
     */
    public static void testGetSolution() {
        // Read in a new maze
        Maze maze = new Maze("Resources/mazeGetSolutionTester.txt");

        // Create our expected solution
        ArrayList<MazeCell> expected = new ArrayList<MazeCell>();
        expected.add(maze.getCell(5, 0));
        expected.add(maze.getCell(5, 1));
        expected.add(maze.getCell(4, 1));
        expected.add(maze.getCell(3, 1));
        expected.add(maze.getCell(2, 1));
        expected.add(maze.getCell(1, 1));
        expected.add(maze.getCell(0, 1));
        expected.add(maze.getCell(0, 2));
        expected.add(maze.getCell(0, 3));
        expected.add(maze.getCell(0, 4));
        expected.add(maze.getCell(0, 5));

        // Manually set parents according to our solution
        for (int i=expected.size()-1; i>0; i--) {
            expected.get(i).setParent(expected.get(i-1));
        }

        // Get the solution from MazeSolver
        MazeSolver ms = new MazeSolver(maze);
        ArrayList<MazeCell> received = ms.getSolution();

        // Check if solutions are equal size
        if (received.size() != expected.size()) {
            System.out.println("Test Get Solution Failed: Paths are different sizes");
            System.out.println("Expected: " + expected.size() + " but got: " + received.size());
            return;
        }

        // Check that each cell in the solution is the same
        for (int i=0; i<expected.size(); i++) {
            MazeCell exCell = expected.get(i);
            MazeCell recCell = received.get(i);

            if (exCell.getRow() != recCell.getRow() || exCell.getCol() != recCell.getCol()) {
                System.out.println("Test Get Solution Failed");
                System.out.println("Expected: (" + exCell.getRow() + ", " + exCell.getCol() + ")");
                System.out.println("But Got: (" + recCell.getRow() + ", " + recCell.getCol() + ")");
                return;
            }
        }

        System.out.println("Test Get Solution Passed!");
    }

    public static void main(String[] args) {
        MazeTester.testGetSolution();
    }
}
