package net.nightwhistler.htmlspanner;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public final class HtmlUtils {
    public static Spanned fromHtml(String source) {
        return Html.fromHtml(source, new ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                Drawable d = new BitmapDrawable();
                return d;
            }
        }, null);
    }

    public static void fromHtml(final String source, final TextView view) {
        new AsyncTask<Void, Void, Spannable>() {
            @Override
            protected Spannable doInBackground(Void... params) {
                return new HtmlSpanner().fromHtml(source);
            }

            @Override
            protected void onPostExecute(Spannable result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                view.setText(result/*HtmlUtils.fromHtml(item.body)*/);

                if (view.getText() instanceof Spannable
                        && view.getLayout() != null) {
                    SpannableString sb = (SpannableString) view.getText();
                    view.getLayout().getHeight();
                    view.setText(sb);
                    LayoutParams lp = view.getLayoutParams();
                    lp.height = view.getLayout().getHeight();
                    view.requestLayout();
                }
            }
        }.execute(new Void[]{});
    }

    public static Spanned fromHtml(String source, TagHandler handler) {
        return Html.fromHtml(source, new ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                Drawable d = new BitmapDrawable();
                return d;
            }
        }, handler);
    }
}
