import javax.swing.JFrame;
import java.util.Scanner;


public class Bars{
    static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        JFrame screen = new JFrame();
        System.out.println("how many bars?(1-100)");
        int totalBars = input.nextInt();
        
        BarMaker rows = new BarMaker(totalBars);

        screen.setBounds(0,0,700,600);
        screen.setTitle("visualizer");
        screen.setResizable(true);
        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screen.add(rows);
        inquiry(rows);
        screen.setVisible(false);
        screen.dispose();
    }
        
        public static void sleep(){
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        public static void inquiry(BarMaker rows){
            String s = " ";
            while(s.equals("done") == false){
                System.out.println("what sort would you like to use: selection, insertion, bubble, merge, quick \nor are you done?");
                s = input.nextLine();
                rows.inquiry(s);
            }
            return;
        }
    
}