package com.androidboilerplate;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ModalBottomSheetWithCustomLayout extends BottomSheetDialogFragment {

    private static View inflateView = null;

    public static ModalBottomSheetWithCustomLayout newInstance(View layout) {
        ModalBottomSheetWithCustomLayout modalBottomSheetWithCustomLayout = new ModalBottomSheetWithCustomLayout();
        Bundle arguments = new Bundle();
        inflateView = layout;
        modalBottomSheetWithCustomLayout.setArguments(arguments);
        return modalBottomSheetWithCustomLayout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        dialog.setContentView(inflateView);
        setPeekHeightOfBottomSheet();
    }

    private void setPeekHeightOfBottomSheet(){
        inflateView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {
                inflateView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) inflateView.getParent()).getLayoutParams();
                CoordinatorLayout.Behavior behavior = params.getBehavior();

                if (behavior != null && behavior instanceof BottomSheetBehavior) {
                    ((BottomSheetBehavior) behavior).setPeekHeight(inflateView.getHeight());
                }
            }
        });
    }
}
