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
import ir.ashkaran.mzq.objects.PeopleObject;
import ir.ashkaran.mzq.ui.CommutingActivity;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.MyViewHolder> {

    List<PeopleObject> list;
    Activity activity;

    public PeopleAdapter(List<PeopleObject> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(activity).inflate(R.layout.row_people , parent , false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PeopleObject people = list.get(position);
        holder.fNamelName.setText(people.getFNamelName());
        holder.ID.setText(people.getID());
        holder.father.setText("فرزند " + people.getFather());
        holder.plaque.setText(people.getPlaque());
        holder.plaque.setText(people.getPlaque());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fNamelName)     AppCompatTextView fNamelName;
        @BindView(R.id.ID)             AppCompatTextView ID ;
        @BindView(R.id.father)         AppCompatTextView father;
        @BindView(R.id.plaque)         AppCompatTextView plaque;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @OnClick(R.id.root)
        public void onClick(View view) {
            PeopleObject object = list.get(getAdapterPosition());
            Intent intent = new Intent(activity , CommutingActivity.class);
            intent.putExtra("PeopleObject" , object);
            activity.startActivity(intent);
        }
    }
}
