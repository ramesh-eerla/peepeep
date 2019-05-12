package com.peepeep.transport.uicomponents;

import android.content.Context;
import android.graphics.Color;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import androidx.appcompat.widget.AppCompatEditText;

import com.peepeep.transport.R;


/**
 * <p>
 * Custom Edit Text to apply the custom type face
 * </p>
 */
public class PpEditText extends AppCompatEditText {

    private Context mCntxt;
    private IOnBackButtonListener mListener;

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
    public PpEditText(Context context) {
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
    }

    /**
     * Parametric constructor to set attributes
     *
     * @param context
     * @param attrs
     */
    public PpEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Method to set the custom font
     *
     * @param fontFileName
     *            name of the font file
     */

    /**
     * Method to handle Done Button in keyboard
     */
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        InputConnection connection = super.onCreateInputConnection(outAttrs);
        int imeActions = outAttrs.imeOptions & EditorInfo.IME_MASK_ACTION;
        if ((imeActions & EditorInfo.IME_ACTION_DONE) != 0) {
            outAttrs.imeOptions ^= imeActions;
            outAttrs.imeOptions |= EditorInfo.IME_ACTION_DONE;
        }
        if ((outAttrs.imeOptions & EditorInfo.IME_FLAG_NO_ENTER_ACTION) != 0) {
            outAttrs.imeOptions &= ~EditorInfo.IME_FLAG_NO_ENTER_ACTION;
        }
        return connection;
    }



    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setTextColor(Color.BLACK);
        setBackground(getResources().getDrawable(R.drawable.selet_edittext));

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