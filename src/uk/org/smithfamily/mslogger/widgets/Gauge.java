// Copyright (c) 2010, Freddy Martens (http://atstechlab.wordpress.com),
// MindTheRobot (http://mindtherobot.com/blog/) and contributors
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification,
// are permitted provided that the following conditions are met:
//
// * Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright notice,
// this list of conditions and the following disclaimer in the documentation
// and/or other materials provided with the distribution.
// * Neither the name of Ondrej Zara nor the names of its contributors may be used
// to endorse or promote products derived from this software without specific
// prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
// IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
// INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
// BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
// OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
// NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
// EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package uk.org.smithfamily.mslogger.widgets;

import uk.org.smithfamily.mslogger.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public final class Gauge extends View implements Indicator
{
    Handler                     h                      = new Handler();
    private static final String TAG                    = Gauge.class.getSimpleName();

    // drawing tools
    private RectF               rimRect;
    private Paint               rimPaint;
    private Paint               rimCirclePaint;

    private RectF               faceRect;
    private Bitmap              faceTexture;
    private Paint               facePaint;
    private Paint               rimShadowPaint;

    private Paint               scalePaint;
    private RectF               scaleRect;

    private RectF               valueRect;
    private RectF               rangeRect;

    private Paint               rangeOkPaint;
    private Paint               rangeWarningPaint;
    private Paint               rangeErrorPaint;
    private Paint               rangeAllPaint;

    private Paint               valueOkPaint;
    private Paint               valueWarningPaint;
    private Paint               valueErrorPaint;
    private Paint               valueAllPaint;

    private Paint               unitPaint;
    private Path                unitPath;
    private RectF               unitRect;

    private Paint               lowerTitlePaint;
    private Paint               upperTitlePaint;
    private Path                lowerTitlePath;
    private Path                upperTitlePath;
    private RectF               titleRect;

    private Paint               handPaint;
    private Path                handPath;

    private Paint               handScrewPaint;

    private Paint               backgroundPaint;
    // end drawing tools

    private Bitmap              background;                                                                 // holds the cached static part

    // scale configuration
    // Values passed as property. Defaults are set here.
    private boolean             showHand               = false;
    private boolean             showGauge              = false;
    private boolean             showRange              = false;

    private int                 rangeSegmentDegrees    = 270;
    private int                 totalNotches           = 120;
    private float               incrementPerLargeNotch = 10;
    private float               incrementPerSmallNotch = 2;

    private int                 scaleColor             = 0x9f004d0f;
    private float               scaleMinValue          = -90;
    private float               scaleMaxValue          = 120;
    private float               degreeMinValue         = 0;
    private float               degreeMaxValue         = 0;

    private int                 rangeOkColor           = 0x9f00ff00;
    private float               rangeOkMinValue        = scaleMinValue;
    private float               rangeOkMaxValue        = 45;
    private float               degreeOkMinValue       = 0;
    private float               degreeOkMaxValue       = 0;

    private int                 rangeWarningColor      = 0x9fff8800;
    private float               rangeWarningMinValue   = rangeOkMaxValue;
    private float               rangeWarningMaxValue   = 80;
    private float               degreeWarningMinValue  = 0;
    private float               degreeWarningMaxValue  = 0;

    private int                 rangeErrorColor        = 0x9fff0000;
    private float               rangeErrorMinValue     = rangeWarningMaxValue;
    private float               rangeErrorMaxValue     = 120;
    private float               degreeErrorMinValue    = 0;
    private float               degreeErrorMaxValue    = 0;

    private String              upperTitle             = "";
    private String              unitTitle              = "";

    // Fixed values.
    private static final float  scalePosition          = 0.10f;                                             // The distance from the rim to the scale
    private static final float  valuePosition          = 0.285f;                                            // The distance from the rim to the ranges
    private static final float  rangePosition          = 0.122f;                                            // The distance from the rim to the ranges
    private static final float  titlePosition          = 0.145f;                                            // The distance from the rim to the titles
    private static final float  unitPosition           = 0.300f;                                            // The distance from the rim to the unit
    private static final float  rimSize                = 0.02f;

    private float               degreesPerNotch        = (float) rangeSegmentDegrees / (float) totalNotches;

    // hand dynamics
    private boolean             dialInitialized        = false;
    private float               currentValue           = scaleMinValue;
    private float               targetValue            = scaleMinValue;
    private float               dialVelocity           = 0.0f;
    private float               dialAcceleration       = 0.0f;
    private long                lastDialMoveTime       = -1L;

    private int                 rangeSegmentOffset     = 0;
    
    private String channel;
    private boolean disabled;

    public Gauge(Context context)
    {
        super(context);
        init(context, (AttributeSet)null);
    }

    
    public Gauge(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public Gauge(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        Bundle bundle = (Bundle) state;
        Parcelable superState = bundle.getParcelable("superState");
        super.onRestoreInstanceState(superState);

        dialInitialized = bundle.getBoolean("dialInitialized");
        currentValue = bundle.getFloat("currentValue");
        targetValue = bundle.getFloat("targetValue");
        dialVelocity = bundle.getFloat("dialVelocity");
        dialAcceleration = bundle.getFloat("dialAcceleration");
        lastDialMoveTime = bundle.getLong("lastDialMoveTime");
    }

    @Override
    protected Parcelable onSaveInstanceState()
    {
        Parcelable superState = super.onSaveInstanceState();

        Bundle state = new Bundle();
        state.putParcelable("superState", superState);
        state.putBoolean("dialInitialized", dialInitialized);
        state.putFloat("currentValue", currentValue);
        state.putFloat("targetValue", targetValue);
        state.putFloat("dialVelocity", dialVelocity);
        state.putFloat("dialAcceleration", dialAcceleration);
        state.putLong("lastDialMoveTime", lastDialMoveTime);
        return state;
    }
     private void init(Context context, AttributeSet attrs)
    {
        // Get the properties from the resource file.
        if (context != null && attrs != null)
        {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Dial);
            showRange = a.getBoolean(R.styleable.Dial_showRange, showRange);
            showGauge = a.getBoolean(R.styleable.Dial_showGauge, showGauge);
            showHand = a.getBoolean(R.styleable.Dial_showHand, showHand);

            rangeSegmentDegrees = a.getInt(R.styleable.Dial_rangeSegmentDegrees, rangeSegmentDegrees);
            rangeSegmentOffset = a.getInt(R.styleable.Dial_rangeSegmentOffset, rangeSegmentOffset);
            incrementPerLargeNotch = a.getFloat(R.styleable.Dial_incrementPerLargeNotch, incrementPerLargeNotch);
            incrementPerSmallNotch = a.getFloat(R.styleable.Dial_incrementPerSmallNotch, incrementPerSmallNotch);
            scaleColor = a.getInt(R.styleable.Dial_scaleColor, scaleColor);
            scaleMinValue = a.getFloat(R.styleable.Dial_scaleMinValue, scaleMinValue);
            scaleMaxValue = a.getFloat(R.styleable.Dial_scaleMaxValue, scaleMaxValue);
            rangeOkColor = a.getInt(R.styleable.Dial_rangeOkColor, rangeOkColor);
            rangeOkMinValue = a.getFloat(R.styleable.Dial_rangeOkMinValue, rangeOkMinValue);
            rangeOkMaxValue = a.getFloat(R.styleable.Dial_rangeOkMaxValue, rangeOkMaxValue);
            rangeWarningColor = a.getInt(R.styleable.Dial_rangeWarningColor, rangeWarningColor);
            rangeWarningMinValue = a.getFloat(R.styleable.Dial_rangeWarningMinValue, rangeWarningMinValue);
            rangeWarningMaxValue = a.getFloat(R.styleable.Dial_rangeWarningMaxValue, rangeWarningMaxValue);
            rangeErrorColor = a.getInt(R.styleable.Dial_rangeErrorColor, rangeErrorColor);
            rangeErrorMinValue = a.getFloat(R.styleable.Dial_rangeErrorMinValue, rangeErrorMinValue);
            rangeErrorMaxValue = a.getFloat(R.styleable.Dial_rangeErrorMaxValue, rangeErrorMaxValue);
            disabled = a.getBoolean(R.styleable.Dial_disabled, false);
            String unitTitle = a.getString(R.styleable.Dial_unitTitle);
            String upperTitle = a.getString(R.styleable.Dial_upperTitle);
            if (unitTitle != null)
                this.unitTitle = unitTitle;

            if (upperTitle != null)
                this.upperTitle = upperTitle;
        }
        init(context);

    }

    private void init(Context context)
    {
        totalNotches = (int) ((scaleMaxValue - scaleMinValue) / incrementPerSmallNotch);

        degreesPerNotch = (float) rangeSegmentDegrees / (float) totalNotches;

        degreeMinValue = valueToAngle(scaleMinValue) - 90;
        degreeMaxValue = valueToAngle(scaleMaxValue) - 90;
        degreeOkMinValue = valueToAngle(rangeOkMinValue) - 90;
        degreeOkMaxValue = valueToAngle(rangeOkMaxValue) - 90;
        degreeWarningMinValue = valueToAngle(rangeWarningMinValue) - 90;
        degreeWarningMaxValue = valueToAngle(rangeWarningMaxValue) - 90;
        degreeErrorMinValue = valueToAngle(rangeErrorMinValue) - 90;
        degreeErrorMaxValue = valueToAngle(rangeErrorMaxValue) - 90;

        currentValue = scaleMinValue;
        setValue((scaleMinValue));

        initDrawingTools(context);
    }

    private void initDrawingTools(Context context)
    {
        rimRect = new RectF(0.0f, 0.0f, 1.0f, 1.0f);

        faceRect = new RectF();
        faceRect.set(rimRect.left + rimSize, rimRect.top + rimSize, rimRect.right - rimSize, rimRect.bottom - rimSize);

        scaleRect = new RectF();
        scaleRect.set(faceRect.left + scalePosition, faceRect.top + scalePosition, faceRect.right - scalePosition, faceRect.bottom
                - scalePosition);

        rangeRect = new RectF();
        rangeRect.set(faceRect.left + rangePosition, faceRect.top + rangePosition, faceRect.right - rangePosition, faceRect.bottom
                - rangePosition);

        valueRect = new RectF();
        valueRect.set(faceRect.left + valuePosition, faceRect.top + valuePosition, faceRect.right - valuePosition, faceRect.bottom
                - valuePosition);

        titleRect = new RectF();
        titleRect.set(faceRect.left + titlePosition, faceRect.top + titlePosition, faceRect.right - titlePosition, faceRect.bottom
                - titlePosition);

        unitRect = new RectF();
        unitRect.set(faceRect.left + unitPosition, faceRect.top + unitPosition, faceRect.right - unitPosition, faceRect.bottom
                - unitPosition);

        faceTexture = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.plastic);
        BitmapShader paperShader = new BitmapShader(faceTexture, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        Matrix paperMatrix = new Matrix();
        paperMatrix.setScale(1.0f / faceTexture.getWidth(), 1.0f / faceTexture.getHeight());

        paperShader.setLocalMatrix(paperMatrix);

        rimShadowPaint = new Paint();
        rimShadowPaint.setShader(new RadialGradient(0.5f, 0.5f, faceRect.width() / 2.0f, new int[] { 0x00000000, 0x00000500,
                0x50000500 }, new float[] { 0.96f, 0.96f, 0.99f }, Shader.TileMode.MIRROR));
        rimShadowPaint.setStyle(Paint.Style.FILL);

        // the linear gradient is a bit skewed for realism
        rimPaint = new Paint();
        rimPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        rimPaint.setShader(new LinearGradient(0.40f, 0.0f, 0.60f, 1.0f, Color.rgb(0xf0, 0xf5, 0xf0), Color.rgb(0x30, 0x31, 0x30),
                Shader.TileMode.CLAMP));

        rimCirclePaint = new Paint();
        rimCirclePaint.setAntiAlias(true);
        rimCirclePaint.setStyle(Paint.Style.STROKE);
        rimCirclePaint.setColor(Color.argb(0x4f, 0x33, 0x36, 0x33));
        rimCirclePaint.setStrokeWidth(0.005f);

        facePaint = new Paint();
        facePaint.setFilterBitmap(true);
        facePaint.setStyle(Paint.Style.FILL);
        facePaint.setShader(paperShader);

        scalePaint = new Paint();
        scalePaint.setStyle(Paint.Style.STROKE);
        scalePaint.setColor(scaleColor);
        scalePaint.setStrokeWidth(0.005f);
        scalePaint.setAntiAlias(true);
        scalePaint.setTextSize(0.045f);
        scalePaint.setTypeface(Typeface.SANS_SERIF);
        scalePaint.setTextScaleX(0.8f);
        scalePaint.setTextAlign(Paint.Align.CENTER);

        rangeOkPaint = new Paint();
        rangeOkPaint.setStyle(Paint.Style.STROKE);
        rangeOkPaint.setColor(rangeOkColor);
        rangeOkPaint.setStrokeWidth(0.012f);
        rangeOkPaint.setAntiAlias(true);

        rangeWarningPaint = new Paint();
        rangeWarningPaint.setStyle(Paint.Style.STROKE);
        rangeWarningPaint.setColor(rangeWarningColor);
        rangeWarningPaint.setStrokeWidth(0.012f);
        rangeWarningPaint.setAntiAlias(true);

        rangeErrorPaint = new Paint();
        rangeErrorPaint.setStyle(Paint.Style.STROKE);
        rangeErrorPaint.setColor(rangeErrorColor);
        rangeErrorPaint.setStrokeWidth(0.012f);
        rangeErrorPaint.setAntiAlias(true);

        rangeAllPaint = new Paint();
        rangeAllPaint.setStyle(Paint.Style.STROKE);
        rangeAllPaint.setColor(0xcff8f8f8);
        rangeAllPaint.setStrokeWidth(0.012f);
        rangeAllPaint.setAntiAlias(true);
        rangeAllPaint.setShadowLayer(0.005f, -0.002f, -0.002f, 0x7f000000);

        valueOkPaint = new Paint();
        valueOkPaint.setStyle(Paint.Style.STROKE);
        valueOkPaint.setColor(rangeOkColor);
        valueOkPaint.setStrokeWidth(0.20f);
        valueOkPaint.setAntiAlias(true);

        valueWarningPaint = new Paint();
        valueWarningPaint.setStyle(Paint.Style.STROKE);
        valueWarningPaint.setColor(rangeWarningColor);
        valueWarningPaint.setStrokeWidth(0.20f);
        valueWarningPaint.setAntiAlias(true);

        valueErrorPaint = new Paint();
        valueErrorPaint.setStyle(Paint.Style.STROKE);
        valueErrorPaint.setColor(rangeErrorColor);
        valueErrorPaint.setStrokeWidth(0.20f);
        valueErrorPaint.setAntiAlias(true);

        valueAllPaint = new Paint();
        valueAllPaint.setStyle(Paint.Style.STROKE);
        valueAllPaint.setColor(0xcff8f8f8);
        valueAllPaint.setStrokeWidth(0.20f);
        valueAllPaint.setAntiAlias(true);
        valueAllPaint.setShadowLayer(0.005f, -0.002f, -0.002f, 0x7f000000);

        unitPaint = new Paint();
        unitPaint.setColor(0xaf0c0c0c);
        unitPaint.setAntiAlias(true);
        unitPaint.setTypeface(Typeface.DEFAULT_BOLD);
        unitPaint.setTextAlign(Paint.Align.CENTER);
        unitPaint.setTextSize(0.05f);
        unitPaint.setTextScaleX(0.8f);

        upperTitlePaint = new Paint();
        upperTitlePaint.setColor(0xaf0c0c0c);
        upperTitlePaint.setAntiAlias(true);
        upperTitlePaint.setTypeface(Typeface.DEFAULT_BOLD);
        upperTitlePaint.setTextAlign(Paint.Align.CENTER);
        upperTitlePaint.setTextSize(0.04f);
        upperTitlePaint.setTextScaleX(0.8f);

        lowerTitlePaint = new Paint();
        lowerTitlePaint.setColor(0xaf000000);
        lowerTitlePaint.setAntiAlias(true);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/ziska.ttf");
        lowerTitlePaint.setTypeface(font);
        lowerTitlePaint.setTextAlign(Paint.Align.CENTER);
        lowerTitlePaint.setTextSize(0.1f);
        lowerTitlePaint.setTextScaleX(0.8f);

        handPaint = new Paint();
        handPaint.setAntiAlias(true);
        handPaint.setColor(0xff392f2c);
        handPaint.setShadowLayer(0.01f, -0.005f, -0.005f, 0x7f000000);
        handPaint.setStyle(Paint.Style.FILL);

        handScrewPaint = new Paint();
        handScrewPaint.setAntiAlias(true);
        // handScrewPaint.setColor(0xff493f3c);
        handScrewPaint.setColor(0xffff3f3c);
        handScrewPaint.setStyle(Paint.Style.FILL);

        backgroundPaint = new Paint();
        backgroundPaint.setFilterBitmap(true);
        if (!this.isInEditMode())
        {
            unitPath = new Path();
            unitPath.addArc(unitRect, 180.0f, 180.0f);

            upperTitlePath = new Path();
            upperTitlePath.addArc(titleRect, 180.0f, 180.0f);

            lowerTitlePath = new Path();
            lowerTitlePath.addArc(titleRect, -180.0f, -180.0f);
        }
        // The hand is drawn with the tip facing up. That means when the image
        // is not rotated, the tip
        // faces north. When the the image is rotated -90 degrees, the tip is
        // facing west and so on.
        if (!this.isInEditMode())
        {
            handPath = new Path(); // X Y

            handPath.moveTo(0.5f, 0.5f + 0.2f); // 0.500, 0.700
            handPath.lineTo(0.5f - 0.010f, 0.5f + 0.2f - 0.007f); // 0.490, 0.630
            handPath.lineTo(0.5f - 0.002f, 0.5f - 0.40f); // 0.498, 0.100
            handPath.lineTo(0.5f + 0.002f, 0.5f - 0.40f); // 0.502, 0.100
            handPath.lineTo(0.5f + 0.010f, 0.5f + 0.2f - 0.007f); // 0.510, 0.630
            handPath.lineTo(0.5f, 0.5f + 0.2f); // 0.500, 0.700
            handPath.addCircle(0.5f, 0.5f, 0.025f, Path.Direction.CW);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        Log.d(TAG, "Width spec: " + MeasureSpec.toString(widthMeasureSpec));
        Log.d(TAG, "Height spec: " + MeasureSpec.toString(heightMeasureSpec));

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int chosenWidth = chooseDimension(widthMode, widthSize);
        int chosenHeight = chooseDimension(heightMode, heightSize);

        int chosenDimension = Math.min(chosenWidth, chosenHeight);

        setMeasuredDimension(chosenDimension, chosenDimension);
    }

    private int chooseDimension(int mode, int size)
    {
        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY)
        {
            return size;
        }
        else
        { // (mode == MeasureSpec.UNSPECIFIED)
            return getPreferredSize();
        }
    }

    // in case there is no size specified
    private int getPreferredSize()
    {
        return 250;
    }

    private void drawRim(Canvas canvas)
    {
        // first, draw the metallic body
        canvas.drawOval(rimRect, rimPaint);
        // now the outer rim circle
        canvas.drawOval(rimRect, rimCirclePaint);
    }

    private void drawFace(Canvas canvas)
    {
        canvas.drawOval(faceRect, facePaint);
        // draw the inner rim circle
        canvas.drawOval(faceRect, rimCirclePaint);
        // draw the rim shadow inside the face
        if (!this.isInEditMode())
        {
            canvas.drawOval(faceRect, rimShadowPaint);
        }
    }

    private void drawBackground(Canvas canvas)
    {
        if (background == null)
        {
            Log.w(TAG, "Background not created");
        }
        else
        {
            canvas.drawBitmap(background, 0, 0, backgroundPaint);
        }
    }

    private void drawScale(Canvas canvas)
    {
        // Draw the circle
        canvas.drawOval(scaleRect, scalePaint);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate(valueToAngle(scaleMinValue), 0.5f, 0.5f);
        for (int i = 0; i <= this.totalNotches; ++i)
        {
            float y1 = scaleRect.top;
            float y2 = y1 - 0.015f;
            float y3 = y1 - 0.025f;

            float value = notchToValue(i);

            if (i % (incrementPerLargeNotch / incrementPerSmallNotch) == 0)
            {
                if (value >= scaleMinValue && value <= scaleMaxValue)
                {
                    // draw a nick
                    canvas.drawLine(0.5f, y1, 0.5f, y3, scalePaint);

                    String valueString;
                    if (incrementPerSmallNotch >= 1)
                    {
                        valueString = Integer.toString((int) value);
                    }
                    else
                    {
                        valueString = Float.toString(value);
                    }
                    // Draw the text 0.15 away from y3 which is the long nick.
                    canvas.drawText(valueString, 0.5f, y3 - 0.015f, scalePaint);
                }
                else
                {
                    Log.d("Gauge", "value=" + value + " scaleMin=" + scaleMinValue + " scaleMax=" + scaleMaxValue);
                }
            }
            else
            {
                if (value >= scaleMinValue && value <= scaleMaxValue)
                {
                    // draw a nick
                    canvas.drawLine(0.5f, y1, 0.5f, y2, scalePaint);
                }
                else
                {
                    Log.d("Gauge", "value=" + value + " scaleMin=" + scaleMinValue + " scaleMax=" + scaleMaxValue);
                }

            }

            canvas.rotate(degreesPerNotch, 0.5f, 0.5f);
        }
        canvas.restore();
    }

    private void drawScaleRanges(Canvas canvas)
    {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.drawArc(rangeRect, degreeMinValue, degreeMaxValue - degreeMinValue, false, rangeAllPaint);
        canvas.drawArc(rangeRect, degreeOkMinValue, degreeOkMaxValue - degreeOkMinValue, false, rangeOkPaint);
        canvas.drawArc(rangeRect, degreeWarningMinValue, degreeWarningMaxValue - degreeWarningMinValue, false, rangeWarningPaint);
        canvas.drawArc(rangeRect, degreeErrorMinValue, degreeErrorMaxValue - degreeErrorMinValue, false, rangeErrorPaint);
        canvas.restore();
    }

    private void drawTitle(Canvas canvas)
    {
        // Use a vertical offset when printing the upper title. The upper and lower title
        // use the same rectangular but the spacing between the title and the ranges
        // is not equal for the upper and lower title and therefore, the upper title is
        // moved downwards.
        if (!this.isInEditMode())
        {
            canvas.drawTextOnPath(upperTitle, upperTitlePath, 0.0f, 0.02f, upperTitlePaint);
            canvas.drawTextOnPath(Integer.toString((int) targetValue), lowerTitlePath, 0.0f, 0.0f, lowerTitlePaint);
            canvas.drawTextOnPath(unitTitle, unitPath, 0.0f, 0.0f, unitPaint);
        }
    }

    private void drawHand(Canvas canvas)
    {
        if (!disabled && dialInitialized && !this.isInEditMode())
        {
            float angle = valueToAngle(currentValue);

            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            canvas.rotate(angle, 0.5f, 0.5f);
            canvas.drawPath(handPath, handPaint);
            canvas.restore();

            canvas.drawCircle(0.5f, 0.5f, 0.01f, handScrewPaint);
        }
    }

    private void drawGauge(Canvas canvas)
    {
        if (dialInitialized)
        {
            // When currentValue is not rotated, the tip of the hand points to n -90 degrees.
            float angle = valueToAngle(currentValue) - 90;

            Paint colour = valueOkPaint;
            if (targetValue <= rangeOkMaxValue)
            {
                colour = valueOkPaint;
            }
            if ((targetValue > rangeOkMaxValue) && (targetValue <= rangeWarningMaxValue))
            {
                colour = valueWarningPaint;
            }
            if ((targetValue > rangeWarningMaxValue) && (targetValue <= rangeErrorMaxValue))
            {
                colour = valueErrorPaint;
            }
            canvas.drawArc(valueRect, degreeMinValue, angle - degreeMinValue, false, colour);

        }
    }

    private void drawBezel(Canvas canvas)
    {
        // Draw the bevel in which the value is draw.
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.drawArc(valueRect, degreeMinValue, degreeMaxValue - degreeMinValue, false, valueAllPaint);
        canvas.restore();
    }

    /*
     * Translate a notch to a value for the scale. The notches are evenly spread across the scale, half of the notches on the left hand side and the other half on the right hand side. The raw value
     * calculation uses a constant so that each notch represents a value n + 2.
     */
    private float notchToValue(int notch)
    {
        float rawValue = notch * incrementPerSmallNotch;
        float shiftedValue = rawValue + scaleMinValue;
        return shiftedValue;
    }

    private float valueToAngle(float value)
    {
        float scaleRange = scaleMaxValue - scaleMinValue;
        float scaledValue = (value - scaleMinValue) / scaleRange;
        float angle = scaledValue * rangeSegmentDegrees - rangeSegmentOffset;
        return angle;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        drawBackground(canvas);

        float scale = (float) getWidth();
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(scale, scale);
        // Draw the needle using the updated value
        if (showGauge)
        {
            drawGauge(canvas);
        }

        // Draw the needle using the updated value
        if (showHand)
        {
            drawHand(canvas);
        }
        drawTitle(canvas);

        canvas.restore();

        // Calculate a new current value.
        calculateCurrentValue();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        Log.d(TAG, "Size changed to " + w + "x" + h);
        regenerateBackground();
    }

    private void regenerateBackground()
    {
        // free the old bitmap
        if (background != null)
        {
            background.recycle();
        }

        background = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas backgroundCanvas = new Canvas(background);
        float scale = (float) getWidth();
        backgroundCanvas.scale(scale, scale);

        drawRim(backgroundCanvas);
        drawFace(backgroundCanvas);
        drawScale(backgroundCanvas);
        if (showRange)
        {
            drawScaleRanges(backgroundCanvas);
        }
        if (showGauge)
        {
            drawBezel(backgroundCanvas);
        }
    }

    // Move the hand slowly to the new position.
    private void calculateCurrentValue()
    {
        if (!(Math.abs(currentValue - targetValue) > 0.01f))
        {
            return;
        }

        if (lastDialMoveTime != -1L)
        {
            long currentTime = System.currentTimeMillis();
            float delta = (currentTime - lastDialMoveTime) / 1000.0f;

            float direction = Math.signum(dialVelocity);
            if (Math.abs(dialVelocity) < 90.0f)
            {
                dialAcceleration = 5.0f * (targetValue - currentValue);
            }
            else
            {
                dialAcceleration = 0.0f;
            }
            currentValue += dialVelocity * delta;
            dialVelocity += dialAcceleration * delta;
            if ((targetValue - currentValue) * direction < 0.01f * direction)
            {
                currentValue = targetValue;
                dialVelocity = 0.0f;
                dialAcceleration = 0.0f;
                lastDialMoveTime = -1L;
            }
            else
            {
                lastDialMoveTime = System.currentTimeMillis();
            }
            invalidate();
        }
        else
        {
            lastDialMoveTime = System.currentTimeMillis();
            calculateCurrentValue();
        }
    }

    public void setValue(float value)
    {
        if (value < scaleMinValue)
            value = scaleMinValue;
        else if (value > scaleMaxValue)
            value = scaleMaxValue;

        targetValue = value;
        dialInitialized = true;

        invalidate(); // forces onDraw() to be called.
    }

    public float getValue()
    {
        return targetValue;
    }

    public float getScaleMinValue()
    {
        return scaleMinValue;
    }

    public void setScaleMinValue(float scaleMinValue)
    {
        this.scaleMinValue = scaleMinValue;
    }

    public float getScaleMaxValue()
    {
        return scaleMaxValue;
    }

    public void setScaleMaxValue(float scaleMaxValue)
    {
        this.scaleMaxValue = scaleMaxValue;
    }

    public String getUpperTitle()
    {
        return upperTitle;
    }

    public void setUpperTitle(String upperTitle)
    {
        this.upperTitle = upperTitle;
    }

    public String getUnitTitle()
    {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle)
    {
        this.unitTitle = unitTitle;
    }

    public float getCurrentValue()
    {
        return currentValue;
    }

    public void setMin(float min)
    {
        this.scaleMinValue = min;
        this.rangeOkMinValue = min;
    }

    public void setMax(float max)
    {

        this.scaleMaxValue = max;
        this.rangeErrorMaxValue = max;
        
    }

    public void setTitle(String title)
    {
        this.upperTitle=title;
        
    }

    public void setWarningPoint(float warn)
    {

        this.rangeWarningMinValue=warn;
        this.rangeOkMaxValue=warn;
        
    }

    public void setErrorPoint(float err)
    {
        this.rangeWarningMaxValue=err;
        this.rangeErrorMinValue=err;
    }

    public void setCurrentValue(double value)
    {
        setValue((float) value);
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }
    
    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        
        IndicatorManager.INSTANCE.registerIndicator(this);

    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        
        IndicatorManager.INSTANCE.deregisterIndicator(this);

    }


	@Override
	public void setDisabled(boolean disabled)
	{
		// TODO Auto-generated method stub
		
	}
}
