package controler;

import java.util.Map;

import model.*;

/**
 * Created by phoenix on 11.01.17.
 */
public class Parameters {

    private static int firstAttackEnemy;
    private static int secondBeUnderAttack;
    private static int thirdWithdrawAttackOnEnemy;
    private static int fourthWithdrawAttackOnMe;
    private static int fifthDontTakeAChanceToAttack;
    private static int sixthDontTakeAChanceToBeUnderAttack;
    private static int seventhDontTakeAChanceToWithdrawAttackOnEnemy;
    private static int eighthDontTakeAChanceToWithdrawAttackOnMe;
    private Color color;
    private final static int NUMBER_OF_THREADS = 10;

    public Parameters(Color color) {
        this.color = color;
    }

    public static void countFirstParameterForSpecialTurns(int turnsNumber){
        Turn turn = Board.getInstance().getPossibleTurnsAndKillings().get(turnsNumber);
        int numberOfFiguresIAttack = turn.getFigure().getWhoCouldBeKilled().size();
    }

}
