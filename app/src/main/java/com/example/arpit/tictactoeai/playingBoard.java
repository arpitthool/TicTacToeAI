package com.example.arpit.tictactoeai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class playingBoard extends AppCompatActivity {

    private Button btnRefresh;
    private TextView playerWon;
    private RadioGroup rg;
    private boolean firstPlayerAI = false;
    private boolean secondPlayerAI = false;
    private int counter;
    private Button[][] buttonsArray = new Button[3][3];
    public int[][] board = new int[3][3];
    private final int[][] buttonsID = {
            {   R.id.btn_0, R.id.btn_1, R.id.btn_2},
            {   R.id.btn_3, R.id.btn_4, R.id.btn_5},
            {   R.id.btn_6, R.id.btn_7, R.id.btn_8}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_board);

        playerWon = (TextView)findViewById(R.id.txt_playerWon);
        this.counter = 1;

        //Add listener to the playing board buttons
        for( int i =0;i<3;i++){
            for( int j =0;j<3;j++){
                this.buttonsArray[i][j] = findViewById( this.buttonsID[i][j]);
                this.buttonsArray[i][j].setBackgroundResource( R.drawable.empty);
                final int finalJ = j;
                final int finalI = i;
                this.buttonsArray[i][j].setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        clickButton(finalI, finalJ);
                    }

                });
            }
        }

        //Add listener to the refresh button
        this.btnRefresh = (Button)findViewById( R.id.btn_refresh);
        this.btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });

        //Add listener to the radio buttons
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_grp);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_firstPlayerAI:
                        firstPlayerAI = true;
                        secondPlayerAI = false;
                        refresh();
                        break;
                    case R.id.radio_secondPlayerAI:
                        firstPlayerAI = false;
                        secondPlayerAI = true;
                        refresh();
                        break;
                    case R.id.radio_humanVshuman:
                        firstPlayerAI = false;
                        secondPlayerAI = false;
                        refresh();
                        break;
                }
            }
        });

        playAI();
    }

    public void clickButton(int i, int j) {
        if( firstPlayerAI){
            if ( counter % 2 == 1) {
                buttonsArray[i][j].setBackgroundResource( R.drawable.tick);
                board[i][j] = 1;
            } else {
                buttonsArray[i][j].setBackgroundResource( R.drawable.cross);
                board[i][j] = -1;
            }
        }
        else if( secondPlayerAI){
            if (counter % 2 == 1) {
                buttonsArray[i][j].setBackgroundResource( R.drawable.tick);

                board[i][j] = -1;
            } else {
                buttonsArray[i][j].setBackgroundResource( R.drawable.cross);
                board[i][j] = 1;
            }
        }
        else{
            if (counter % 2 == 1) {
                buttonsArray[i][j].setBackgroundResource( R.drawable.tick);
                board[i][j] = -1;
            } else {
                buttonsArray[i][j].setBackgroundResource( R.drawable.cross);
                board[i][j] = 1;
            }
        }
        buttonsArray[i][j].setClickable(false);
        counter++;
        showWinner();
        playAI();
    }

    private void showWinner(){
        if( this.findWinner() == 1){
            if( this.firstPlayerAI){
                this.playerWon.setText("Haha I Win!!");
                this.disableAll();
            }
            else if(this.secondPlayerAI){
                this.playerWon.setText("Haha I Win!!");
                this.disableAll();
            }
            else{
                this.playerWon.setText("Player 1 Wins!!");
                this.disableAll();
            }
        }
        else if( this.findWinner() == -1){
            if( this.firstPlayerAI){
                this.playerWon.setText("Haha I Win!!");
                this.disableAll();
            }
            else if(this.secondPlayerAI){
                this.playerWon.setText("Haha I Win!!");
                this.disableAll();
            }
            else{
                this.playerWon.setText("Player 1 Wins!!");
                this.disableAll();
            }
        }
        else if (!this.isMoveLeft()) {
            if( this.firstPlayerAI){
                this.playerWon.setText("It's a Draw!!");
                this.disableAll();
            }
            else if(this.secondPlayerAI){
                this.playerWon.setText("It's a Draw!!");
                this.disableAll();
            }
            else{
                this.playerWon.setText("It's a Draw!!");
                this.disableAll();
            }
        }
    }

    private void pause(int x){
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void disableAll(){
        for ( int i=0;i<3;i++ ){
            for( int j=0;j<3;j++){
                buttonsArray[i][j].setClickable( false);
            }
        }
    }

    private boolean isMoveLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int findWinner() {
        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] + this.board[i][1] + this.board[i][2] == 3) {

                return 1;
            } else if (this.board[i][0] + this.board[i][1] + this.board[i][2] == -3) {
                return -1;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (this.board[0][i] + this.board[1][i] + this.board[2][i] == 3) {
                return 1;
            }
            if (this.board[0][i] + this.board[1][i] + this.board[2][i] == -3) {
                return -1;
            }
        }

        if (this.board[0][0] + this.board[1][1] + this.board[2][2] == 3) {
            return 1;
        }
        if (this.board[0][0] + this.board[1][1] + this.board[2][2] == -3) {
            return -1;
        }

        if (this.board[0][2] + this.board[1][1] + this.board[2][0] == 3) {
            return 1;
        }
        if (this.board[0][2] + this.board[1][1] + this.board[2][0] == -3) {
            return -1;
        }
        return 0;
    }

    private void refresh(){
        this.playerWon.setText("");
        this.counter = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttonsArray[i][j].setBackgroundResource(R.drawable.empty);
                this.buttonsArray[i][j].setClickable(true);
                this.board[i][j] = 0;
            }
        }
        playAI();
    }

    private void playAI() {
        if( this.isMoveLeft() ) {
            if (this.firstPlayerAI && this.counter % 2 == 1) {
                if (this.counter == 1) {
                    this.buttonsArray[0][0].performClick();
                } else {
                    AI_Player x = new AI_Player();
                    int[] bestMove = x.findBestMove(this.board);
                    this.buttonsArray[bestMove[0]][bestMove[1]].performClick();
                }
            } else if (this.secondPlayerAI && this.counter % 2 == 0) {
                AI_Player x = new AI_Player();
                int[] bestMove = x.findBestMove(this.board);
                this.buttonsArray[bestMove[0]][bestMove[1]].performClick();
            }
        }
    }
}