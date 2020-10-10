package br.com.weatherapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Class to create a simple dialog with interaction of user.
 */
public abstract class Dialog {

    /**
     * Create an {@link AlertDialog.Builder} whit an title and text.
     *
     * @param context Context application.
     * @param title Title of alert.
     * @param text Message to user.
     * @return {@link AlertDialog.Builder} editable before to be show to user.
     */
    public static AlertDialog.Builder createAlertDialog(Context context, String title, String text){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        return alertDialog;
    }

    /**
     * Show an alert to user with only "Ok" button to close alert.
     *
     * @param context Context application.
     * @param title Title of alert.
     * @param text Message to user.
     */
    public static void showAlert(Context context, String title, String text){
        AlertDialog.Builder alertDialog = createAlertDialog(context, title, text);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}