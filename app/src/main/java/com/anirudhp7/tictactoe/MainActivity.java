// Anirudh Prakash

package com.anirudhp7.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean turn; // false/0: player 1; true/1: player 2
    private static final String[] chars = {"X", "O"}; // could have preference for letters/characters and colors

    private int count;
    private boolean gameOver;
    private Button[] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turn = false;
        count = 1;
        gameOver = false;

        board = new Button[9];
        board[0] = findViewById(R.id.top_left);
        board[1] = findViewById(R.id.top_middle);
        board[2] = findViewById(R.id.top_right);
        board[3] = findViewById(R.id.middle_left);
        board[4] = findViewById(R.id.middle_middle);
        board[5] = findViewById(R.id.middle_right);
        board[6] = findViewById(R.id.bottom_left);
        board[7] = findViewById(R.id.bottom_middle);
        board[8] = findViewById(R.id.bottom_right);

        displayMessage("Player " + ((turn ? 1 : 0) + 1) + " (" + chars[turn ? 1 : 0] + ") turn.");

        for (final Button square : board) {
            square.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!gameOver) {
                        square.setTextSize(80);
                        square.setText(chars[turn ? 1 : 0]);
                        square.setEnabled(false);
                        square.setTextColor(getResources().getColor(R.color.colorTextPrimary));
                        displayMessage("Player " + ((turn ? 0 : 1) + 1) + " (" + chars[turn ? 0 : 1] + ") turn.");
                        check();
                        turn = !turn;
                        count++;
                    } else {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn = false;
                count = 1;
                gameOver = false;
                for (Button square : board) {
                    square.setText("");
                    square.setEnabled(true);
                }
                Toast.makeText(MainActivity.this, "Game Reset", Toast.LENGTH_SHORT).show();
                displayMessage("Player " + ((turn ? 1 : 0) + 1) + " (" + chars[turn ? 1 : 0] + ") turn.");
            }
        });
    }

    // check if game over
    private void check() {
        if (count >= 5) {
            int player = turn ? 1 : 0;
            String playerChar = chars[player];
            int size = (int) Math.sqrt(board.length);
            for (int i = 0; i < size; i++) { // across and down
                if (board[i * size].getText().toString().equals(board[i * size + 1].getText().toString()) &&
                     board[i * size].getText().toString().equals(board[i * size + 2].getText().toString()) &&
                     board[i * size].getText().toString().equals(playerChar)) {
                    gameOver = true;
                    board[i * size].setTextColor(getResources().getColor(R.color.winColor));
                    board[i * size + 1].setTextColor(getResources().getColor(R.color.winColor));
                    board[i * size + 2].setTextColor(getResources().getColor(R.color.winColor));
                    displayMessage("Player " + (player + 1) +  " (" + playerChar + ") wins!");
                }
                if (board[i].getText().toString().equals(board[i + size].getText().toString()) &&
                     board[i].getText().toString().equals(board[i + size * 2].getText().toString()) &&
                     board[i].getText().toString().equals(playerChar)) {
                    gameOver = true;
                    board[i].setTextColor(getResources().getColor(R.color.winColor));
                    board[i + size].setTextColor(getResources().getColor(R.color.winColor));
                    board[i + size * 2].setTextColor(getResources().getColor(R.color.winColor));
                    displayMessage("Player " + (player + 1) +  " (" + playerChar + ") wins!");
                }
            }
            if (board[4].getText().toString().equals(playerChar)) {
                if (board[4].getText().toString().equals(board[0].getText().toString()) &&
                    board[4].getText().toString().equals(board[8].getText().toString())) {
                    gameOver = true;
                    board[4].setTextColor(getResources().getColor(R.color.winColor));
                    board[0].setTextColor(getResources().getColor(R.color.winColor));
                    board[8].setTextColor(getResources().getColor(R.color.winColor));
                    displayMessage("Player " + (player + 1) + " (" + playerChar + ") wins!");
                }
                if (board[4].getText().toString().equals(board[2].getText().toString()) &&
                    board[4].getText().toString().equals(board[6].getText().toString())) {
                    gameOver = true;
                    board[4].setTextColor(getResources().getColor(R.color.winColor));
                    board[2].setTextColor(getResources().getColor(R.color.winColor));
                    board[6].setTextColor(getResources().getColor(R.color.winColor));
                    displayMessage("Player " + (player + 1) + " (" + playerChar + ") wins!");
                }
            }
            if(!gameOver && count >= 9) {
                gameOver = true;
                for (Button square : board) {
                    square.setTextColor(getResources().getColor(R.color.winColor));
                }
                displayMessage("It's a draw.");
            }
        }
    }

    private void displayMessage(String message) {
        TextView promptTextView = findViewById(R.id.prompter);
        promptTextView.setText(message);
    }
}
