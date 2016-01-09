package com.icetea09.droidmax.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icetea09.droidmax.R;

/**
 * A custom TextView with dynamic typeface and left image
 */
public class ImageButton extends LinearLayout {

    private ImageView mImgIcon;
    private TextView mTvText;
    private ImageView mImgSecondaryButton;
    private RelativeLayout mRootView;

    public ImageButton(Context context) {
        super(context);
        init(context);
    }

    public ImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setClickable(true);
        init(context);
        TypedArray arrAttrs = context.obtainStyledAttributes(attrs, R.styleable.ImageButton);
        final int length = arrAttrs.getIndexCount();
        for (int i = 0; i < length; ++i) {
            int attr = arrAttrs.getIndex(i);
            switch (attr) {
                case R.styleable.ImageButton_buttonIcon:
                    Drawable drawable = arrAttrs.getDrawable(attr);
                    if (drawable != null) {
                        mImgIcon.setImageDrawable(drawable);
                    }
                    break;
                case R.styleable.ImageButton_buttonText:
                    mTvText.setText(arrAttrs.getString(attr));
                    break;
            }
        }
        arrAttrs.recycle();
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.component_image_text_view, this);
        mRootView = (RelativeLayout) findViewById(R.id.root_view);
        mImgIcon = (ImageView) findViewById(R.id.img_button_icon);
        mTvText = (TextView) findViewById(R.id.tv_text_value);
        mImgSecondaryButton = (ImageView) findViewById(R.id.btn_secondary);
        setSecondaryButtonVisibility(View.GONE);
    }

    public RelativeLayout getRootView() {
        return mRootView;
    }

    public ImageView getSecondaryButton() {
        return mImgSecondaryButton;
    }

    public void setText(int textResId) {
        mTvText.setText(textResId);
    }

    public void setText(String text) {
        mTvText.setText(text);
    }

    public void setIcon(int resId) {
        mImgIcon.setImageResource(resId);
    }

    public void setTextColor(int color) {
        mTvText.setTextColor(color);
    }

    public TextView getTextView() {
        return mTvText;
    }

    public void setSecondaryButtonVisibility(int value) {
        mImgSecondaryButton.setVisibility(value);
    }

    public void setOnClickListener(OnClickListener listener) {
        mRootView.setOnClickListener(listener);
    }

    public void setSecondaryButtonOnClickListener(OnClickListener listener) {
        mImgSecondaryButton.setOnClickListener(listener);
    }
}
