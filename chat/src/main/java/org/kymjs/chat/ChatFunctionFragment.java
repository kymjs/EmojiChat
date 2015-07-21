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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.kymjs.kjframe.ui.SupportFragment;

/**
 * 聊天键盘功能界面
 *
 * @author kymjs (http://www.kymjs.com/) on 7/6/15.
 */
public class ChatFunctionFragment extends SupportFragment {

    private LinearLayout layout1;
    private LinearLayout layout2;

    private OnOperationListener listener;

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.chat_item_menu, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        layout1 = (LinearLayout) parentView.findViewById(R.id.chat_menu_images);
        layout2 = (LinearLayout) parentView.findViewById(R.id.chat_menu_photo);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if (v == layout1) {
            clickMenu(0);
        } else if (v == layout2) {
            clickMenu(1);
        }
    }

    private void clickMenu(int i) {
        if (listener != null) {
            listener.selectedFunction(i);
        }
    }
}
