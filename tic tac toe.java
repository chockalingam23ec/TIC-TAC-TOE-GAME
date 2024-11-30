import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    // Define the game board
    private JButton[][] buttons;
    private char currentPlayer;
    private int moveCount;
    
    // Constructor to set up the game
    public TicTacToe() {
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        moveCount = 0;
        
        setTitle("Tic-Tac-Toe");
        setLayout(new GridLayout(3, 3)); // Create 3x3 grid
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        
        // Initialize buttons and add listeners
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
        
        setVisible(true);
    }

    // ActionListener for button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        
        // If the button is already marked, ignore the click
        if (!clickedButton.getText().equals("")) {
            return;
        }
        
        // Mark the button with the current player's symbol
        clickedButton.setText(String.valueOf(currentPlayer));
        moveCount++;
        
        // Check if the current player has won
        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetGame();
            return;
        }
        
        // Check if it's a draw
        if (moveCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
            return;
        }
        
        // Switch players
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Method to check if the current player has won
    private boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) && 
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) && 
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) && 
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) && 
                buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) && 
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) && 
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) && 
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) && 
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    // Method to reset the game
    private void resetGame() {
        moveCount = 0;
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        new TicTacToe();
    }
}

