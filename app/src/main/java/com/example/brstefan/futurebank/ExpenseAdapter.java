package com.example.brstefan.futurebank;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class ExpenseAdapter extends FirestoreRecyclerAdapter<Expense,ExpenseAdapter.ExpenseHolder> {

    private OnItemClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ExpenseAdapter(@NonNull FirestoreRecyclerOptions<Expense> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExpenseHolder holder, int position, @NonNull Expense model) {
        if(model.getTip()==1)
        {
            String display = "-"+String.valueOf(model.getSuma());
            holder.textViewSuma.setText(display);
            holder.textViewSuma.setTextColor(Color.parseColor("#ff0000"));
        }
        else
        {
            String display = "+"+String.valueOf(model.getSuma());
            holder.textViewSuma.setText(display);
            holder.textViewSuma.setTextColor(Color.parseColor("#008800"));
        }
        holder.textViewCategorie.setText((String.valueOf(model.getCategorie())));
        //holder.textViewData.setText(model.getData());
    }

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_item,viewGroup,false);
        return new ExpenseHolder(v);
    }

    class ExpenseHolder extends RecyclerView.ViewHolder{
        TextView textViewSuma;
        TextView textViewCategorie;
        //TextView textViewData;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            textViewSuma=itemView.findViewById(R.id.rw_suma);
            textViewCategorie=itemView.findViewById(R.id.rw_categorie);
            //textViewData=itemView.findViewById(R.id.rw_data);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }
    }

    public interface  OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
