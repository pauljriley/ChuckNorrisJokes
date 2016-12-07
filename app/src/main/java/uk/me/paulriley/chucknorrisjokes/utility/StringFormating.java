package uk.me.paulriley.chucknorrisjokes.utility;

import android.text.Html;
import android.text.Spanned;

public class StringFormating {
    @SuppressWarnings("deprecation")
    public static String fromHtml(String joke) {
        if (joke == null) return "";

        Spanned result;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(joke, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(joke);
        }

        return result.toString();
    }
}
