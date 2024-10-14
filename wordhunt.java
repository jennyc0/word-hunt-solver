import java.io.*;
import java.util.*;

public class wordhunt {
    
    private static ArrayList<String> words ;
    
    public static void main(String[] args)throws FileNotFoundException {
        words = new ArrayList<>();
        Scanner sc = new Scanner(new File("wordList.txt"));
        while (sc.hasNextLine()){
            String word = sc.nextLine();
            if (word.length()>1){
                words.add(word.toLowerCase());
            }
        }
        
        
    }
}

