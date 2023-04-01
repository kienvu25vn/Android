package com.example.ktra1.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra1.R;

import java.util.ArrayList;
import java.util.List;

public class LogoAdapter extends RecyclerView.Adapter<LogoAdapter.LogoViewHolder>{
    private Context context;
    private List<Logo> listBackup;
    private List<Logo> mList;
    private LogoItemListener mLogoItem;
    public LogoAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }
    public List<Logo> getBackup(){
        return listBackup;
    }
    public void filterList(List<Logo> filterlist){
        mList = filterlist;
        notifyDataSetChanged();
    }
    public void setClickListener(LogoItemListener mLogoItem){
        this.mLogoItem = mLogoItem;
    }
    @NonNull
    @Override
    public LogoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent,false);
        return new LogoViewHolder(view);
    }

    public Logo getItem(int pos){
        return mList.get(pos);
    }
    @Override
    public void onBindViewHolder(@NonNull LogoViewHolder holder, int position) {
        Logo logo = mList.get(position);
        if(logo == null)
            return;
        holder.img.setImageResource(logo.getImg());
        holder.tName.setText(logo.getName());
        holder.tDecs.setText(logo.getDesc() + " m2");
        holder.tPrice.setText(logo.getPrice() + " trieu");
        String txt="";
        if (logo.isWifi()) {
            txt += "co wifi ";
        }
        if(logo.isDieuhoa()){
            txt+="co dieu hoa ";
        }
        if(logo.isMaygiat()){
            txt+="co may giat ";
        }
        holder.tService.setText(txt);
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan muon xoa logo "+ logo.getName()+" nay khong");
                builder.setIcon(R.drawable.remove);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listBackup.remove(holder.getAdapterPosition());
                        mList.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void add(Logo l){
        listBackup.add(l);
        mList.add(l);
        notifyDataSetChanged();
    }

    public void update(int pos, Logo logo){
        listBackup.set(pos, logo);
        mList.set(pos, logo);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    public  class LogoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tName, tDecs, tPrice,tService;
        private Button btRemove;
        public LogoViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tName = view.findViewById(R.id.txtName);
            tDecs = view.findViewById(R.id.txtDesc);
            tPrice = view.findViewById(R.id.txtPrice);
            tService = view.findViewById(R.id.txtService);
            btRemove = view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mLogoItem != null){
                mLogoItem.onItemClick(view, getAdapterPosition());
            }
        }
    }
    public interface LogoItemListener{
        void onItemClick(View view, int pos);
    }

}
