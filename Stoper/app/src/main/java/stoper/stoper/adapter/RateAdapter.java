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

import org.w3c.dom.Text;

import stoper.stoper.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import stoper.stoper.DTO.RateDTO;

public class RateAdapter extends  RecyclerView.Adapter<RateAdapter.ViewHolder>{

    private List<RateDTO> rates;

    public RateAdapter(List<RateDTO> rates){
        this.rates = rates;
    }

    @Override
    public int getItemCount(){
        return rates.size();
    }

    @Override
    public RateAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.added_ratings_card_view, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        RateDTO rateDTO = rates.get(position);
        TextView textView = (TextView) cardView.findViewById(R.id.added_mark_to_name);
        textView.setText(rateDTO.getRecieverFirstName());

        TextView textMark = (TextView)cardView.findViewById(R.id.added_mark_to_mark_text);
        textMark.setText(rateDTO.getMark());


        TextView comment = (TextView)cardView.findViewById(R.id.added_mark_to_mark_comment);
        comment.setText(rateDTO.getComment());

        ImageView image = (ImageView)cardView.findViewById(R.id.added_mark_to_evaluator_image);
        if (rateDTO != null && rateDTO.getEvaluatorProfileImage() != null && rateDTO.getEvaluatorProfileImage().length > 0 ) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(rateDTO.getEvaluatorProfileImage(), 0, rateDTO.getEvaluatorProfileImage().length);
            image.setImageBitmap(bitmap);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(rateDTO.getDate());
        String month = new SimpleDateFormat("MMM").format(cal.getTime());
        String year = Integer.toString(cal.get(Calendar.YEAR));
        TextView date = (TextView) cardView.findViewById(R.id.added_mark_to_mark_date);
        date.setText(String.format("%s %s", month,year));

        int drawableCircle = getColorImage(rateDTO.getMark());
        ImageView imageCircle = (ImageView)cardView.findViewById(R.id.added_mark_to_mark_color);
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
