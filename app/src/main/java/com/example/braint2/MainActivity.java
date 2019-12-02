package com.example.braint2;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    GridLayout gl;
    RelativeLayout rl;
    TextView qn;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    TextView note;
    Generator begin;
    CountDownTimer c;
    Button b5;
    Button b6;
    String v;
    int keep; //store solved answer
    int totalqns;
    int passFail;



    public void start(View view){
        Button go = findViewById(R.id.button);
        go.setVisibility(View.INVISIBLE);

        gl.setVisibility(View.VISIBLE);
        rl.setVisibility(View.VISIBLE);

        c = new CountDownTimer(24000, 1000) {
            @Override
            public void onTick(long l) {
                b5.setText(l/1000 + "s");


            }

            @Override
            public void onFinish() {
                b1.setClickable(false);
                b2.setClickable(false);
                b3.setClickable(false);
                b4.setClickable(false);
                Log.i("passed", "pass"+ passFail);

            }
        }.start();

    }

    // next qn
    public void transition(View view){
       //int change = Integer.parseInt(v);
        Button b = (Button) view; // change view to button presses
        int anz = Integer.parseInt(b.getText().toString());
       // Log.i("lol", "lol"+ b.getText().toString());
       // Log.i("aa", "cc"+ keep);
       // int change  = Integer.parseInt(view.getTag().toString());
        if (anz == keep){
            // if ans == pressed
            updatePass(true);
        }else{
            updatePass(false);
        }



    }
    public void updatePass(boolean ans){
        if (ans){
            passFail+=1;
            qn.setText("CORRECT");
            qn.setTextColor(getResources().getColor(android.R.color.holo_red_dark));

        }else{ qn.setText("WRONG");
            qn.setTextColor(getResources().getColor(android.R.color.black));}

        totalqns +=1;
        b6.setText(Integer.toString(passFail)+"/"+Integer.toString(totalqns) );
        begin = new Generator();
        note.setText(begin.question());
        Button dunno = answerBtn();
        keep = begin.solved();
        dunno.setText(Integer.toString(begin.solved()));

        updatebtn(dunno);
    }
    //randomly put answer in a button

    public Button answerBtn(){
        ArrayList<Button> think = new ArrayList<Button>();
        think.add(b1);
        think.add(b2);
        think.add(b4);
        think.add(b3);
        Random rand = new Random();
        return think.get(rand.nextInt(think.size()));
    }
    public void updatebtn(Button btn){
        v = btn.getTag().toString() ;
        int a = Integer.parseInt(v);
        if (a == 1){
            b2.setText(Integer.toString(begin.value()));
            b3.setText(Integer.toString(begin.value()));
            b4.setText(Integer.toString(begin.value()));
        }else if (a == 2){

            b1.setText(Integer.toString(begin.value()));
            b3.setText(Integer.toString(begin.value()));
            b4.setText(Integer.toString(begin.value()));

        }else if (a==3){
            b1.setText(Integer.toString(begin.value()));
            b2.setText(Integer.toString(begin.value()));
            b4.setText(Integer.toString(begin.value()));
        }else{
            b1.setText(Integer.toString(begin.value()));
            b2.setText(Integer.toString(begin.value()));
            b3.setText(Integer.toString(begin.value()));

        }

    }
    class Generator{
        int store;
        int store2;
        public Generator(){

        }



            public int value(){
                Random r = new Random();
                int number = r.nextInt(51);
                return number;


            }
            public String question(){
                 int val1 = value();
                 store = val1;
                 int val2 = value();
                 store2 = val2;
                 String text = (val1)+ "+ " + (val2);
                 return text;
            }
            public int solved(){
                 int ans = store + store2;
                 return ans;

            }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        begin = new Generator();

        gl = findViewById(R.id.gridLayout2);
        rl = findViewById(R.id.RL);
        note = findViewById(R.id.add);

        gl.setVisibility(View.INVISIBLE);
        rl.setVisibility(View.INVISIBLE);
        //gl.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        b1 = findViewById(R.id.button1);
        b1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        b2 = findViewById(R.id.button2);
        b2.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
        b3 = findViewById(R.id.button3);
        b3.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        b4 = findViewById(R.id.button4);
        b4.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        b5 = findViewById(R.id.timer);
        b6 = findViewById(R.id.pass);
        qn = findViewById(R.id.correcto);
        note.setText(begin.question());
        Button dunno = answerBtn();
        keep = begin.solved();
        b6.setClickable(false);
        b5.setClickable(false);
        totalqns = 1;
        passFail = 0;
        b6.setText(Integer.toString(passFail)+"/"+Integer.toString(totalqns) );
        dunno.setText(Integer.toString(begin.solved()));

        updatebtn(dunno);














    }
}
