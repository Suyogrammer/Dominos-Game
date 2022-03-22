/**
 * @author : Suyog Raj Joshi
 * CS351L
 * Project2 : Dominos
 * Date: 02/25/2021
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * It takes 28 dominos for the game
 */
public class Boneyard {
    ArrayList<Dominos> playerHands;
    private final ArrayList<Dominos> boneyardList = new ArrayList<>();

    public Boneyard() {
        for (int i = 0; i < 7; i++){
            for (int j = i; j < 7; j++){
                Dominos dominos = new Dominos(i, j);
                boneyardList.add(dominos);
            }
        }
    }

    /**
     * @returns the remaining list
     */
    public List<Dominos> remaining(){
        return boneyardList;
    }

    /**
     * @returns random player Hands for the player
     */
    public List<Dominos> playerHands() {
        List<Dominos> playerHands = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Random random = new Random();
            int rand = new Random().nextInt(getBoneyardSize());
            playerHands.add(getDominos(rand));
        }
        return playerHands;
    }

    /**
     *
     * @param index for index of the list
     * @returns player Hands index
     */
    public Dominos getreqDominos(int index) {
        return playerHands.get(index);
    }

    /**
     * @param index is the index of the domino
     * @return domino of the given specific index */
    public Dominos getDominos(int index) {
        return boneyardList.remove(index);
    }

    /**
     * @returns boneyard list
     */
    public List<Dominos> getAllDominos() {
        return boneyardList;
    }

    /**
     * @returns boneyard size
     */
    public int getBoneyardSize() {
        return boneyardList.size();
    }

    /**
     * Checks for boneyard size
     * @returns true if less than 0
     */
    public boolean isEmpty() {
        return boneyardList.size() <= 0;
    }

    /**
     * Draw from boneyard if needed
     */
    public void drawFromBoneyard() {
        if (boneyardList.size() >= 1)
            getDominos(0);
        boneyardList.remove(0);
    }
}

