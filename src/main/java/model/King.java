package model;

import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.abs;

/**
 * Created by phoenix on 21.12.16.
 */
public class King extends Figure {

    private boolean oportunityToCastling = true;

//    public final static int KING_WEIGHT = Integer.MAX_VALUE;

    public King(Field field, Color color) {
        super(field, color);
        attackedFields();
    }

//TODO must be changed method for field

//    @Override
//    protected void possibleTurns() {
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


    public boolean isOportunityToCastling() {
        return oportunityToCastling;
    }

    public boolean isUnderAttack(){
        Set set;
        if (this.getColor() == Color.WHITE){
            set = Board.getBlackFigures();
        }else {
            set = Board.getWhiteFigures();
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Figure figure = (Figure) iterator.next();
            if (figure.getClass() == Knight.class){
                Iterator possibleTurns = (figure).getPossibleFieldsToMove().iterator();
                while (possibleTurns.hasNext()){
                    Field currentField = (Field) possibleTurns.next();
                    if (this.getField().equals(currentField)){
                        return true;
                    }
                }
            }else {
                if (figure.getClass() != King.class){
                    Iterator fieldsUnderAttack = figure.getAttackedFields().iterator();
                    while (fieldsUnderAttack.hasNext()){
                        Field currentField = (Field) fieldsUnderAttack.next();
                        if (this.getField().equals(currentField)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void attackedFields() {
        for (int  i = 0; i < Board.SIZE; i++){
            for (int j = 0; j < Board.SIZE; j++){
                if ((abs(this.getField().getX() - i) <= 1) && (abs(this.getField().getY() - j) <= 1)) {
                    if (this.getField().getX() == i && this.getField().getY() == j){
                        continue;
                    }
                    this.getAttackedFields().add(new Field(i, j));
                }
            }
        }
    }
}
