public class Space {
    public enum Tag {
        START,
        END
    }

    private SpaceID id;
    //List of tags to identify the space as a start/end (would this be better as a bit mask?)
    private CacyLinkedList<Tag> tags;

    public Space (SpaceID id, CacyLinkedList<Tag> tags){
        this.id = id;
        this.tags = tags;
    }

    public Space (SpaceID id){
        this(id,null);
    }

    public SpaceID getId() {
        return id;
    }

    public CacyLinkedList<Tag> getTags() {
        return tags;
    }
}
