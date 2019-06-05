import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
public class Main {
    public static void main(String[] args){
        Village playerVillage;
        //initializes with save if user wants too... also i wanted to cheat at my own game so yea...
        if(JOptionPane.showInputDialog("Use Save File? y/n").equalsIgnoreCase("y")){
            try{
                FileReader save = new FileReader("Village1.txt");
                BufferedReader reader = new BufferedReader(save);
                int wood,food,pop,day,houses;
                String temp;
                reader.readLine();//skips needless line
                temp = reader.readLine(); //could have done this in one statement but Its my program not yours dont judge i know it was dumb
                temp = temp.replaceAll("Population: ", "");//gets rid of non numbers
                pop = Integer.parseInt(temp);//parses line for int input
                temp = reader.readLine();
                temp = temp.replaceAll("Houses: ", "");
                houses = Integer.parseInt(temp);
                reader.readLine();//two more needless lines in save file
                reader.readLine();
                temp = reader.readLine();
                temp = temp.replaceAll("Wood: ", "");
                wood = Integer.parseInt(temp);
                temp = reader.readLine();
                temp = temp.replaceAll("Food: ", "");
                food = Integer.parseInt(temp);
                temp = reader.readLine();
                temp = temp.replaceAll("Day: ","");
                day = Integer.parseInt(temp);
                playerVillage = new Village(wood,food,pop,day,houses);
                JOptionPane.showMessageDialog(null,"Welcome back!");
                GameRunner game = new GameRunner(playerVillage);
            }catch(Exception e){
                System.out.println("Error: " + e);
            }
        }
        else{
            playerVillage = new Village(); // initialize village to be used can use overwritten village to simulate different part of game or save
            JOptionPane.showMessageDialog(null,"You wander into a clearing with a small house... it seems abandoned...");
            GameRunner game = new GameRunner(playerVillage); //start game runner
        }

    }
}
