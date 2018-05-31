package studio.n.qrscanner.helpers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Imran Khan on 31 may 2018.
 */

public class Message {

    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
