public class Space {
    public static final int MAX_ADJACENT = 4;

    private String type;        // "O", "X", "S", "E"
    private Space[] adjacent;   // adjacent spaces (up, down, left, right)
    private int adjacentCount;
    private boolean onPath;
    private int row, col;       // for GridWriter

    public Space(String type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
        this.adjacent = new Space[MAX_ADJACENT];
        this.adjacentCount = 0;
        this.onPath = false;
    }

    public void addAdjacent(Space s) {
        if (adjacentCount < MAX_ADJACENT) {
            adjacent[adjacentCount++] = s;
        }
    }

    public Space[] getAdjacent() { return adjacent; }
    public int getAdjacentCount() { return adjacentCount; }
    public String getType() { return type; }
    public boolean isOnPath() { return onPath; }
    public void setOnPath(boolean onPath) { this.onPath = onPath; }
    public int getRow() { return row; }
    public int getCol() { return col; }

    public boolean isOpen() {
        return type.equals("O") || type.equals("S") || type.equals("E");
    }
}
