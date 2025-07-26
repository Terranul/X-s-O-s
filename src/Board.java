import java.util.*;

import Exceptions.InvalidPositionException;

public class Board {
    
    /*
     *    1 | 2 | 3
     *    4 | 5 | 6
     *    7 | 8 | 9
     */

     private ArrayList<Integer> xList;
     private ArrayList<Integer> oList;
     private ArrayList<ArrayList<Integer>> winningCombos;
     // integer represents the position of your marker
     // the boolean being true represents the X and false represents O

     public Board() {
        xList = new ArrayList<>();
        oList = new ArrayList<>();
        generateWinningMovesList();
     }

     public void move(Integer position, Boolean isX) throws InvalidPositionException {
         if (position >= 1 && position <= 9) {
             if (isX) {
                 if (!xList.contains(position) && !oList.contains(position)) {
                     xList.add(position);
                 } else {
                     throw new InvalidPositionException();
                 }
             } else {
                 if (!oList.contains(position) && !oList.contains(position)) {
                     oList.add(position);
                 } else {
                     throw new InvalidPositionException();
                 }
             }
         } else {
            throw new InvalidPositionException();
         }
     }

     // determines if a player has won tic tac toe
     // xList contains all of the moves played by X's
     // oList contains all moves played by O's
     // isX is true when analyzing if X's have won
     // board layout 
     // 1 | 2 | 3
     // 4 | 5 | 6
     // 7 | 8 | 9
     public Boolean hasPlayerWon(Boolean isX) {
         if (isX) {
             for (ArrayList<Integer> combo : winningCombos) {
                 if (xList.containsAll(combo)) {
                     return true;
                 }
             }
         } else {
             for (ArrayList<Integer> combo : winningCombos) {
                 if (oList.containsAll(combo)) {
                     return true;
                 }
             }
         }
         return false;
     }


     public void generateWinningMovesList() {
         winningCombos = new ArrayList<>();

         winningCombos.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
         winningCombos.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
         winningCombos.add(new ArrayList<>(Arrays.asList(7, 8, 9)));

         winningCombos.add(new ArrayList<>(Arrays.asList(1, 4, 7)));
         winningCombos.add(new ArrayList<>(Arrays.asList(2, 5, 8)));
         winningCombos.add(new ArrayList<>(Arrays.asList(3, 6, 9)));

         winningCombos.add(new ArrayList<>(Arrays.asList(1, 5, 9)));
         winningCombos.add(new ArrayList<>(Arrays.asList(3, 5, 7)));
     }

     public ArrayList<Integer> generateMoves(Boolean isX) {
         ArrayList<Integer> possibleMoves = new ArrayList<>();
         if (isX) {
             for (int i = 1; i <= 9; i++) {
                 if (!xList.contains(i) && !oList.contains(i)) {
                    possibleMoves.add(i);
                 }
             }
         } else {
            for (int i = 1; i <= 9; i++) {
                 if (!oList.contains(i) && !xList.contains(i)) {
                    possibleMoves.add(i);
                 }
             }
         }
         return possibleMoves;
     }

     public Board cloneBoard() {
        Board clonedBoard = new Board();
        clonedBoard.updateOList(getoList());
        clonedBoard.updateXList(getxList());
        return clonedBoard;
     }

     private void updateXList(ArrayList<Integer> xList) {
        this.xList.addAll(xList);
     }

     private void updateOList(ArrayList<Integer> oList) {
        this.oList.addAll(oList);
     }

     public ArrayList<Integer> getoList() {
         return oList;
     }

     public ArrayList<Integer> getxList() {
         return xList;
     }
}
