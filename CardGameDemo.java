import java.util.Scanner;

public class CardGameDemo {
        public CardGameDemo() {

        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                CardGame game = new CardGame();

                System.out.println("Card Game \nPlayer Options:-");
                System.out.println("1. Start Game \n2. End Game");
                System.out.print("Please select an option : ");

                int i = 1;

                while (i != 0) {
                        Scanner input = new Scanner(System.in);
                        i = input.nextInt();

                        switch (i) {
                                case 1:
                                        System.out.println("Provide the Number of Players (should be between 2 to 4) : ");
                                        input = new Scanner(System.in);
                                        i = input.nextInt();
                                        if(i > 1 && i <= 4){
                                                game.playGame(i);
                                                game.displayWinners();
                                        } else {
                                                System.out.println("Error! Please provide input between 2 to 4");
                                        }
                                        break;

                                case 2:
                                        System.exit(0);

                        }
                        if(i != 1 && i != 2){
                                System.out.println("Error! Please choose 1 or 2");     
                        }

                        System.out.println();
                        System.out.println("Card Game \nSelect User Options");
                        System.out.println("1. Start Game \n2. End Game");
                        System.out.print("Please select an option : ");

                }

        }
}