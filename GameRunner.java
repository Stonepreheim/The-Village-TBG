import javax.swing.JOptionPane;

public class GameRunner {
    public GameRunner(Village playerVillage){
        boolean hasQuit = false;
        if(JOptionPane.showInputDialog("Show how to play? Y/N").equalsIgnoreCase("y")){
            JOptionPane.showMessageDialog(null, "Every day you will assign jobs for builders, collectors, and farmers.\nFarmers get 2 food a day each\nBuilders will build a house for every 3 assigned in that day\nCollectors will collect 2 wood per day\nit takes 15 wood to build a house and for every house your population capacity is increased by 5\nEach person in your village eats 1 food per day don't let them starve!\nEvery night a random event will take place. Be ready for anything!");
        }
        while(playerVillage.getFood() > 0 && playerVillage.getPop() > 0 && hasQuit == false){
            JOptionPane.showMessageDialog(null,  "Day: " + playerVillage.getDay() + "\nVillage Pop: " + playerVillage.getPop());
            playerVillage.assignJobs();
            playerVillage.runEvent();
            if(playerVillage.getDay() == 15){
                JOptionPane.showMessageDialog(null,"Sounds of laughter and all hell can be heard in the night... Things are getting worse...");
            }
            JOptionPane.showMessageDialog(null, playerVillage.showVillage());
            playerVillage.subFood(playerVillage.getPop());
            playerVillage.addDay();
            String cont = JOptionPane.showInputDialog("Start next day?\n\tY/N\nn will quit game");
            if(cont.equalsIgnoreCase("n")){
                //playerVillage.subFood(playerVillage.getFood());
                //JOptionPane.showMessageDialog(null,"Your Village Starved...\nDays Survived: " + playerVillage.getDay());
                hasQuit = true;
            }
        }
        if(hasQuit){
            System.out.println("USER HAS QUIT");
            playerVillage.saveVillage();
            System.out.println("Village Saved Successfully... Exiting");
        }
        else{
            JOptionPane.showMessageDialog(null, "Your Village Starved...\nDays Survived: " + playerVillage.getDay());
        }
    }
}
