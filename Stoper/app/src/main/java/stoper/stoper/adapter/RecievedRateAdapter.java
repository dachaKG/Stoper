package stoper.stoper.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import stoper.stoper.DTO.RateDTO;
import stoper.stoper.R;

public class RecievedRateAdapter extends  RecyclerView.Adapter<RecievedRateAdapter.ViewHolder> {

    private List<RateDTO> rates;

    public RecievedRateAdapter(List<RateDTO> rates){
        this.rates = rates;
    }

    @Override
    public int getItemCount(){
        return rates.size();
    }

    @Override
    public RecievedRateAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recieved_ratings_card_view, parent, false);
        return new RecievedRateAdapter.ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull RecievedRateAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        RateDTO rateDTO = rates.get(position);
        TextView textView = (TextView) cardView.findViewById(R.id.recieved_mark_from_name);
        textView.setText(rateDTO.getEvaluatorFirstName());

        TextView textMark = (TextView)cardView.findViewById(R.id.recieved_mark_from_mark_text);
        textMark.setText(rateDTO.getMark());


        TextView comment = (TextView)cardView.findViewById(R.id.recieved_mark_from_mark_comment);
        comment.setText(rateDTO.getComment());

        ImageView image = (ImageView)cardView.findViewById(R.id.recieved_mark_from_reciever_image);
        if (rateDTO != null && rateDTO.getRecieverProfileImage() != null && rateDTO.getRecieverProfileImage().length > 0 ) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(rateDTO.getRecieverProfileImage(), 0, rateDTO.getRecieverProfileImage().length);
            image.setImageBitmap(bitmap);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(rateDTO.getDate());
        String month = new SimpleDateFormat("MMM").format(cal.getTime());
        String year = Integer.toString(cal.get(Calendar.YEAR));
        TextView date = (TextView) cardView.findViewById(R.id.recieved_mark_from_mark_date);
        date.setText(String.format("%s %s", month,year));

        int drawableCircle = getColorImage(rateDTO.getMark());
        ImageView imageCircle = (ImageView)cardView.findViewById(R.id.recieved_mark_from_mark_color);
        imageCircle.setImageResource(drawableCircle);
    }

    private int getColorImage(String mark){
        int result = 0;
        if(mark.equals("Izvanredno") || mark.equals("Perfect")){
            result = R.drawable.green_circle;
        }else if(mark.equals("Odlično") || mark.equals("Excellent")){
            result = R.drawable.green_circle;

        }else if (mark.equals("Dobro") || mark.equals("Good")){
            result = R.drawable.yellow_circle;

        }else if (mark.equals("Loše") || mark.equals("Bad")){
            result = R.drawable.orange_circle;

        }else if (mark.equals("Veoma razočaravajuće") || mark.equals("Very disappointing")){
            result = R.drawable.red_circle;
        }

        return  result;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
