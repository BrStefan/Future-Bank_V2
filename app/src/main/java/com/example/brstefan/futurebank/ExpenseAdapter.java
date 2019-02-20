package com.example.brstefan.futurebank;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ExpenseAdapter extends FirestoreRecyclerAdapter<Expense,ExpenseAdapter.ExpenseHolder> {

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
        holder.textViewSuma.setText(String.valueOf(model.getSuma()));
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
        //TextView textViewData;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            textViewSuma=itemView.findViewById(R.id.rw_suma);
            //textViewData=itemView.findViewById(R.id.rw_data);
        }
    }
}
