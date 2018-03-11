package com.example.tt.showcase.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tt.showcase.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TT on 3.11.2017..
 *
 * Holds a TextView and a Button to be used to display network errors and allow the user to retry a network request.
 */

public class RetryView extends RelativeLayout {

    public static final int IDLE = 0;
    public static final int ERROR = 1;
    public static final int LOADING = 2;

    @BindView(R.id.txtErrorText) TextView txtErrorText;
    @BindView(R.id.btnRetry) Button btnRetry;

    public RetryView(Context context) {
        super(context);
        init(context);
    }

    public RetryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RetryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_retry, this);
        ButterKnife.bind(this);
        if(!isInEditMode()) {
            setState(IDLE);
        }
    }

    public void setState(int state) {
        switch (state){
            case LOADING:
                btnRetry.setVisibility(INVISIBLE);
                txtErrorText.setVisibility(INVISIBLE);
                break;
            case ERROR:
                setVisibility(VISIBLE);
                btnRetry.setVisibility(VISIBLE);
                txtErrorText.setVisibility(VISIBLE);
                break;
            case IDLE:
                btnRetry.setVisibility(INVISIBLE);
                txtErrorText.setVisibility(INVISIBLE);
                break;
        }
    }

    public void setRetryClickListener(OnClickListener clickListener){
        btnRetry.setOnClickListener(clickListener);
    }

    public Button getBtnRetry(){
        return btnRetry;
    }

    public void setError(String text){
        setState(ERROR);
        txtErrorText.setText(text);
    }
}
