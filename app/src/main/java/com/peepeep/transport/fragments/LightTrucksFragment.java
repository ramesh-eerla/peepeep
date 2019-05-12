package com.peepeep.transport.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.peepeep.transport.R;
import com.peepeep.transport.acitivities.BookingActivity;
import com.peepeep.transport.acitivities.InvoiceActivity;
import com.peepeep.transport.uicomponents.PpEditText;
import com.peepeep.transport.utils.CommonHelper;
import com.peepeep.transport.utils.DateAndTimePickerUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LightTrucksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LightTrucksFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.book_vehicle_button)
    AppCompatButton mBookVehicleButton;
    @BindView(R.id.insurence_checkbox)
    AppCompatCheckBox mInsurencecheckbox;
    @BindView(R.id.labour_count_inc_button)
    AppCompatButton mIncresse_button;
    @BindView(R.id.labour_count_button)
    AppCompatButton mCountButton;
    @BindView(R.id.labour_count_dec_button)
    AppCompatButton mDecresseButton;
    @BindView(R.id.labour_image)
    AppCompatImageView mLabourImage;
    @BindView(R.id.any_truck_lable)
    AppCompatTextView mAnyTruckLable;
    @BindView(R.id.any_truck_image)
    AppCompatImageView mAnyTruckImage;
    @BindView(R.id.any_truck_layout)
    LinearLayout mAnytruckLayout;

    @BindView(R.id.close_truck_lable)
    AppCompatTextView mCloseTruckLable;
    @BindView(R.id.close_truck_image)
    AppCompatImageView mCloseTruckImage;
    @BindView(R.id.close_truck_layout)
    LinearLayout mClosetruckLayout;

    @BindView(R.id.open_truck_lable)
    AppCompatTextView mOpenTruckLable;
    @BindView(R.id.open_truck_image)
    AppCompatImageView mOpenTruckImage;
    @BindView(R.id.open_truck_layout)
    LinearLayout mOpentruckLayout;

    @BindView(R.id.select_vehicle_type_lable)
    AppCompatTextView mSelectvehicleLable;
    @BindView(R.id.material_type_edit)
    AutoCompleteTextView mMaterialedit;
    @BindView(R.id.material_type_lable)
    AppCompatTextView mMateriallabel;

    @BindView(R.id.material_weight_edit)
    AutoCompleteTextView mMaterialweightedit;
    @BindView(R.id.material_weight_lable)
    AppCompatTextView mMaterialweightlabel;

    @BindView(R.id.drop_location_lable)
    AppCompatTextView mDropLocationLable;
    @BindView(R.id.drop_location_layout)
    LinearLayout mDroplocationLayout;
    @BindView(R.id.drop_time_lable)
    AppCompatTextView mDroptimeLable;
    @BindView(R.id.drop_month_lable)
    AppCompatTextView mDropmonthLable;
    @BindView(R.id.drop_week_lable)
    AppCompatTextView mDropweekLable;
    @BindView(R.id.drop_day_lable)
    AppCompatTextView mDropdayLable;
    @BindView(R.id.drop_layout)
    LinearLayout mDrop_layout;
    @BindView(R.id.drop_lable)
    AppCompatTextView mDropLable;

    @BindView(R.id.start_location_lable)
    AppCompatTextView mPickLocationLable;
    @BindView(R.id.pick_location_layout)
    LinearLayout mPicklocationLayout;
    @BindView(R.id.start_time_lable)
    AppCompatTextView mPicktimeLable;
    @BindView(R.id.start_month_lable)
    AppCompatTextView mPickmonthLable;
    @BindView(R.id.start_week_lable)
    AppCompatTextView mPickweekLable;
    @BindView(R.id.start_day_lable)
    AppCompatTextView mPickdayLable;
    @BindView(R.id.pick_layout)
    LinearLayout mPick_layout;
    @BindView(R.id.pick_lable)
    AppCompatTextView mPickLable;

    @BindView(R.id.labour_lable)
    AppCompatTextView mLabourLable;
    @BindView(R.id.labour_count_layout)
    LinearLayout mlabourcountlayout;


   /* @BindView(R.id.sliding_panel)
    SlidingPaneLayout mSlidingpanel;*/

    private DateAndTimePickerUtils mDateAndTimePickerUtils;
    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;
    private Context mContext;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private boolean pickflag=false,dropflag=false;

    public LightTrucksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LightTrucksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LightTrucksFragment newInstance(String param1, String param2) {
        LightTrucksFragment fragment = new LightTrucksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_landingpage, container, false);
        ButterKnife.bind(this, view);
        mContext=getActivity();
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mDateAndTimePickerUtils=new DateAndTimePickerUtils(getActivity());
        mDatePickerDialog=new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if(pickflag){
                        mPickdayLable.setText((dayOfMonth<10)? ("0"+dayOfMonth) : ""+dayOfMonth);
                        mPickweekLable.setText(CommonHelper.getDay(Calendar.DAY_OF_WEEK));
                        mPickmonthLable.setText(CommonHelper.getMonth((monthOfYear )));
                        }else{
                            mDropdayLable.setText((dayOfMonth<10)? ("0"+dayOfMonth) : ""+dayOfMonth);
                            mDropweekLable.setText(CommonHelper.getDay(Calendar.DAY_OF_WEEK));
                            mDropmonthLable.setText(CommonHelper.getMonth((monthOfYear)));
                        }
                       mTimePickerDialog.show();

                    }
                }, mYear, mMonth, mDay);
        mTimePickerDialog=new TimePickerDialog(mContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        if (pickflag)
                       mPicktimeLable.setText(CommonHelper.getTimeAMPM(hourOfDay,minute));
                        else
                            mDroptimeLable.setText(CommonHelper.getTimeAMPM(hourOfDay,minute));


                    }
                }, mHour, mMinute, false);
        mBookVehicleButton.setOnClickListener(this);
        mDecresseButton.setOnClickListener(this);
        mIncresse_button.setOnClickListener(this);
        mPicklocationLayout.setOnClickListener(this);
        mDrop_layout.setOnClickListener(this);
        mDroplocationLayout.setOnClickListener(this);
        mPick_layout.setOnClickListener(this);
        mOpentruckLayout.setOnClickListener(this);
        mClosetruckLayout.setOnClickListener(this);
        mAnytruckLayout.setOnClickListener(this);
        mCountButton.setText("0");

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_truck_layout:
                BottomSheetDialogFragment bottomSheet = new BottomsheetDialogFragment();

                bottomSheet.show(getActivity().getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.close_truck_layout:
                BottomSheetDialogFragment bottomSheet1 = new BottomsheetDialogFragment();
                bottomSheet1.show(getActivity().getSupportFragmentManager(), "exampleBottomSheet");
                break;
            case R.id.any_truck_layout:
                startActivity(new Intent(getActivity(), InvoiceActivity.class));

                break;
            case R.id.book_vehicle_button://book the vehicle
                break;
            case R.id.labour_count_dec_button://to decrease the labour count value
                int dec_count=getcomponentvalue(mCountButton);
                if(dec_count>0){
                    dec_count=dec_count-1;
                mCountButton.setText(""+dec_count);
                }
                break;
            case R.id.labour_count_inc_button:// to increase the labour count value
                int inc_count=getcomponentvalue(mCountButton);
                inc_count=inc_count+1;
                mCountButton.setText(""+inc_count);

                break;
            case R.id.pick_layout://to open the date and time picker
                pickflag=true;
                dropflag=false;
                mDatePickerDialog.show();

                break;
            case R.id.pick_location_layout://to navigate to map for the pickup location
              startActivity(new Intent(getActivity(),BookingActivity.class));
                break;
            case R.id.drop_layout://to open the date and time picker
                pickflag=false;
                dropflag=true;
                mDatePickerDialog.show();
                break;
            case R.id.drop_location_layout://to navigate to map for the drop location
                startActivity(new Intent(getActivity(),BookingActivity.class));
                break;

        }
    }

    public int getcomponentvalue(View view){
        int value=0;
        if(view instanceof AppCompatButton)
           return value=Integer.parseInt(((AppCompatButton)view).getText().toString());
        return value;
    }
}
