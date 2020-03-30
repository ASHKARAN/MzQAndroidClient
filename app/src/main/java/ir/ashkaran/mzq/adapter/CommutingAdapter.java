package ir.ashkaran.mzq.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.ashkaran.mzq.R;
import ir.ashkaran.mzq.objects.CommutingObject;
import ir.ashkaran.mzq.objects.PeopleObject;
import ir.ashkaran.mzq.ui.CommutingActivity;

public class CommutingAdapter extends RecyclerView.Adapter<CommutingAdapter.MyViewHolder> {

    List<CommutingObject> list;
    Activity activity;

    public CommutingAdapter(List<CommutingObject> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(activity).inflate(R.layout.row_commuting , parent , false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CommutingObject people = list.get(position);
        holder.commutingType.setText(people.getType().equals("in")?"ورود":"خروج");
        holder.commutingType.setTextColor(
                activity.getResources().getColor(
                        people.getType().equals("in")?R.color.greenLevel1:R.color.redLevel1
                )
                );
        holder.commutingTime.setText(people.getDateTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.commutingType)     AppCompatTextView commutingType;
        @BindView(R.id.commutingTime)     AppCompatTextView commutingTime ;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
