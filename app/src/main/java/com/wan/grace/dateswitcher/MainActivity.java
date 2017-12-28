package com.wan.grace.dateswitcher;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextSwitcher switcher;
    private TextView beforeDay;
    private TextView afterDay;
    //日历控件
    private CalendarView calendarView;
    //全局的long类型date
    private long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        calendarView = (CalendarView) findViewById(R.id.calendarview);
        date = calendarView.getDate();
        beforeDay = (TextView) findViewById(R.id.before_day);
        afterDay = (TextView) findViewById(R.id.after_day);
        switcher = (TextSwitcher) findViewById(R.id.text_switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                final ScrollTextView tv = new ScrollTextView(MainActivity.this);
                tv.setTextSize(16);
                tv.setMaxEms(10);
                tv.setHeight(140);
                //跑马灯效果
//                tv.setFocusable(true);
//                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//                tv.setMarqueeRepeatLimit(1);
//                tv.setFocusableInTouchMode(true);
//                tv.setSingleLine(true);//设置单行显示
//                tv.setHorizontallyScrolling(true);//设置水平滚动效果
                tv.setGravity(Gravity.CENTER);
                //设置点击事件
//                tv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                                Toast.makeText(MainActivity.this,tv.getText().toString(),Toast.LENGTH_SHORT).show();
//                    }
//                });
                tv.setOnScrollChangeListener(new ScrollTextView.OnScrollChangeListener() {
                    @Override
                    public void scrollUp() {
                        switcher.setInAnimation(MainActivity.this, R.anim.anim_in);
                        switcher.setOutAnimation(MainActivity.this, R.anim.anim_out);
                        String afterDate = DateConvertUtils.nextDay(date);
                        date = date + DateConvertUtils.DAY_TO_LONG;
                        switcher.setText(afterDate + DateConvertUtils.getWeek(date, "yyyy年MM月dd日"));
                        calendarView.setDate(date);
                    }

                    @Override
                    public void scrollDown() {
                        switcher.setInAnimation(MainActivity.this, R.anim.anim_up);
                        switcher.setOutAnimation(MainActivity.this, R.anim.anim_down);
                        String beforeDate = DateConvertUtils.beforeDay(date);
                        date = date - DateConvertUtils.DAY_TO_LONG;
                        switcher.setText(beforeDate + DateConvertUtils.getWeek(date, "yyyy年MM月dd日"));
                        calendarView.setDate(date);
                    }
                });
                return tv;
            }
        });
        switcher.setText(DateConvertUtils.getNowDay(date) + DateConvertUtils.getWeek(date, "yyyy年MM月dd日"));

        beforeDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.setInAnimation(MainActivity.this, R.anim.anim_up);
                switcher.setOutAnimation(MainActivity.this, R.anim.anim_down);
                String beforeDate = DateConvertUtils.beforeDay(date);
                date = date - DateConvertUtils.DAY_TO_LONG;
                switcher.setText(beforeDate + DateConvertUtils.getWeek(date, "yyyy年MM月dd日"));
                calendarView.setDate(date);
            }
        });
        afterDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.setInAnimation(MainActivity.this, R.anim.anim_in);
                switcher.setOutAnimation(MainActivity.this, R.anim.anim_out);
                String afterDate = DateConvertUtils.nextDay(date);
                date = date + DateConvertUtils.DAY_TO_LONG;
                switcher.setText(afterDate + DateConvertUtils.getWeek(date, "yyyy年MM月dd日"));
                calendarView.setDate(date);
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                        long currectLong = view.getDate();
                Date currectDate = DateConvertUtils.longToDate(date, "yyyy年MM月dd日");
                Date clickDate = DateConvertUtils.stringToDate(year + "年" + (month + 1) + "月" + dayOfMonth + "日", "yyyy年MM月dd日");
                int compareNum = clickDate.compareTo(currectDate);
                if (compareNum < 0) {
                    switcher.setInAnimation(MainActivity.this, R.anim.anim_up);
                    switcher.setOutAnimation(MainActivity.this, R.anim.anim_down);
                } else if (compareNum > 0) {
                    switcher.setInAnimation(MainActivity.this, R.anim.anim_in);
                    switcher.setOutAnimation(MainActivity.this, R.anim.anim_out);
                } else {

                }
                date = DateConvertUtils.dateToLong(clickDate);
                switcher.setText(DateConvertUtils.getNowDay(date) + DateConvertUtils.getWeek(clickDate));
            }
        });

    }
}
