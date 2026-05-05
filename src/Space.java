public class Space {
    public enum Type{
        START,
        END,
        OPEN
    }

    private final Type type;        // "O", "X", "S", "E"
    private final CacyLinkedList<SpaceID> adjacent;   // adjacent spaces (up, down, left, right)
    private int adjacentCount;
    private boolean onPath;
    private SpaceID id;

    public Space( Type type, SpaceID spaceID) {
        this.type = type;
        this.id = spaceID;
        this.adjacent = new CacyLinkedList<>();
        this.adjacentCount = 0;
        this.onPath = false;
    }

    public void addAdjacent(SpaceID space) {
        adjacent.add(space);
        adjacentCount++;
    }

    public CacyLinkedList<SpaceID> getAdjacent() { return adjacent; }
    public SpaceID getId() {
        return id;
    }
    public int getAdjacentCount() { return adjacentCount; }
    public Type getType() { return type; }
    public boolean isOnPath() { return onPath; }
    public void setOnPath(boolean onPath) { this.onPath = onPath; }
}
