package com.example.thinkingandtest.JavaBeanAndAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thinkingandtest.R;

import java.util.List;


public class FruitAdapter2 extends RecyclerView.Adapter<FruitAdapter2.ViewHolder> {

    private List<Fruit> fruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View FruitView;
        ImageView icon;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FruitView = itemView;
            icon = itemView.findViewById(R.id.lv_it_icon);
            name = itemView.findViewById(R.id.lv_it_name);
        }
    }


    public FruitAdapter2(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.FruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition() + 1;
                Fruit fruit = fruitList.get(pos - 1);
                Toast.makeText(v.getContext(), "第" + pos + "位：" + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition() + 1;
                Fruit fruit = fruitList.get(pos - 1);
                Toast.makeText(v.getContext(), "第" + pos + "位图片：" + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.icon.setImageResource(fruit.getIcon());
        holder.name.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
