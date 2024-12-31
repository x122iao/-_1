package com.m7.imkfsdk;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.m7.imkfsdk.utils.permission.PermissionConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PermissionExplainDialog extends AlertDialog {
    private List<String> permissions;

    public PermissionExplainDialog(Context context, String... permission) {
        super(context);
        permissions = Arrays.asList(permission);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ykfsdk_dialog_permission_explain);
        LinearLayout one = findViewById(R.id.lay_one);
        LinearLayout two = findViewById(R.id.lay_two);
        LinearLayout three = findViewById(R.id.lay_three);
        for (String permission : permissions) {
            if (Objects.equals(permission, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || Objects.equals(permission, Manifest.permission.READ_MEDIA_AUDIO)
                    || Objects.equals(permission, Manifest.permission.READ_MEDIA_IMAGES)
                    || Objects.equals(permission, Manifest.permission.READ_MEDIA_VIDEO)) {
                three.setVisibility(View.VISIBLE);
            }
            if (Objects.equals(permission, PermissionConstants.CAMERA)) {
                two.setVisibility(View.VISIBLE);
            }
            if (Objects.equals(permission, PermissionConstants.RECORD_AUDIO)) {
                one.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void show() {
        super.show();
        getWindow().setGravity(Gravity.TOP);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));


    }
}
