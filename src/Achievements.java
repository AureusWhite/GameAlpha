import java.util.HashMap;
import java.util.HashSet;

public class Achievements {
    private final String name, description;

    private boolean unlocked, completed;
    private final int points;
    private final HashMap<Item, Boolean> requiredItems = new HashMap<>();
    private final HashMap<Room, Boolean> requiredPlaces = new HashMap<>();
    private final HashMap<PlayerStatus, Boolean> requiredStatus = new HashMap<>();
    private final HashSet<Achievements> requiredAchievements = new HashSet<>();
    private final HashMap<Equipment, Boolean> requiredEquipment = new HashMap<>();
    private final HashMap<NPC, NPCStatus> requiredNPC = new HashMap<>();

    public Achievements(String name, String description, int points) {
        this.name = name;
        this.description = description;
        this.points = points;
        this.unlocked = false;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public int getPoints() {
        return points;
    }

    public boolean checkAchievements() {
        for (Boolean collected1 : requiredItems.values()) {
            if (!collected1) {
                return false;
            }
        }
        for (Boolean visited1 : requiredPlaces.values()) {
            if (!visited1) {
                return false;
            }
        }
        for (Boolean status1 : requiredStatus.values()) {
            if (!status1) {
                return false;
            }
        }

        for (Achievements achievement : requiredAchievements) {
            if (!achievement.isUnlocked()) {
                return false;
            }
        }
        for (Boolean equipped1 : requiredEquipment.values()) {
            if (!equipped1) {
                return false;
            }
        }
        for (NPCStatus status : requiredNPC.values()) {
            if (status == NPCStatus.NOT_SPOKEN_TO) {
                return false;
            }
        }

        return true;
    }

    public void updateAchievement() {
        if (checkAchievements()) {
            this.setUnlocked(true);
            System.out.println("Achievement unlocked: " + this.name);
        }
    }

    public void markRoomVisited(Room room) {
        if (requiredPlaces.containsKey(room)) {
            requiredPlaces.put(room, true);
            updateAchievement();
        }
    }

    public void markEquipmentEquipped(Equipment equipment) {
        if (requiredEquipment.containsKey(equipment)) {
            requiredEquipment.put(equipment, true);
            updateAchievement();
        }
    }

    public void openItem(Item item) {
        if (requiredItems.containsKey(item)) {
            requiredItems.put(item, true);
            updateAchievement();
        }
    }

    public void markItemCollected(Item item) {
        if (requiredItems.containsKey(item)) {
            requiredItems.put(item, true);
            updateAchievement();
        }
    }

    public void markNPCSpokenTo(NPC npc) {
        if (requiredNPC.containsKey(npc)) {
            requiredNPC.put(npc, NPCStatus.SPOKEN_TO);
            updateAchievement();
        }
    }

    public void markNPCBefriended(NPC npc) {
        if (requiredNPC.containsKey(npc)) {
            requiredNPC.put(npc, NPCStatus.FRIEND);
            updateAchievement();
        }
    }

    public void markNPCEnemy(NPC npc) {
        if (requiredNPC.containsKey(npc)) {
            requiredNPC.put(npc, NPCStatus.ENEMY);
            updateAchievement();
        }
    }

    public void addRequiredItem(Item item) {
        requiredItems.put(item, false); 
    }

    public void addRequiredPlace(Room room) {
        requiredPlaces.put(room, false); 
    }

    public void addRequiredStatus(PlayerStatus status) {
        requiredStatus.put(status, false); 
    }

    public void addRequiredEquipment(Equipment equipment) {
        requiredEquipment.put(equipment, false);
    }

    public void addRequiredNPC(NPC npc, NPCStatus status) {
        requiredNPC.put(npc, NPCStatus.NOT_SPOKEN_TO);
        requiredNPC.put(npc,status);
    } 

    public HashMap<NPC, NPCStatus> getRequiredNPC() {
        return requiredNPC; 
    }

    public HashMap<Room, Boolean> getRequiredPlaces() {
        return requiredPlaces;
    }

    public HashMap<Item, Boolean> getRequiredItems() {
        return requiredItems;
    }

    public HashMap<Equipment, Boolean> getRequiredEquipment() {
        return requiredEquipment;
    }

    public HashSet<Achievements> getRequiredAchievements() {
        return requiredAchievements;
    }

    public HashMap<PlayerStatus, Boolean> getRequiredStatus() {
        return requiredStatus;
    }

    public boolean checkNPCs() {
        for (NPCStatus status : requiredNPC.values()) {
            if (status == NPCStatus.NOT_SPOKEN_TO) {
                return false;  // Not all NPC conditions are met
            }
        }
        return true;
    }

    public boolean checkNPCAchievements() {
        return checkNPCs() && checkAchievements();
    }

    @Override
    public String toString() {
        return "Achievements [name=" + name + ", description=" + description + ", unlocked=" + unlocked + ", points="
                + points + ", requiredItems=" + requiredItems + ", requiredPlaces=" + requiredPlaces
                + ", requiredStatus=" + requiredStatus + ", requiredAchievements=" + requiredAchievements
                + ", requiredNPC=" + requiredNPC + ", requiredEquipment=" + requiredEquipment + "]";
    }

    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
