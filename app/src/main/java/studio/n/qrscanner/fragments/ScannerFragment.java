package studio.n.qrscanner.fragments;

import android.content.SharedPreferences;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import studio.n.qrscanner.R;


/**
 * A simple {@link Fragment} subclass.
 * Created by Imran Khan on 31 may 2018.
 */
public class ScannerFragment extends Fragment implements QRCodeReaderView.OnQRCodeReadListener {


    ImageView closeScanner;
    SharedPreferences.Editor editor;
    View view;

    public ScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scanner, container, false);

        closeScanner = view.findViewById(R.id.closeScanner);
        QRCodeReaderView qrCodeReaderView = view.findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the auto-focus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();

        closeScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentTransaction ft;
                android.support.v4.app.Fragment fragment;
                fragment = new HomeFragment();
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_from_right, R.anim.stay);
                ft.replace(R.id.fragment_view, fragment, "scan");
                ft.addToBackStack(null);


                ft.commit();

            }
        });


        return view;
    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {

        android.support.v4.app.FragmentTransaction ft;
        android.support.v4.app.Fragment fragment;
        fragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("RESULT", text);
        fragment.setArguments(bundle);
        ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, 0);
        ft.replace(R.id.fragment_view, fragment, "RESULT");
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
