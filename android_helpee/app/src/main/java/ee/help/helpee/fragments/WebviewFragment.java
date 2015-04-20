package ee.help.helpee.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.network.models.Provider;

/**
 * Created by ian on 19/04/15.
 */
public class WebviewFragment extends Fragment {


    public static final String TAG = "WebviewFragment";

    @InjectView(R.id.webview)
    WebView webview;

    WebViewClient webViewClient;

    String providerUrl;

    public static WebviewFragment newInstance(String providerUrl) {
        WebviewFragment fragment = new WebviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Provider.PROVIDER_URL, providerUrl);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_webview, container, false);

        ButterKnife.inject(this, contentView);
        providerUrl = getArguments().getString(Provider.PROVIDER_URL, "google.com");
        webview.loadUrl(Constants.API_ENDPOINT + providerUrl);
        webViewClient = new WebViewClient();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(webViewClient);
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
