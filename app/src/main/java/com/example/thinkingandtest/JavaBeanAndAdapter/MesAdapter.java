package com.example.thinkingandtest.JavaBeanAndAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thinkingandtest.R;

import java.util.List;

public class MesAdapter extends RecyclerView.Adapter<MesAdapter.ViewHolder> {

    private List<Message> MesList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout y_layout, m_layout;
        TextView y_tv, m_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //绑定控件
            y_layout = itemView.findViewById(R.id.it_mes_y_layout);
            m_layout = itemView.findViewById(R.id.it_mes_m_layout);
            y_tv = itemView.findViewById(R.id.it_tv_y);
            m_tv = itemView.findViewById(R.id.it_tv_m);
        }
    }


    public MesAdapter(List<Message> mesList) {
        MesList = mesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = MesList.get(position);
        if(message.getType() == Message.TYPE_RECEIVED){
            //如果是收到消息，显示左边y_layout
            holder.y_layout.setVisibility(View.VISIBLE);
            holder.m_layout.setVisibility(View.GONE);
            holder.y_tv.setText(message.getContent());
        }else if(message.getType() == Message.TYPE_SENT){
            //如果是发送消息，显示右边m_layout
            holder.m_layout.setVisibility(View.VISIBLE);
            holder.y_layout.setVisibility(View.GONE);
            holder.m_tv.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return MesList.size();
    }
}
