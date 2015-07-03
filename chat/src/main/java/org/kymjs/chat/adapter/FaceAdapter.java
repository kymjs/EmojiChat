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