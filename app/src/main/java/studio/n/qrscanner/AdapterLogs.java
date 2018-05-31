package studio.n.qrscanner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Imran Khan on 31 may 2018.
 */

public class AdapterLogs extends RecyclerView.Adapter<AdapterLogs.MyViewHolder> {


    private ArrayList<LogModel> listLogs;

    public AdapterLogs(ArrayList<LogModel> listLogs) {
        this.listLogs = listLogs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String dateTime = listLogs.get(position).getDate() + " " + listLogs.get(position).getTime();
        holder.tvTimeStamp.setText(dateTime);
        holder.tvText.setText(listLogs.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return listLogs.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText, tvTimeStamp;

        MyViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.description);
            tvTimeStamp = itemView.findViewById(R.id.timestamp);
        }
    }

}
