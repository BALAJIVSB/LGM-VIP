
// JAVA TASK 2 TIC TAC TOE GAME - LGM VIP 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private boolean xTurn;

    public TicTacToeGame() {
        super("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        xTurn = true;

        initializeButtons();

        setSize(300, 300);
        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
                button.addActionListener(new ButtonClickListener(row, col));
                buttons[row][col] = button;
                add(button);
            }
        }
    }

    private void makeMove(int row, int col) {
        if (buttons[row][col].getText().isEmpty()) {
            if (xTurn) {
                buttons[row][col].setText("X");
            } else {
                buttons[row][col].setText("O");
            }

            xTurn = !xTurn;
            checkWin();
        }
    }

    private void checkWin() {
        String[][] board = new String[3][3];

        // Copy button values to the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = buttons[row][col].getText();
            }
        }

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])
                    && !board[row][0].isEmpty()) {
                showWinner(board[row][0]);
                return;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])
                    && !board[0][col].isEmpty()) {
                showWinner(board[0][col]);
                return;
            }
        }

        // Check diagonals
        if ((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) ||
                board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) && !board[1][1].isEmpty()) {
            showWinner(board[1][1]);
            return;
        }

        // Check for a tie
        boolean isTie = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].isEmpty()) {
                    isTie = false;
                    break;
                }
            }
        }
        if (isTie) {
            showWinner("Tie");
        }
    }

    private void showWinner(String winner) {
        String message;
        if (winner.equals("Tie")) {
            message = "It's a tie!";
        } else {
            message = "Player " + winner + " wins!";
        }
        JOptionPane.showMessageDialog(this, message);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            makeMove(row, col);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGame());
    }
}
