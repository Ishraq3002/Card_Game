import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class CardGame implements Game {
        private List<Card> cards;

        private List<Player> players = new ArrayList<Player>();

        private Map<Player, List<Card>> cardsPlayerMap = new HashMap<Player, List<Card>>();
        private int currentPlayerIndex = 0;

        private static final int numberOfCardsPerPlayer = 4;

        private int numberOfPlayers = 2;

        public int getNumberOfPlayers() {
                return numberOfPlayers;
        }

        public List<Player> getPlayers() {
                return players;
        }

        public CardGame() {
                cards = Card.getPackOfCards();
        }

        public void distributeCardsForPlayers(List<Player> play) {
                this.players = play;
                Card.shuffleCards(cards);
                if (cardsPlayerMap.size() == 0)
                        cardsPlayerMap.clear();

                int m = 0;
                for (Player pl : players) {
                        pl.setPoints(0);
                        List<Card> lcard = new ArrayList<Card>();
                        int cardLimit = m + numberOfCardsPerPlayer;
                        for (int i = m; i < cardLimit; i++) {
                                lcard.add(cards.get(i));
                        }
                        m = cardLimit;
                        cardsPlayerMap.put(pl, lcard);
                }
        }

        public void playGame(int numberOfPlayers) {
                this.numberOfPlayers = numberOfPlayers;
                createMultipleUser(numberOfPlayers);
                int i = 0;
                System.out.println("Game Started... ");
                List<Card> selectCards = new ArrayList<Card>();
                Card maxCard = null;
                Player maxPlayer = new Player(0);
                distributeCardsForPlayers(players);
                for (int j = 0; j < numberOfCardsPerPlayer; j++) {
                        int n = 0;
                        do {
                                Player player = getNextPlayer();
                                System.out.println("\n1. Display Available Cards  \n2. Stop Game");
                                System.out.println("\nChance for Player -> " + player.getPlayerId());
                                System.out.print("Please select your option : ");

                                Scanner input = new Scanner(System.in);
                                i = input.nextInt();
                                
                              

                                switch (i) {
                                        case 1:
                                                displayCardsForPlayer(player);
                                                System.out.println("\nSelect any of the card from above options (should be from 1 to 4) : ");

                                                input = new Scanner(System.in);
                                                int m = input.nextInt();
                                                if(m >= 1 && m <= 4){
                                                        Card c = cardsPlayerMap.get(player).get(m - 1);
                                                        System.out.println("Card Selected -> " + c.toString());
                                                        cardsPlayerMap.get(player).remove(m - 1);
                                                        if (maxCard == null) {
                                                                maxCard = c;
                                                                maxPlayer = player;
                                                        } else {
                                                                if (maxCard.compareTo(c) < 0) {
                                                                        maxCard = c;
                                                                        maxPlayer = player;
                                                                }
                                                        }
                                                        selectCards.add(c);
                                                } else {
                                                        System.out.println("Please provide input in between 1 to 4");
                                                }
                                                break;
                                        case 2:
                                                return;
                                }

                                System.out.println();
                                n++;
                        } while (n < players.size());
                        if (maxPlayer.getPlayerId() > 0)
                                maxPlayer.setPoints((maxPlayer.getPoints()) + 1);
                        maxCard = null;
                        maxPlayer = null;
                        displayScores();
                }
        }

        private void displayScores() {
                for (Player pl : players) {
                        System.out.println("Player " + pl.getPlayerId() + " Score -> " + pl.getPoints());
                }

        }

        private void displayCardsForPlayer(Player pl) {
                int cards = cardsPlayerMap.get(pl).size();
                for (int i = 0; i < cards;) {
                        System.out.print((++i) + "  ");
                }
        }

        public void displayWinners() {
                Collections.sort(players);
                int maxPoints = 0;
                Map<String, List<Player>> playerPointsMap = new TreeMap<String, List<Player>>();
                for (Player p : players) {

                        maxPoints = p.getPoints();
                        if (playerPointsMap.get(maxPoints + "") != null) {
                                List<Player> first = playerPointsMap.get(maxPoints + "");
                                first.add(p);
                                playerPointsMap.put(maxPoints + "", first);
                        } else {
                                List<Player> first = new ArrayList<Player>();
                                first.add(p);
                                playerPointsMap.put(maxPoints + "", first);
                        }
                }
                
                String points =  Integer.valueOf(players.get(players.size() - 1).getPoints()).toString();
                if (playerPointsMap.get(points) != null && playerPointsMap.get(points).size() > 1) {
                        System.out.println("It's a draw among the following players- ");
                        for (Player p : players) {
                                System.out.println("Player -> " + p.getPlayerId());
                        }
                } else if (playerPointsMap.get(points) != null) {
                        System.out.println("And the winner is : Player -> " + playerPointsMap.get(points).get(0).getPlayerId());
                }
        }

        private void createMultipleUser(int j) {
                if (players.size() != 0) {
                        players.clear();
                }

                for (int i = 0; i < j; i++) {
                        int id = i + 1;
                        Player user = new Player(id);
                        players.add(user);
                }
                distributeCardsForPlayers(players);
        }

        private Player getNextPlayer() {

                Player p = null;
                if (currentPlayerIndex == players.size()) {
                        currentPlayerIndex = 1;
                        p = players.get(0);
                } else {
                        p = players.get(currentPlayerIndex);
                        currentPlayerIndex++;
                }

                return p;
        }
}