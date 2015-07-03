package org.kymjs.chat.adapter;

import android.graphics.Typeface;
import android.widget.AbsListView;
import android.widget.TextView;

import org.kymjs.chat.R;
import org.kymjs.chat.bean.Emojicon;
import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * emoji表情界面gridview适配器
 *
 * @author kymjs (http://www.kymjs.com/) on 6/8/15.
 */
public class EmojiAdapter extends KJAdapter<Emojicon> {
    private Typeface emojitf;

    public EmojiAdapter(AbsListView view, Collection<Emojicon> mDatas) {
        super(view, mDatas, R.layout.chat_item_emoji);
        emojitf = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/emoji.ttf");
    }

    @Override
    public void convert(AdapterHolder adapterHolder, Emojicon emojicon, boolean b) {
        TextView itemTvEmoji = adapterHolder.getView(R.id.itemEmoji);
        itemTvEmoji.setTypeface(emojitf);
        itemTvEmoji.setText(emojicon.getValue());
    }
}
