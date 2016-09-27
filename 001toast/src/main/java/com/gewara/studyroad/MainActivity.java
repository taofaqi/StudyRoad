package com.gewara.studyroad;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_toast)
    Button btnToast;
    @BindView(R.id.btn_snackBar)
    Button btnSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnToast.setOnClickListener(this);
        btnSnackBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_toast:
                Toast toast = Toast.makeText(this, "显示Toast", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.mipmap.ic_launcher);
                toast.setView(imageView);
                toast.show();
                break;
            case R.id.btn_snackBar:
                Snackbar snackBar = Snackbar.make(view, "显示SnackBar", Snackbar.LENGTH_LONG);
                //设置背景颜色
                snackBar.getView().setBackgroundResource(R.color.colorAccent);
                //设置按钮文字的颜色
                snackBar.setActionTextColor(Color.WHITE);
                //设置按钮的点击事件
                snackBar.setAction("点击", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "It is Toast!", Toast.LENGTH_SHORT).show();
                    }
                });
                //设置回调
                snackBar.setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                });
                snackBar.show();
                break;
            default:
                break;
        }
    }
}
