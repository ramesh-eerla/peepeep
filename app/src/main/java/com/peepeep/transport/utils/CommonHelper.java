package com.peepeep.transport.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.Html;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

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

            ((Activity) context).runOnUiThread(new Runnable() {

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
        alertDialog.setCancelable(true);
        alertDialog.setTitle(title);
        alertDialog.setMessage(messageText);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                   dialog.dismiss();
                    }
        });

            alertDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

        if (!((Activity) context).isFinishing())
            alertDialog.show();


        return alertDialog;
    }
    public static String convertStringtoDate(String data){
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-yyyy");
        Date date= null;
        try {
            date = formatter6.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return ""+date;
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
        AlertDialog alertDialog = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
        alertDialog.setCancelable(true);
        return alertDialog;
    }

    public static GradientDrawable getGradientDrawable(int solidcolor,int expected_color,int cornerradius){
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
        gd.setSize(450, 150); // Width 450 pixels and height 150 pixels
return gd;

    }
}
