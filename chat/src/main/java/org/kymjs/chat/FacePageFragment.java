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
package org.kymjs.chat;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.kymjs.chat.adapter.FaceAdapter;
import org.kymjs.chat.bean.Faceicon;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 表情分类中每个分类的具体显示
 *
 * @author kymjs (http://www.kymjs.com/)
 */
public class FacePageFragment extends SupportFragment {

    public static final String FACE_FOLDER_PATH = "face_folder_path";

    private static final int ITEM_PAGE_COUNT = 12;

    private ViewPager mPagerFace;
    private LinearLayout pagePointLayout;

    private Activity aty;
    private GridView[] allPageViews;
    private RadioButton[] pointViews;
    private OnOperationListener listener;

    //当前fragment(一个表情分类)所包含的全部表情
    private List<Faceicon> datas;

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        aty = getActivity();
        View rootView = layoutInflater.inflate(R.layout.chat_frag_face, null);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
        String folderPath = getArguments().getString(FACE_FOLDER_PATH);
        if (StringUtils.isEmpty(folderPath)) {
            folderPath = "";
            Log.e("kymjs", getClass().getSimpleName() + " line 69, folder path is empty");
        }
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] faceFiles = folder.listFiles();
            datas = new ArrayList<>(faceFiles.length);
            for (File faceFile : faceFiles) {
                if (!faceFile.isHidden()) {
                    Faceicon data = new Faceicon();
                    data.setName("http://www.oschina.net/image/" + faceFile.getName());
                    data.setFileName(faceFile.getName());
                    data.setPath(faceFile.getAbsolutePath());
                    datas.add(data);
                }
            }
        } else {
            datas = new ArrayList<>(0);
        }
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);
        mPagerFace = (ViewPager) rootView.findViewById(R.id.frag_pager_face);
        pagePointLayout = (LinearLayout) rootView.findViewById(R.id.frag_point);

        int total = datas.size();
        int pages = total / ITEM_PAGE_COUNT
                + (total % ITEM_PAGE_COUNT == 0 ? 0 : 1);

        allPageViews = new GridView[pages];
        pointViews = new RadioButton[pages];

        for (int x = 0; x < pages; x++) {
            int start = x * ITEM_PAGE_COUNT;
            int end = (start + ITEM_PAGE_COUNT) > total ? total
                    : (start + ITEM_PAGE_COUNT);
            final List<Faceicon> itemDatas = datas.subList(start, end);
            GridView view = new GridView(aty);
            FaceAdapter faceAdapter = new FaceAdapter(view, itemDatas);
            view.setAdapter(faceAdapter);

            view.setNumColumns(4);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setVerticalScrollBarEnabled(false);
            view.setPadding(5, 0, 5, 0);
            view.setSelector(new ColorDrawable(Color.TRANSPARENT));
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            view.setGravity(Gravity.CENTER);

            view.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    if (listener != null) {
                        listener.selectedFace(itemDatas.get(position));
                    }
                }
            });
            allPageViews[x] = view;

            RadioButton tip = new RadioButton(aty);
            tip.setBackgroundResource(R.drawable.selector_bg_tip);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                    8, 8);
            layoutParams.leftMargin = 10;
            pagePointLayout.addView(tip, layoutParams);
            if (x == 0) {
                tip.setChecked(true);
            }
            pointViews[x] = tip;
        }

        PagerAdapter facePagerAdapter = new FacePagerAdapter(allPageViews);
        mPagerFace.setAdapter(facePagerAdapter);
        mPagerFace.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int index) {
                pointViews[index].setChecked(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public class FacePagerAdapter extends PagerAdapter {
        private final GridView[] gridViewList;

        public FacePagerAdapter(GridView[] gridViewList) {
            this.gridViewList = gridViewList;
        }

        @Override
        public int getCount() {
            return gridViewList.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(gridViewList[arg1]);
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(gridViewList[arg1]);
            return gridViewList[arg1];
        }
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }
}