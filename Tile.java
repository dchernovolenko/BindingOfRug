public class Tile{
        //instance varaibles
        private Creature _entity;
        private String _type;
        private int _row, _col;

        //constructor for default Tile
        public Tile(int row, int col){
                _entity = null;
                _type = "empty";
                _row = row;
                _col = col;
        }

        //SPECIAL CONSTRUCTORS for custom Tiles
        public Tile(Creature entity, int row, int col){
                _entity = entity;
                _type = "empty";
                _row = row;
                _col = col;
        }
        public Tile(String type, int row, int col){
                _entity = null;
                _type = type;
                _row = row;
                _col = col;
        }
        public Tile(Creature entity, String type, int row, int col){
                _entity = entity;
                _type = type;
                _row = row;
                _col = col;
        }

        //Delete entity on Tile
        public void deleteEntity(){
                _entity = null;
        }
        //put an entity on the Tile
        public void setEntity(Creature entity){
                _entity = entity;
        }
        //get the Entity on the Tile
        public Creature getEntity(){
                return _entity;
        }
        //get Tile's row
        public int getRow(){
                return _row;
        }
        //get Tile's column
        public int getCol(){
                return _col;
        }
        //modify Tile's row
        public void setRow(int row){
                _row = row;
        }
        //modify Tile's column
        public void setCol(int col){
                _col = col;
        }


        //get ASCII / Unicode representation
        public String getChar(){
                if(getEntity() != null){
                        return _entity.getChar();
                }
                else if(_type.equals("wall"))
                        return "█";
                //if not a special type of wall this Tile is empty.
                else{
                        return "_";
                }
        }
}
