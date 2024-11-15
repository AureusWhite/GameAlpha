
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class GameHandler {

    private static final Map<String, Room> rooms = new HashMap<>();
    static final Map<String, NPC> npcs = new HashMap<>();
    static final Map<String, Item> items = new HashMap<>();
    private static final ArrayList<Quest> quests = new ArrayList<>();

    public static Room room;
    private static GUI gui;
    private static Game game;
    public static String fileSection2 = "";
    public static String fileSection3 = "";
    public static String fileSection4 = "";
    private final static ArrayList<Achievements> achievements = new ArrayList<>();
    static Random rand = new Random();

    public static String toSentenceCase(String name) {
        name = name.toLowerCase();
        name = name.replace("_", " ");
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    static void storyTime() {
        Events event = Events.STORY_TIME;
        event.display();
    }

    private Container box, foodTray;

    public Room recoveryRoom, kitchen, mainRoom,
            dorms, bathroom, hallway, stairs, basement,
            attic, garage, garden, driveway, frontYard,
            backYard, shed, pool, patio, deck, porch,
            balcony, cubbies, dramaArea, changingRoom,
            floorPlay, quietArea, homeWorkArea, playHouse,
            treeHouse, storyBookVillage, pillowPile,
            snackArea, greenHall, blueHall, redHall,
            peddleToys, lemonaidStand, toolShed, TRSRoom,
            janitorialRoom, foyer, pantry, roof, demoRoom, cogLabs, classRoom;

    private Equipment trainingPants, diaper, thickDiapers, backPack, underPants, uniformTop, uniformBottom, uniformHat, uniformShoes, uniBackPack;

    private Item toy, trash, modelingClay, coloringBook, crayons,
            markers, paint, paintBrush, glue, scissors, paper, pencil, eraser,
            calculator, ruler, scale, thermometer, magnifyingGlass, telescope, microscope,
            binoculars, camera, videoCamera, tapeRecorder, radio, television,
            computer, tablet, phone, speaker, headphones, microphone, keyboard,
            mouse, monitor, printer, scanner, projector, whiteboard, chalkboard,
            smartboard, globe, map, calendar, compass, protractor, nulearPotato,
            canvas, snackShop, pickapaw, pawFigure, limitedEditionPaw, questBoard,
            mess, spill, puddle, mcGuffin, jukeBox, smashedDollOnAStick, cheese, stick,
            cheeseOnAStick, diaperOnAStick, rock, stickOnAStick, cheezeOnAStick, cheeseCoveredRock,
            diaperWithRock, diaperWithCheese, doll, dollWithCheese, dollWithDiaper, smashedDoll,
            smashedDollWithCheeze;

    private static Item foodTrayDispenser;
    private Puzzle jigSaw, crossword, shapes, colors;
    private Book marytherejuve, theAdventuresOfFuzzy, theAdventuresOfFuzzy2, SoYoureARejuve, theAdventuresOfFuzzy3, careforrejuves;
    private Furniture boosterSeat;
    private NPC msSagely, dawn, taliber, susy, farah, drWhite, msWhite, aureus,
            jessiem, researchStudent1, researchStudent2, jimthejanitor, joy, jessief, jim, fuzzy;

    public void createItems() {

        boosterSeat = new Furniture(FurnitureType.BOOSTERSEAT, "Booster Seat", "A booster seat for you to sit on.", "Sit on the booster seat.");
        items.put("Booster Seat", boosterSeat);
        boosterSeat.getTypes().put(ItemType.TAKEABLE, true);
        boosterSeat.getTypes().put(ItemType.FURNITURE, true);
        boosterSeat.getTypes().put(ItemType.SEAT, true);

        jigSaw = new Puzzle("Jig Saw", "A puzzle that requires fine motor skills", 0, "Jigsaw");
        items.put("Jig Saw", jigSaw);
        jigSaw.getTypes().put(ItemType.TAKEABLE, true);
        jigSaw.getTypes().put(ItemType.PUZZLE, true);
        jigSaw.getTypes().put(ItemType.TOY, true);

        crossword = new Puzzle("Crossword", "A puzzle that requires cognitive skills", 0, "Crossword");
        items.put("Crossword", crossword);
        crossword.getTypes().put(ItemType.TAKEABLE, true);
        crossword.getTypes().put(ItemType.PUZZLE, true);
        crossword.getTypes().put(ItemType.TOY, true);

        shapes = new Puzzle("Shapes", "A puzzle that requires gross motor skills", 0, "Shapes");
        items.put("Shapes", shapes);
        shapes.getTypes().put(ItemType.TAKEABLE, true);
        shapes.getTypes().put(ItemType.PUZZLE, true);
        shapes.getTypes().put(ItemType.TOY, true);

        colors = new Puzzle("Colors", "A puzzle that requires cognitive skills", 0, "Colors");
        items.put("Colors", colors);
        colors.getTypes().put(ItemType.TAKEABLE, true);
        colors.getTypes().put(ItemType.PUZZLE, true);
        colors.getTypes().put(ItemType.TOY, true);

        smashedDollOnAStick = new Item("Smashed Doll on a Stick", "A stick with a smashed doll on it", "Crafting", true);
        items.put("Smashed Doll on a Stick", smashedDollOnAStick);

        diaperOnAStick = new Item("Diaper on a Stick", "A stick with a diaper on it", "Crafting", true);
        items.put("Diaper on a Stick", diaperOnAStick);

        rock = new Item("Rock", "A rock", "Crafting", true);
        items.put("Rock", rock);

        stickOnAStick = new Item("Stick on a Stick", "A stick with a stick on it", "Crafting", true);
        items.put("Stick on a Stick", stickOnAStick);

        cheeseCoveredRock = new Item("Cheese Covered Rock", "A rock with cheese on it", "Crafting", true);
        items.put("Cheese Covered Rock", cheeseCoveredRock);

        diaperWithRock = new Item("Diaper with Rock", "A diaper that has a rock inside", "Crafting", true);
        items.put("Diaper with Rock", diaperWithRock);

        diaperWithCheese = new Item("Diaper with Cheese", "A diaper that has cheese inside", "Crafting", true);
        items.put("Diaper with Cheese", diaperWithCheese);

        doll = new Item("Doll", "A doll", "Crafting", true);
        items.put("Doll", doll);

        dollWithCheese = new Item("Doll with Cheese", "A doll with cheese inside", "Crafting", true);
        items.put("Doll with Cheese", dollWithCheese);

        dollWithDiaper = new Item("Doll with Diaper", "A doll with a diaper inside", "Crafting", true);
        items.put("Doll with Diaper", dollWithDiaper);

        smashedDoll = new Item("Smashed Doll", "A doll that has been smashed", "Crafting", true);
        items.put("Smashed Doll", smashedDoll);

        smashedDollWithCheeze = new Item("Smashed Doll with Cheese", "A doll that has been smashed with cheese inside", "Crafting", true);
        items.put("Smashed Doll with Cheese", smashedDollWithCheeze);

        stick = new Item("Stick", "A stick", "Crafting", true);
        items.put("Stick", stick);

        cheese = new Item("Cheese", "A piece of cheese", "Crafting", true);
        items.put("Cheese", cheese);

        cheeseOnAStick = new Item("Cheese on a Stick", "A stick with cheese on it", "Crafting", true);
        items.put("Cheese on a Stick", cheeseOnAStick);

        cheezeOnAStick = new Item("Cheese on a Stick", "A cheese with stick in it", "Crafting", true);
        items.put("Cheese on a Stick", cheezeOnAStick);

        jukeBox = new Item("Juke Box", "A juke box that plays music.", "Music", true);
        items.put("Juke Box", jukeBox);
        jukeBox.getTypes().put(ItemType.TAKEABLE, true);
        jukeBox.getTypes().put(ItemType.MUSIC, true);

        marytherejuve = new Book("Fuzzy: Teddybear, Andiod, Caretaker.", "Mary The Rejuve", "Fiction", 10, "Fuzzy", "Children");
        items.put("Mary The Rejuve", marytherejuve);
        marytherejuve.getTypes().put(ItemType.TAKEABLE, true);
        marytherejuve.getTypes().put(ItemType.BOOK, true);
        marytherejuve.setName("Mary The Rejuve");

        theAdventuresOfFuzzy = new Book("Fuzzy: Teddybear, Andiod, Caretaker.", "The Adventures Of Fuzzy", "Fiction", 10, "Fuzzy", "Children");
        items.put("The Adventures Of Fuzzy", theAdventuresOfFuzzy);
        theAdventuresOfFuzzy.getTypes().put(ItemType.TAKEABLE, true);
        theAdventuresOfFuzzy.getTypes().put(ItemType.BOOK, true);
        theAdventuresOfFuzzy.setName("The Adventures Of Fuzzy");

        theAdventuresOfFuzzy2 = new Book("Fuzzy: Teddybear, Andiod, Caretaker.", "The Adventures Of Fuzzy 2", "Fiction", 10, "Fuzzy", "Children");
        items.put("The Adventures Of Fuzzy 2", theAdventuresOfFuzzy2);
        theAdventuresOfFuzzy2.getTypes().put(ItemType.TAKEABLE, true);
        theAdventuresOfFuzzy2.getTypes().put(ItemType.BOOK, true);
        theAdventuresOfFuzzy2.setName("The Adventures Of Fuzzy 2");

        SoYoureARejuve = new Book("Dr. White.", "I'm a rejuve? WTF happened? and other common questions.", "Non-Fiction", 10, "Fuzzy", "Children");
        items.put("I'm a rejuve? WTF happened? and other common questions", SoYoureARejuve);
        SoYoureARejuve.getTypes().put(ItemType.TAKEABLE, true);
        SoYoureARejuve.getTypes().put(ItemType.BOOK, true);
        SoYoureARejuve.setName("Common Questions For Rejuves");

        theAdventuresOfFuzzy3 = new Book("Fuzzy: Teddybear, Andiod, Caretaker.", "The Adventures Of Fuzzy 3", "Fiction", 10, "Fuzzy", "Children");
        items.put("The Adventures Of Fuzzy 3", theAdventuresOfFuzzy3);
        theAdventuresOfFuzzy3.getTypes().put(ItemType.TAKEABLE, true);
        theAdventuresOfFuzzy3.getTypes().put(ItemType.BOOK, true);
        theAdventuresOfFuzzy3.setName("The Adventures Of Fuzzy 3");

        careforrejuves = new Book("Dr. White.", "Care for Rejuves - an insight into unique physiology and psychology", "Non-Fiction", 10, "Fuzzy", "Young Adult");
        items.put("Care for Rejuves - an insight into unique physiology and psychology", careforrejuves);
        careforrejuves.getTypes().put(ItemType.TAKEABLE, true);
        careforrejuves.getTypes().put(ItemType.BOOK, true);
        careforrejuves.setName("Care for Rejuves - an insight into unique physiology and psychology");

        mess = new Item("Mess", "A mess that needs to be cleaned up.", "Mess", true);
        items.put("Mess", mess);
        mess.getTypes().put(ItemType.TAKEABLE, true);
        mess.getTypes().put(ItemType.TRASH, true);

        spill = new Item("Spill", "A spill that needs to be cleaned up.", "Spill", true);
        items.put("Spill", spill);
        spill.getTypes().put(ItemType.TAKEABLE, false);
        spill.getTypes().put(ItemType.TRASH, true);

        puddle = new Item("Puddle", "A puddle that needs to be cleaned up.", "Puddle", true);
        items.put("Puddle", puddle);
        puddle.getTypes().put(ItemType.TAKEABLE, false);
        puddle.getTypes().put(ItemType.TRASH, true);

        questBoard = new Item("Quest Board", "A board where you can get and return quests.", "Sign", false);
        items.put("Quest Board", questBoard);
        questBoard.getTypes().put(ItemType.TAKEABLE, false);
        questBoard.getTypes().put(ItemType.INTERACTABLE, true);

        mcGuffin = new Item("mcGuffin", "A mysterious object that is the key to the story.", "mcGuffin", true);
        items.put("mcGuffin", mcGuffin);
        nulearPotato = new Item("Nuclear Potato", "A potato that has been exposed to radiation.", "Toy", true);
        items.put("Nuclear Potato", nulearPotato);
        nulearPotato.getTypes().put(ItemType.TAKEABLE, true);
        nulearPotato.getTypes().put(ItemType.TOY, true);

        foodTray = new Container("Food Tray", "A tray for you to eat food on.", "Food Tray", true);
        items.put("Food Tray", foodTray);
        foodTrayDispenser = new Item("Food Tray Dispenser", "A machine that dispenses food trays.", "Machine/Interactable", false);
        items.put("Food Tray Dispenser", foodTrayDispenser);
        foodTrayDispenser.getTypes().put(ItemType.TAKEABLE, false);
        foodTrayDispenser.getTypes().put(ItemType.INTERACTABLE, true);
        foodTrayDispenser.getTypes().put(ItemType.DISPENCER, true);
        pickapaw = new Shops("Pick a Paw", "A shop where you can buy Paws and Paw and Prowess cards.");
        items.put("Pick a Paw", pickapaw);
        pawFigure = new PawFigure();
        items.put("Paw Figure", pawFigure);

        limitedEditionPaw = new Item("Limited Edition Paw", "A limited edition paw.", "Paw", false);
        items.put("Limited Edition Paw", limitedEditionPaw);
        snackShop = new Shops("Snack Shop", "A shop where you can buy snacks.");
        items.put("Snack Shop", snackShop);
        backPack = new Equipment("Back Pack", "A back pack for you to carry things in.", "Equipment");
        items.put("Back Pack", backPack);
        toy = new Item("Toy", "A toy for you to play with.", "Toy", false);
        items.put("Toy", toy);
        coloringBook = new Item("Coloring Book", "A coloring book for you to color in.", "Crafts", true);
        items.put("Coloring Book", coloringBook);
        crayons = new Item("Crayons", "Crayons for you to color with.", "Crafts", true);
        items.put("Crayons", crayons);
        markers = new Item("Markers", "Markers for you to color with.", "Crafts", true);
        items.put("Markers", markers);
        paint = new Item("Paint", "Paint for you to paint with.", "Crafts", true);
        items.put("Paint", paint);
        canvas = new Item("Canvas", "A canvas for you to paint on.", "Crafts", true);
        items.put("Canvas", canvas);
        paintBrush = new Item("Paint Brush", "A paint brush for you to paint with.", "Crafts", true);
        items.put("Paint Brush", paintBrush);
        glue = new Item("Glue", "Glue for you to glue with.", "Crafts", true);
        items.put("Glue", glue);
        scissors = new Item("Scissors", "Scissors for you to cut with.", "Crafts", true);
        items.put("Scissors", scissors);
        paper = new Item("Paper", "Paper for you to write on.", "Crafts", true);
        items.put("Paper", paper);
        pencil = new Item("Pencil", "A pencil for you to write with.", "Crafts", true);
        items.put("Pencil", pencil);
        eraser = new Item("Eraser", "An eraser for you to erase with.", "Crafts", true);
        items.put("Eraser", eraser);
        calculator = new Item("Calculator", "A calculator for you to calculate with.", "Crafts", true);
        items.put("Calculator", calculator);
        ruler = new Item("Ruler", "A ruler for you to measure with.", "Crafts", true);
        items.put("Ruler", ruler);
        scale = new Item("Scale", "A scale for you to weigh with.", "Crafts", true);
        items.put("Scale", scale);
        thermometer = new Item("Thermometer", "A thermometer for you to measure temperature with.", "Crafts", true);
        items.put("Thermometer", thermometer);
        magnifyingGlass = new Item("Magnifying Glass", "A magnifying glass for you to magnify with.", "Crafts", true);
        items.put("Magnifying Glass", magnifyingGlass);
        telescope = new Item("Telescope", "A telescope for you to see far away with.", "Crafts", true);
        items.put("Telescope", telescope);
        microscope = new Item("Microscope", "A microscope for you to see small things with.", "Crafts", true);
        items.put("Microscope", microscope);
        binoculars = new Item("Binoculars", "Binoculars for you to see far away with.", "Crafts", true);
        items.put("Binoculars", binoculars);
        camera = new Item("Camera", "A camera for you to take pictures with.", "Crafts", true);
        items.put("Camera", camera);
        videoCamera = new Item("Video Camera", "A video camera for you to take videos with.", "Crafts", true);
        items.put("Video Camera", videoCamera);
        tapeRecorder = new Item("Tape Recorder", "A tape recorder for you to record with.", "Crafts", true);
        items.put("Tape Recorder", tapeRecorder);
        radio = new Item("Radio", "A radio for you to listen to music with.", "Crafts", true);
        items.put("Radio", radio);
        television = new Item("Television", "A television for you to watch TV with.", "Crafts", true);
        items.put("Television", television);
        computer = new Item("Computer", "A computer for you to use.", "Crafts", true);
        items.put("Computer", computer);
        tablet = new Item("Tablet", "A tablet for you to use.", "Crafts", true);
        items.put("Tablet", tablet);
        phone = new Item("Phone", "A phone for you to use.", "Crafts", true);
        items.put("Phone", phone);
        speaker = new Item("Speaker", "A speaker for you to listen to music with.", "Crafts", true);
        items.put("Speaker", speaker);
        headphones = new Item("Headphones", "Headphones for you to listen to music with.", "Crafts", true);
        items.put("Headphones", headphones);
        microphone = new Item("Microphone", "A microphone for you to record with.", "Crafts", true);
        items.put("Microphone", microphone);
        keyboard = new Item("Keyboard", "A keyboard for you to type with.", "Crafts", true);
        items.put("Keyboard", keyboard);
        mouse = new Item("Mouse", "A mouse for you to click with.", "Crafts", true);
        items.put("Mouse", mouse);
        monitor = new Item("Monitor", "A monitor for you to see with.", "Crafts", true);
        items.put("Monitor", monitor);
        printer = new Item("Printer", "A printer for you to print with.", "Crafts", true);
        items.put("Printer", printer);
        scanner = new Item("Scanner", "A scanner for you to scan with.", "Crafts", true);
        items.put("Scanner", scanner);
        projector = new Item("Projector", "A projector for you to project with.", "Crafts", true);
        items.put("Projector", projector);
        whiteboard = new Item("Whiteboard", "A whiteboard for you to write on.", "Crafts", true);
        items.put("Whiteboard", whiteboard);
        chalkboard = new Item("Chalkboard", "A chalkboard for you to write on.", "Crafts", true);
        items.put("Chalkboard", chalkboard);
        smartboard = new Item("Smartboard", "A smartboard for you to write on.", "Crafts", true);
        items.put("Smartboard", smartboard);
        globe = new Item("Globe", "A globe for you to learn about the world.", "Crafts", true);
        items.put("Globe", globe);
        map = new Item("Map", "A map for you to learn about the world.", "Crafts", true);
        items.put("Map", map);
        calendar = new Item("Calendar", "A calendar for you to keep track of time.", "Crafts", true);
        items.put("Calendar", calendar);
        compass = new Item("Compass", "A compass for you to find your way.", "Crafts", true);
        items.put("Compass", compass);
        protractor = new Item("Protractor", "A protractor for you to measure angles with.", "Crafts", true);
        items.put("Protractor", protractor);
        modelingClay = new Item("Modeling Clay", "Modeling clay for you to play with.", "Crafts", true);
        items.put("Modeling Clay", modelingClay);
        trash = new Item("Trash", "Trash that needs to be thrown away.", "Trash", true);
        items.put("Trash", trash);
        diaper = new Equipment("Diaper", "A diaper for you, a baby :P.", "Underpants");
        items.put("Diaper", diaper);
        thickDiapers = new Equipment("Thick Diapers", "A diaper for you, a baby.", "Underpants");
        items.put("Thick Diapers", thickDiapers);
        trainingPants = new Equipment("Training Pants", "Training Pants, for you, a big kid", "Underpants");
        items.put("Training Pants", trainingPants);
        box = new Container("Box", "A simple cardboard box for storing items", "Furniture", false);
        box.setContraband(true);
        items.put("Box", box);
        underPants = new Equipment("Underpants", "Underpants, for you, a big kid", "Underpants");
        items.put("Underpants", underPants);
        uniformTop = new Equipment("Uniform Top", "A top for your uniform", "Top");
        items.put("Uniform Top", uniformTop);
        uniformBottom = new Equipment("Uniform Bottom", "A bottom for your uniform", "Bottom");
        items.put("Uniform Bottom", uniformBottom);
        uniformHat = new Equipment("Uniform Hat", "A hat for your uniform", "Hat");
        items.put("Uniform Hat", uniformHat);
        uniformShoes = new Equipment("Uniform Shoes", "Shoes for your uniform", "Shoes");
        items.put("Uniform Shoes", uniformShoes);
        uniBackPack = new Equipment("Uniform Back Pack", "A back pack for your uniform", "Back Pack");
        items.put("Uniform Back Pack", uniBackPack);
        uniBackPack.setSlot("Back");
        uniformBottom.setSlot("Bottom");
        uniformTop.setSlot("Top");
        uniformHat.setSlot("Hat");
        uniformShoes.setSlot("Shoes");
        trainingPants.setSlot("Underpants");
        diaper.setSlot("Underpants");
        thickDiapers.setSlot("Underpants");
        underPants.setSlot("Underpants");

    }

    static void generateRandomQuests(Item aThis) {
        Room randomRoom = getRandomRoom(RoomType.GREEN);
        NPC randomNPC = getRandomNPC(NPCType.REJUVE);
        Quest eQuest = new EscortQuest("Escort Quest", "Escort " + randomNPC.getName() + " to the " + randomRoom.getName() + " ", 10, randomRoom, randomNPC);
        aThis.getQuests().add(eQuest);
        Quest cQuest = new Quest("Cleaning Quest", "Clean up " + randomRoom.getName(), 10);
        aThis.getQuests().add(cQuest);

    }

    public static void addItem(Item item) {
        items.put(item.getName(), item);
        getGui().display("Added " + item.getName(), "black");
    }

    private static NPC getRandomNPC(NPCType npcType) {
        int rando = rand.nextInt(npcs.size());
        Iterator<NPC> iterator = npcs.values().iterator();
        for (int i = 0; i < rando; i++) {
            iterator.next();
        }
        NPC npc = iterator.next();
        if (npc.getType().containsKey(NPCType.REJUVE)) {
            return npc;
        } else {
            return getRandomNPC(npcType);
        }
    }
    private final ArrayList<Room> requiredRooms = new ArrayList<>();

    public ArrayList<Room> getRequiredRooms() {
        return requiredRooms;
    }
    private ArrayList<Item> requiredItems = new ArrayList<>();

    public void setRequiredItems(ArrayList<Item> requiredItems) {
        this.requiredItems = requiredItems;
    }

    public void setUpNPCs() {
        fuzzy = new NPC("Fuzzy", "A large stuffed bear, he is always around and always seems to be watching.", foyer, "companion");
        npcs.put("Fuzzy", fuzzy);
        msSagely = new NPC("Ms_Sagely", "A wisen old woman whos presence is as comforting as it is dignified.", foyer, "adult");
        npcs.put("Ms_Sagely", msSagely);
        dawn = new NPC("Dawn", "An ERE TeAchr and assistant to Ms Sagely, she is dressed in fun and colorful clothing.", foyer, "child");
        npcs.put("Dawn", dawn);
        taliber = new NPC("Taliber", "A rejuve and prefect of about seven, he seems to be someone who the others want to follow.", foyer, "child");
        npcs.put("Taliber", taliber);
        susy = new NPC("Susy", "A rejuve and prefect, she acts a lot older than the eight or so she looks like. She wears a white apron and and a pink chiefs hat near always. ", foyer, "child");
        npcs.put("Susy", susy);
        farah = new NPC("Farah", "A rejuve and prefect, she looks alot older than the other students here. The others seem to come to her for advice and help, unlike the other prefects who throw their weight around.", foyer, "child");
        npcs.put("Farah", farah);
        drWhite = new NPC("Dr_White", "A thin and tall man, Dr White observes more than he talks. He can be seen standing off to the side as the other adults do their jobs.", foyer, "adult");
        npcs.put("Dr_White", drWhite);
        msWhite = new NPC("Ms_White", "The rejuve/pediatric nurse, also a doctor of her own right how ever she is rarely seen", foyer, "adult");
        npcs.put("Ms_White", msWhite);
        aureus = new NPC("Aureus", "This girl a rejuve of about 4 is as feisty as she is short, she is always wearing a dog eared touque, even to bed or when it's 100 degrees out.", foyer, "child");
        npcs.put("Aureus", aureus);
        jessiem = new NPC("Jessie", "This is jessie, brother to Jessie. Strangly for being brother and sister they hardly are ever seen together.", foyer, "child");
        npcs.put("Jessie", jessiem);
        researchStudent1 = new NPC("Research_Student_M", "As tight liped as the doctor they research under, also with the drab and white coat, He and his colleges sport an emotionless expression and always carry a 'clicker' and notepad.", foyer, "child");
        npcs.put("Research_Student_M", researchStudent1);
        researchStudent2 = new NPC("Research_Student_F", "As tight liped as the doctor they research under, also with the drab and white coat, She and her colleges sport an emotionless expression and always carry a 'clicker' and notepad.", foyer, "child");
        npcs.put("Research_Student_F", researchStudent2);
        jimthejanitor = new NPC("Jim", "He can be seen cleaning up after the kids, he is a nice man who is always willing to lend a hand.", foyer, "adult");
        npcs.put("Jim", jimthejanitor);
        joy = new NPC("Joy", "You're not sure what she does here, but she is always around, she is always smiling when the kids to listen to her tell stories or run arts and crafts.", foyer, "adult");
        npcs.put("Joy", joy);
        jessief = new NPC("Jessie", "This is jessie, sister to Jessie. Strangly for being brother and sister they hardly are ever seen together.", foyer, "child");
        npcs.put("Jessie", jessief);
    }

    public void createAchievements() {
        Achievements explorer = new Achievements("Exsplore", "Exsplore all the rooms in the house", 10);
        explorer.addRequiredPlace(foyer);
        explorer.addRequiredPlace(dorms);

        Achievements snappyDresser = new Achievements("Snappy Dresser", "Wear a uniform", 10);
        snappyDresser.addRequiredEquipment(uniformTop);
        snappyDresser.addRequiredEquipment(uniformBottom);
        snappyDresser.addRequiredEquipment(uniformHat);
        snappyDresser.addRequiredEquipment(uniformShoes);
        snappyDresser.addRequiredEquipment(uniBackPack);

        Achievements collector = new Achievements("Collector", "Collect all the toys", 10);
        collector.addRequiredItem(toy);

        Achievements socialite = new Achievements("Socialite", "Make a friend", 10);
        socialite.addRequiredNPC(fuzzy, NPCStatus.FRIEND);

        Achievements nemisis = new Achievements("Nemisis", "Make an enemy", 10);
        nemisis.addRequiredNPC(fuzzy, NPCStatus.ENEMY);

        Achievements bookWorm = new Achievements("Book Worm", "Read all the books", 10);
        bookWorm.addRequiredItem(marytherejuve);
        bookWorm.addRequiredItem(theAdventuresOfFuzzy);
        bookWorm.addRequiredItem(theAdventuresOfFuzzy2);
        bookWorm.addRequiredItem(SoYoureARejuve);
        bookWorm.addRequiredItem(theAdventuresOfFuzzy3);
        bookWorm.addRequiredItem(careforrejuves);

        Achievements meetEveryone = new Achievements("Meet Everyone", "Speak to all NPCs", 20);
        meetEveryone.addRequiredNPC(fuzzy, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(msSagely, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(dawn, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(taliber, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(susy, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(farah, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(drWhite, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(msWhite, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(aureus, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(jessiem, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(researchStudent1, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(researchStudent2, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(jimthejanitor, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(joy, NPCStatus.SPOKEN_TO);
        meetEveryone.addRequiredNPC(jessief, NPCStatus.SPOKEN_TO);
        // Add all the achievements to the list
        achievements.add(explorer);
        achievements.add(snappyDresser);
        achievements.add(collector);
        achievements.add(socialite);
        achievements.add(bookWorm);
        achievements.add(meetEveryone);
        achievements.add(nemisis);
    }

    public void createRoutine() {
        final RoutineManager routineManager1 = new RoutineManager();
        this.routineManager = routineManager1;
    }

    public RoutineManager getRoutineManager() {
        return this.routineManager;
    }

    public static void updateAchievementsForNPC(NPC npc, NPCStatus status) {
        for (Achievements achievement : achievements) {
            if (achievement.getRequiredNPC().containsKey(npc)) {
                achievement.getRequiredNPC().put(npc, status);
                achievement.updateAchievement();
            }
        }

    }

    public void buildRooms() {
        kitchen = new Room("Kitchen", "A room where you can cook food.");
        rooms.put("Kitchen", kitchen);
        kitchen.setType(RoomType.GREEN);

        classRoom = new Room("Class_Room", "A room where you can learn.");
        rooms.put("Class_Room", classRoom);
        classRoom.setType(RoomType.BLUE);

        mainRoom = new Room("Main_Room", "The main room of the daycare.");
        rooms.put("Main_Room", mainRoom);
        mainRoom.setType(RoomType.GREEN);

        dorms = new Room("Dorms", "A room where you can sleep.");
        rooms.put("Dorms", dorms);
        dorms.setType(RoomType.GREEN);

        bathroom = new Room("Bathroom", "A room where you can clean yourself.");
        rooms.put("Bathroom", bathroom);
        bathroom.setType(RoomType.GREEN);

        hallway = new Room("Hallway", "A hallway that connects the rooms.");
        rooms.put("Hallway", hallway);
        hallway.setType(RoomType.RED);

        stairs = new Room("Stairs", "A staircase that leads to the basement and attic.");
        rooms.put("Stairs", stairs);
        stairs.setType(RoomType.RED);

        basement = new Room("Basement", "A room where you can store things.");
        rooms.put("Basement", basement);
        basement.setType(RoomType.RED);

        attic = new Room("Attic", "A room where you can store things.");
        rooms.put("Attic", attic);
        attic.setType(RoomType.RED);

        garage = new Room("Garage", "A room where you can store vehicles.");
        rooms.put("Garage", garage);
        garage.setType(RoomType.RED);

        garden = new Room("Garden", "A room where you can grow plants.");
        rooms.put("Garden", garden);
        garden.setType(RoomType.BLUE);

        driveway = new Room("Driveway", "A driveway that leads to the street.");
        rooms.put("Driveway", driveway);
        driveway.setType(RoomType.RED);

        frontYard = new Room("Front_Yard", "The front yard of the daycare.");
        rooms.put("Front_Yard", frontYard);
        frontYard.setType(RoomType.BLUE);

        backYard = new Room("Back_Yard", "The back yard of the daycare.");
        rooms.put("Back_Yard", backYard);

        cogLabs = new Room("cogLabs", "A room with a sign that says \"Please do not take the box\"");
        rooms.put("cogLabs", cogLabs);

        pool = new Room("Pool", "A pool where you can swim.");
        rooms.put("Pool", pool);

        patio = new Room("Patio", "A patio where you can relax.");
        rooms.put("Patio", patio);

        deck = new Room("Deck", "A deck where you can relax.");
        rooms.put("Deck", deck);

        porch = new Room("Porch", "A porch where you can relax.");
        rooms.put("Porch", porch);

        balcony = new Room("Balcony", "A balcony where you can relax.");
        rooms.put("Balcony", balcony);

        roof = new Room("Roof", "A roof where you can relax.");
        rooms.put("Roof", roof);

        cubbies = new Room("Cubbies", "A room where you can store your things.");
        rooms.put("Cubbies", cubbies);
        cubbies.setType(RoomType.GREEN);

        dramaArea = new Room("Drama_Area", "A room where you can act out plays.");
        rooms.put("Drama_Area", dramaArea);
        dramaArea.setType(RoomType.GREEN);

        changingRoom = new Room("Changing_Room", "A room where you can change your clothes.");
        rooms.put("Changing_Room", changingRoom);
        changingRoom.setType(RoomType.GREEN);

        floorPlay = new Room("Floor_Play", "A room where you can play on the floor.");
        rooms.put("Floor_Play", floorPlay);
        floorPlay.setType(RoomType.GREEN);

        quietArea = new Room("Quiet_Area", "A room where you can relax.");
        rooms.put("Quiet_Area", quietArea);
        quietArea.setType(RoomType.GREEN);

        homeWorkArea = new Room("Homework_Area", "A room where you can do your homework.");
        rooms.put("Homework_Area", homeWorkArea);
        homeWorkArea.setType(RoomType.GREEN);

        playHouse = new Room("Play_House", "A room where you can play house.");
        rooms.put("Play_House", playHouse);

        treeHouse = new Room("Tree_House", "A room where you can play in a tree house.");
        rooms.put("Tree_House", treeHouse);

        storyBookVillage = new Room("Storybook_Village", "A room where you can read books.");
        rooms.put("Storybook_Village", storyBookVillage);
        storyBookVillage.setType(RoomType.GREEN);

        pillowPile = new Room("Pillow_Pile", "A room where you can relax on a pile of pillows.");
        rooms.put("Pillow_Pile", pillowPile);
        pillowPile.setType(RoomType.GREEN);

        snackArea = new Room("Snack_Area", "A room where you can eat snacks.");
        rooms.put("Snack_Area", snackArea);
        snackArea.setType(RoomType.GREEN);

        greenHall = new Room("Green_Hall", "A hallway that connects the rooms.");
        rooms.put("Green_Hall", greenHall);
        greenHall.setType(RoomType.GREEN);

        blueHall = new Room("Blue_Hall", "A hallway that connects the rooms.");
        rooms.put("Blue_Hall", blueHall);

        redHall = new Room("Red_Hall", "A hallway that connects the rooms.");
        rooms.put("Red_Hall", redHall);

        peddleToys = new Room("Peddle_Toys", "A room where you can play with peddle toys.");
        rooms.put("Peddle_Toys", peddleToys);

        lemonaidStand = new Room("Lemonaid_Stand", "A room where you can sell lemonaid.");
        rooms.put("Lemonaid_Stand", lemonaidStand);

        toolShed = new Room("Tool_Shed", "A shed where you can store tools.");
        rooms.put("Tool_Shed", toolShed);

        TRSRoom = new Room("TRSRoom", "A room where you can relax.");
        rooms.put("TRSRoom", TRSRoom);

        janitorialRoom = new Room("Janitorial_Room", "A room where you can clean.");
        rooms.put("Janitorial_Room", janitorialRoom);

        foyer = new Room("Foyer", "The foyer of the daycare.");
        rooms.put("Foyer", foyer);
        foyer.setType(RoomType.GREEN);

        pantry = new Room("Pantry", "A room where you can store food.");
        rooms.put("Pantry", pantry);

        recoveryRoom = new Room("Recovery_Room", "A room where you can recover.");
        rooms.put("Recovery_Room", recoveryRoom);
        recoveryRoom.setType(RoomType.GREEN);

        demoRoom = new Room("Demo_Room", "A room where you can play with toys.");
        rooms.put("Demo_Room", demoRoom);

        snackArea.addItem(foodTrayDispenser);
        foyer.addItem(snackShop);
        foyer.addItem(questBoard);
        cogLabs.addItem(trash);
        mainRoom.addItem(pickapaw);
        Player.setRoom(mainRoom);
        farah.setRoom(mainRoom);
        aureus.setRoom(foyer);
        fuzzy.addItem(nulearPotato);
        demoRoom.addItem(jigSaw);
        demoRoom.addItem(crossword);
        demoRoom.addItem(colors);
        demoRoom.addItem(shapes);
        //demoRoom.addItem(puzzles);
        //demoRoom.addItem(letters);
        //demoRoom.addItem(numbers);
        //demoRoom.addItem(animals);
        //demoRoom.addItem(vehicles);
        //demoRoom.addItem(people);
        demoRoom.addItem(boosterSeat);

    }

    public void populateRooms() {
        //basement.addNPC();
        //attic.addNPC();
        garage.addNPC(jim);
        //garden.addNPC();
        //driveway.addNPC();
        //frontYard.addNPC();
        //backYard.addNPC();
        cogLabs.addNPC(researchStudent1);
        cogLabs.addNPC(jimthejanitor);
        //pool.addNPC();
        //patio.addNPC();
        //deck.addNPC();
        //porch.addNPC();
        //balcony.addNPC();
        //roof.addNPC();
        //cubbies.addNPC();
        //dramaArea.addNPC();
        //changingRoom.addNPC();
        //floorPlay.addNPC();
        //quietArea.addNPC();
        //homeWorkArea.addNPC();
        //playHouse.addNPC();
        treeHouse.addNPC(taliber);
        storyBookVillage.addNPC(joy);
        //pillowPile.addNPC();
        //snackArea.addNPC();
        //greenHall.addNPC();
        //blueHall.addNPC();
        //redHall.addNPC();
        //peddleToys.addNPC();
        //lemonaidStand.addNPC();
        //toolShed.addNPC();
        //TRSRoom.addNPC();
        janitorialRoom.addNPC(msSagely);
        mainRoom.addNPC(dawn);
        mainRoom.addNPC(jessief);
        mainRoom.addNPC(jessiem);
        mainRoom.addNPC(taliber);
        mainRoom.addNPC(joy);
        kitchen.addNPC(susy);
        foyer.addNPC(aureus);
        //pantry.addNPC();
        //demoRoom.addNPC();
    }

    public void buildExits() {
        //BackYard
        backYard.addExit(deck);
        backYard.addExit(patio);
        backYard.addExit(playHouse);
        backYard.addExit(pool);
        backYard.addExit(toolShed);
        backYard.addExit(treeHouse);
        backYard.addExit(foyer);

        //Bathroom
        bathroom.addExit(changingRoom);

        //BlueHall
        blueHall.addExit(foyer);
        blueHall.addExit(redHall);

        //ChangingRoom
        changingRoom.addExit(bathroom);
        changingRoom.addExit(foyer);

        //Cubbies
        cubbies.addExit(dramaArea);
        cubbies.addExit(floorPlay);
        cubbies.addExit(homeWorkArea);
        cubbies.addExit(quietArea);
        cubbies.addExit(storyBookVillage);
        cubbies.addExit(mainRoom);
        cubbies.addExit(pillowPile);

        //Deck
        deck.addExit(backYard);

        //DemoRoom
        demoRoom.addExit(foyer);

        //Dorms
        dorms.addExit(foyer);
        dorms.addExit(greenHall);

        //DramaArea
        dramaArea.addExit(cubbies);
        dramaArea.addExit(floorPlay);
        dramaArea.addExit(homeWorkArea);
        dramaArea.addExit(quietArea);
        dramaArea.addExit(storyBookVillage);
        dramaArea.addExit(mainRoom);
        dramaArea.addExit(pillowPile);

        //FloorPlay
        floorPlay.addExit(cubbies);
        floorPlay.addExit(dramaArea);
        floorPlay.addExit(homeWorkArea);
        floorPlay.addExit(quietArea);
        floorPlay.addExit(storyBookVillage);
        floorPlay.addExit(mainRoom);
        floorPlay.addExit(pillowPile);

        //Foyer
        foyer.addExit(backYard);
        foyer.addExit(blueHall);
        foyer.addExit(dorms);
        foyer.addExit(frontYard);
        foyer.addExit(greenHall);
        foyer.addExit(kitchen);
        foyer.addExit(mainRoom);
        foyer.addExit(recoveryRoom);
        foyer.addExit(cogLabs);
        foyer.addExit(demoRoom);
        foyer.addExit(changingRoom);
        foyer.addExit(janitorialRoom);

        //FrontYard
        frontYard.addExit(foyer);
        frontYard.addExit(lemonaidStand);
        frontYard.addExit(porch);

        //GreenHall
        greenHall.addExit(dorms);
        greenHall.addExit(foyer);
        greenHall.addExit(classRoom);

        //ClassRoom
        classRoom.addExit(greenHall);

        //HomeWorkArea
        homeWorkArea.addExit(cubbies);
        homeWorkArea.addExit(dramaArea);
        homeWorkArea.addExit(floorPlay);
        homeWorkArea.addExit(quietArea);
        homeWorkArea.addExit(storyBookVillage);
        homeWorkArea.addExit(mainRoom);
        homeWorkArea.addExit(pillowPile);

        //JanitorialRoom
        janitorialRoom.addExit(foyer);
        janitorialRoom.addExit(TRSRoom);
        janitorialRoom.addExit(demoRoom);
        janitorialRoom.addExit(cogLabs);

        //Kitchen
        kitchen.addExit(foyer);
        kitchen.addExit(pantry);

        //LemonaidStand
        lemonaidStand.addExit(frontYard);

        //MainRoom
        mainRoom.addExit(cubbies);
        mainRoom.addExit(dramaArea);
        mainRoom.addExit(floorPlay);
        mainRoom.addExit(homeWorkArea);
        mainRoom.addExit(pillowPile);
        mainRoom.addExit(quietArea);
        mainRoom.addExit(snackArea);
        mainRoom.addExit(storyBookVillage);
        mainRoom.addExit(foyer);
        mainRoom.addExit(demoRoom);

        //Pantry
        pantry.addExit(kitchen);

        //Patio
        patio.addExit(backYard);

        //PillowPile
        pillowPile.addExit(cubbies);
        pillowPile.addExit(dramaArea);
        pillowPile.addExit(floorPlay);
        pillowPile.addExit(homeWorkArea);
        pillowPile.addExit(quietArea);
        pillowPile.addExit(storyBookVillage);
        pillowPile.addExit(mainRoom);

        //PlayHouse
        playHouse.addExit(backYard);

        //Pool
        pool.addExit(backYard);

        //Porch
        porch.addExit(frontYard);

        //laboratories
        cogLabs.addExit(foyer);

        //QuietArea
        quietArea.addExit(cubbies);
        quietArea.addExit(dramaArea);
        quietArea.addExit(floorPlay);
        quietArea.addExit(homeWorkArea);
        quietArea.addExit(storyBookVillage);
        quietArea.addExit(mainRoom);
        quietArea.addExit(pillowPile);

        //RecoveryRoom
        recoveryRoom.addExit(foyer);

        //RedHall
        redHall.addExit(blueHall);

        //SnackArea
        snackArea.addExit(mainRoom);

        //StoryBookVillage
        storyBookVillage.addExit(cubbies);
        storyBookVillage.addExit(dramaArea);
        storyBookVillage.addExit(floorPlay);
        storyBookVillage.addExit(homeWorkArea);
        storyBookVillage.addExit(quietArea);
        storyBookVillage.addExit(pillowPile);
        storyBookVillage.addExit(mainRoom);

        //ToolShed
        toolShed.addExit(backYard);

        //TreeHouse
        treeHouse.addExit(backYard);

    }

    public void playIntro() {
        readFile("intro1");
        getGui().waitForInput();
        readFile("intro2");
        getGui().waitForInput();
        readFile("intro3");
        getGui().waitForInput();
        createQuests();
    }

    public void setupPlayer() {
        givePlayerStuff();
        setCharacterBio();
        updateStatus();
        Game.setRunning(true);
        getGui().unlockButtons();
    }

    private void givePlayerStuff() {
        Player.setMoney(9);
        Player.setExperience(0);
        Player.setHunger(50);
        Player.setThirst(50);
        Player.setResilience(100);
    }

    private void dressPlayer() {
        readFile("introFuzzy");

        if (!Player.getEquipment().containsKey("Underpants")) {
            if (Player.isPottyTrained()) {
                Player.equip(new Equipment("Big Kid Underwear", "Classic Breif style with a design on them.", "underpants"), "Underpants");
            } else {
                Player.equip(diaper, "Underpants");

            }
        }
        if (!Player.getEquipment().containsKey("Top")) {
            Player.equip(new Equipment("Uniform Shirt", "A oversized tee with a beaver holding a paint brush on it.", "Top"), "Top");
        }
        if (!Player.getEquipment().containsKey("Bottom")) {
            if (Player.isPottyTrained()) {
                Player.equip(new Equipment("Uniform Pants", "Pants in a selection of colors", "Bottom"), "Bottom");
            } else {
                Equipment snapPants = new Equipment("Snap Pants", "Clean pants with bright red snaps running up one inner leg and down the other.", "Bottom");
                snapPants.setCondition(ItemCondition.CLEAN, true);
                snapPants.setCondition(ItemCondition.BUTTONS, true);
                Player.equip(snapPants, "Bottom");
            }
            if (!Player.getEquipment().containsKey("Socks")) {
                Player.equip(new Equipment("Uniform Socks", "White socks whith a paintbush holding cartoon beaver on them.", "Socks"), "Socks");
            }
            if (!Player.getEquipment().containsKey("Shoes")) {
                if (Player.getProficiencies().contains(Proficiencies.DRESSING)) {
                    Player.equip(new Equipment("Uniform Shoes", "Lavender colored with thick laces they are a little loose.", "Shoes"), "Shoes");

                } else {

                    Player.equip(new Equipment("Velcro Shoes", "Lavender colored shoes with velcro instead of laces", "Shoes"), "Shoes");
                }
            }
        }
        assignFuzzy(fuzzy);
        fuzzy.setRoom(Player.getRoom());
    }

    private void assignFuzzy(NPC fuzzy) {
        if (fuzzy.getType().containsKey(NPCType.COMPANION)) {
            String[] choices = {"Okay!", "No thanks.", "F*** off, crazy andriod"};

            int choice = JOptionPane.showOptionDialog(null, "Would you like to be friends with " + fuzzy.getName() + "?", "Friend Request", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
            switch (choice) {
                case 0 -> {
                    getGui().display("You are now friends with " + fuzzy.getName(), "Black");
                    updateAchievementsForNPC(fuzzy, NPCStatus.FRIEND);
                    fuzzy.movePlayer("Hold Hands", snackArea);
                    break;
                }
                case 1 -> {
                    getGui().display("Awee, *Fuzzy looks a little sad* okay, well have fun doing things without fuzzy, I will be here if you need me." + fuzzy.getName(), "Black");
                    PlayerStatus.HOLDING_HANDS.deactivate();
                    PlayerStatus.CARRIED.deactivate();
                    Player.setHoldingNPC(null);
                    break;
                }
                case 2 -> {
                    getGui().display("Awee, *Fuzzy looks a little sad* okay, well have fun doing things without fuzzy, I will be here if you need me." + fuzzy.getName(), "Black");
                    PlayerStatus.HOLDING_HANDS.deactivate();
                    PlayerStatus.CARRIED.deactivate();
                    Player.setHoldingNPC(null);
                    break;
                }
            }
        }
    }   


    
    public void giveItems() {
        drWhite.addItem(box);

        foyer.addItem(box);
        foyer.addItem(trash);
        box.addItem(trash);
        box.addItem(toy);
        snackShop.addItem(thickDiapers);
        snackShop.addItem(toy);
        toy.setPrice(5);
        diaper.setPrice(5);
        diaper.setPockets(1);
        thickDiapers.setPrice(5);
        thickDiapers.setPockets(2);
        uniBackPack.setPockets(5);
        backPack.setPockets(10);
        box.setPrice(6);
        box.setContraband(true);
        box.setType(ItemType.FURNITURE);
        pickapaw.addItem(pawFigure);
        pickapaw.addItem(limitedEditionPaw);
        limitedEditionPaw.setPrice(50);
        pawFigure.setPrice(10);
        foyer.addItem(marytherejuve);
        foyer.addItem(SoYoureARejuve);
        foyer.addItem(theAdventuresOfFuzzy);
        foyer.addItem(theAdventuresOfFuzzy2);
    }

    public void playGame() {
        playTutorial();
    }

    public void endGame() {
        playOutro();
    }

    public static Room getRoomByName(String name) {
        return rooms.get(name);
    }

    public static GUI getGui() {
        return gui;
    }

    public static void updateStatus() {
        getGui().getStatsLabel().setText("Player: " + Player.getName() + "    | |    Experience: " + Player.getExperience() + "    | |    Shiny Pennies: " + Player.getMoney() + "    | |    Resilience: " + Player.getResilience() + "    | |    Time: " + FatherTime.getClock().getTimeOfDay() + "    | |    Hunger/Thirst: " + Player.getHungerThirst() + "    | |    Alignment: " + Player.getAlignment());
    }

    public static Map<String, Room> getRooms() {
        return rooms;
    }

    public static Map<String, NPC> getNpcs() {
        return npcs;
    }

    public static Map<String, Item> getItems() {
        return items;
    }

    public static void removeItemFromRoom(Item item) {
        ArrayList<Item> itemsI = Player.getRoom().getInventory();
        Iterator<Item> iterator = itemsI.iterator();
        while (iterator.hasNext()) {
            Item currentItem = iterator.next();
            if (currentItem.equals(item)) {
                iterator.remove();
                break;
            }
        }
    }

    public static NPC getNPCByName(String person) {
        return npcs.get(person);
    }

    public static String getFileSection2() {
        return fileSection2;
    }

    public static String getFileSection3() {
        return fileSection3;
    }

    public static String getFileSection4() {
        return fileSection4;
    }

    public static PawFigure getFigureByName(String name) {
        for (Item item : items.values()) {
            if (item instanceof PawFigure figure) {
                if (figure.getName().equalsIgnoreCase(name)) {
                    getGui().display("This happened!", "Black");
                    return figure;
                }
            }
        }
        return null;
    }

    static Item getItemByName(String itemName) {
        return items.get(itemName);
    }

    static String readFile(String fileName) {
    // Get the base directory where the EXE or JAR is running
    String baseDir = System.getProperty("user.dir");

    // Construct the path to the file within the 'resources' folder
    File file = Paths.get(baseDir, "resources", fileName + ".txt").toFile();

    if (file.exists()) {
        try {
            StringBuilder content = new StringBuilder();
            StringBuilder section2 = new StringBuilder();
            StringBuilder section3 = new StringBuilder();
            StringBuilder section4 = new StringBuilder();

            try (FileReader reader = new FileReader(file)) {
                int character;
                int sectionCount = 1; // Track which section we're in
                while ((character = reader.read()) != -1) {
                    if (character == '#') {
                        sectionCount++; // Increment when we encounter a '#'
                        continue; // Skip appending '#'
                    }

                    // Append to content based on which section we're in
                    switch (sectionCount) {
                        case 1 ->
                            content.append((char) character); // First section (before the first '#')
                        case 2 ->
                            section2.append((char) character); // Second section (between first and second '#')
                        case 3 ->
                            section3.append((char) character); // Third section (between second and third '#')
                        case 4 ->
                            section4.append((char) character); // Fourth section (between third and fourth '#')
                        default -> {
                        }
                    }
                }

                // Display the sections
                if (!content.isEmpty()) {
                    getGui().display(content.toString(), "Black");
                } // Display everything before first '#'
                if (section2.length() > 0) {
                    fileSection2 = section2.toString(); // Display second section
                }
                if (section3.length() > 0) {
                    fileSection3 = section3.toString();
                }
                if (section4.length() > 0) {
                    fileSection4 = section4.toString();
                }
            }
            return content.toString(); // Return the content before first '#'
        } catch (IOException e) {
            getGui().display("Error reading file.", "Red");
        }
    } else {
        try {
            file.getParentFile().mkdirs(); // Create directories if they don’t exist
            file.createNewFile(); // Create the file if it doesn’t exist
        } catch (IOException e) {
            getGui().display("Error creating file.", "Red");
        }
    }
    return null;
}

    private void explainCharacterBio() {
        readFile("characterBio");
    }

    private void playTutorial() {
        readFile("tutorial");
    }

    private void playOutro() {
        readFile("outro");

    }

    public static void demo() {
        readFile("demo");
        String response;
        String[] dialogOptions = {"Don't I get a say?", "I belong to no one, I just got here", "I only woke up a few hours ago", "I don't know what's going on."};
        response = (String) JOptionPane.showInputDialog(null,
                "What do you do?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, dialogOptions, dialogOptions[0]);
        switch (response) {
            case "Don't I get a say?" ->
                getGui().display(fileSection2, "Black");
            case "I belong to no one, I just got here" ->
                getGui().display(fileSection3, "Black");
            case "I only woke up a few hours ago" ->
                getGui().display(fileSection4, "Black");
            case "I don't know what's going on." ->
                getGui().display(fileSection2, "Black");
            default -> {
            }
        }
        dialogOptions = new String[]{"I'm with Taliber!", "I kinda like Dishes.", "I'm with Farah.", "I don't want to deside yet."};
        response = (String) JOptionPane.showInputDialog(null,
                "What do you do?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, dialogOptions, dialogOptions[0]);
        switch (response) {
            case "I'm with Taliber!" ->
                readFile("demo3");
            case "I kinda like Dishes." ->
                readFile("demo3");
            case "I'm with Farah." ->
                readFile("demo3");
            case "I don't want to deside yet." ->
                readFile("demo3");
            default -> {
                break;
            }
        }

    }

    public void setNPCTypes() {
        fuzzy.setType(NPCType.COMPANION);
        msSagely.setType(NPCType.ADULT);
        dawn.setType(NPCType.ADULT);
        taliber.setType(NPCType.REJUVE);
        susy.setType(NPCType.REJUVE);
        farah.setType(NPCType.REJUVE);
        drWhite.setType(NPCType.ADULT);
        msWhite.setType(NPCType.ADULT);
        aureus.setType(NPCType.REJUVE);
        aureus.setStatus(NPCStatus.BADMOOD, true);
        jessiem.setType(NPCType.REJUVE);
        researchStudent1.setType(NPCType.REJUVE);
        researchStudent2.setType(NPCType.REJUVE);
        jimthejanitor.setType(NPCType.ADULT);
        joy.setType(NPCType.ADULT);
        jessief.setType(NPCType.REJUVE);
    }

    public void buildRoomTypes() {
        Room.roomType.put(kitchen, RoomType.KITCHEN);
        Room.roomType.put(classRoom, RoomType.BLUE);
        Room.roomType.put(mainRoom, RoomType.GREEN);
        Room.roomType.put(dorms, RoomType.GREEN);
        Room.roomType.put(bathroom, RoomType.BATHROOM);
        Room.roomType.put(hallway, RoomType.RED);
        Room.roomType.put(stairs, RoomType.RED);
        Room.roomType.put(basement, RoomType.RED);
        Room.roomType.put(attic, RoomType.RED);
        Room.roomType.put(garage, RoomType.RED);
        Room.roomType.put(garden, RoomType.BLUE);
        Room.roomType.put(driveway, RoomType.RED);
        Room.roomType.put(frontYard, RoomType.BLUE);
        Room.roomType.put(backYard, RoomType.BLUE);
        Room.roomType.put(cogLabs, RoomType.BLUE);
        Room.roomType.put(pool, RoomType.RED);
        Room.roomType.put(patio, RoomType.BLUE);
        Room.roomType.put(deck, RoomType.BLUE);
        Room.roomType.put(porch, RoomType.BLUE);
        Room.roomType.put(balcony, RoomType.BLUE);
        Room.roomType.put(roof, RoomType.RED);
        Room.roomType.put(cubbies, RoomType.GREEN);
        Room.roomType.put(dramaArea, RoomType.GREEN);
        Room.roomType.put(changingRoom, RoomType.GREEN);
        Room.roomType.put(floorPlay, RoomType.GREEN);
        Room.roomType.put(quietArea, RoomType.GREEN);
        Room.roomType.put(homeWorkArea, RoomType.GREEN);
        Room.roomType.put(playHouse, RoomType.BLUE);
        Room.roomType.put(treeHouse, RoomType.BLUE);
        Room.roomType.put(storyBookVillage, RoomType.GREEN);
        Room.roomType.put(pillowPile, RoomType.GREEN);
        Room.roomType.put(snackArea, RoomType.GREEN);
        Room.roomType.put(greenHall, RoomType.GREEN);
        Room.roomType.put(blueHall, RoomType.BLUE);
        Room.roomType.put(redHall, RoomType.RED);
        Room.roomType.put(peddleToys, RoomType.BLUE);
        Room.roomType.put(lemonaidStand, RoomType.BLUE);
        Room.roomType.put(toolShed, RoomType.RED);
        Room.roomType.put(TRSRoom, RoomType.RED);
        Room.roomType.put(janitorialRoom, RoomType.RED);
        Room.roomType.put(foyer, RoomType.GREEN);
        Room.roomType.put(pantry, RoomType.KITCHEN);
        Room.roomType.put(recoveryRoom, RoomType.GREEN);
        Room.roomType.put(demoRoom, RoomType.BLUE);
    }

    static void playerTimeOut(int i, String act, NPC npc) {
        boolean apology = false;
        int attempts = 0;
        //getGui().lockButtons();
        getGui().clearTextPane();
        getGui().display("You were put in time out for " + i + " minutes.", "Black");

        while (!apology) {
            String response;
            String[] acts = {"stealing", "pranking", "Vandalism", "Skipped", "Trespassing", "Sneaking", "climbed", "I don't know", "Not Listening"};
            response = (String) JOptionPane.showInputDialog(null,
                    "What did you do?",
                    "Choose", JOptionPane.QUESTION_MESSAGE,
                    null, acts, acts[0]);

            if (response == null) {
                getGui().display("You Will stay here until you give the correct answers.", "Red");
                attempts++;
                i += 5;
                continue;
            }

            switch (response) {
                case "stealing" -> {
                    if (!act.equalsIgnoreCase("stealing")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you steal?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "pranking" -> {
                    if (!act.equalsIgnoreCase("pranking")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you prank someone?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "Vandalism" -> {
                    if (!act.equalsIgnoreCase("Vandalism")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you vandalize?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "Not Listening" -> {
                    if (!act.equalsIgnoreCase("Not Listening")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you not listen?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "Pickedon" -> {
                    if (!act.equalsIgnoreCase("Pickedon")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you pick on someone?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "Skipped" -> {
                    if (!act.equalsIgnoreCase("Skipped")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you skip?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "Trespassing" -> {
                    if (!act.equalsIgnoreCase("Trespassing")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you trespass?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "Sneaking" -> {
                    if (!act.equalsIgnoreCase("Sneaking")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you sneak?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "climbed" -> {
                    if (!act.equalsIgnoreCase("climbed")) {
                        getGui().display("That's not what you did", "Red");
                        break;
                    }
                    String[] reasons = {"Fun", "Profit"};
                    response = (String) JOptionPane.showInputDialog(null,
                            "Why did you climb?",
                            "Choose", JOptionPane.QUESTION_MESSAGE,
                            null, reasons, reasons[0]);

                    if (response == null) {
                        getGui().display("You Will stay here until you give the correct answers.", "Red");
                        attempts++;
                        i += 5;
                        continue;
                    }
                    if (response.equals("Fun") || response.equals("Profit")) {
                        apology = true;
                    }
                }
                case "I don't know" -> {
                    getGui().display("You will stay here until you give the correct answers.", "Red");
                    attempts++;
                    i += 5;
                }
            }
        }

        if (apology) {
            getGui().display("You have been forgiven", "Green");
            getGui().unlockButtons();
            FatherTime.getClock().moveTime(attempts + i + 2);
        }
    }

    private static ArrayList<Achievements> getAchievements() {
        return achievements;
    }

    public static Achievements getAchievementByName(String name) {
        for (Achievements achievement : achievements) {
            if (achievement.getName().equalsIgnoreCase(name)) {
                return achievement;
            }
        }
        return null;
    }
    private RoutineManager routineManager;

    public GameHandler(GUI gui1, Game game1) {
        game = game1;
        gui = gui1;
    }

    public Room movePlayer(Room room) {
        Player.setRoom(room);
        return Player.getRoom();
    }

    public void moveItem(Item item, Room room) {
        room.getInventory().add(item);
    }

    public Game getGame() {
        return game;
    }

    public static void updateAchievementsForRoomVisit(Room room) {
        // Assuming you have a list of achievements somewhere in the game, like a GameHandler or AchievementManager
        for (Achievements Achievement : getAchievements()) {
            // Check if the achievement involves visiting rooms
            if (Achievement.getRequiredPlaces().containsKey(room)) {
                Achievement.markRoomVisited(room); // Mark the room as visited for the achievement
            }
        }
    }

    public static void updateAchievementsForEquip(Equipment equipment) {
        // Assuming you have a list of achievements somewhere in the game, like a GameHandler or AchievementManager
        for (Achievements Achievement : getAchievements()) {
            // Check if the achievement involves equipping items
            if (Achievement.getRequiredEquipment().containsKey(equipment)) {
                Achievement.markEquipmentEquipped(equipment); // Mark the equipment as equipped for the achievement
            }
        }
    }

    public void setCharacterBio() {
        explainCharacterBio();
        setupPlayerBio();
    }

    private void getSelfCare() {
        //are you potty trained?
        GameHandler.getGui().display("Bed Side Computer: Are you potty trained?", "Black");
        String response;
        String[] dialogOptions = {"Yes", "No"};
        response = (String) JOptionPane.showInputDialog(null,
                "Are you potty trained?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, dialogOptions, dialogOptions[0]);
        if (response.equalsIgnoreCase("No")) {
            Player.setPottyTrained(false);
        } else {
            Player.setPottyTrained(true);
            GameHandler.getGui().display("If you need help, just ask.", "Black");
        }
        GameHandler.getGui().display("Bed Side Computer: You okay with a fork? Could you use some practice?", "Black");
        response = (String) JOptionPane.showInputDialog(null,
                "Do you need help with eating with out making a mess?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, dialogOptions, dialogOptions[0]);
        if (response.equalsIgnoreCase("No")) {
            Player.addProficiency("Eating");
            for (Proficiencies proficiency : Player.getProficiencies()) {
                if (proficiency.getName().equalsIgnoreCase("Eating")) {
                    GameHandler.getGui().display("You have gained proficiency in " + proficiency.getName(), "Black");
                } else {

                }

            }
        } else {
            GameHandler.getGui().display("If you need help, just ask.", "Black");
        }
        GameHandler.getGui().display("Bed Side Computer: Do you want help bathing?", "Black");
        response = (String) JOptionPane.showInputDialog(null,
                "Do you need help with bathing?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, dialogOptions, dialogOptions[0]);
        if (response.equalsIgnoreCase("No")) {
            Player.addProficiency("Bathing");
            for (Proficiencies proficiency : Player.getProficiencies()) {
                if (proficiency.getName().equalsIgnoreCase("Bathing")) {
                    GameHandler.getGui().display("You have gained proficiency in " + proficiency.getName(), "Black");
                } else {

                }

            }
        } else {
            GameHandler.getGui().display("If you need help, just ask.", "Black");
        }
        GameHandler.getGui().display("Bed Side Computer: Do you need help dressing?", "Black");
        response = (String) JOptionPane.showInputDialog(null,
                "Do you need help with dressing?",
                "Choose", JOptionPane.QUESTION_MESSAGE,
                null, dialogOptions, dialogOptions[0]);
        if (response.equalsIgnoreCase("No")) {
            Player.addProficiency("Dressing");
            for (Proficiencies proficiency : Player.getProficiencies()) {
                if (proficiency.getName().equalsIgnoreCase("Dressing")) {
                    GameHandler.getGui().display("You have gained proficiency in " + proficiency.getName(), "Black");
                } else {

                }
            }
        } else {
            GameHandler.getGui().display("If you need help, just ask.", "Black");
        }
        GameHandler.getGui().display("If you need help, just ask.", "Black");
    }

    public static void makeFoodTray() {
        Container foodTray1 = (Container) getItems().get("Food Tray");
        if (foodTray1 == null) {
            foodTray1 = new Container("Food Tray", "A plastic food tray", "Container", false);
            getItems().put("Food Tray", foodTray1); // Add it to game items
        }
        int fruitIndex = rand.nextInt(EatingAndFood.fruitChoices.values().length);
        int vegetableIndex = rand.nextInt(EatingAndFood.vegetableChoices.values().length);
        int junkIndex = rand.nextInt(EatingAndFood.junkChoices.values().length);
        int proteinIndex = rand.nextInt(EatingAndFood.proteinChoices.values().length);
        int drinkIndex = rand.nextInt(EatingAndFood.drinkChoices.values().length);
        // Create food items
        Item drink = new EatingAndFood(EatingAndFood.drinkChoices.values()[drinkIndex].toString(), "A delicious " + EatingAndFood.drinkChoices.values()[drinkIndex].toString(), EatingAndFood.FoodType.DRINK, 5);
        Item fruit = new EatingAndFood(EatingAndFood.fruitChoices.values()[fruitIndex].toString(), "A delicious " + EatingAndFood.fruitChoices.values()[fruitIndex].toString(), EatingAndFood.FoodType.FRUIT, 5);
        Item veg = new EatingAndFood(EatingAndFood.vegetableChoices.values()[vegetableIndex].toString(), "A delicious " + EatingAndFood.vegetableChoices.values()[vegetableIndex].toString(), EatingAndFood.FoodType.VEGETABLE, 5);
        Item junk = new EatingAndFood(EatingAndFood.junkChoices.values()[junkIndex].toString(), "A delicious " + EatingAndFood.junkChoices.values()[junkIndex].toString(), EatingAndFood.FoodType.JUNK, 5);
        Item protein = new EatingAndFood(EatingAndFood.proteinChoices.values()[proteinIndex].toString(), "A delicious " + EatingAndFood.proteinChoices.values()[proteinIndex].toString(), EatingAndFood.FoodType.MEAL, 5);
        drink.getTypes().put(ItemType.DRINK, true);
        drink.setName(toSentenceCase(drink.getName()));

        fruit.getTypes().put(ItemType.FOOD, true);
        fruit.setName(toSentenceCase(fruit.getName()));

        veg.getTypes().put(ItemType.FOOD, true);
        veg.setName(toSentenceCase(veg.getName()));

        junk.getTypes().put(ItemType.FOOD, true);
        junk.setName(toSentenceCase(junk.getName()));

        protein.getTypes().put(ItemType.FOOD, true);
        protein.setName(toSentenceCase(protein.getName()));

        fruit.setSaturation(7);
        veg.setSaturation(10);
        junk.setSaturation(3);
        protein.setSaturation(15);
        drink.setSaturation(5);

        foodTray1.getInventory().clear(); // Clear the food tray
        foodTray1.addItem(fruit);
        foodTray1.addItem(veg);
        foodTray1.addItem(junk);
        foodTray1.addItem(protein);
        foodTray1.addItem(drink);

        items.put(fruit.getName(), fruit);
        items.put(veg.getName(), veg);
        items.put(junk.getName(), junk);
        items.put(protein.getName(), protein);
        items.put(drink.getName(), drink);

        // Display success message
        getGui().display("You filled the food tray with delicious items!", "Red");
        foodTrayDispenser.addItem(foodTray1);
        foodTray1.displayInventory();
        foodTrayDispenser.displayInventory();
    }

    public static Room getRandomRoom(RoomType type) {
        rand.nextInt(rooms.size());
        ArrayList<String> keys = new ArrayList<>(rooms.keySet());
        Room randRoom = rooms.get(keys.get(rand.nextInt(keys.size())));
        if (randRoom.getType() != type) {
            return getRandomRoom(type);
        }
        return randRoom;
    }

    public ArrayList<Item> getRequiredItems() {
        return requiredItems;
    }

    public static Quest getQuestByName(String string) {
        for (Quest quest : quests) {
            if (quest.getName().equalsIgnoreCase(string)) {
                return quest;
            }
        }
        return null;
    }

    public static ArrayList<Quest> getQuests() {
        return quests;
    }

    public void createQuests() {
        CleaningQuest tidyUp = new CleaningQuest("TidyUp", "Escort Jim to help clean up the trash in the cogLabs", 1, cogLabs);
        quests.add(tidyUp);
        questBoard.addQuest(tidyUp);

        EscortQuest goToBed = new EscortQuest("Go to Bed", "Escort Aureus to the Dorms and put her down for a nap", 1, dorms, aureus);
        quests.add(goToBed);
        questBoard.addQuest(goToBed);
    }

    public static void writeFile(String concat, String text) {
        try {
            try (FileWriter writer = new FileWriter(concat + ".txt")) {
                writer.write(text);
            }
        } catch (IOException e) {
            getGui().display("Error writing file.", "Red");
        }
    }

    public static Book getBookByName(String selectedBook) {
        for (Item item : items.values()) {
            if (item instanceof Book book) {
                if (book.getName().equalsIgnoreCase(selectedBook)) {
                    return book;
                }
            }
        }
        return null;
    }

    public static Puzzle getPuzzleByName(String selectedPuzzle) {
        for (Item item : items.values()) {
            if (item instanceof Puzzle puzzle) {
                if (puzzle.getName().equalsIgnoreCase(selectedPuzzle)) {
                    return puzzle;
                }
            }
        }
        return null;
    }

    private void setupPlayerBio() {
        // ask name
        Player.setName("Little One");

        //ask age
        Player.setAge();

        // ask pronouns
        explainPronouns();
        Player.setPronouns();
        getGui().waitForInput();

        // set stats
        getGui().display("BSC: Your abilities have been assessed as ", "Black");
        Player.setStats(Player.getAge());
        explainMaturity();

        GameHandler.getGui().display(String.valueOf(Player.getMaturity()), "Black");

        // set profeciencies
        explainSkillsAndProficiencies();
        getGui().waitForInput();

        // set maturity
        Player.getMaturity();
        Player.setProficiencies();
        getSelfCare();
        dressPlayer();
    }

    private void explainMaturity() {
        readFile("maturity");
    }

    private void explainSkillsAndProficiencies() {
        readFile("skillsAndProficiencies");
    }

    private void explainPronouns() {
        readFile("pronouns");
    }

    public static Furniture getFurnitureByName(String argument) {
        for (Item item : items.values()) {
            if (item instanceof Furniture furniture) {
                if (furniture.getName().equalsIgnoreCase(argument)) {
                    return furniture;
                }
            }
        }
        return null;
    }

    public static String[] getRoomNames() {
        ArrayList<String> roomNames = new ArrayList<>();
        for(Room room : rooms.values()) {
            roomNames.add(room.getName());
        }
        return roomNames.toArray(new String[0]);
    }

    public static EatingAndFood getFoodByName(String selectedFood) {
        for (Item item : items.values()) {
            if (item instanceof EatingAndFood food) {
                if (food.getName().equalsIgnoreCase(selectedFood)) {
                    return food;
                }
            }
        }
        return null;
    }
}
