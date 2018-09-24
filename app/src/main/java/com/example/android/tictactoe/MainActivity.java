package com.example.android.tictactoe;

import android.os.Build;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM;

public class MainActivity extends AppCompatActivity {

    int[] field = new int[9];
    /*
            0 | 1 | 2
            ---------
            3 | 4 | 5    -  field map
            ---------
            6 | 7 | 8
     */
    int userFlag = 1; // Next turn User 1 or User 2
    boolean isCellOcuppied = false; // Not need may be
    int clickedCellId;
    public int isWin(){

            if( field[0] + field[1] + field[2] == 3|| //first row
                    field[0] + field[3] + field[6] == 3|| //first column
                    field[6] + field[7] + field[8] == 3|| //third row
                    field[2] + field[5] + field[8] == 3|| //third column
                    field[1] + field[4] + field[7] == 3|| //second column
                    field[3] + field[4] + field[5] == 3|| // second row
                    field[0] + field[4] + field[8] == 3||
                    field[2] + field[4] + field[6] == 3){
                return 1;// Player 1 is Win
            }
            if( field[0] + field[1] + field[2] == -3||
                    field[0] + field[3] + field[6] == -3||
                    field[6] + field[7] + field[8] == -3||
                    field[2] + field[5] + field[8] == -3||
                    field[1] + field[4] + field[7] == -3||
                    field[3] + field[4] + field[5] == -3||
                    field[0] + field[4] + field[8] == -3||
                    field[2] + field[4] + field[6] == -3){
                return -1;// Player 2 is Win
            }

        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        LinearLayout firstRow =findViewById(R.id.firstRow);
        LinearLayout secondRow =findViewById(R.id.secondRow);
        LinearLayout thirdRow =findViewById(R.id.thirdRow);
        for(int i =0; i<field.length; i++){
            final TextView cell = new TextView(MainActivity.this);
            int width = (int)getResources().getDimension(R.dimen.row_height);
            int textSize = (int)getResources().getDimension(R.dimen.figureSize);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width);
            cell.setId(i+100000);
            cell.setText(" ");
            cell.setGravity(Gravity.CENTER);//выравнивание по центру ячейки
            cell.setBackgroundResource(R.drawable.border);//рамка
            cell.setTextSize(textSize); // фиксированый размер
            if(i<3)
            {
                firstRow.addView(cell, layoutParams); //первый ряд 0,1,2
            }
            else if(i<6)
            {
                secondRow.addView(cell, layoutParams); // второй ряд 3,4,5
            }
            else
            {
                thirdRow.addView(cell, layoutParams); // третий ряд 6,7,8
            }
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if((" ").contentEquals(cell.getText())) {
                        if (userFlag == 1) {
                            cell.setText("X");
                            clickedCellId = (cell.getId())-100000;
                            field[clickedCellId] = 1;
                            userFlag = 2;
                        } else {
                            cell.setText("O");
                            clickedCellId = (cell.getId())-100000;
                            field[clickedCellId] = -1;
                            userFlag = 1;
                        }
                     //Todo: add isWin()
                        if(isWin() == 1){
                            Toast player1 = Toast.makeText(getApplicationContext(),"Player X - is Win", Toast.LENGTH_LONG);
                            player1.show();
                        }else if(isWin() == -1){
                            Toast player2 = Toast.makeText(getApplicationContext(),"Player O - is Win", Toast.LENGTH_LONG);
                            player2.show();
                        }
                    }
                }
            });

        }
    }
}
