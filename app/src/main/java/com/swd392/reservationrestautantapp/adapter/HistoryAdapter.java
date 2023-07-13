package com.swd392.reservationrestautantapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swd392.reservationrestautantapp.R;
import com.swd392.reservationrestautantapp.model.Reservation;

import org.w3c.dom.Text;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<Reservation> list;

    private Context context;

    public HistoryAdapter() {
    }

    public HistoryAdapter(List<Reservation> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // create and start View Holder but not fill data in
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list, parent, false);
        return new HistoryViewHolder(view);
    }

    // find and fill data to view
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Reservation r = list.get(position);
        if (r == null) return;

        holder.timeBook.setText(r.getStartTime().toString());
        holder.numberPeople.setText(r.getNumber_guest());
        holder.priceDeposit.setText("Deposit: " + r.getPrice());
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView timeBook;

        private TextView numberPeople;

        private TextView priceDeposit;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            timeBook = itemView.findViewById(R.id.timeBook);
            numberPeople = itemView.findViewById(R.id.numberPeople);
            priceDeposit = itemView.findViewById(R.id.priceDeposit);
        }
    }

}
