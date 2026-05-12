public class Space {
    public enum Type{
        START,
        END,
        OPEN
    }

    private final Type type;
    private final CacyLinkedList<SpaceID> adjacent;   // adjacent spaces
    private int adjacentCount;
    public boolean onPath;
    public boolean isDeadEnd;
    private final SpaceID id;
    private final int row;
    private final int col;
    private final boolean wall;

    public Space( Type type, SpaceID spaceID) {
        this.type = type;
        this.id = spaceID;
        this.adjacent = new CacyLinkedList<>();
        this.adjacentCount = 0;
        this.onPath = false;
        this.isDeadEnd = false;
        this.row = -1;
        this.col = -1;
        this.wall = false;
    }

    public void addAdjacent(SpaceID space) {
        adjacent.add(space);
        adjacentCount++;
    }
    public Space(String token, int row, int col) {
        this.wall = token.trim().toUpperCase().equals("W");

        switch (token.trim().toUpperCase()) {
            case "S":  this.type = Type.START; break;
            case "E":  this.type = Type.END;   break;
            default:   this.type = Type.OPEN;  break;  // "O" and anything else
        }
        this.id          = new SpaceUUID();
        this.adjacent    = new CacyLinkedList<>();
        this.adjacentCount = 0;
        this.onPath      = false;
        this.isDeadEnd   = false;
        this.row         = row;   // add: private final int row;
        this.col         = col;   // add: private final int col;
    }

    // Returns false for walls so GridReader can skip linking them
    public boolean isOpen() {
        return !wall;
    }


    public CacyLinkedList<SpaceID> getAdjacent() { return adjacent; }
    public SpaceID getId() {
        return id;
    }
    public int getAdjacentCount() { return adjacentCount; }
    public Type getType() { return type; }
    public int getRow() { return row; }
    public int getCol() { return col; }
}
