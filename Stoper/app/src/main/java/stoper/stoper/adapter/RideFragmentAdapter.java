package stoper.stoper.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import stoper.stoper.R;
import stoper.stoper.model.Ride;

public class RideFragmentAdapter extends RecyclerView.Adapter<RideFragmentAdapter.ViewHolder> {

    public List<Ride> rides;
    private String startDestination[];
    private String endDestination[];
    private Listener listener;

    public RideFragmentAdapter(List<Ride> rides){
        this.rides = rides;
    }

    public interface  Listener{
        void onClick(int position);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView listView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_rides, parent, false);
        return new ViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CardView listView = holder.listView;
        TextView startDestinationHolder = listView.findViewById(R.id.start_destination_fragment);
        TextView endDestinationHolder = listView.findViewById(R.id.end_destination_fragment);
        startDestinationHolder.setText(rides.get(position).getStartDestination());
        endDestinationHolder.setText(rides.get(position).getEndDestination());
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rides.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView listView;
        public ViewHolder(CardView v){
            super(v);
            listView = v;
        }
    }


}
