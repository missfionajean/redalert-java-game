import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class RedAlert extends JFrame {

    public int[] RandomSeed() {
        // generating seed container
        int[] seed = new int[20];
    
        // declaring count variables to ensure equality
        int zeroCount = 0;
        int oneCount = 0;
    
        // for loop to access array index and update
        for (int i = 0; i < seed.length; i++) {
            // randomly generates a 1 or a 0
            int randomBinary = (int)(Math.random() * 2);
    
            // ensures there are exactly ten of each
            if (randomBinary == 0) {
                if (zeroCount < 10) {
                    seed[i] = randomBinary;
                    zeroCount++;
                } else {
                    seed[i] = 1;
                    oneCount++;
                }
            } else if (randomBinary == 1) {
                if (oneCount < 10) {
                    seed[i] = randomBinary;
                    oneCount++;
                } else {
                    seed[i] = 0;
                    zeroCount++;
                }
            } 
        }

        // return statement so class can be called elsewhere
        return seed;
    }

    public RedAlert() {
        // sets header for program window
        setTitle("Level 1 - Time: 30");

        // sets "x" button functionality to close window directly
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creates display template for elements on screen
        setLayout(new GridLayout(5, 4));

        // creates a random seed for mixing up buttons
        int[] seed = RandomSeed();

        // creates list for level checking and re-randomization
        java.util.List<JButton> allButtons = new java.util.ArrayList<>();

        // creates and adds buttons to window
        for (int i = 0; i < 20; i++) {
            // assigns the correspodning array value to button
            JButton button = new JButton();

            // makes the button red or blue based on binary
            if (seed[i] == 0) {
                button.setBackground(Color.RED);
            } else {
                button.setBackground(Color.GREEN);
            }

            // adds a function to each button press
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.getBackground() == Color.RED) {
                        button.setBackground(Color.GREEN);
                    } else {
                        button.setBackground(Color.RED);
                    }
                }
            });

            // adds the button to the visual layout
            add(button);

            // adds button to the list for management
            allButtons.add((JButton) button);

            // sets size of individual button before pack
            button.setPreferredSize(new Dimension(120, 100));
        }

        // timer logic
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            // local variables to be manipulated
            int level = 1;
            int countdown = 30;
            @Override
            public void run() {
                // time remaining
                if (countdown >= 0) {
                    // check for win
                    boolean levelComplete = true;

                    // iterates through buttons checking for all green
                    for (JButton button : allButtons) {
                        if (button.getBackground().equals(Color.RED)) {
                            levelComplete = false;
                        }
                    }

                    // iterates through buttons and reandomizes them
                    if (levelComplete) {
                        level += 1;
                        countdown += 4;
                        int[] newSeed = RandomSeed();
                        for (int i = 0; i < 20; i++) {
                            if (newSeed[i] == 0) {
                                allButtons.get(i).setBackground(Color.RED);
                            } else {
                                allButtons.get(i).setBackground(Color.GREEN);
                            }
                        }
                    }

                    // update timer
                    setTitle("Level " + level + " - Time: " + countdown);
                    countdown--;

                // not time remaining
                } else {
                    // pop-up displays final score and thank you
                    JOptionPane.showMessageDialog(null, "Final Score: " + level + "\nThanks for playing!", "GAME OVER", JOptionPane.PLAIN_MESSAGE);

                    // exits the program once "OK" is clicked
                    System.exit(0);
                }
            }
        };

        // schedule task to run every second (1000 miiliseconds)
        timer.scheduleAtFixedRate(task, 0, 1000);

        // sets frame to fit contents at or above preferred size
        pack();

        // shows window on screen instead of just in memory
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RedAlert();
            }
        });
    }
}