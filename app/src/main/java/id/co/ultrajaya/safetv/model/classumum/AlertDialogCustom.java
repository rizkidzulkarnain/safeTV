package id.co.ultrajaya.safetv.model.classumum;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class AlertDialogCustom {
    static AlertDialog _alertDialog;

    public AlertDialogCustom(final Context icontext) { //tipe 1 save
        _alertDialog = new AlertDialog.Builder(icontext).create();
        _alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }
        );
    }

    public void showAlertDialog(String imsg, int itipe) {
        SpannableStringBuilder aStringBuilder = changeColorAlert(itipe);
        _alertDialog.setTitle(aStringBuilder);
        _alertDialog.setMessage(imsg);
        _alertDialog.show();
    }

    public void hideAlertDialog() {
        _alertDialog.hide();
    }

    public static SpannableStringBuilder changeColorAlert(int icolor) {
        int acolor;
        String atitle;
        //1 red, 2 green, 3 blue
        switch (icolor) {
            case 1:
                atitle = "Error !!!";
                acolor = Color.RED;
                break;
            case 2:
                atitle = "Success !!!";
                acolor = Color.BLUE;
                break;
            default:
                atitle = "Info !!!";
                acolor = Color.BLACK;
                break;
        }

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(acolor);
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(atitle);
        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                atitle.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return ssBuilder;
    }
}
