import java.util.UUID;

public class SpaceUUID extends SpaceID{

    private UUID id;

    public SpaceUUID(){
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(SpaceID otherSpaceID) {
        if (!sameIDType(otherSpaceID)) return false;
        return ((SpaceUUID) otherSpaceID).getId().equals(id);
    }
}
