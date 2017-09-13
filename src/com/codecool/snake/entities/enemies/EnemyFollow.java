package com.codecool.snake.entities.enemies;

        import com.codecool.snake.entities.GameEntity;
        import com.codecool.snake.Globals;
        import com.codecool.snake.entities.Animatable;
        import com.codecool.snake.Utils;
        import com.codecool.snake.entities.Interactable;
        import com.codecool.snake.entities.snakes.SnakeHead;
        import javafx.geometry.Point2D;
        import javafx.scene.layout.Pane;

        import java.util.Random;

public class EnemyFollow extends GameEntity implements Animatable, Interactable{

    private Point2D heading;
    private static final int damage = 10;
    private static final int speed = 1;

    public EnemyFollow(Pane pane) {
        super(pane);

        setImage(Globals.followEnemy);
        pane.getChildren().add(this);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        int direction = Utils.coordinatesToDirection(new Point2D(getX(), getY()), Globals.snakeHeadEntity.getPlace() );
        heading = Utils.headingVectorFromCoords(new Point2D(getX(), getY()), Globals.snakeHeadEntity.getPlace(), speed);
        setRotate(direction);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

}
