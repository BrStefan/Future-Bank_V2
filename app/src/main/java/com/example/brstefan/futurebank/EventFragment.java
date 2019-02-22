package com.example.brstefan.futurebank;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventFragment extends Fragment {

    private TextView mTextDate;
    private Button mDisplayDate;
    private TextView mTextTime;
    private Button mDisplayTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton;
    private boolean time_check=false;
    private boolean date_check=false;
    private boolean name_check=false;
    private boolean freq_check=false;
    private EditText mEditText;
    private int Year;
    private int Month;
    private int Day;
    private int Hour;
    private int Minute;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event,container,false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDisplayDate = (Button) view.findViewById(R.id.date_button);
        mTextDate = (TextView) view.findViewById(R.id.text_view_date);
        mDisplayTime = (Button)view.findViewById(R.id.time_button);
        mTextTime = (TextView)view.findViewById(R.id.text_view_time);
        mRadioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        mEditText = (EditText)view.findViewById(R.id.nume_eveniment);
        mAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

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
                date_check=true;
                Day=dayOfMonth;
                Month=month;
                Year=year;
            }
        };

        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,mTimeSetListener,hour,minute, android.text.format.DateFormat.is24HourFormat(getContext()));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String display;
                if(minute <10)display = hourOfDay + ":0" + minute;
                else display = hourOfDay + ":0" + minute;
                mTextTime.setText(display);
                time_check=true;
                Hour=hourOfDay;
                Minute=minute;
            }
        };


        Button buttonSubmit = view.findViewById(R.id.buton_submit_event);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mRadioGroup.getCheckedRadioButtonId()!=-1)freq_check=true;
                if(!mEditText.getText().toString().trim().equals(""))name_check=true;
                if(time_check && date_check && freq_check && name_check)
                {
                    String data = Year+"-"+Month+"-"+Day+"T"+Hour+":"+Minute+":00Z";
                    //Log.e("TAG",data);
                    Map<String,Object> docData = new HashMap<>();
                    docData.put("Data",getDateFromString(data));
                    db.collection("evenimente").document("1").set(docData);
                }
                else Toast.makeText(getContext(), "Completati toate campurile!", Toast.LENGTH_LONG).show();
            }
        });
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

    public Date getDateFromString(String datetoSaved) {
        try {
            String value;
            Date date = format.parse(datetoSaved);
            return date;
        }catch (ParseException e){
            return null;
        }
    }
}
