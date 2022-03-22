/**
 * @author : Suyog Raj Joshi
 * CS351L
 * Project2 : Dominos
 * Date: 02/25/2021
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Players to start the game
 */
public class Players {
    int input;
    Boneyard boneyard = new Boneyard();
    private final List<Dominos> playerHands;
    private final List<Dominos> drawboneyard;
    private final List<Dominos> computerHands;
    private final LinkedList<Dominos> humanplayedDomino = new LinkedList<>();
    private final LinkedList<Dominos> computerplayedDomino = new LinkedList<>();
    private final LinkedList<Dominos> playedDomino = new LinkedList<>();


    /**
     * Checks the user input and performs the assigned task as instructed
     */
    public void userInput() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        if (input.equals("p")){
            System.out.println("Which Domino?");
            play();
        }
        else if (input.equals("d")){
            System.out.println("Draw from boneyard");
            playerDraw();
        }
        else if (input.equals("q")){
            System.out.println("Thanks for playing");
            System.exit(0);
        }
        else{
            System.out.println("Sorry wrong input");
            System.out.println("Try again");
            userInput();
        }
    }

    public Players() {
        playerHands = boneyard.playerHands();
        computerHands = boneyard.playerHands();
        drawboneyard= boneyard.remaining();
    }

    /**
     * Initializes the game
     */
    public void player() {
        System.out.println("Tray: " + playerHands);
        System.out.println("Human's Turn");
        System.out.println("[p] Play Domino");
        System.out.println("[d] Draw from boneyard");
        System.out.println("[q] Quit");
        userInput();
        if (winner() == true){
            System.out.println("GAME OVER");
            if (humanscore()>computerscore()){
                System.out.println("Computer wins");
                System.exit(0);
                }
            else if (computerscore() > humanscore()){
                System.out.println("Congrats!!!!! You win");
                System.exit(0);
            }
            else{
                System.out.println("Draw");
                System.exit(0);
            }
        }
        else{
            computer();
            player();
        }
    }

    /**
     * Rotates the dominos
     */
    private void rotate() {
        System.out.println("Rotate first? (y/n)");
        Scanner scan = new Scanner(System.in);
        String rotate = scan.nextLine();
        if (rotate.equals("y")){
            playerHands.get(input).flipDomino();
        }
    }

    /**
     * Takes the input and gives the direction
     * @returns true if correct input is given
     */
    private boolean direction(){
        boolean left = false;
        System.out.println("Left or Right? (l/r)");
        Scanner scan = new Scanner(System.in);
        String direction = scan.nextLine();

        while (direction.equals("l")){
            left = true;
            break;
        }
        return left;
    }

    /**
     * Computer movements
     */
    public void computer() {
        System.out.println("Tray: " + playerHands);
        computerCases();
        System.out.println("Computer has " + computerHands.size() + " dominos");
        System.out.println("Computer plays " + computerplayedDomino);
        System.out.println("Boneyard contains " + boneyard.getBoneyardSize() + " dominos");
        System.out.println(playedDomino);
        for (int i = 0; i < playedDomino.size(); i++) {
            if (i % 2 == 0) {
                System.out.print(playedDomino.get(i)+"  ");
            }
            if ( i == playedDomino.size()-1 ){
                System.out.print("\n");
            }
        }
        for (int i = 0; i < playedDomino.size(); i++) {
            if (i % 2 == 1) {
                System.out.print("   " + playedDomino.get(i));
            }
            if ( i == playedDomino.size()-1) {
                System.out.print("\n");
            }
        }
    }

    /**
     * Starts the game for the player
     */
    public void play() {
        Scanner scan = new Scanner(System.in);
        input =  scan.nextInt();
        Dominos dominos = playerHands.get(input);
        rotate();
        validMove(playerHands.get(input));
        addplayerDomino(input);
        System.out.println("Playing" + humanplayedDomino );
        System.out.println("Boneyard contains " + boneyard.getBoneyardSize() + " Dominos.");

    }

    /**
     * Checks for the human move
     * @param dominos is used for for dominos
     * @return true if the input is correct, false otherwise
     */
    public  boolean validMove(Dominos dominos){
        if (playedDomino.size()>1){
            if((dominos.getLeft()!=playedDomino.getLast().getRight()) && (dominos.getRight()!=playedDomino.getFirst().getLeft())){
                System.out.println("Domino does not match");
                System.out.println("Please match your domino");
                player();
                userInput();
            }
        }
        return true;
    }

    /**
     * Stores the computer score to decide for the winner if needed
     * Score is used to store the computer score
     * @returns computerScore
     */
    public int computerscore(){
        int score=0;
        for (int i = 0; i< computerHands.size(); i++){
            if (computerHands.size()<1) return 0;
            int count = computerHands.get(i).getLeft() + computerHands.get(i).getRight();
            score = score + count;
        }
        return score;
    }

    /**
     * Stores the human score to decide for the winner if needed
     * Store is used to store the human scores
     * @returns humanScore
     */
    public int humanscore(){
        int score=0;
        for (int i = 0; i< playerHands.size(); i++){
            if (playerHands.size()<1) return 0;
            int count = playerHands.get(i).getLeft() + playerHands.get(i).getLeft();
            score = score + count;
        }
        return score;
    }


    /**
     * Checks for the winner condition and
     * @return
     */
    public boolean winner(){
        boolean over=false;
        if (((drawboneyard.size()<1) && (playerHands.size()<1|| computerHands.size()<1))){
            over=true;
        }
        if (playerHands.size()<1 &&  drawboneyard.size()<1){
            for(int i = computerHands.size(); i<=0; i++){
                if(computerHands.size()==0)
                    computerCases();
            }
        }
        return over;
    }

    /**
     * Computer Cases for the domino
     */
    public void computerCases() {
        boolean x = false;
        for (int i = 0; i < computerHands.size(); i++) {
            Dominos computerDomino = computerHands.get(i);
            if(computerDomino.getRight() == playedDomino.getFirst().getLeft()){
                addcomputerDomino(i,true);
                x = true;
            }
            if (x == true) break;
            if(computerDomino.getLeft() == playedDomino.getFirst().getLeft()){
                computerDomino.flipDomino();
                addcomputerDomino(i,true);
                x = true;
            }
            if (x == true) break;
            if(computerDomino.getRight() == playedDomino.getLast().getRight()){
                computerDomino.flipDomino();
                addcomputerDomino(i,false);
                x=true;
            }
            if (x==true) break;
            if(computerDomino.getLeft() == playedDomino.getLast().getRight()){
                addcomputerDomino(i,false);
                x=true;
            }
            if (x==true) break;
        }
        if(x != true){
            computerDraw();
        }
    }

    /**
     * Function to draw for computer
     */
    public void computerDraw(){
        for (int i=0; i<drawboneyard.size();i++){
            computerHands.add(drawboneyard.remove(i));
            break;
        }
        computerCases();
    }

    /**
     * Checks for the human draw is valid or not
     */
    public void playerDraw(){
        boolean draw =false;
        for(int i = 0; i< playerHands.size(); i++){
            if(playerHands.get(i).getLeft()==playedDomino.getFirst().getRight()
                    || playerHands.get(i).getRight()==playedDomino.getFirst().getLeft()) {
                System.out.println("Play the dominos you have");
                player();
            }
            if(playerHands.get(i).getLeft()==playedDomino.getLast().getRight()
                    || playerHands.get(i).getRight()==playedDomino.getLast().getLeft()) {
                if((playerHands.get(i).getLeft() == 0) && (playerHands.get(i).getRight() == 0)){
                    System.out.println("Play [0 , 0]");
                }
                System.out.println("Play the dominos you have");
                player();
            }
            draw=true;
        }
        if(draw==true) {
            for (int i = 0; i < drawboneyard.size(); i++) {
                playerHands.add(drawboneyard.remove(i));
                break;
            }
            player();
        }
    }

    /**
     * Adds to the player's list
     * @param index
     */
    public void addplayerDomino(int index) {
        Dominos dominos = playerHands.remove(index);
        humanplayedDomino.add(dominos);
        if(direction()==true){
            playedDomino.addFirst(dominos);}
        else {
            playedDomino.addLast(dominos);
        }
    }

    /**
     * Adds to computer's list
     */
    public void addcomputerDomino(int index, boolean left) {
        Dominos dominos = computerHands.remove(index);
        if (left == false){
            computerplayedDomino.addLast(dominos);
            playedDomino.addLast(dominos);
        }
        else{
            computerplayedDomino.addFirst(dominos);
            playedDomino.addFirst(dominos);
        }
    }
}
