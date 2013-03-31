
package bx.flashlight;

import bx.flashlight.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Flashlight extends Activity {
    private boolean ifLocked = false;

    private PowerManager.WakeLock mWakeLock;

    private PowerManager mPowerManager;

    private LinearLayout mLinearLayout;

    private int mUserBrightness = 0;

    private static final int M_CHOOSE = Menu.FIRST;

    private static final int M_EXIT = Menu.FIRST + 1;

    private int[] color = {
            R.drawable.white, R.drawable.blue, R.drawable.pink, R.drawable.green,
            R.drawable.orange, R.drawable.yellow
    };

    private int[] text = {
            R.string.str_white, R.string.str_blue, R.string.str_pink, R.string.str_green,
            R.string.str_orange, R.string.str_yellow
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);

        // Activity启动时将屏幕亮调整为最亮
        setBrightness(1.0f);

        // 初始化mLinearLayout
        mLinearLayout = (LinearLayout) findViewById(R.id.myLinearLayout1);

        // 取得PowerManager
        mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        // 取得WakeLock
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "BackLight");

        try {
            mUserBrightness = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            Toast.makeText(Flashlight.this, "" + e, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu群组ID
        int idGroup1 = 0;
        // menuItemID
        int orderMenuItem1 = Menu.NONE;
        int orderMenuItem2 = Menu.NONE + 1;
        // 建立menu
        menu.add(idGroup1, M_CHOOSE, orderMenuItem1, R.string.str_title);
        menu.add(idGroup1, M_EXIT, orderMenuItem2, R.string.str_exit);
        menu.setGroupCheckable(idGroup1, true, true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (M_CHOOSE):
                // 跳到选择背后颜色的AlertDialog
                new AlertDialog.Builder(Flashlight.this)
                        .setTitle(getResources().getString(R.string.str_title))
                        .setAdapter(new MyAdapter(this, color, text), listener1)
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                break;
            case (M_EXIT):
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 选择背后颜色的AlertDialog的OnClickListener
    OnClickListener listener1 = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // 更改背景颜色
            mLinearLayout.setBackgroundResource(color[which]);
            Toast.makeText(Flashlight.this, getResources().getString(text[which]),
                    Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onResume() {
        wakeLock();
        super.onResume();
    }

    @Override
    protected void onPause() {
        wakeUnlock();
        super.onPause();
    }

    private void wakeLock() {
        if (!ifLocked) {
            setBrightness(1.0f);
            ifLocked = true;
            mWakeLock.acquire();
        }
        setBrightness(1.0f);
    }

    private void wakeUnlock() {
        if (ifLocked) {
            mWakeLock.release();
            ifLocked = false;
            setBrightness(mUserBrightness);
        }
    }

    private void setBrightness(float brightness) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = brightness;
        getWindow().setAttributes(lp);
    }

}
