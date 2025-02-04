package com.vwdigitalhub.cleaningrobots.domain;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.CoordinatesOutOfSquareException;
import com.vwdigitalhub.cleaningrobots.domain.exceptions.RobotInTheSamePositionException;
import com.vwdigitalhub.cleaningrobots.domain.interfaces.Observer;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.Cardinal;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.Coordinate;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.MovementLetter;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.OrientedPosition;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.RobotMovements;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.RobotPosition;
import java.util.Objects;

public final class Robot implements Observer {

    private final Square square;
    private RobotPosition robotPosition;
    private Cardinal cardinal;
    private final RobotMovements robotMovements;

    public Robot(OrientedPosition orientedPosition, RobotMovements robotMovements, Square square){
        this.robotPosition = orientedPosition.getRobotPosition();
        this.cardinal = orientedPosition.getCardinal();
        this.robotMovements = robotMovements;
        this.square = square;
    }

    public RobotPosition getRobotPosition() {
        return robotPosition;
    }
    public Cardinal getCardinal() {
        return cardinal;
    }

    private void move(MovementLetter letter){
        if ( letter == MovementLetter.MOVE){
            int x = robotPosition.getX();
            int y = robotPosition.getY();

            switch (this.cardinal) {
                case EAST -> x++;
                case WEST -> x--;
                case NORTH -> y++;
                case SOUTH -> y--;
            }
            RobotPosition newRobotPosition = new RobotPosition(x, y);
            validatePositions(newRobotPosition);

            robotPosition = newRobotPosition;
        } else {
            cardinal = getNewCardinal(letter);
        }

    }

    private void validatePositions(RobotPosition robotPosition) {
        validateInSquare(robotPosition);
        square.movementTo(robotPosition);
    }

    private void validateInSquare(Coordinate coordinate) {
        if( this.square.isOutOfArea(coordinate)){
            throw new CoordinatesOutOfSquareException("Robot can't move out of the square");
        }
    }

    private Cardinal getNewCardinal(MovementLetter letter) {
        Cardinal currentCardinal = cardinal;
        int offset = (letter == MovementLetter.RIGHT) ? 1 : -1;
        Cardinal[] cardinals = Cardinal.values();
        int currentOrdinal = currentCardinal.ordinal();
        int newOrdinal = (currentOrdinal + offset + cardinals.length) % cardinals.length;
        return cardinals[newOrdinal];
    }

    public void movement(){
        robotMovements.getMovementLetterList().forEach(this::move);
    }

    public String getPosition(){
        return String.format("%d%d%s",
                getRobotPosition().getX(), getRobotPosition().getY(), getCardinal().getValue()
        );
    }

    @Override
    public void isConflictWithCoordinate(Coordinate coordinate) {
        if(coordinate.equals(robotPosition)){
            throw new RobotInTheSamePositionException("there is another robot in the same position: "+coordinate);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(robotPosition, robot.robotPosition)
                && Objects.equals(cardinal, robot.cardinal)
                && Objects.equals(robotMovements, robot.robotMovements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(robotPosition, cardinal, robotMovements);
    }
}
