package com.example.fucai4.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.fucai4.R;


/**
 * 网络等待对话框
 * Created by ${小强同学} on 2017/12/18
 */

public class NetworkDialog extends Dialog {

    private TextView tv_msg = null;

    public NetworkDialog(@NonNull Context context) {
        super(context, R.style.AlertDialogStyle);
        setContentView(R.layout.network_dialog);
        setCanceledOnTouchOutside(false);//点击空白处不消失
        tv_msg = findViewById(R.id.tv_msg);
    }

    public void setMsgTextView(String nmg) {
        if (tv_msg != null) {
            tv_msg.setText(nmg);
        }
    }

}
