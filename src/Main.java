public class Main {

    public static void main(String[] args) {
        SmartRobot robot = new SmartRobot();
        robot.moveDown();
        robot.moveDown();
        robot.moveLeft();
        robot.moveUp();
        robot.moveDown();
        robot.moveLeft();
        robot.moveLeft();

        System.out.println("Координаты робота: " + robot.getX() + ":" + robot.getY());
        System.out.println("Количество шагов: " + robot.getStepsCount());
    }
}

class Robot {
    private int x;
    private int y;

    public void moveRight() {
        x++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveUp() {
        y--;
    }

    public void moveDown() {
        y++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class SmartRobot extends Robot {
    private int countStep = 0;

    public void moveRight() {
        super.moveRight();
        countStep++;
    }

    public void moveLeft() {
        super.moveLeft();
        countStep++;
    }

    public void moveUp() {
        super.moveUp();
        countStep++;
    }

    public void moveDown() {
        super.moveDown();
        countStep++;
    }

    public int getStepsCount() {
        return countStep;
    }
}