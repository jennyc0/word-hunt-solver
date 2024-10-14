import java.util.*;
import java.io.*;

public class Board {
    public static final int SIZE = 4;
    private String[][] board;
    private static ArrayList<String> wordsFound;
    private Trie dict;
    public Board(String currBoard) throws FileNotFoundException {
        wordsFound = new ArrayList<>();
        board = new String[4][4];
        Scanner sc = new Scanner(new File("wordList.txt"));
        dict = new Trie();
        while (sc.hasNextLine()){
            String word = sc.nextLine();
            if (word.length()>2) {
                dict.insert(word);
            }
        }
        //print board 
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                board[row][col] = currBoard.substring(0, 1);
                currBoard = currBoard.substring(1);
                System.out.print(board[row][col] + "|");
            }
            System.out.println();
        }
    }
    
    public boolean inDict(String word ){
        return dict.findWord(word);
    }
    
    public void traverse (int row, int col, String buildWord, boolean[][] visited){
        //if (row < 0 || row >= SIZE || col < 0 || col >= SIZE ) return;
        //if (visited[row][col]) return;
        if (!dict.startsWith(buildWord) || visited[row][col]) {
            return;
        }
        //add to wordsFound if current fragment of word is a word 
        if (dict.findWord(buildWord) && !wordsFound.contains(buildWord)){
            wordsFound.add(buildWord);
        }
        
        buildWord += board[row][col];
        visited[row][col] = true;
        
        //go all directions 
        for (int r2 = row-1; r2 <= row + 1 && r2 < SIZE; r2++){
            for (int c2 = col-1; c2 <= col +1 && c2 < SIZE; c2++){
                if (r2 >= 0 && c2 >= 0){
                    traverse(r2, c2, buildWord, visited);
                }
            }
        }
        
        visited[row][col] = false;

    }

    public void solve(){
        boolean[][] visited = new boolean[4][4];
        
        //the starting point;
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                traverse(row, col, "", visited);
            }
        }
        
        //sort wordsFound from least length to greatest 
        Collections.sort(wordsFound, (word1, word2) -> word1.length() - word2.length());
    }

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("Enter the board for the word hunt as one long line, with each row following the next.");
        System.out.println("i.e. input abcdefghijklmnop for ");
        System.out.println("|a|b|c|d|\n|e|f|g|h|\n|i|j|k|l|\n|m|n|o|p|");
        
        Scanner in = new Scanner(System.in);
        Board b = new Board(in.next());
        b.solve();
        
        for (String word : wordsFound){
            System.out.println(word);
        }
    }

}