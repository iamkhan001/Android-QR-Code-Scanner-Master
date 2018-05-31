package studio.n.qrscanner.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import studio.n.qrscanner.AdapterLogs;
import studio.n.qrscanner.LogModel;
import studio.n.qrscanner.R;
import studio.n.qrscanner.helpers.DataProcessor;

/**
 *Created by Imran Khan on 31 may 2018.
 */

public class LogsFragment extends Fragment {


    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ImageView imgNoLogs;

    public LogsFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logs, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        imgNoLogs = view.findViewById(R.id.icon_no_logs);
        fab = view.findViewById(R.id.fab_clear_logs);
        fab.setVisibility(View.GONE);
        imgNoLogs.setVisibility(View.GONE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataProcessor processor = new DataProcessor(getActivity());
                processor.clearLogs();
                Snackbar.make(view, "All logs cleared successfully!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                recyclerView.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                imgNoLogs.setVisibility(View.VISIBLE);
            }
        });


        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                showRecentLogs();
            }
        });

        return view;
    }

    private void showRecentLogs() {

        DataProcessor processor = new DataProcessor(getActivity());
        ArrayList<LogModel> list = processor.getLogs();
        if (list.size() > 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new AdapterLogs(list));
            fab.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
            imgNoLogs.setVisibility(View.VISIBLE);
        }

    }
}
