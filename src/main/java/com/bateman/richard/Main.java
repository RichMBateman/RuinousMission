package com.bateman.richard;

import com.bateman.richard.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Ruinous Mission test project running.");
        milestoneNumber1();
    }

    /**
     * The goal of this function is to just simulate my first milestone.
     */
    private static void milestoneNumber1() {
        try {
            HashMap<String, CombatantReference> combatantReferenceHashMapPlayer = loadCombatantReferences("playerclasses");
            HashMap<String, CombatantReference> combatantReferenceHashMapEnemy = loadCombatantReferences("enemyclasses");

            System.out.println("Player classes: " + combatantReferenceHashMapPlayer.size());
            System.out.println("Enemy classes: " + combatantReferenceHashMapPlayer.size());

            Combatant combatantFighter = new Combatant(combatantReferenceHashMapPlayer.get("fighter"));
            Combatant combatantTroll = new Combatant(combatantReferenceHashMapEnemy.get("troll brute"));

            System.out.println(combatantFighter);
            System.out.println(combatantTroll);

            HashMap<String, ActionCard> actionCardHashMap = loadActionCards();
            actionCardHashMap.forEach((k, v) -> System.out.println(v));

            combatantFighter.addActionCard("Quick Slash", 10);
            combatantFighter.addActionCard("Slash", 10);

            combatantTroll.addActionCard("Heavy-Handed Slam", 8);
            combatantTroll.addActionCard("Scowl", 6);
            combatantTroll.addActionCard("Blunt Strike", 6);

            CombatDirector combatDirector = new CombatDirector();
            CombatArena combatArena = new CombatArena();

            boolean playerWon = false;
            boolean enemyWon = false;
            Scanner in = new Scanner(System.in);
            while(!playerWon || !enemyWon) {
                // Mocking turn order.  Just alternating player and enemy.  Should use combatant speed
                // Show player options.  Mocking these options (normally would draw X action cards and show)
                System.out.println("Player turn.  Choose an action for Fighter:");
                System.out.println("1) Quick Slash");
                System.out.println("2) Slash");

                int option = in.nextInt();
                switch(option) {
                    case 1:
                        combatantTroll.hp -= 2;
                        break;
                    case 2:
                        combatantTroll.hp -= 3;
                        break;
                }

                System.out.println("Fighter attacks Brute Troll.  Troll loses " + (option + 1) + " health.");
                if(combatantTroll.hp <= 0) {
                    playerWon = true;
                    break;
                }

                System.out.println("Troll attacks fighter!  Fighter loses 1 hp.");
                combatantFighter.hp--;
                if(combatantFighter.hp <= 0) {
                    enemyWon = true;
                    break;
                }
            }

            if(playerWon) {
                System.out.println("Congrats!  The player won.");
            } else {
                System.out.println("The enemy won.");
            }

        } catch(Exception exc) {
            System.out.println("Exception: " + exc);
        }



    }

    private static HashMap<String, ActionCard> loadActionCards() throws IOException {
        HashMap<String, ActionCard> actionCards = new HashMap<>();

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("actioncards.json");
        if(inputStream == null) throw new InvalidStateException("inputStream is null");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String inputStr;
        while((inputStr = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputStr);
        }
        bufferedReader.close();
        String data = stringBuilder.toString();

        JSONObject jsonData = new JSONObject(data); // (String data)
        JSONArray itemsArray = jsonData.getJSONArray("actioncards"); // assumes the json file contains an “classtype” object

        for(int i = 0; i < itemsArray.length(); i++) {
            JSONObject jsonActionCard = itemsArray.getJSONObject(i);
            String type = jsonActionCard.getString("type");
            String definition = jsonActionCard.getString("definition");
            String flavortext = (jsonActionCard.has("flavortext") ? jsonActionCard.getString("flavortext") : null);
            String accuracy = (jsonActionCard.has("accuracy") ? jsonActionCard.getString("accuracy") : null);
            String name = jsonActionCard.getString("name");

            ActionCard actionCard = new ActionCard.Builder()
                    .setType(type)
                    .setDefinition(definition)
                    .setFlavorText(flavortext)
                    .setAccuracy(accuracy)
                    .setName(name)
                    .build();

            actionCards.put(actionCard.name, actionCard);
        }

        return actionCards;
    }

    private static HashMap<String, CombatantReference> loadCombatantReferences(String classtype) throws IOException{
        HashMap<String, CombatantReference> hashMap = new HashMap<>();

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("characterclasses.json");
        if(inputStream == null) throw new InvalidStateException("inputStream is null");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String inputStr;
        while((inputStr = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputStr);
        }
        bufferedReader.close();
        String data = stringBuilder.toString();

        JSONObject jsonData = new JSONObject(data); // (String data)
        JSONArray itemsArray = jsonData.getJSONArray(classtype); // assumes the json file contains an “classtype” object

        for(int i = 0; i < itemsArray.length(); i++) {
            JSONObject jsonCombatantReference = itemsArray.getJSONObject(i);
            String name = jsonCombatantReference.getString("name");
            String preferredZone = jsonCombatantReference.getString("preferredZone");
            int hp = jsonCombatantReference.getInt("hp");
            int sp = jsonCombatantReference.getInt("sp");
            int speed = jsonCombatantReference.getInt("speed");
            int morale = jsonCombatantReference.getInt("morale");
            int stealth = jsonCombatantReference.getInt("stealth");

            CombatantReference combatantReference = new CombatantReference.Builder()
                    .setName(name)
                    .setPreferredZone(preferredZone)
                    .setHp(hp)
                    .setSp(sp)
                    .setSpeed(speed)
                    .setMorale(morale)
                    .setStealth(stealth)
                    .build();

            hashMap.put(combatantReference.name, combatantReference);
        }

        return hashMap;
    }

}
