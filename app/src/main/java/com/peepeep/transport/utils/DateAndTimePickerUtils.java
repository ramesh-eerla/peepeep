package com.peepeep.transport.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateAndTimePickerUtils {
    Context mContext;
    private int mYear, mMonth, mDay, mHour, mMinute;
    public DateAndTimePickerUtils(Context mcontext){
        this.mContext=mcontext;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
    }

    public DatePickerDialog getDatePiker(){



        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Log.d("Date_picker",dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        /*txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);*/

                    }
                }, mYear, mMonth, mDay);
       /* datePickerDialog.show();*/
        return datePickerDialog;
    }
    public TimePickerDialog getTimePicker()
    {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        Log.d("Date_picker",hourOfDay + ":" + minute);


                    }
                }, mHour, mMinute, false);
        /*timePickerDialog.show();*/
        return timePickerDialog;
    }

}
