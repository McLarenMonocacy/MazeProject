public abstract class SpaceID {
    public abstract boolean equals(SpaceID otherSpaceID);

    public boolean sameIDType(SpaceID otherSpaceID){
        return otherSpaceID.getClass() == this.getClass();
    }
}
