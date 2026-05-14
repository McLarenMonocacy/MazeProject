public class MazeSolver {

    private final Maze maze;

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public boolean solve(){
        return solveFrom(maze.getSpace(maze.getStart()));
    }

    private boolean solveFrom(Space current) {
        // Base case: we reached the exit
        if (current.getType() == Space.Type.END) {
            current.onPath = true;
            return true;
        }

        // Mark current space as part of the path
        current.onPath = true;

        // Try each adjacent space
        CacyLinkedList<SpaceID> spaces = current.getAdjacent();
        spaces.initIterator();
        while (spaces.hasNext()){
            Space adjSpace = maze.getSpace(spaces.next());
            if (canGo(adjSpace)){
                if (solveFrom(adjSpace)){
                    return true;  // Found a path!
                }
            }
        }

        // Dead end — unmark and backtrack
        current.onPath = false;
        current.isDeadEnd = true;
        return false;
    }

    private boolean canGo(Space space){
        return !(space.onPath || space.isDeadEnd);
    }
}
