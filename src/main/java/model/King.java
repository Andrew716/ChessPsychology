package model;

import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.abs;

/**
 * Created by phoenix on 21.12.16.
 */
public class King extends Figure {

    private boolean opportunityToCastling = true;

//    public final static int KING_WEIGHT = Integer.MAX_VALUE;

    public King(Field field, Color color) {
        super(field, color);
        attackedFields();
    }

//TODO fix bug comparing possible fields for king if some fields are under attack

    public void possibleTurns(){
        Set set;
        for (Object currentField : this.getAttackedFields()){
            if (this.getColor() == Color.BLACK){
                set = Board.getFieldsUnderWhiteInfluence();
            }else {
                set = Board.getFieldsUnderBlackInfluence();
            }
            if (!set.contains(currentField)){
                if (((Field)currentField).isTaken()){
                    if (this.getColor() == ((Field)currentField).getFigureByField().getColor()){
                        ((Field)currentField).getFigureByField().addAlien(this);
                    }else {
                        ((Field)currentField).getFigureByField().addEnemy(this);
                        this.getWhoCouldBeKilled().add(((Field)currentField).getFigureByField());
                    }
                }else {
                    this.getPossibleFieldsToMove().add(currentField);
                    this.getFieldsUnderMyInfluence().add((Field)currentField);
                }
            }
        }
    }


    public boolean isOpportunityToCastling() {
        return opportunityToCastling;
    }

    public void setOpportunityToCastling(boolean opportunityToCastling) {
        this.opportunityToCastling = opportunityToCastling;
    }

    public boolean isUnderAttack(){
        Set set;
        if (this.getColor() == Color.WHITE){
            set = Board.getBlackFigures();
        }else {
            set = Board.getInstance().getWhiteFigures();
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
                    Field field = new Field(i, j);
                    this.getAttackedFields().add(field);
                }
            }
        }
    }
}
