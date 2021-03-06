package model;

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

    @Override
    public void possibleTurns(){
        Set<Field> enemyInfluence;
        Board board = Board.getInstance();
        for (Field field : this.getAttackedFields()){
            Figure figure = Board.getFieldToFigure().get(field);
            if (this.getColor() == Color.BLACK){
                enemyInfluence = board.getFieldsUnderWhiteInfluence();
            }else {
                enemyInfluence = board.getFieldsUnderBlackInfluence();
            }
            if (!enemyInfluence.contains(field)){
                if (figure != null){
                    if (this.getColor() == figure.getColor()){
                        figure.addAlien(this);
                    }else {
                        figure.addEnemy(this);
                        this.getWhoCouldBeKilled().add(figure);
                    }
                }else {
                    this.getPossibleFieldsToMove().add(field);
                    this.getFieldsUnderMyInfluence().add(field);
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



    //Test commit

    public boolean isUnderAttack(){
        Set<Field> enemyInfluence;
        Board board = Board.getInstance();
        if (this.getColor() == Color.WHITE){
            enemyInfluence = board.getFieldsUnderBlackInfluence();
        }else {
            enemyInfluence = board.getFieldsUnderWhiteInfluence();
        }
        return enemyInfluence.contains(this.getField());
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
