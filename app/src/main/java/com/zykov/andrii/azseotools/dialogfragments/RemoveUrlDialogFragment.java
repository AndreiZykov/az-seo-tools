package com.zykov.andrii.azseotools.dialogfragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.zykov.andrii.azseotools.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andrii on 10/22/17.
 */

public class RemoveUrlDialogFragment extends DialogFragment {

    public static final String TAG = RemoveUrlDialogFragment.class.getSimpleName();

    private static final String REMOVE_URL_DIALOG_FRAGMENT_ARGV_KEY = "URL_DIALOG_FRAGMENT_ARGV_KEY";

    private String url;

    private View rootView;

    public static RemoveUrlDialogFragment newInstance(String url) {
        RemoveUrlDialogFragment fr = new RemoveUrlDialogFragment();
        Bundle args = new Bundle();
        args.putString(REMOVE_URL_DIALOG_FRAGMENT_ARGV_KEY, url);
        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
        url = getArguments().getString(REMOVE_URL_DIALOG_FRAGMENT_ARGV_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.remove_url_dialog_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), 0);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        return dialog;
    }

    public interface RemoveUrlDialogFragmentListener {
        void removeUrl(String url);
    }

    @OnClick({R.id.remove_url_dialog_remove_btn, R.id.remove_url_dialog_cancel_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.remove_url_dialog_remove_btn:
                if (getActivity() != null && getActivity() instanceof RemoveUrlDialogFragmentListener) {
                    ((RemoveUrlDialogFragmentListener) getActivity()).removeUrl(url);
                }
                break;
            case R.id.remove_url_dialog_cancel_btn:
                break;
            default:
                break;
        }
        dismiss();
    }
}
