package com.swd392.reservationrestautantapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.swd392.reservationrestautantapp.BookingDetailUser;
import com.swd392.reservationrestautantapp.History;
import com.swd392.reservationrestautantapp.HomePage;
import com.swd392.reservationrestautantapp.LoginActivity;
import com.swd392.reservationrestautantapp.R;
import com.swd392.reservationrestautantapp.model.Reservation;
import com.swd392.reservationrestautantapp.model.ReservationHistory;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<Reservation> list;
    private List<ReservationHistory> list1;

    private Context context;

    public HistoryAdapter() {
    }

    public HistoryAdapter(List<Reservation> list, Context context, List<ReservationHistory> list1) {
        this.list = list;
        this.context = context;
        this.list1 = list1;
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
        ReservationHistory r = list1.get(position);
        if (r == null) return;

        //2023-07-18
        String datestr1 = r.getDate().toString();
        String day = datestr1.substring(8, 10);	//22
        String month = datestr1.substring(5, 7);	//6
        String year = datestr1.substring(0, 4);		//2001
        String datestr = day + " th" + month + ", " + year;

        holder.timeBook.setText(datestr + " - " + r.getStartTime().toString());
        holder.numberPeople.setText(r.getNumber_guest() + "");
        holder.priceDeposit.setText("Deposit: " + r.getPrice() + "");

        holder.itemEleRcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookingDetailUser.class);
                intent.putExtra("ID_BOOKING", r.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list1 != null){
            return  list1.size();
        }
        return 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView timeBook;

        private TextView numberPeople;

        private TextView priceDeposit;
        private CardView itemEleRcv;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            timeBook = itemView.findViewById(R.id.timeBook);
            numberPeople = itemView.findViewById(R.id.numberPeople);
            priceDeposit = itemView.findViewById(R.id.priceDeposit);
            itemEleRcv = itemView.findViewById(R.id.itemEleRcv);
        }
    }

}
