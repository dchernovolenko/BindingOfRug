public class Tile<E>{
        private E _entity;
        private String _type;
        private int _row, _col;
        public Tile(int row, int col){
                _entity = null;
                _type = "empty";
                _row = row;
                _col = col;
        }
        public Tile(E entity, int row, int col){
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
        public Tile(E entity, String type, int row, int col){
                _entity = entity;
                _type = type;
                _row = row;
                _col = col;
        }
        public void deleteEntity(){
                _entity = null;
        }
        public int getRow(){
                return _row;
        }
        public int getCol(){
                return _col;
        }
        public void setRow(int row){
                _row = row;
        }
        public void setCol(int col){
                _col = col;
        }
        public void setEntity(E entity){
                _entity = entity;
        }
        public E getEntity(){
                return _entity;
        }
        public String getChar(){
                if (_type.equals("_"))
                        return "_";
                //if entity exists, return the entity's char
                else if(_entity != null)
                        return _entity.getChar();
        }
}
