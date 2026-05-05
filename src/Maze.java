public class Maze {
    private final CacyLinkedList<Space> spaces;

    private int spaceCount;
    private SpaceID start;
    private SpaceID end;

    public Maze(CacyLinkedList<Space> spaces){
        this.spaces = spaces;
        this.spaceCount = spaces.length();
    }

    public Maze(Space[] spaces){
        this.spaces = new CacyLinkedList<>();
        for (Space space : spaces){
            //Add start and end
            this.spaces.add(space);
        }
    }

    public Space getSpace(SpaceID id){
        spaces.initIterator();
        while (spaces.hasNext()){
            Space space = spaces.next();
            if (space.getId().equals(id)) return space;
        }
        return null;
    }

    public SpaceID getStart() { return start;}
    public SpaceID getEnd() { return end;}
    public int getSpaceCount(){ return spaceCount;}


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