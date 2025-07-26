import java.util.Scanner;

public class UI {

    private Boolean turn;
    
    public void run() {
        Engine engine;
        String response;
        System.out.println("Welcome");
        System.out.println("Would you like to go first?. Respond y or n");
        Scanner scanner = new Scanner(System.in);
        response = scanner.nextLine();
        if  (response.equals("y")) {
            turn = true;
            engine = new Engine(false);
            System.out.println("What move would you like to play");
            int move = scanner.nextInt();
            try {
                engine.move(move, true);
            } catch (Exception e) {
                System.out.println("ok");
            }
        } else {
            turn = false;
            engine = new Engine(true);
        }

        while (true) {
            turn = !turn;
            Integer compMove = engine.run();
            System.out.println("The computer plays: " + compMove);
            System.out.println(engine.getState());
            System.out.println("------------------------");
            System.out.println();
            engine.move(compMove, turn);
            if (engine.checkIfWinner(turn)) {
                System.out.println("The computer won.");
                break;
            }
            if (engine.checkIfDraw(turn)) {
                System.out.println("You drew.");
                break;
            }
            turn = !turn;
             System.out.println("What move would you like to play");
            Integer move = scanner.nextInt();
            System.out.println("------------------------");
            System.out.println();
            engine.move(move, turn);
            if (engine.checkIfWinner(turn)) {
                System.out.println("You won!");
                break;
            }
            if (engine.checkIfDraw(turn)) {
                System.out.println("You drew.");
                break;
            }
        }       
    }
}
