package com.example.fahrul.qr_codereadertarepository;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class listfileAdapter extends RecyclerView.Adapter<listfileAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> listfile;
    public String nama;


    public listfileAdapter(Context context, ArrayList<String> listfile) {

        this.context = context;
        this.listfile = listfile;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(context).inflate(R.layout.single_listdownload, parent, false);
        return new ViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.namafile.setText(listfile.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent myIntent = new Intent(Intent.ACTION_VIEW);
                myIntent.setData(Uri.fromFile(new File(listfile.get(position))));
                Intent j = Intent.createChooser(myIntent, "Choose an application to open with:");
                context.startActivity(j);*/
                String path = Environment.getExternalStorageDirectory()+"/QR_Reader";

                File directory = new File(path);
                File[] files = directory.listFiles();
                int fileslength = files.length;
                for (int i = 0; i < fileslength; i++)
                {
                    /*Log.d("Files", "FileName:" + files[i].getName());*/
                    nama = files[i].getName();

                }
                Intent intent = new Intent(Intent.ACTION_SEND);

                Intent myIntent = new Intent(Intent.ACTION_VIEW);
                myIntent.setData(Uri.fromFile(new File(path + "/" + listfile.get(position))));
                Intent j = Intent.createChooser(myIntent, "Choose an application to open with:");
                context.startActivity(j);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listfile.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView namafile;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);

            namafile = itemView.findViewById(R.id.title_single);
            cardView = itemView.findViewById(R.id.cfile);
        }
    }


}