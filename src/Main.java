public class Main {
    public static void main(String[] args) {

        Maze maze = GridReader.readMaze("Resources/sample_maze.txt");

        MazeSolver solver = new MazeSolver(maze);

        if (solver.solve()){
            System.out.println(new GridMazeOutput(maze).output(false));
            System.out.println(new GridMazeOutput(maze).output(true));
        }
        else {
            System.out.println("No path found");
        }
    }
}