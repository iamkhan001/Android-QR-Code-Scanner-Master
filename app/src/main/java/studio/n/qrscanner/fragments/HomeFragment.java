package studio.n.qrscanner.fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import studio.n.qrscanner.AdapterLogs;
import studio.n.qrscanner.LogModel;
import studio.n.qrscanner.R;
import studio.n.qrscanner.helpers.DataProcessor;
import studio.n.qrscanner.helpers.Message;

/**
 * A simple {@link Fragment} subclass.
 * Created by Imran Khan on 31 may 2018.
 */
public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    TextView noLogs;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout scanView = view.findViewById(R.id.start_scan);
        noLogs = view.findViewById(R.id.no_logs_text);
        scanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    android.support.v4.app.FragmentTransaction ft;
                    android.support.v4.app.Fragment fragment;
                    fragment = new ScannerFragment();
                    ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_from_right, 0);
                    ft.replace(R.id.fragment_view, fragment, "scan");
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    Message.show(getContext(), "Camera permission is Mandatory!");
                }
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);


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
        ArrayList<LogModel> sortLogs = new ArrayList<>();


        if (list.size() > 0) {
            if (list.size() > 5) {
                for (int i = list.size() - 1; i >= list.size() - 5; i--) {
                    sortLogs.add(list.get(i));
                }

            } else {
                for (int i = list.size() - 1; i >= 0; i--) {
                    sortLogs.add(list.get(i));
                }

            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new AdapterLogs(sortLogs));
        } else {
            recyclerView.setVisibility(View.GONE);
            noLogs.setVisibility(View.VISIBLE);
        }


    }

    boolean checkPermission() {
        final Context context = getContext();
        return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }
}
