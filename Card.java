import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card implements Comparable<Card> {
        private Card() {
        }

        public enum CardNumber {
                TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12),
                KING(13), ACE(14);

                private int order;

                private CardNumber(int i) {
                        this.order = i;
                }

                /**
                 * Returns the ordinal position of the enum
                 *
                 * @return int order
                 */
                public int getOrder() {
                        return order;
                }
        }

        public enum CardType {
                CLUB, DIAMOND, HEARTS, SPADE;
        }

        private CardNumber cardNum;
        private CardType cardType;

        public CardNumber getCardNum() {
                return cardNum;
        }

        public CardType getCardType() {
                return cardType;
        }

        public static List<Card> getPackOfCards() {
                List<Card> cardList = new ArrayList<Card>();

                for (CardType type : CardType.values()) {
                        for (CardNumber cdNum : CardNumber.values()) {
                                Card cd = new Card();
                                cd.cardNum = cdNum;
                                cd.cardType = type;
                                cardList.add(cd);
                        }
                }
                return cardList;
        }

        public static void shuffleCards(List<Card> cards) {
                Collections.shuffle(cards);
        }

        @Override
        public int compareTo(Card x) {
                if (this.getCardNum() == x.getCardNum()) {
                        return 0;
                } else if (this.getCardNum().getOrder() > x.getCardNum().getOrder()) {
                        return 1;
                } else
                        return -1;
        }

        @Override
        public String toString() {
                return "Card Number = " + cardNum + ", Card Type = " + cardType ;
        }
}