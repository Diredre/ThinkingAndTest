package com.example.thinkingandtest.JavaBeanAndAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thinkingandtest.R;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int res;


    public FruitAdapter(Context context, int textViewRes, List<Fruit> objects){
        super(context, textViewRes, objects);
        res = textViewRes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(res, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.icon = view.findViewById(R.id.lv_it_icon);
            viewHolder.name = view.findViewById(R.id.lv_it_name);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.icon.setImageResource(fruit.getIcon());
        viewHolder.name.setText(fruit.getName());

        return view;
    }

    class ViewHolder{
        ImageView icon;
        TextView name;
    }
}
