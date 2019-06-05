import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;

public class Village {
    private int popcap, builders, collectors, farmers, popAssigned, wood, food, pop, day, houses, lDayResourses[];
    //assign variables for possible save file startup in future
    public Village(int woodIn, int foodIn, int popIn, int daySurvivedIn, int bhouses){
        wood = woodIn;
        food = foodIn;
        pop = popIn;
        day = daySurvivedIn;
        houses = bhouses;
        popcap = houses * 5;
    }
    public Village(){ //start game variables
        wood = 15;
        food = 10;
        pop = 1;
        day = 1;
        houses = 2;
        popcap = houses * 5;
    }
    //beginning of manipulation methods for private variables
    public void subWood(int x){
        wood -= x;
    }//could have written these more efficiently
    public void addWood(int x){
        wood += x;
    }
    public void subFood(int x){
        food -= x;
    }
    public void addFood(int x){
        food += x;
    }
    public void subPop(int x){
        pop -= x;
    }
    public void addPop(int x){
        pop += x;
    }
    public void addDay(){
        day++;
    }
    public void assignJobs(){// use to assign jobs every day before event
        popAssigned = 1;
    while(popAssigned != 0) {
        popAssigned = pop;
        try{
            farmers = Integer.parseInt(JOptionPane.showInputDialog("Enter Farmers for the Day" + "\nPop Remaining: " + popAssigned));
            if(farmers < 0){
                farmers = 0;
            }
        }catch(NumberFormatException e){
            farmers = 0;
            System.out.println("Exception handled Farmers set to 0");
        }
        popAssigned -= farmers;
        try{
            builders = Integer.parseInt(JOptionPane.showInputDialog("Enter Builders for the Day" + "\nPop Remaining: " + popAssigned));
            if(builders < 0){
                builders = 0;
            }
        }catch(NumberFormatException e){
            builders = 0;
            System.out.println("Exception handled builders set to 0");
        }
        popAssigned -= builders;
        try{
            collectors = Integer.parseInt(JOptionPane.showInputDialog("Enter Collectors for the Day" + "\nPop Remaining: " + popAssigned));
            if(collectors < 0){
                collectors = 0;
                System.out.println("Exception handled collectors set to 0");
            }
        }catch(NumberFormatException e){
            collectors = 0;
        }
        popAssigned -= collectors;
    }
    wood += 2 * collectors;
    food += 2 * farmers;
    if(builders >= 3 && wood >= (builders / 3)*15){
        houses += (builders / 3);
        popcap += (builders / 3) * 5;
        wood -= ((builders / 3) * 15);
    }
    }
    //end of manipulation methods
    //start access methods
    public int getDay(){
        return day;
    }
    public int getWood(){
        return wood;
    }
    public int getFood(){
        return food;
    }
    public int getPop(){
        return pop;
    }
    public String showVillage(){
        String villageList = "Stats: \n" + "Population: " + pop + "\n"
                + "Houses: " + houses +"\nPop Cap: " + popcap + "\n" + "Materials: \n" + "Wood: " + wood + "\nFood: "
                + food;
        return villageList;
    }
    //Lets try this whole saving thing
    public void saveVillage(){
        File saveFile = new File("Village1.txt");
        try{
            FileWriter saver = new FileWriter(saveFile);
            saver.write(showVillage());//writes show village to file for parsing on reopen
            saver.write("\nDay: " + day);
            saver.close();
        }catch(Exception e){
         System.out.println(e);
        }
    }
    //end access methods
    public void runEvent(){
        int random;
        if(day <= 5){
            random = (int)(Math.random()*5);//change number for number of events(higher number less chance per)
            if(random <= 3){
                JOptionPane.showMessageDialog(null,"Over the Night 3 scavengers wander in for safety...");
                if(pop < popcap){
                    pop += 3;
                }
            }
            else if(random > 3){
                JOptionPane.showMessageDialog(null,"The night is silent...");
            }
        }
        if(day > 5 && day <= 15){ //days 6-15 events
            random = (int)(Math.random()*5);
            if(random == 1){
                JOptionPane.showMessageDialog(null,"A outbreak of an unknown disease breaks out and kills 6 poor souls...");
                pop -= 6;
            }
            else if(random == 2){
                JOptionPane.showMessageDialog(null,"A pack of dogs raids the village during the night and eats " + (day * 4) + " food...");
                food -= (day * 4);
            }
            else{
                JOptionPane.showMessageDialog(null,"Two people come seeking shelter");
                if(pop < popcap && (pop+2) <= popcap){
                    pop += 2;
                }
            }
        }
        if(day > 15){ // random events for after day 15 till infinity.
            random = (int)(Math.random()*14);//change number for number of events and chance
            if(random == 1){
                JOptionPane.showMessageDialog(null, "A loud yell is heard during the night and a child is born...");
                if(pop<popcap && (pop++) <= popcap)
                    pop ++;
            }
            else if(random >= 2 && random < 5){
                JOptionPane.showMessageDialog(null,"Two people come seeking shelter");
                if(pop<popcap && (pop+ 2) <= popcap)
                    pop += 2;
            }
            else if(random == 5 && random < 10){
                JOptionPane.showMessageDialog(null,"The night is silent...");
            }
            else if(random ==10){
                JOptionPane.showMessageDialog(null, "Raiders come in the night and take 3 captives...");
                pop -= 3;
            }
            else if(random == 11){
                JOptionPane.showMessageDialog(null, "A distant king sends a convoy demanding 2 slaves and 15 food...");
                String temp = JOptionPane.showInputDialog("Comply? Y/N");
                    if(temp.equalsIgnoreCase("y")){
                        pop -= 2;
                        food -= 15;
                    }else{
                        int rand1 = (int)(Math.random()*5);
                        if(rand1 > 1){
                            JOptionPane.showMessageDialog(null,"Nobody denies king Big Dick Rick...\n"+ (pop * 2) + " food is taken...");
                            food -= (pop * 2);
                        }else{
                            JOptionPane.showMessageDialog(null,"They leave for now...");
                        }
                    }
                }
            else if(random == 12){
                JOptionPane.showMessageDialog(null,"A distant village is destroyed and people flood your village...");
                String temp = JOptionPane.showInputDialog("Accept them? Y/N");
                if(temp.equalsIgnoreCase("y")){
                    JOptionPane.showMessageDialog(null,"Your village population is filled.");
                    pop = popcap;
                }
            }
            else if(random ==13){
                JOptionPane.showMessageDialog(null,"There seems to be a storm that is brewing on the horizon");//need to implement a way to make it so days resources = 0
            }
            else if(random == 14){
                JOptionPane.showMessageDialog(null, "The people purge themselves in light of the lawless nature they live in...");
                if(wood > 10){
                    pop -= 15;
                    JOptionPane.showMessageDialog(null, "15 are murdered...");
                }else{
                    pop -= wood * 2;
                    JOptionPane.showMessageDialog(null, (wood * 2) + " are murdered...");
                }
            }
            else if(random == 15){
                JOptionPane.showMessageDialog(null,"");
            }
            }
        }
    }
