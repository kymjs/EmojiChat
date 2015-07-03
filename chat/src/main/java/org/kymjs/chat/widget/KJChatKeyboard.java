package org.kymjs.chat.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.kymjs.chat.OnOperationListener;
import org.kymjs.chat.R;
import org.kymjs.chat.SoftKeyboardStateHelper;
import org.kymjs.chat.adapter.FaceCategroyAdapter;

import java.util.List;

/**
 * 控件主界面
 *
 * @author kymjs (http://www.kymjs.com/)
 */
public class KJChatKeyboard extends RelativeLayout implements
        SoftKeyboardStateHelper.SoftKeyboardStateListener {

    public interface OnToolBoxListener {
        void onShowFace();
    }

    /**
     * 最上层输入框
     */
    private EditText mEtMsg;
    private Button mBtnFace;
    private Button mBtnSend;

    /**
     * 表情
     */
    private ViewPager mPagerFaceCagetory;
    private RelativeLayout mRlFace;
    private PagerSlidingTabStrip mFaceTabs;

    private FaceCategroyAdapter adapter;

    private Context context;
    private OnOperationListener listener;
    private OnToolBoxListener mFaceListener;
    private SoftKeyboardStateHelper mKeyboardHelper;
    private Typeface emojitf;

    public KJChatKeyboard(Context context) {
        super(context);
        init(context);
    }

    public KJChatKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KJChatKeyboard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View root = View.inflate(context, R.layout.chat_tool_box, null);
        this.addView(root);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initData();
        this.initWidget();
    }

    private void initData() {
        mKeyboardHelper = new SoftKeyboardStateHelper(((Activity) getContext())
                .getWindow().getDecorView());
        mKeyboardHelper.addSoftKeyboardStateListener(this);
        emojitf = Typeface.createFromAsset(context.getAssets(), "fonts/emoji.ttf");
    }

    private void initWidget() {
        mEtMsg = (EditText) findViewById(R.id.toolbox_et_message);
        mEtMsg.setTypeface(emojitf);
        mBtnSend = (Button) findViewById(R.id.toolbox_btn_send);
        mBtnFace = (Button) findViewById(R.id.toolbox_btn_face);
        mRlFace = (RelativeLayout) findViewById(R.id.toolbox_layout_face);
        mPagerFaceCagetory = (ViewPager) findViewById(R.id.toolbox_pagers_face);
        mFaceTabs = (PagerSlidingTabStrip) findViewById(R.id.toolbox_tabs);
        adapter = new FaceCategroyAdapter(
                ((FragmentActivity) getContext()).getSupportFragmentManager());
        mBtnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    String content = mEtMsg.getText().toString();
                    listener.send(content);
                    mEtMsg.setText(null);
                }
            }
        });
        // 点击表情按钮
        mBtnFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRlFace.getVisibility() == VISIBLE) {
                    hideFaceLayout();
                    showKeyboard(context);
                } else {
                    showFaceLayout();
                }
            }
        });
        // 点击消息输入框
        mEtMsg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFaceLayout();
            }
        });
        mEtMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(s)) {
                    mBtnSend.setText("图片");
                } else {
                    mBtnSend.setText("文字");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void setFaceData(List<String> faceData) {
        adapter.refresh(faceData);
        mPagerFaceCagetory.setAdapter(adapter);
        mFaceTabs.setViewPager(mPagerFaceCagetory);
        //加1是表示第一个分类为默认的emoji表情分类，这个分类是固定不可更改的
        if (faceData.size() + 1 < 2) {
            mFaceTabs.setVisibility(GONE);
        }
    }

    public EditText getEditTextBox() {
        return mEtMsg;
    }

    public void showFaceLayout() {
        hideKeyboard(this.context);
        // 延迟一会，让键盘先隐藏再显示表情键盘，否则会有一瞬间表情键盘和软键盘同时显示
        postDelayed(new Runnable() {
            @Override
            public void run() {
                mRlFace.setVisibility(View.VISIBLE);
                if (mFaceListener != null) {
                    mFaceListener.onShowFace();
                }
            }
        }, 50);
    }


    public boolean isShow() {
        return mRlFace.getVisibility() == VISIBLE;
    }

    public void hideFaceLayout() {
        mRlFace.setVisibility(View.GONE);
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive() && activity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                        .getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     */
    public static void showKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(activity.getCurrentFocus()
                    .getWindowToken(), 0);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public OnOperationListener getOnOperationListener() {
        return listener;
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
        adapter.setOnOperationListener(onOperationListener);
    }

    public void setOnToolBoxListener(OnToolBoxListener mFaceListener) {
        this.mFaceListener = mFaceListener;
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        hideFaceLayout();
    }

    @Override
    public void onSoftKeyboardClosed() {
    }
}
