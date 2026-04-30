public class Maze {
    private final CacyLinkedList<Space> spaces;

    public Maze(CacyLinkedList<Space> spaces){
        this.spaces = spaces;
    }

    public Space getSpace(SpaceID id){
        spaces.initIterator();
        while (spaces.hasNext()){
            Space space = spaces.next();
            if (space.getId().equals(id)) return space;
        }
        return null;
    }
}
