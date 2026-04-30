public class Maze {
    public static final int MAX_SPACES = 2500; // 50x50

    private Space[] spaces;
    private int spaceCount;
    private Space start;
    private Space end;
    private boolean solved;

    public Maze() {
        spaces = new Space[MAX_SPACES];
        spaceCount = 0;
        solved = false;
    }

    public void addSpace(Space s) {
        spaces[spaceCount++] = s;
        if (s.getType().equals("S")) start = s;
        if (s.getType().equals("E")) end = s;
    }

    public Space getStart() { return start; }
    public Space getEnd()   { return end; }
    public Space[] getSpaces() { return spaces; }
    public int getSpaceCount() { return spaceCount; }
    public boolean isSolved() { return solved; }


    public boolean solve() {
        if (start == null || end == null) return false;
        solved = solveFrom(start);
        return solved;
    }


    private boolean solveFrom(Space current) {
        // Base case: we reached the exit
        if (current == end) {
            current.setOnPath(true);
            return true;
        }

        // Mark current space as part of the path
        current.setOnPath(true);

        // Try each adjacent space
        for (int i = 0; i < current.getAdjacentCount(); i++) {
            Space neighbor = current.getAdjacent()[i];
            if ( !neighbor.isOnPath() && neighbor.isOpen()) {
                if (solveFrom(neighbor)) {
                    return true;  // Found a path!
                }
            }
        }

        // Dead end — unmark and backtrack
        current.setOnPath(false);
        return false;
    }
}