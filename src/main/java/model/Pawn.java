package model;

import java.util.Iterator;

/**
 * Created by phoenix on 21.12.16.
 */
public class Pawn extends Figure {

    private final static int PAWN_WEIGHT = 1;
    private int numberOfDoneTurns;

    public Pawn(Field field, Color color) {
        super(field, color);
        attackedFields();
       // fillAttackedAndProtectedFigures();
    }

    @Override
    public void possibleTurns() {
        if (this.getColor() == Color.WHITE){
            if (this.getField().getX() == 6){
                Field firstPossibleTurn = new Field(this.getField().getX() - 1, this.getField().getY());
                Field secondPossibleTurn = new Field(this.getField().getX() - 2, this.getField().getY());
                if (!firstPossibleTurn.isTaken()){
                    this.getPossibleFieldsToMove().add(firstPossibleTurn);
                }else {
                    return;
                }
                if (!secondPossibleTurn.isTaken()){
                    this.getPossibleFieldsToMove().add(secondPossibleTurn);
                }else {
                    return;
                }
            }else {
                Field possibleTurn = new Field(this.getField().getX() - 1, this.getField().getY());
                if (!possibleTurn.isTaken()){
                    this.getPossibleFieldsToMove().add(possibleTurn);
                }else {
                    return;
                }
            }
        }else {
            if (this.getField().getX() == 1){
                Field firstPossibleTurn = new Field(this.getField().getX() + 1, this.getField().getY());
                Field secondPossibleTurn = new Field(this.getField().getX() + 2, this.getField().getY());
                if (!firstPossibleTurn.isTaken()){
                    this.getPossibleFieldsToMove().add(firstPossibleTurn);
                }else {
                    return;
                }
                if (!secondPossibleTurn.isTaken()){
                    this.getPossibleFieldsToMove().add(secondPossibleTurn);
                }else {
                    return;
                }
            }else {
                Field possibleTurn = new Field(this.getField().getX() + 1, this.getField().getY());
                if (!possibleTurn.isTaken()){
                    this.getPossibleFieldsToMove().add(possibleTurn);
                }else {
                    return;
                }
            }
        }
    }

    @Override
    public void attackedFields() {
        for (int i = 1; i < Board.SIZE - 1; i++){
            if (this.getColor() == Color.WHITE){
                if (this.getField().getX() - i == 1){
                    if (this.getField().getY() + 1 < Board.SIZE){
                        Field field = new Field(i, this.getField().getY() + 1);
                        this.getAttackedFields().add(field);
                        this.getFieldsUnderMyInfluence().add(field);
                    }
                    if (this.getField().getY() - 1 >= 0){
                        Field field = new Field(i, this.getField().getY() - 1);
                        this.getAttackedFields().add(field);
                        this.getFieldsUnderMyInfluence().add(field);
                    }
                }
            }else {
                if (i - this.getField().getX() == 1){
                    if (this.getField().getY() - 1 >= 0){
                        Field field = new Field(i, this.getField().getY() - 1);
                        this.getAttackedFields().add(field);
                        this.getFieldsUnderMyInfluence().add(field);
                    }
                    if (this.getField().getY() + 1 < Board.SIZE){
                        Field field = new Field(i, this.getField().getY() + 1);
                        this.getAttackedFields().add(field);
                        this.getFieldsUnderMyInfluence().add(field);
                    }
                }
            }
        }
//        enPassant();
        fillAttackedAndProtectedFigures();
    }

    private void fillAttackedAndProtectedFigures(){
        Iterator<Field> fieldIterator = this.getAttackedFields().iterator();
        while (fieldIterator.hasNext()){
            Field currentField = fieldIterator.next();
            if (currentField.isTaken()){
                Figure currentFigure = currentField.getFigureByField();
                if (currentFigure.getColor() == this.getColor()){
                    currentFigure.addAlien(this);
                }else {
                    currentFigure.addEnemy(this);
                    this.getWhoCouldBeKilled().add(currentFigure);
                }

            }
        }
    }

//    private void enPassant(){
//        if (this.getColor() == Color.WHITE){
//            if(this.getField().getX() == 3){
//                Field leftField = new Field(3, this.getField().getY()-1);
//                if (leftField.isTaken() && leftField.getFigureByField().getColor() == Color.BLACK && leftField.getFigureByField().getClass() == Pawn.class){
//                    if (Board.getInstance().getPreviousTurn().equals(leftField)){
//                        this.getAttackedFields().add(new Field(2, this.getField().getY() - 1));
//                        this.getWhoCouldBeKilled().add(leftField.getFigureByField());
//                    }
//                }
//                Field rightField = new Field(3, this.getField().getY() + 1);
//                if (rightField.isTaken() && rightField.getFigureByField().getColor() == Color.BLACK && rightField.getFigureByField().getClass() == Pawn.class){
//                    if (Board.getInstance().getPreviousTurn().equals(rightField)){
//                        this.getAttackedFields().add(new Field(2, this.getField().getY() + 1));
//                        this.getWhoCouldBeKilled().add(rightField.getFigureByField());
//                    }
//                }
//            }
//        }else {
//            if (this.getField().getX() == 4){
//                Field leftField = new Field(4, this.getField().getY() - 1);
//                if (leftField.isTaken() && leftField.getFigureByField().getColor() == Color.WHITE && leftField.getFigureByField().getClass() == Pawn.class){
//                    if (Board.getInstance().getPreviousTurn().equals(leftField)){
//                        this.getAttackedFields().add(new Field(5, this.getField().getY() - 1));
//                        this.getWhoCouldBeKilled().add(leftField.getFigureByField());
//                    }
//                }
//                Field rightField = new Field(4, this.getField().getY() + 1);
//                if (rightField.isTaken() && rightField.getFigureByField().getColor() == Color.WHITE && rightField.getFigureByField().getClass() == Pawn.class){
//                    if (Board.getInstance().getPreviousTurn().equals(rightField)){
//                        this.getAttackedFields().add(new Field(5, this.getField().getY() + 1));
//                        this.getWhoCouldBeKilled().add(rightField.getFigureByField());
//                    }
//                }
//            }
//        }
//    }
}
