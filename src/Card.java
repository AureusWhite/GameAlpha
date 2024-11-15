 
import java.util.function.BiConsumer;

public class Card {
    private final String name;
    private final String type;
    private final BiConsumer<Paw, Paw> action;

    public Card(String name, String type, BiConsumer<Paw, Paw> action) {
        this.name = name;
        this.type = type;
        this.action = action;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public void play(Paw playerPaw, Paw opponentPaw) {
        action.accept(playerPaw, opponentPaw);  // Execute the action
    }
    public BiConsumer<Paw, Paw> getAction() {
        return action;
    }
    @Override
    public String toString() {
        return "Card [name=" + name + ", type=" + type + "]";
    }
}
