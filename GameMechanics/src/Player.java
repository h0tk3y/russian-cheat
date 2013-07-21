public abstract class Player {
    private boolean[] cards;

    private String name;

    public String getName() {
        return name;
    }
    //TODO: переписать FirstTurn, DependentTurn и их результаты, чтобы declaredCard было типа Card.CardValue

    abstract public FirstTurnResult firstTurn();

    abstract public DependentTurnResult dependentTurn(int declaredCard, int cardsOnBoardCount, int actualCardsCount);

    public class DeckException extends Exception {
        Player problemPlayer;
        int problemCard;

        DeckException(String msg, int card) {
            super(msg);
            problemPlayer = Player.this;
            problemCard = card;
        }
    }

    class FirstTurnResult {
        int declaredCardValue;
        int[] cards;
    }

    class DependentTurnResult {
        boolean isChecking;
        int cardToCheck;
        int[] cards;
    }

    public void takeCard(int card) throws DeckException {
        if (!cards[card])
            cards[card] = true;
        else {
            throw new DeckException("The player's deck already contains the card", card);
        }
    }

    public boolean hasCards() {
        boolean result = false;
        for (int i = 0; i < cards.length && !result; i++)
            result |= cards[i];
        return result;
    }

    //card is 52-cards deck card value
    public boolean hasCard(int card) {
        return cards[card];
    }

    public int cardsOfValue(Card.CardValue value) {
        int count = 0;
        for (Card.CardSuit suit : Card.CardSuit.values()) {
            if (hasCard(Card.getCardIndex(value, suit)))
                count++;
        }
        return count;
    }
}