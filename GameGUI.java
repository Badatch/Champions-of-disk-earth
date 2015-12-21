import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import javax.swing.*;
/**
 * A GUI that runs the game with the basic methods to play and complete the game.
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
public class GameGUI
{
    Game CODE = new Player("Hero");
    private JFrame myFrame = new JFrame("CODE");
    private Font titleFont = new Font("Sans Serif", Font.BOLD, 20);

    private Container contentPane = myFrame.getContentPane();

    private JPanel westPanel = new JPanel();
    private JPanel centrePanel = new JPanel(new BorderLayout(1,1));

    private JButton meetChall = new JButton("Meet Challenge");   
    private JButton viewState = new JButton("View state");
    private JButton clear = new JButton("Clear");
    private JButton quit = new JButton("Quit");

    private JTextArea textArea = new JTextArea();

    private JScrollPane scroll;

    /**
     * Constructor for the GameGUI class. Here we add the different panels and areas and add things to them.
     * We also declare the action listners for the buttons that will be present on the main content pane and panels
     */
    public GameGUI()
    {        
        contentPane.setLayout(new BorderLayout());

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        textArea.setVisible(true);
        scroll = new JScrollPane(textArea);
        contentPane.add(westPanel,BorderLayout.WEST);
        westPanel.setLayout(new GridLayout(4,1));

        contentPane.add(centrePanel,BorderLayout.CENTER);
        centrePanel.setVisible(true);
        centrePanel.add(scroll, BorderLayout.CENTER);
        scroll.setVisible(false);


        meetChall.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { meetChallenge(); }
            });

        viewState.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { state(); }
            });

        clear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { clear(); hideClear(); }
            });

        quit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { quit(); }
            });

        makeMenuBar();
        westPanel.add(meetChall);
        westPanel.add(viewState);
        westPanel.add(quit);
        westPanel.add(clear);
        clear.setVisible(false);

        myFrame.getContentPane().setPreferredSize(new Dimension(500,400));
        myFrame.pack();
        myFrame.setVisible(true);
    }

    //Panel button methods
    /**
     * The method for view state which is called when the View State button is pressed
     */
    private void state()
    {
        hideClear();
        scroll.setVisible(true);
        textArea.setText(CODE.toString());
        myFrame.pack();
    }

    /**
     * The method for clearing the text area from the screen when the button is pressed
     */
    private void clear()
    {
        textArea.setText("");
        scroll.setVisible(false);
    }

    /**
     * The method for quitting the game
     */
    private void quit()
    {
        int choice = JOptionPane.showConfirmDialog(myFrame, "Do you wish to quit game?", "Enter", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(myFrame, CODE.toString());
            System.exit(0);
        }
    }

    //Menu bar methods
    /**
     * Method for bringing up the hire champion input dialog
     */
    private void hireChamp()
    {
        hideClear();
        scroll.setVisible(false);
        String champNm = JOptionPane.showInputDialog("Enter the name of the Champion you want to hire");
        String message = CODE.hireChampion(champNm);
        JOptionPane.showMessageDialog(myFrame, message);
        myFrame.pack();
    }

    /**
     * The method for meetChallenge input dialog
     */
    private void meetChallenge()
    {
        hideClear();
        scroll.setVisible(false);
        String strNo = JOptionPane.showInputDialog("Enter the Challenge Number you wish to try");
        try
        {
            int challNo = Integer.parseInt(strNo);
            String message = CODE.meetChallenge(challNo);
            JOptionPane.showMessageDialog(myFrame, message);
            myFrame.pack();
        }
        catch (NumberFormatException e)
        {
        }
    }

    /**
     * Displays all the champions to the text area
     */
    private void listChamps()
    {
        String s = CODE.getAllChampionsForHire();
        textArea.setText(s);
        scroll.setVisible(true);
        clear.setVisible(true);        
        myFrame.pack();
    }

    /**
     * Displays all the champions in the army to the text area
     */
    private void listArmy()
    {
        String s = CODE.getArmy();
        textArea.setText(s);
        scroll.setVisible(true); 
        clear.setVisible(true);              
        myFrame.pack();
    }

    /**
     * The champion is dismissed when entering their name in the input dialog
     */
    private void dismissChampion()
    {
        hideClear();
        scroll.setVisible(false);
        String champNm = JOptionPane.showInputDialog("Enter the name of the Champion you want to dismiss");
        if(CODE.getChampion(champNm) != null)
        {
            boolean result = CODE.dismissChampion(champNm);
            if(result)
            {
                String message = ("\nChampion " + champNm +
                        " dismissed" + "\nTreasury: " + CODE.getMoney());
                JOptionPane.showMessageDialog(myFrame, message);
                myFrame.pack();
            }
            else
            {
                String message = ("Dismissal not performed");
                JOptionPane.showMessageDialog(myFrame, message);
                myFrame.pack();
            }
        }
        myFrame.pack();       
    }

    /**
     * Restores a champion if they are resting using an input dialog
     */
    private void restoreChampion()
    {
        hideClear();
        scroll.setVisible(false);
        String champNm = JOptionPane.showInputDialog("Enter the name of the Champion you want to restore");
        {   
            boolean result = CODE.restoreChampion(champNm);
            if(result)
            {
                String message = ("\nChampion " + champNm +
                        " restored" + "\nTreasury: " + CODE.getMoney());
                JOptionPane.showMessageDialog(myFrame, message);
                myFrame.pack();
            }
            else
            {
                String message = ("Restoration not performed");
                JOptionPane.showMessageDialog(myFrame, message);
                myFrame.pack();
            }
        }
        myFrame.pack();
    }

    /**
     * Calls the getChampion() method and returns the champion using the name entered in the input dialog
     */
    private void viewChampion()
    {
        hideClear();
        scroll.setVisible(false);
        String champNm = JOptionPane.showInputDialog("Enter the name of the Champion you want to view");
        String message = CODE.getChampion(champNm);
        if (champNm == null)
        {
        }
        else if(champNm != null )
        {
            JOptionPane.showMessageDialog(myFrame, message);
        }
        myFrame.pack();
    }

    /**
     * Hides the clear button
     */
    private void hideClear()
    {
        clear.setVisible(false);
    }

    /**
     * Makes the menu bar located at the top of the screen which stores methods related to champions.
     * It also declares the action listeners for the menu items.
     */
    private void makeMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        myFrame.setJMenuBar(menuBar);

        JMenu champMenu = new JMenu("Champions");
        menuBar.add(champMenu);

        JMenuItem allChampItem = new JMenuItem("List all champions");
        allChampItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { listChamps(); }
            });
        champMenu.add(allChampItem);

        JMenuItem armyItem = new JMenuItem("List my army");
        armyItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { listArmy(); }
            });
        champMenu.add(armyItem);

        JMenuItem hireItem = new JMenuItem("Hire champion");
        hireItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { hireChamp(); }
            });
        champMenu.add(hireItem);

        JMenuItem dismissItem = new JMenuItem("Dismiss champion");
        dismissItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { dismissChampion(); }
            });
        champMenu.add(dismissItem);

        JMenuItem restoreItem = new JMenuItem("Restore champion");
        restoreItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { restoreChampion(); }
            });
        champMenu.add(restoreItem);

        JMenuItem viewItem = new JMenuItem("View champion");
        viewItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { viewChampion(); }
            });
        champMenu.add(viewItem);
    }
}
