package org.kymjs.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public abstract class SupportFragment extends Fragment {
    public SupportFragment() {
    }

    protected abstract View inflaterView(LayoutInflater var1, ViewGroup var2, Bundle var3);

    protected void initWidget(View parentView) {
    }

    protected void initData() {
    }

    protected void threadDataInited() {
    }

    protected void widgetClick(View v) {
    }

    public void onClick(View v) {
        this.widgetClick(v);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = this.inflaterView(inflater, container, savedInstanceState);
        this.initData();
        this.initWidget(view);
        return view;
    }
}
