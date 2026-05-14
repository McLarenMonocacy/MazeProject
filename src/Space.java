public class Space {
    public enum Type{
        START,
        END
        //All spaces are open so a null Type is just an open space
    }

    private final Type type;
    private final CacyLinkedList<SpaceID> adjacent;   // adjacent spaces
    private int adjacentCount;
    public boolean onPath;
    public boolean isDeadEnd;
    private final SpaceID id;

    public Space( Type type, SpaceID spaceID) {
        this.type = type;
        this.id = spaceID;
        this.adjacent = new CacyLinkedList<>();
        this.adjacentCount = 0;
        this.onPath = false;
        this.isDeadEnd = false;
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
}
