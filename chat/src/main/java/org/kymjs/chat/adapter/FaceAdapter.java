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
import android.widget.ImageView;

import org.kymjs.chat.R;
import org.kymjs.chat.bean.Faceicon;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * 表情区域GridView的适配器
 *
 * @author kymjs (http://www.kymjs.com/)
 */
public class FaceAdapter extends KJAdapter<Faceicon> {
    private KJBitmap kjb = new KJBitmap();
    
    public FaceAdapter(AbsListView view, Collection<Faceicon> mDatas) {
        super(view, mDatas, R.layout.chat_item_face);
    }

    @Override
    public void convert(AdapterHolder adapterHolder, Faceicon data, boolean b) {
        ImageView view = adapterHolder.getView(R.id.itemImage);
//        int id = view.getResources().getIdentifier(s,
//                "drawable", view.getContext().getPackageName());
//        view.setImageResource(id);
        kjb.display(view, data.getPath());
    }
}