package com.example.brstefan.futurebank;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class EventFragment extends Fragment {

    private TextView mTextDate;
    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDisplayDate = (Button) view.findViewById(R.id.date_button);
        mTextDate = (TextView) view.findViewById(R.id.text_view_date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String display;
                display = dayOfMonth + " " + displayMonth(month) + " " + year;
                mTextDate.setText(display);
            }
        };
    }

    public String displayMonth(int i)
    {
        String aux=null;
        switch (i)
        {
            case 0:
            {
                aux="Ian";
                break;
            }
            case 1:
            {
                aux="Feb";
                break;
            }
            case 2:
            {
                aux="Mar";
                break;
            }
            case 3:
            {
                aux="Apr";
                break;
            }
            case 4:
            {
                aux="Mai";
                break;
            }
            case 5:
            {
                aux="Iun";
                break;
            }
            case 6:
            {
                aux="Iul";
                break;
            }
            case 7:
            {
                aux="Aug";
                break;
            }
            case 8:
            {
                aux="Sep";
                break;
            }
            case 9:
            {
                aux="Oct";
                break;
            }
            case 10:
            {
                aux="Noi";
                break;
            }
            case 11:
            {
                aux="Dec";
                break;
            }
        }
        return aux;
    }
}
