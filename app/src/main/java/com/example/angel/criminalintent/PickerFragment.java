package com.example.angel.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Angel on 20.05.15.
 */
public class PickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.example.angel.criminalIntent.date";
    private Date mDate;
    private int mMonthOfYear;
    private int mYear;
    private int mDayOfMonth;
    private int mHourOfDay;
    private int mMinute;

    public static PickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        PickerFragment fragment = new PickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);

        //Create a Calendar to get the year, month and day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        mYear = calendar.get(Calendar.YEAR);
        mMonthOfYear = calendar.get(Calendar.MONTH);
        mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_picker, null);

        DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        TimePicker timePicker = (TimePicker)v.findViewById(R.id.dialog_date_timePicker);

        timePicker.setCurrentHour(mHourOfDay);
        timePicker.setCurrentMinute(mMinute);
        datePicker.init(mYear, mMonthOfYear, mDayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonthOfYear = monthOfYear;
                mDayOfMonth = dayOfMonth;
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHourOfDay = hourOfDay;
                mMinute = minute;
            }
        });




        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode){
        if (getTargetFragment() == null)
            return;

        mDate = new GregorianCalendar(mYear, mMonthOfYear, mDayOfMonth, mHourOfDay, mMinute).getTime();

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
