package model;

import static java.lang.Math.abs;

/**
 * Created by phoenix on 21.12.16.
 */
public class Knight extends Figure {

    private final static int KNIGHT_WEIGHT = 3;

    public Knight(Field field, Color color) {
        super(field, color);
        attackedFields();
    }

//    @Override
//    public void possibleTurns() {
//        Iterator<Field> iterator = attackedFields.iterator();
//        while (iterator.hasNext()){
//            Field currentField = iterator.next();
//            if (Field.isTaken(currentField)){
//                if (this.getColor() == Field.getFigureByField(currentField).getColor()){
//                    Field.getFigureByField(currentField).addAlien(this);
//                }else {
//                    Field.getFigureByField(currentField).addEnemy(this);
//                }
//            }else {
//                possibleFieldsToMove.add(currentField);
//            }
//        }
//    }

    @Override
    protected void attackedFields() {
        for (int i = 0; i < Board.SIZE; i++){
            for (int j = 0; j < Board.SIZE; j++){
                if (abs(this.getField().getX() - i) + abs(this.getField().getY() - j) == 3){
                    if (this.getField().getX()== i || this.getField().getY() == j){
                        continue;
                    }
                    this.getAttackedFields().add(new Field(i, j));
                }
            }
        }
    }
}

