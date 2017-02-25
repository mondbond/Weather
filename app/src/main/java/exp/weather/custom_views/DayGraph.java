package exp.weather.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

public class DayGraph extends View {

    private float mWithoutPaddingHeight;
    private float mWithoutPaddingWidth;

    private float mZeroLine;

    private Paint mLevelPaint;
    private Paint mTextPaint;

    private ArrayList<Float> mTempList;
    private ArrayList<String> mHourList;

    public DayGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mWithoutPaddingHeight = getHeight() - getPaddingBottom() - getPaddingTop();
        mWithoutPaddingWidth = getWidth() - getPaddingLeft() - getPaddingRight();

        drawMiddleLine(canvas);
        drawHoursLevels(canvas);
        drawTempLevels(canvas);

        if(mTempList != null) {
            drawTemperaturePath(canvas);
        };
    }

    private void drawMiddleLine(Canvas canvas)
    {
        mLevelPaint = new Paint();
        mLevelPaint.setStrokeWidth(2);
        mLevelPaint.setColor(Color.BLACK);

        mZeroLine = getHeight()/2;
        canvas.drawLine(getPaddingLeft(), mZeroLine, getWidth() - getPaddingRight(), mZeroLine, mLevelPaint);
    }

    private void drawHoursLevels(Canvas canvas)
    {
        mHourList = new ArrayList<>();
        mHourList.add("00;00");
        mHourList.add("03;00");
        mHourList.add("06;00");
        mHourList.add("09;00");
        mHourList.add("12;00");
        mHourList.add("15;00");
        mHourList.add("18;00");
        mHourList.add("21;00");

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(16);

        float hourDistance = mWithoutPaddingWidth /8;

        for(int i = 0; i != mHourList.size(); i++) {
            canvas.drawText(mHourList.get(i) ,getPaddingLeft() + i*hourDistance, mZeroLine - 3, mTextPaint);
        }
    }

    private void drawTempLevels(Canvas canvas)
    {
        mLevelPaint.setColor(Color.RED);
        mLevelPaint.setStrokeWidth(3);

        float levelDistance = mWithoutPaddingHeight /8;

        for(int i = 1; i != 5; i++)
        {
            canvas.drawLine(getPaddingLeft(), mZeroLine - (i*levelDistance),
                    getWidth() - getPaddingRight(), mZeroLine - (i*levelDistance), mLevelPaint);

            canvas.drawText(Integer.toString(i*10), getPaddingLeft(),
                    mZeroLine - (i*levelDistance) - 4, mTextPaint);
        }

        mLevelPaint.setColor(Color.BLUE);

        for(int i = 1; i != 5; i++)
        {
            canvas.drawLine(getPaddingLeft(), mZeroLine + (i * levelDistance),
                    getWidth() - getPaddingRight(), mZeroLine + (i * levelDistance), mLevelPaint);

            canvas.drawText(Integer.toString(i*-10), getPaddingLeft(),
                    mZeroLine + (i*levelDistance) - 4, mTextPaint);
        }
    }

    private void drawTemperaturePath(Canvas canvas)
    {
        ArrayList<Float> list = mTempList;

        float pointSize = mWithoutPaddingHeight /80;
        float tempDistance = mWithoutPaddingWidth /8;

        Path path = new Path();

        Paint paintTempPath = new Paint();
        paintTempPath.setColor(Color.BLACK);
        paintTempPath.setStrokeWidth(5);
        paintTempPath.setAntiAlias(true);
        paintTempPath.setStyle(Paint.Style.STROKE);

        path.moveTo(getPaddingLeft(), mZeroLine - (list.get(0)*pointSize));

        for(int i = 0; i != list.size(); i++)
        {
            path.lineTo(getPaddingLeft() + i*tempDistance, mZeroLine - (list.get(i)*pointSize));
        }

        canvas.drawPath(path, paintTempPath);
    }

    public void setNewTemp(ArrayList<Float> newTempList) {
        this.mTempList = newTempList;
        invalidate();
    }


    // for future update
    private float getMax(ArrayList<Float> array) {
        float max = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }

        return max;
    }

    private float getMin(ArrayList<Float> array) {
        float min = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) < min) {
                min = array.get(i);
            }
        }

        return min;
    }
}
