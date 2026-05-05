public class GridID extends SpaceID{
    private final int row, column;

    public GridID (int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(SpaceID otherSpaceID) {
        if (!sameIDType(otherSpaceID)) return false;

        GridID other = (GridID) otherSpaceID;
        return (row == other.getRow() && column == other.getColumn());
    }
}
