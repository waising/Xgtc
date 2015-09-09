package com.xgtongcheng.xgtc.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.view.View;

import com.github.mrengineer13.snackbar.SnackBar;
import com.xgtongcheng.xgtc.R;

/**
 * Created by WWX on 2015/9/9.
 */
public class SBar {
    static SnackBar snackBar;

    public static void showShort(String text,Activity activity){
        show(text,activity,SnackBar.SHORT_SNACK);
    }

    public static void showLong(String text,Activity activity){
        show(text,activity,SnackBar.LONG_SNACK);
    }

    private static void show(String text,Activity activity,short time){
        new SnackBar.Builder(activity)
                .withMessage(text)
                .withDuration(time)
                .show();
    }

    public static void showLong(String text,Context context,View view){
        show(text, context, view, SnackBar.LONG_SNACK);
    }

    public static void showShort(String text,Context context,View view){
        show(text,context,view,SnackBar.SHORT_SNACK);
    }

    private static void show(String text,Context context,View view,short time){
        new SnackBar.Builder(context,view)
                .withMessage(text)
                .withDuration(time)
                .show();
    }
    public static void showActionLong(String text,Context context,View view){
        showAction(text, context, view, SnackBar.LONG_SNACK);
    }

    public static void showActionShort(String text,Context context,View view){
        showAction(text,context,view,SnackBar.SHORT_SNACK);
    }

    private static void showAction(String text,Context context,View view,short time){
        snackBar = new SnackBar.Builder(context,view)
                .withMessage(text)
                .withOnClickListener(new SnackBar.OnMessageClickListener() {
                    @Override
                    public void onMessageClick(Parcelable parcelable) {
                        snackBar.hide();
                    }
                })
                .withDuration(time)
                .withActionMessage("取消")
                .withTextColorId(R.color.tomato)
                .show();
    }

    public static void showActionLong(String text,Activity activity){
        showAction(text,activity,SnackBar.LONG_SNACK);
    }
    public static void showActionShort(String text,Activity activity){
        showAction(text,activity,SnackBar.SHORT_SNACK);
    }

    private static void showAction(String text,Activity activity,short time){
        snackBar = new SnackBar.Builder(activity)
                .withOnClickListener(new SnackBar.OnMessageClickListener() {
                    @Override
                    public void onMessageClick(Parcelable parcelable) {
                        snackBar.hide();
                    }
                })
                .withMessage(text)
                .withDuration(time)
                .withActionMessage("取消")
                .withTextColorId(R.color.tomato)
                .show();
    }



    public static void fraShow(String text,Activity activity,short time){
        new SnackBar.Builder(activity)
                .withMessage(text)
                .withDuration(time)
                .show();
    }

    public static void frahow(String text,Context context,View view,short time){
        new SnackBar.Builder(context,view)
                .withMessage(text)
                .withDuration(time)
                .show();
    }

    public static void frahowAction(String text,Context context,View view,short time){
        snackBar = new SnackBar.Builder(context,view)
                .withMessage(text)
                .withOnClickListener(new SnackBar.OnMessageClickListener() {
                    @Override
                    public void onMessageClick(Parcelable parcelable) {
                        snackBar.hide();
                    }
                })
                .withDuration(time)
                .withActionMessage("取消")
                .withTextColorId(R.color.tomato)
                .show();
    }

    public static void fraShowAction(String text,Activity activity,short time){
        snackBar = new SnackBar.Builder(activity)
                .withOnClickListener(new SnackBar.OnMessageClickListener() {
                    @Override
                    public void onMessageClick(Parcelable parcelable) {
                        snackBar.hide();
                    }
                })
                .withMessage(text)
                .withDuration(time)
                .withActionMessage("取消")
                .withTextColorId(R.color.tomato)
                .show();
    }
}
