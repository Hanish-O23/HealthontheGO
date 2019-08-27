package com.example.hanish.health_on_the_go;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wenchao.cardstack.CardStack;

import java.util.ArrayList;
import java.util.Collection;

public class Questionaire extends AppCompatActivity implements CardStack.CardEventListener{
    ArrayList<String> card_list;
    CardStack cardstack;
    SwipeCardAdapter swipe_card_adapter;
    ImageButton ibtnYes,ibtnNo;
    String prev_dis="",qstn,pred_dis;
    Database_acess database_acess;
    int curr_qstn_no=1;
    int attempted=0;
    SharedPreferences sp;





    Collection<String> qstn_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);
        ibtnYes= (ImageButton) findViewById(R.id.ibtnYes);
        ibtnNo = (ImageButton) findViewById(R.id.ibtnNo);
        cardstack = (CardStack) findViewById(R.id.container);
        Health_databasehandler hdb = new Health_databasehandler(this);
        database_acess =Database_acess.getInstance(getApplicationContext());
        card_list = new ArrayList<>();
        sp = getSharedPreferences("D1", MODE_PRIVATE);

        database_acess.open();
        qstn_list.add("1");
        qstn = database_acess.getQuestion("1");

        card_list.add(qstn);











        cardstack = (CardStack) findViewById(R.id.container);
        cardstack.setContentResource(R.layout.cardview);
        cardstack.setStackMargin(18);
        cardstack.setListener(this);

        swipe_card_adapter = new SwipeCardAdapter(getApplicationContext(),0,card_list);
        cardstack.setAdapter(swipe_card_adapter);
        try {


            ibtnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardstack.discardTop(0);
                }
            });
            ibtnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardstack.discardTop(1);


                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    @Override
    public boolean swipeEnd(int section, float distance) {
        return true;
    }

    @Override
    public boolean swipeStart(int section, float distance) {
        return true;
    }

    @Override
    public boolean swipeContinue(int section, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void discarded(int mIndex, int direction) {

        int swiped_card_postion = mIndex -1;

        //getting the string attached with the card

        String swiped_card_text = card_list.get(swiped_card_postion).toString();

        if (direction == 1) {

            call_for_qstn(direction);

        } else if (direction == 0) {


            call_for_qstn(direction);

        } else {

            Toast.makeText(getApplicationContext(),swiped_card_text+" Swipped to Bottom",Toast.LENGTH_SHORT).show();

        }


    }
    public void call_for_qstn(int res)
    {


        database_acess.open();
        for(int i=curr_qstn_no;i<78;i++) {
            if ((res == 0)) {
                qstn_list.add((++i + "").trim());
                prev_dis = database_acess.getDisease(--i + "");
                i++;
                qstn = database_acess.getQuestion(i + "");
                card_list.add(qstn);
                curr_qstn_no = i;
                cardstack.setContentResource(R.layout.cardview);
                cardstack.setStackMargin(18);
                cardstack.setListener(this);
                swipe_card_adapter = new SwipeCardAdapter(getApplicationContext(), 0, card_list);
                cardstack.setAdapter(swipe_card_adapter);
                Toast.makeText(this,(prev_dis.equalsIgnoreCase(database_acess.getDisease(i+"")))+"", Toast.LENGTH_SHORT).show();

                if(!(prev_dis.equalsIgnoreCase(database_acess.getDisease(i+""))))
                {
                    Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
                    pred_dis=prev_dis;
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Disease", pred_dis);
                    editor.commit();
                    Intent j=new Intent(Questionaire.this,Disease_Activity.class);
                    startActivity(j);
                }

            }


            else
            {
                attempted=0;
                for (int j=curr_qstn_no;j<100&&attempted==0;j++)

                {

                    if(!(prev_dis.equalsIgnoreCase(database_acess.getDisease(j+""))))
                    {
                        curr_qstn_no=j;
                        qstn = database_acess.getQuestion(curr_qstn_no+"");
                        prev_dis = database_acess.getDisease(curr_qstn_no++ + "");
                        attempted=1;
                        card_list.add(qstn);
                        cardstack.setContentResource(R.layout.cardview);
                        cardstack.setStackMargin(18);
                        cardstack.setListener(this);
                        swipe_card_adapter = new SwipeCardAdapter(getApplicationContext(),0,card_list);
                        cardstack.setAdapter(swipe_card_adapter);
                        Toast.makeText(this,database_acess.getDisease(j+""), Toast.LENGTH_SHORT).show();;
                        break;

                    }



                }



            }

            break;




        }
    }

    @Override
    public void topCardTapped() {
        Toast.makeText(this, "blah", Toast.LENGTH_SHORT).show();

    }





}

/*public class MainActivity extends AppCompatActivity implements CardStack.CardEventListener {

    ArrayList<String> card_list;
    CardStack cardstack;
    SwipeCardAdapter swipe_card_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        card_list = new ArrayList<>();

        card_list.add("card 1");
        card_list.add("card 2");
        card_list.add("card 3");
        card_list.add("card 4");
        card_list.add("card 5");

        cardstack = (CardStack) findViewById(R.id.container);
        cardstack.setContentResource(R.layout.layout_card);
        cardstack.setStackMargin(18);
        cardstack.setListener(this);

        swipe_card_adapter = new SwipeCardAdapter(getApplicationContext(),0,card_list);
        cardstack.setAdapter(swipe_card_adapter);

    }

    @Override
    public boolean swipeEnd(int section, float distance) {
        return true;
    }

    @Override
    public boolean swipeStart(int section, float distance) {
        return true;
    }

    @Override
    public boolean swipeContinue(int section, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void discarded(int mIndex, int direction) {

        int swiped_card_postion = mIndex -1;

        //getting the string attached with the card

        String swiped_card_text = card_list.get(swiped_card_postion).toString();

        if (direction == 1) {

            Toast.makeText(getApplicationContext(),swiped_card_text+ " Swipped to Right",Toast.LENGTH_SHORT).show();

        } else if (direction == 0) {

            Toast.makeText(getApplicationContext(),swiped_card_text+" Swipped to Left",Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(),swiped_card_text+" Swipped to Bottom",Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void topCardTapped() {

        Toast.makeText(getApplicationContext(),"Clicked top card",Toast.LENGTH_SHORT).show();

    }
}*/
