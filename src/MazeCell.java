/**
 * A class that represents a single cell of the maze.
 * @author Ms. Namasivayam & Kai Mawakana
 * @version 04/08/2025
 */

public class MazeCell {
    private boolean explored;
    private boolean isWall;
    private MazeCell parent;
    private int row;
    private int col;

    public MazeCell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isWall = false;
        this.explored = false;
        this.parent = null;
    }

    /** Getters and Setters **/
    public boolean isExplored() {
        return this.explored;
    }

    public boolean isWall() {
        return this.isWall;
    }

    public MazeCell getParent() {
        return this.parent;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public void setParent(MazeCell parent) {
        this.parent = parent;
    }

    public void setWall(boolean wall) {
        this.isWall = wall;
    }
}
