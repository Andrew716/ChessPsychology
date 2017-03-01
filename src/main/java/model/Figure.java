package model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by phoenix on 21.12.16.
 */
public abstract class Figure implements Observer {

    private Field field;
    private Color color;
    private Set<Figure> enemiesAttackMe = new LinkedHashSet<Figure>();
    private Set<Figure> aliensProtectMe = new LinkedHashSet<Figure>();
    private int numberOfAliensProtectMe;
    private int numberOfEnemiesAttackMe;
    private Set<Figure> whoCouldBeKilled = new LinkedHashSet<Figure>();
    private Set<Field> attackedFields = new LinkedHashSet<Field>();
    private Set<Field> fieldsUnderMyInfluence = new LinkedHashSet<Field>();
    private Set<Field> possibleFieldsToMove = new LinkedHashSet<Field>();
    protected abstract void attackedFields();
    public abstract void possibleTurns();

    public Figure(){}

    public Figure(Field field, Color color) {
        this.field = field;
        this.color = color;
    }

//    public boolean isAttack(int coordinate_X, int coordinate_Y){
//        boolean flag = false;
//        Point givenPointForCheck = new Point(coordinate_X, coordinate_Y);
//        if (attackedFields.contains(givenPointForCheck)){
//            flag = true;
//        }
//        return flag;
//    }

    public void update(Field field){
        this.field = field;
        this.enemiesAttackMe.clear();
        this.aliensProtectMe.clear();
        this.whoCouldBeKilled.clear();
        this.attackedFields.clear();
        this.possibleFieldsToMove.clear();
        this.fieldsUnderMyInfluence.clear();
        if (this.getClass() == King.class){
            ((King) this).setOpportunityToCastling(false);
        }else {
            if (this.getClass() == Rock.class){
                ((Rock) this).setOpportunityToCastling(false);
            }
        }
        attackedFields();
        possibleTurns();
    }

    public void update(){
        this.enemiesAttackMe.clear();
        this.aliensProtectMe.clear();
        this.whoCouldBeKilled.clear();
        this.attackedFields.clear();
        this.possibleFieldsToMove.clear();
        this.fieldsUnderMyInfluence.clear();
        attackedFields();
        possibleTurns();
    }

    public Field getField(){
        return this.field;
    }

    public Color getColor() {
        return color;
    }

    public Set<Figure> getWhoCouldBeKilled() {
        return whoCouldBeKilled;
    }

    public Set<Field> getAttackedFields() {
        return attackedFields;
    }

    public Set<Field> getFieldsUnderMyInfluence() {
        return fieldsUnderMyInfluence;
    }

    public void addEnemy(Figure figure){
        enemiesAttackMe.add(figure);
        numberOfEnemiesAttackMe++;
    }

    public Set<Figure> getEnemiesAttackMe() {
        return enemiesAttackMe;
    }

    public Set<Figure> getAliensProtectMe() {
        return aliensProtectMe;
    }

    public int getNumberOfAliensProtectMe() {
        return numberOfAliensProtectMe;
    }

    public int getNumberOfEnemiesAttackMe() {
        return numberOfEnemiesAttackMe;
    }

    public Set<Field> getPossibleFieldsToMove() {
        return possibleFieldsToMove;
    }

    public void addAlien(Figure figure){
        aliensProtectMe.add(figure);
        numberOfAliensProtectMe++;
    }

//    public void possibleTurns(){
//        Iterator<Field> iterator = attackedFields.iterator();
//        while (iterator.hasNext()){
//            Field currentField = iterator.next();
//            if (currentField.isTaken()){
//                if (this.getColor() == currentField.getFigureByField().getColor()){
//                    currentField.getFigureByField().addAlien(this);
//                }else {
//                    currentField.getFigureByField().addEnemy(this);
//                    this.getWhoCouldBeKilled().add(currentField.getFigureByField());
//                }
//            }else {
//                possibleFieldsToMove.add(currentField);
//            }
//        }
//    }

    protected boolean checksFieldsForTaken(Field field){
        if (!field.isTaken()){
            this.getPossibleFieldsToMove().add(field);
        }else {
            Figure tempFigure = field.getFigureByField();
            if (tempFigure.getColor() == this.getColor()){
                tempFigure.addAlien(this);
                return true;
            }else {
                tempFigure.addEnemy(this);
                this.getWhoCouldBeKilled().add(tempFigure);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        if (this.getClass() != o.getClass()){
            return false;
        }
        return this.field.equals(((Figure)o).getField())
                && this.getColor() == ((Figure)o).getColor();
    }

    @Override
    public int hashCode(){
        return 31*this.getField().getX() + 97*this.getField().getY();
    }

    @Override
    public String toString(){
        if (this.getClass() == Pawn.class) {
            return "" + this.getField();
        }
        if (this.getClass() == Rock.class) {
            return "R" + this.getField();
        }
        if (this.getClass() == Knight.class) {
            return "N" + this.getField();
        }
        if (this.getClass() == Bishop.class) {
            return "B" + this.getField();
        }
        if (this.getClass() == Queen.class) {
            return "Q" + this.getField();
        }
        if (this.getClass() == King.class) {
            return "K" + this.getField();
        }
        return null;
    }
}
