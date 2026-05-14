public class GridMazeOutput {
    private Maze maze;
    public GridMazeOutput(Maze maze){
        this.maze = maze;
    }

    public String output(boolean showPath){
        CacyLinkedList<SpaceID> spaces = maze.getAllSpaces();
        spaces.initIterator();

        int columnMax = 0, rowMax = 0;
        while (spaces.hasNext()){
            //First pass checks that all SpaceIDs are GridIDs and finds the size of the maze
            SpaceID spaceID = spaces.next();
            if (spaceID.getClass() != GridID.class) return "Invalid Maze"; //Requires GridId so anything else can't be processed
            GridID gridID = (GridID) spaceID;

            if (gridID.getColumn() > columnMax) columnMax = gridID.getColumn();
            if (gridID.getRow() > rowMax) rowMax = gridID.getRow();
        }

        if (columnMax == 0 || rowMax == 0) return "Invalid Maze";
        Space[][] recomputedGridMaze = new Space[columnMax+1][rowMax+1];

        spaces.initIterator();
        while (spaces.hasNext()){
            //recompute the maze into grid form
            GridID gridID = (GridID) spaces.next();
            Space space = maze.getSpace(gridID);
            recomputedGridMaze[gridID.getColumn()][gridID.getRow()] = space;
        }

        //Convert into output string
        StringBuilder builder = new StringBuilder();
        for (Space[] column : recomputedGridMaze) {
            for (Space space : column) {
                Space.Type type;
                char c = getOutputChar(space, showPath);
                builder.append(c);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private char getOutputChar(Space space, boolean showPath){
        char outputChar;
        if (space == null) outputChar = 'X';
        else {
            boolean usePathChar = showPath && space.onPath;
            switch (space.getType()){
                case START -> {outputChar = (usePathChar) ? '$' : 'S';}
                case null -> {outputChar = (usePathChar) ? '*' : 'O';}
                case END -> {outputChar = (usePathChar) ? '€' : 'E';}
            }
        }
        return outputChar;
    }
}
