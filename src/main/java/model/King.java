package model;

import java.util.Iterator;
import java.util.Set;

import static java.lang.Math.abs;

/**
 * Created by phoenix on 21.12.16.
 */
public class King extends Figure {

    private boolean opportunityToCastling = true;

    public King(Field field, Color color) {
        super(field, color);
        attackedFields();
    }

//TODO fix bug comparing possible fields for king if some fields are under attack

    public void possibleTurns(){
        Set set;
        Board board = Board.getInstance();
        for (Object currentField : this.getAttackedFields()){
            if (this.getColor() == Color.BLACK){
                set = board.getFieldsUnderWhiteInfluence();
            }else {
                set = board.getFieldsUnderBlackInfluence();
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
                    this.getPossibleFieldsToMove().add((Field) currentField);
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
        Board board = Board.getInstance();
        if (this.getColor() == Color.WHITE){
            set = board.getBlackFigures();
        }else {
            set = board.getWhiteFigures();
        }
        for (Object figure : set){
            if(figure.getClass() == Knight.class){
                for (Field currentField : ((Figure)figure).getPossibleFieldsToMove()){
                    if(this.getField().equals(currentField)){
                        return true;
                    }
                }
            }else{
                for (Field currentField : ((Figure)figure).getAttackedFields()){
                    if (this.getField().equals(currentField)){
                        return true;
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
