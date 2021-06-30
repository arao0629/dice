package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    EditText ed;
    TextView tv1;
    TextView tv3;
    int[] diceImageId = new int[]{R.id.imageView1, R.id.imageView2, R.id.imageView3, R.id.imageView4, R.id.imageView5};
    ImageView[] diceImage = new ImageView[5];
    int[] diceCheck = new int[5];
    int diceCheckNo;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 5; i++) {
            diceImage[i] = this.findViewById(diceImageId[i]);
        }
        btn = findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed = findViewById(R.id.editText1);
                String name = ed.getText().toString();
                ed.setEnabled(!ed.isEnabled());
                btn.setEnabled(!btn.isEnabled());
                for (int i = 0; i < 5; i++) {
                    diceImage[i].setImageResource(0);
                }
                tv3 = findViewById(R.id.textView3);
                tv3.setText("");
                if (name.isEmpty()) {
                    tv1.setText("名前を入力してください。");
                    ed.setEnabled(!ed.isEnabled());
                    btn.setEnabled(!btn.isEnabled());
                } else {
                    tv1 = findViewById(R.id.textView1);
                    tv1.setText(String.format("%sさんの今日の運勢は", ed.getText().toString()));
                    ed.setText("");
                    Handler handler = new Handler(getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 5; i++) {
                                int[] drawableId = new int[]{R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
                                int rand = (int) (Math.floor(Math.random() * 6));
                                diceImage[i].setImageResource(drawableId[rand]);
                                diceCheckNo = rand;
                                diceCheck[i] = diceCheckNo;
                            }

                            Handler handler2 = new Handler(getMainLooper());
                            handler2.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (diceCheck[1] == diceCheck[2] && diceCheck[2] == diceCheck[3] && (diceCheck[3] == diceCheck[0] || diceCheck[3] == diceCheck[4])) {
                                        tv3.setText("大吉");
                                    } else if ((diceCheck[1] == diceCheck[2] && diceCheck[2] == diceCheck[3]) || (diceCheck[0] == diceCheck[4] && diceCheck[1] == diceCheck[3]) ||  ((diceCheck[0] == diceCheck[1]&&diceCheck[1] == diceCheck[2]) && (diceCheck[3] == diceCheck[4]))){
                                        tv3.setText("吉");
                                    } else if (diceCheck[1] == diceCheck[2] || diceCheck[2] == diceCheck[3] || diceCheck[3] == diceCheck[1]) {
                                        tv3.setText("中吉");
                                    } else if (diceCheck[0] + diceCheck[1] + diceCheck[2] + diceCheck[3] + diceCheck[4] > 10) {
                                        tv3.setText("小吉");
                                    } else {
                                        tv3.setText("凶");
                                    }
                                    ed.setEnabled(!ed.isEnabled());
                                    btn.setEnabled(!btn.isEnabled());
                                }
                            }, 1000);/*handler.post*/
                        }
                    }, 1000);/*handler.post*/
                }
            }/*onClick*/
        });/*setOnClickListener*/
    } /*onCreate*/
}/*MainActivity*/

