package studio.n.qrscanner.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import studio.n.qrscanner.R;
import studio.n.qrscanner.helpers.DataProcessor;

/**
 *Created by Imran Khan on 31 may 2018.
 */
public class ResultFragment extends Fragment {


    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView result = view.findViewById(R.id.scan_result);
        result.setText(getArguments().getString("RESULT"));

        final String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        final String time = new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime());
        final String description = getArguments().getString("RESULT");
        DataProcessor processor = new DataProcessor(getActivity());
        processor.insertLog(time, date, description);

        return view;
    }

}
