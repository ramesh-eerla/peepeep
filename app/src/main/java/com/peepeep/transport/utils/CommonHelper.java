package com.peepeep.transport.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.peepeep.transport.R;
import com.peepeep.transport.servicerequest.responsemodels.LoginDataset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonHelper {
    private ProgressDialog dialog = null;
    public static AlertDialog alertDialog = null;

    /**
     * This is to show error alert dialog for corresponding context
     *
     * @param context is the corresponding context
     * @param title   is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return void
     */
    public static void showErrorAlertDiaolog(final Context context, final String title, final String message) {
        final CommonHelper common_helper_obj = new CommonHelper();
        try {

            ((AppCompatActivity) context).runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    common_helper_obj.setErrorAlertDiaolog(context, title, (message != null) ? message : title);

                }
            });
        } catch (Exception e) {
            common_helper_obj.setErrorAlertDiaolog(context, title, message);
        }
    }

    /**
     * This is to set error alert dialog for corresponding context
     *
     * @param context is the corresponding context
     * @param title   is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return alert  dialog
     */
    public AlertDialog setErrorAlertDiaolog(final Context context, String title, String message) {


        String messageText = String.valueOf(Html.fromHtml(message.replaceAll("(\r\n|\n)", "<br>")));
        alertDialog = getAlertDialog(context, messageText);
        alertDialog.setTitle(title);
        alertDialog.setMessage(messageText);
        alertDialog.setCanceledOnTouchOutside(false);


        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });



        if (!((AppCompatActivity) context).isFinishing())
            alertDialog.show();


        return alertDialog;
    }

    public static String convertStringtoDate(String data) {
        SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-yyyy");
        Date date = null;
        try {
            date = formatter6.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return "" + date;
    }


    public ProgressDialog showDialog(Context context) {
        dialog = new CustomProgressDialog(context).showProgressDialog("", "Loading ..");
        if (dialog != null)
            dialog.setCanceledOnTouchOutside(false);


        return dialog;
    }

    /*
     * @Author :: Ramesh eerla
     * Use : creating the new reference if different text or null dialog
     * */
    public static AlertDialog getAlertDialog(Context context, String message) {

        if (alertDialog == null) {
            return createNewAlertDialogInstance(context);

        }
        return null;
    }

    /*
     * @Author :: Ramesh Eerla
     * Use : Create the Alertdialog reference
     * */
    private static AlertDialog createNewAlertDialogInstance(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCancelable(true);
        return alertDialog;
    }

    public static GradientDrawable getGradientDrawable(int solidcolor, int expected_color, int cornerradius) {
        // Initialize a new GradientDrawable
        GradientDrawable gd = new GradientDrawable();

        // Set the color array to draw gradient
        gd.setColor(solidcolor);
        gd.setCornerRadius(cornerradius);

        // Set the GradientDrawable gradient type linear gradient
        gd.setGradientType(GradientDrawable.RECTANGLE);

        // Set GradientDrawable shape is a rectangle
        gd.setShape(GradientDrawable.RECTANGLE);

        // Set 3 pixels width solid blue color border
        gd.setStroke(1, expected_color);

        // Set GradientDrawable width and in pixels
        // gd.setSize(450, 150); // Width 450 pixels and height 150 pixels
        return gd;

    }

    public static View setErrorTextBackground(View view, Context context, String string) {
        EditText editText;
        int error_text_color = context.getResources().getColor(R.color.red_color);
        // Initialize a new GradientDrawable
        GradientDrawable gd = new GradientDrawable();

        // Set the color array to draw gradient
        gd.setColor(Color.WHITE);

        gd.setCornerRadius(45);
        // Set the GradientDrawable gradient type linear gradient
        gd.setGradientType(GradientDrawable.RECTANGLE);

        // Set GradientDrawable shape is a rectangle
        gd.setShape(GradientDrawable.RECTANGLE);

        // Set 3 pixels width solid blue color border
        gd.setStroke(2, error_text_color);
        try {
            editText = (EditText) view;
            editText.setText("");
            editText.setHint(string);
            editText.setHintTextColor(error_text_color);
            editText.setBackground(gd);
            view = editText;
        } catch (Exception e) {
            TextView textView = (TextView) view;
            textView.setText(string);
            textView.setTextColor(error_text_color);
            view = textView;

        }
        return view;


    }

    /**
     * Gives the Day of Week
     *
     * @param day day of month
     * @return String representation of a week day
     */
    @SuppressWarnings("unused")
    public static String getDay(int day) {
        String sDay = "";
        switch (day) {
            case 1:
                sDay = "Sun";
                break;
            case 2:
                sDay = "Mon";
                break;
            case 3:
                sDay = "Tue";
                break;
            case 4:
                sDay = "Wed";
                break;
            case 5:
                sDay = "Thu";
                break;
            case 6:
                sDay = "Fri";
                break;
            case 7:
                sDay = "Sat";
                break;
        }
        return sDay;
    }

    public static String getTimeAMPM(int hours,int minuts) {

        String time_value=((hours<10)? ("0"+hours) : ""+hours)+":"+((minuts<10)? ("0"+minuts) : ""+minuts)+" am";
        if(hours>12&&minuts>0)
            time_value=(hours-12)+":"+((minuts<10)? ("0"+minuts) : ""+minuts)+" pm";
        else if(hours==12)
            time_value=(hours)+":"+((minuts<10)? ("0"+minuts) : ""+minuts)+" pm";

    return time_value;
    }


    public static String getMonth(int month) {
        String sMonth = "";
        switch (month) {
            case 0:
                sMonth = "Jan";
                break;
            case 1:
                sMonth = "Feb";
                break;
            case 2:
                sMonth = "Mar";
                break;
            case 3:
                sMonth = "Apr";
                break;
            case 4:
                sMonth = "May";
                break;
            case 5:
                sMonth = "Jun";
                break;
            case 6:
                sMonth = "Jul";
                break;
            case 7:
                sMonth = "Aug";
                break;
            case 8:
                sMonth = "Sep";
                break;
            case 9:
                sMonth = "Oct";
                break;
            case 10:
                sMonth = "Nov";
                break;
            case 11:
                sMonth = "Dec";
                break;
        }
        return sMonth;
    }

    public static LoginDataset getLoginDataset(Context mContext){
        LoginDataset mLoginDataset;
        SharedPreferences mPrefs = mContext.getSharedPreferences("Login_repsonse",mContext.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        mLoginDataset = gson.fromJson(json, LoginDataset.class);
        return mLoginDataset;
    }

    public static ArrayList<String> getString(List<LoginDataset.Good> listdata){
        ArrayList<String> data= new ArrayList<>();

           // LoginDataset.Good listdata=(LoginDataset.Good)object;
            for (int i=0;i<listdata.size();i++){
                data.add(listdata.get(i).getGoodsName());
            }


        return data;
    }
    public static ArrayAdapter getdefaultAdapter(Context mContext,ArrayList<String> list_data){
        ArrayAdapter adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, list_data);
        return adapter;
    }
}
