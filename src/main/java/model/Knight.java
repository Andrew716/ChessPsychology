package model;

import java.util.Iterator;

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

    public void possibleTurns(){
        Iterator<Field> iterator = this.getAttackedFields().iterator();
        while (iterator.hasNext()){
            Field currentField = iterator.next();
            if (currentField.isTaken()){
                if (this.getColor() == currentField.getFigureByField().getColor()){
                    currentField.getFigureByField().addAlien(this);
                }else {
                    currentField.getFigureByField().addEnemy(this);
                    this.getWhoCouldBeKilled().add(currentField.getFigureByField());
                }
            }else {
                this.getPossibleFieldsToMove().add(currentField);
                this.getFieldsUnderMyInfluence().add(currentField);
            }
        }
    }

    @Override
    protected void attackedFields() {
        for (int i = 0; i < Board.SIZE; i++){
            for (int j = 0; j < Board.SIZE; j++){
                if (abs(this.getField().getX() - i) + abs(this.getField().getY() - j) == 3){
                    if (this.getField().getX()== i || this.getField().getY() == j){
                        continue;
                    }
                    Field field = new Field(i, j);
                    this.getAttackedFields().add(field);
                }
            }
        }
    }
}

