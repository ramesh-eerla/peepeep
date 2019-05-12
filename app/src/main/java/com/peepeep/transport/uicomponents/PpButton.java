package com.peepeep.transport.uicomponents;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import androidx.appcompat.widget.AppCompatButton;

import com.peepeep.transport.R;
import com.peepeep.transport.utils.CommonHelper;

public class PpButton extends AppCompatButton {

    private Context mCntxt;
    private PpEditText.IOnBackButtonListener mListener;

    /**
     * Listener for Back Key
     */
    public interface IOnBackButtonListener {
        boolean onEditTextBackButton();
    }

    /**
     * Parametric constructor to set context
     *
     * @param context
     */
    public PpButton(Context context) {
        super(context);
        init(context);
    }

    /**
     * Initializing the Context and Font
     *
     * @param context Activity Context
     */
    private void init(Context context) {
        mCntxt = context;
        setBackground(getResources().getDrawable(R.drawable.topgradient));//CommonHelper.getGradientDrawable(getResources().getColor(R.color.colorDark), getResources().getColor(R.color.colorDark), 85));
    }

    /**
     * Parametric constructor to set attributes
     *
     * @param context
     * @param attrs
     */
    public PpButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    /**
     * Method for to catch back key event in "Add Note" editor
     */
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && mListener != null && mListener.onEditTextBackButton()) {
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }
}