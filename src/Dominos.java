/**
 * @author : Suyog Raj Joshi
 * CS351L
 * Project2 : Dominos
 * Date: 02/25/2021
 */
public class Dominos {

    int right;
    int left;

    /**
     * @param left for the left element of the domino
     * @param right for the right element of the domino
     */
    public Dominos (int left, int right) {
        this.left = left;
        this.right = right;
    }

    /**
     * @returns left element of the domino
     * */
    public int getLeft() {
        return left;
    }

    /**
     * @returns right element of the domino
     */
    public int getRight() {
        return right;
    }

    /**
     * Flips the domino
     */
    public void flipDomino() {
        int temp = this.getLeft();
        left = this.getRight();
        right = temp;
    }

    /**
     * @returns the domino
     */
    public String toString() {
        return "[" + getLeft() + " " + getRight() + "]";
    }
}
