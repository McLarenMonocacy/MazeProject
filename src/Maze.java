public class Maze {
    private final CacyLinkedList<Space> spaces;

    private int spaceCount;
    private SpaceID start;
    private SpaceID end;

    public Maze(CacyLinkedList<Space> spaces){
        this.spaces = spaces;
        this.spaceCount = spaces.length();
        this.spaces.initIterator();
        while ((start == null || end == null) && this.spaces.hasNext()){
            Space space = spaces.next();
            if (end == null && space.getType() == Space.Type.END) end = space.getId();
            if (start == null && space.getType() == Space.Type.START) start = space.getId();

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
}