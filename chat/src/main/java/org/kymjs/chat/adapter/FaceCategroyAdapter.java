package org.kymjs.chat.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.kymjs.chat.FacePageFragment;
import org.kymjs.chat.OnOperationListener;
import org.kymjs.chat.R;
import org.kymjs.chat.emoji.EmojiPageFragment;
import org.kymjs.chat.widget.PagerSlidingTabStrip;

import java.util.List;

/**
 * 控件分类的viewpager适配器
 *
 * @author kymjs (http://www.kymjs.com/)
 */
public class FaceCategroyAdapter extends FragmentPagerAdapter implements
        PagerSlidingTabStrip.IconTabProvider {

    //每个item表示一个folder绝对路径，每个folder中存放的是一套face图片
    private List<String> datas;
    private OnOperationListener listener;

    public FaceCategroyAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 底部表情分类的图标，这里为了省事就都返回同一个了
     * @param position
     * @return
     */
    @Override
    public int getPageIconResId(int position) {
        return R.drawable.face_btn_normal;
    }

    @Override
    public int getCount() {
        int count = datas == null ? 0 : datas.size();
        //加1是因为有默认的emoji表情分类
        return (count + 1);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (position == 0) {
            f = new EmojiPageFragment();
            ((EmojiPageFragment) f).setOnOperationListener(listener);
        } else {
            position--;
            f = new FacePageFragment();
            ((FacePageFragment) f).setOnOperationListener(listener);
            Bundle bundle = new Bundle();
            bundle.putString(FacePageFragment.FACE_FOLDER_PATH, datas.get(position));
            f.setArguments(bundle);
        }
        return f;
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }

    public void refresh(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
}