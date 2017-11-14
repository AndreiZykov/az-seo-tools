package com.zykov.andrii.azseotools.dialogfragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.zykov.andrii.azseotools.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andrii on 10/22/17.
 */

public class UrlDialogFragment extends DialogFragment {

    public static final String TAG = UrlDialogFragment.class.getSimpleName();

    private static final String URL_DIALOG_FRAGMENT_ARGV_KEY = "URL_DIALOG_FRAGMENT_ARGV_KEY";

    private String url;

    private View rootView;

    @BindView(R.id.url_layout_edit_text)
    EditText urlEditText;

    @BindView(R.id.url_dialog_fragment_error_text_view)
    TextView errorTextView;

    public static UrlDialogFragment newInstance(String url) {
        UrlDialogFragment fr = new UrlDialogFragment();
        Bundle args = new Bundle();
        args.putString(URL_DIALOG_FRAGMENT_ARGV_KEY, url);
        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
        url = getArguments().getString(URL_DIALOG_FRAGMENT_ARGV_KEY);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.url_dialog_fragment, container, false);
        ButterKnife.bind(this, rootView);
        if (url != null && !url.isEmpty()) {
            urlEditText.setText(url);
            if (errorTextView != null)
                errorTextView.setVisibility(View.VISIBLE);
        }
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

    public interface UrlDialogFragmentListener {
        void addUrl(String url);
    }

    @OnClick({R.id.url_dialog_add_btn, R.id.url_dialog_cancel_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.url_dialog_add_btn:
                if (getActivity() != null && getActivity() instanceof UrlDialogFragmentListener) {
                    ((UrlDialogFragmentListener) getActivity()).addUrl(urlEditText.getText().toString());
                }
                break;
            case R.id.url_dialog_cancel_btn:
                break;
            default:
                break;
        }
        dismiss();
    }
}
