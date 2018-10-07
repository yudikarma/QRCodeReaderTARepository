package com.example.fahrul.qr_codereadertarepository;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class listDownload_fragment extends Fragment {


    public listDownload_fragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private ArrayList<String> arrayList = new ArrayList<>();
    public String nama;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview = inflater.inflate(R.layout.fragment_list_download_fragment, container, false);


        recyclerView = mview.findViewById(R.id.list_file);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final File yourAppDir = new File(Environment.getExternalStorageDirectory() + "/QR_Reader");

        if (!yourAppDir.exists() && !yourAppDir.isDirectory()) {
            // create empty directory
            if (yourAppDir.mkdirs()) {
                Log.i("CreateDir", "App dir created");
            } else {
                Log.w("CreateDir", "Unable to create app dir!");
            }
        } else {
            Log.i("CreateDir", "App dir already exists");


            String path = Environment.getExternalStorageDirectory() + "/QR_Reader";

            File directory = new File(path);
            File[] files = directory.listFiles();
            int fileslength = files.length;
            if (!(fileslength < 0)) {
                for (int i = 0; i < fileslength; i++) {
                    /*Log.d("Files", "FileName:" + files[i].getName());*/
                    nama = files[i].getName();
                    arrayList.add(nama);
                }
                //array
                listfileAdapter adapter = new listfileAdapter(getActivity(), arrayList);
                recyclerView.setAdapter(adapter);
            }


        }
        return mview;

    }
}
