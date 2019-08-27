package com.example.hanish.health_on_the_go;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hanish on 08-03-2019.
 */

public class SwipeCardAdapter extends ArrayAdapter<String> {

    ArrayList<String> card_list;

    public SwipeCardAdapter(Context context, int resource, ArrayList<String> card_list) {
        super(context, resource);
        this.card_list = card_list;
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){

        TextView tv_card_number = (TextView)(contentView.findViewById(R.id.Question));
        tv_card_number.setText(card_list.get(position).toString());
        return contentView;
    }


    @Override
    public int getCount() {
        return this.card_list.size();
    }

}