# Red Alert
## Dexterity Game Built Using Java Swing

### About
Red Alert is a dexterity game which generates a random spread of green and red buttons, asking the player to change them all to green by clicking on them. The program that creates its own game window using Java Swing and AWT to create functional buttons.

### Game Window
Using Java Swing, the program creates twenty buttons on load, each with a randomly decided background color (green or red) and tha ability to change the color through a user click. It uses GridLayout() to format the buttons into a 5 x 4 grid and the pack() method to place them into an appropriately sized window. Dimensions can be easily changed withing the code if desired.

### Action Events
AWT is used to give each button a click event. A custom event listener is attached to each button, checking the color and swapping it to the opposite on a click event. This is done dynamically using list iteration, so the grid size can be changed by only refactoring a small portion of the code. This makes is scalable in the event of adding new difficulty setting, board sizes, etc.

### Randomization
The buttons are randomized using a RandomSeed file I created, which returns a list of binary digits that is exactly 20 items long and has exactly ten of each digit. This ensures balance and also creates an extra mechanic where the player can accidentally click a green button and turn it red if they're not careful, moving them further from their goal.

### Timer
The Timer method of java.util is used to run a block of code on an interval. This is where the logic for the countdown at the top of the window is stored, as well as the levelComplete checking logic. Each second (1000ms) the program ticks down the countdown value and iterates through the buttons to check that all of them are green. If so, a new random seed is generated and is used to assign a new spread of colors to the board. The level counter is incremented each time this happens and your final score is displayed to you via alert when the timer hits zero. You also receive bonus time

### Design Challenges
In creating this program, I challenged myself to learn Java in a functional user-facing way. faced several design challenges. Below are the most prominent and the solutions I found:
- I needed the program to check for a complete board almost constantly. I first planned to impement a loop that runs concurrent to the timer, but it required creating more variables and logic to keep it in sync with the game. I addressed this by putting the logic in as a timer task, since it was already running a task each second. 
- I had a friend test the game and found that the levelComplete logic would sometimes take slightly longer (a few hundred ms) to process and hamper the experience. I fixed this by adding an extra second to the reward time for finishing a level.
- I slightly increased the speed of processing by adding break statement to the button-checking loop in the levelComplete logic. Once it has found a single red button, it stops iterating, saving on processing load and allowing it to start the next check faster.
