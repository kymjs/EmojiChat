/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kymjs.chat.adapter;

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

    public EmojiAdapter(AbsListView view, Collection<Emojicon> mDatas) {
        super(view, mDatas, R.layout.chat_item_emoji);
    }

    @Override
    public void convert(AdapterHolder adapterHolder, Emojicon emojicon, boolean b) {
        TextView itemTvEmoji = adapterHolder.getView(R.id.itemEmoji);
        itemTvEmoji.setText(emojicon.getValue());
    }
}
