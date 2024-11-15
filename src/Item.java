import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Item {

    private int saturation;
    protected String name;
    protected String description;
    private int price;
    private final HashMap<ItemCondition, Boolean> conditions = new HashMap<>();
    private final ArrayList<Item> inventory = new ArrayList<>();
    private final HashMap<ItemType, Boolean> types = new HashMap<>();
    private final ArrayList<Quest> quests = new ArrayList<>();

    public HashMap<ItemCondition, Boolean> getConditions() {
        return this.conditions;
    }

    public HashMap<ItemType, Boolean> getTypes() {
        return this.types;
    }

    public Item(String name, String description, String type, boolean takable) {
        this.name = name;
        this.description = description;
        this.price = 0;
        this.types.put(ItemType.TAKEABLE, takable);
    }

    public boolean isBroken() {
        if (this.conditions.containsKey(ItemCondition.BROKEN)) {
            return this.conditions.get(ItemCondition.BROKEN);
        }
        return false;
    }

    public void setBroken(boolean b) {
        this.conditions.put(ItemCondition.BROKEN, b);
    }

    public boolean isLocked() {
        if (this.conditions.containsKey(ItemCondition.LOCKED)) {
            return this.conditions.get(ItemCondition.LOCKED);
        }
        return false;
    }

    public void setLocked(boolean locked) {
        this.conditions.put(ItemCondition.LOCKED, locked);
    }

    public boolean isTakable() {
        if (this.types.containsKey(ItemType.TAKEABLE)) {
            return this.types.get(ItemType.TAKEABLE);
        }
        return false;
    }

    public int getSaturation() {
        return this.saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public void setTakable(boolean takable) {
        this.types.put(ItemType.TAKEABLE, takable);
    }

    public void use() {
        if (this.types.containsKey(ItemType.BOOK)) {
            GameHandler.getGui().display("You read the " + this.getName(), "Black");
            GameHandler.getGui().display(((Book) this).Read(), "Black");

        }
        if (this.getClass().equals(Container.class)) {
            String[] choices = this.getItemChoices();
            String choice = JOptionPane.showInputDialog(null, "Choose an item to take", "Dispencer", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]).toString();
            if (choice.equals("Close")) {
                return;
            }
            for (Item item : this.inventory) {
                if (item.getName().equals(choice)) {
                    GameHandler.getGui().display("You take the " + item.getName(), "Black");
                    Player.addItem(item);
                    this.removeItem(item);
                    return;
                }
            }
        } else if (this.types.containsKey(ItemType.FOOD) || this.types.containsKey(ItemType.DRINK)) {
            if (this.types.containsKey(ItemType.FOOD)) {
                Player.setHunger(Player.getHunger() + this.getSaturation());
                GameHandler.getGui().display("Saturation: " + this.getSaturation(), "Black");
                GameHandler.getGui().display("You eat the " + this.getName(), "Black");
                GameHandler.getGui().display("You are now " + Player.getHunger() + "% full", "Black");
            } else if (this.types.containsKey(ItemType.DRINK)) {
                Player.setThirst(Player.getThirst() + this.getSaturation());
                GameHandler.getGui().display("Saturation: " + this.getSaturation(), "Black");
                GameHandler.getGui().display("You drink the " + this.getName(), "Black");
                GameHandler.getGui().display("You are now " + Player.getThirst() + "% quenched", "Black");
                Player.setBlatter(Player.getBlatter() + this.getSaturation());
            }
        } else if (this.types.containsKey(ItemType.DISPENCER)) {
            String[] choices = getItemChoices();
            String choice = JOptionPane.showInputDialog(null, "Choose an item to take", "Dispencer", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]).toString();
            if (choice.equals("Close")) {
                return;
            }
            for (Item item : this.inventory) {
                if (item.getName().equals(choice)) {
                    GameHandler.getGui().display("You take the " + item.getName(), "Black");
                    Player.addItem(item);
                    this.removeItem(item);
                    return;
                }
            }
        } else if (this.types.containsKey(ItemType.TOY)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.types.containsKey(ItemType.TEST)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.types.containsKey(ItemType.KEY)) {
            String[] choices = Player.getRoom().getExitChoises();
            String choice = JOptionPane.showInputDialog(null, "Choose a door to unlock", "Unlock", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]).toString();
            if (choice.equals("Close")) {
                //return;
            }
            /*if (!Player.getRoom().getExit(choice).isLocked()) {
                GameHandler.getGui().display("The " + choice + " door is already unlocked", "Black");
            } else if (Player.getRoom().getExit(choice).isLocked()) {
                if (this.getName().equals(Player.getRoom().getExit(choice).getKey())) {
                    Player.getRoom().getExit(choice).setLocked(false);
                    GameHandler.getGui().display("You unlock the " + choice + " door", "Black");
                    Player.removeItem(this);
                } else {
                    GameHandler.getGui().display("The " + choice + " door is locked, and you do not have the key.", "Black");
                }
            } else {
                GameHandler.getGui().display("The " + choice + " door is already unlocked", "Black");
            }
             */
        } else if (this.types.containsKey(ItemType.CLOTHING)) {
            GameHandler.getGui().display("Use equip..", "Black");

        } else if (this.types.containsKey(ItemType.SEAT)) {
            GameHandler.getGui().display("You sit on the " + this.getName(), "Black");
        } else if (this.types.containsKey(ItemType.CONTRABAND)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.types.containsKey(ItemType.DROPPABLE)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.VANDELIZED)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.types.containsKey(ItemType.INTERACTABLE)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.BROKEN)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.conditions.containsKey(ItemCondition.LOCKED)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
            Player.removeItem(this);
        } else if (this.types.containsKey(ItemType.TAKEABLE)) {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
        } else if (this.types.containsKey(ItemType.DRINK)) {
        } else {
            GameHandler.getGui().display("You use the " + this.getName(), "Black");
        }

    }

    public void setContraband(boolean b) {
        this.types.put(ItemType.CONTRABAND, b);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public void setDescription(String description1) {
        this.description = description1;
    }

    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }

    public boolean isDroppable() {
        if (this.types.containsKey(ItemType.DROPPABLE)) {
            return this.types.get(ItemType.DROPPABLE);
        }
        return true;
    }

    public void setDroppable(boolean b) {
        this.types.put(ItemType.DROPPABLE, b);
    }

    public boolean isContraband() {
        if (this.types.containsKey(ItemType.CONTRABAND)) {
            return this.types.get(ItemType.CONTRABAND);
        }
        return false;
    }

    public void setVandalized(boolean b) {
        this.conditions.put(ItemCondition.VANDELIZED, b);
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    void addItem(Item item) {
        this.inventory.add(item);
    }

    public void interact() {
        if (this.types.containsKey(ItemType.INTERACTABLE)) {
            switch (this.getName()) {
                case "Food Tray Dispenser" -> {
                    GameHandler.makeFoodTray();
                    String[] choices = getItemChoices();
                    String choice = JOptionPane.showInputDialog(null, "Choose an item to take", "Dispencer", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]).toString();
                    if (choice.equals("Close")) {
                        return;
                    }
                    for (Item item : this.inventory) {
                        if (item.getName().equals(choice)) {
                            GameHandler.getGui().display("You take the " + item.getName(), "Black");
                            Player.addItem(item);
                            this.removeItem(item);
                            return;
                        }
                    }
                }
                case "Quest Board" -> {
                    ArrayList<Quest> toRemove = new ArrayList<>();
                    GameHandler.getGui().display("You read the quest board", "Black");
                    String[] options = {"Turn in", "View Quests"};
                    String option = JOptionPane.showInputDialog(null, "What do you want to do?", "Quest Board", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();
                    switch (option) {
                        case "Turn in" -> {
                            if (Player.getQuests().isEmpty()) {
                                GameHandler.getGui().display("You have no quests to turn in", "Black");
                                return;
                            }
                            String[] whichQuest = new String[Player.getQuests().size()];
                            for (int i = 0; i < Player.getQuests().size(); i++) {
                                whichQuest[i] = Player.getQuests().get(i).getName();
                            }
                            String quest = JOptionPane.showInputDialog(null, "Which quest do you want to turn in?", "Quest Board", JOptionPane.QUESTION_MESSAGE, null, whichQuest, whichQuest[0]).toString();
                            for (Quest q : Player.getQuests()) {
                                if (q.getName().equals(quest)) {
                                    if (q.checkCompleted()) {
                                        GameHandler.getGui().display("You completed the quest", "Black");
                                        Player.addXP(q.getDifficulty() + 5);
                                        this.removeQuest(q);
                                        toRemove.add(q);
                                    } else {
                                        GameHandler.getGui().display("You did not complete the quest", "Black");
                                    }
                                }
                            }
                            Player.getQuests().removeAll(toRemove);
                        }
                        case "View Quests" -> {
                            if (this.getQuests().isEmpty()) {
                                GameHandler.generateRandomQuests(this);
                            }
                            for (Quest avalableQuests : this.getQuests()) {
                                GameHandler.getGui().display(avalableQuests.getName() + ": " + avalableQuests.getDescription(), "Black");

                                String[] answer = {"Yes", "No", "Close"};
                                String answer1 = JOptionPane.showInputDialog(null, "Do you want to accept the quest?", "Quest Board", JOptionPane.QUESTION_MESSAGE, null, answer, answer[0]).toString();
                                if (answer1.equals("Yes")) {
                                    Player.addQuest(avalableQuests);
                                } else {
                                    GameHandler.getGui().display("You did not accept the quest", "Black");
                                }
                            }
                        }
                        default ->
                            GameHandler.getGui().display("You use the " + this.getName(), "Black");
                    }
                }
            }
        }
    }

    public void removeItem(Item item) {
        if (!item.isTypeMet(ItemType.UNREMOVABLE)) {
            this.inventory.remove(item);
        }
    }

    public boolean isConditionMet(ItemCondition condition) {
        if (this.conditions.containsKey(condition)) {
            return this.conditions.get(condition);
        }
        return false;
    }

    public boolean isTypeMet(ItemType type) {
        if (this.types.containsKey(type)) {
            return this.types.get(type);
        }
        return false;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void displayInventory() {
        for (Item item : this.inventory) {
            GameHandler.getGui().display(item.getName(), "Black");
        }
    }

    private String[] getItemChoices() {
        ArrayList<String> choices = new ArrayList<>();
        for (Item item : this.inventory) {
            choices.add(item.getName());
        }
        choices.add("Close");
        return choices.toArray(String[]::new);
    }

    boolean isVandalized() {
        if (this.conditions.containsKey(ItemCondition.VANDELIZED)) {
            return this.conditions.get(ItemCondition.VANDELIZED);
        }
        return false;
    }

    ArrayList<Quest> getQuests() {
        return this.quests;
    }

    void addQuest(Quest quest) {
        this.quests.add(quest);
    }

    private void removeQuest(Quest q) {
        this.quests.remove(q);
    }
}
