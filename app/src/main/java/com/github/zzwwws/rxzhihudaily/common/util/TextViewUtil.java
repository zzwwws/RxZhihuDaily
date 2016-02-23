package com.github.zzwwws.rxzhihudaily.common.util;

import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.widget.TextView;


/**
 * Created by Vincent on 12/7/15.
 */
public class TextViewUtil {

    public static void setTextAndMakeSmallSpannable(TextView textView, String text) {
        SpannableString spannableString = SpanUtil.makeSpannable(textView.getContext(), text, MoonUtil.SMALL_SCALE, ImageSpan.ALIGN_BOTTOM);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString);
    }

    public static void setTextAndMakeSpannable(TextView textView, String text) {
        SpannableString spannableString = SpanUtil.makeSpannable(textView.getContext(), text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString);
    }

    public static void setTextAndEmojiSpannable(TextView textView, String text) {
        SpannableString spannableString = MoonUtil.replaceEmoticons(textView.getContext(), text);
        textView.setText(spannableString);
    }

    public static void setTextAndSmallEmojiSpannable(TextView textView, String text) {
        SpannableString spannableString = MoonUtil.replaceEmoticons(textView.getContext(), text, MoonUtil.SMALL_SCALE, ImageSpan.ALIGN_BOTTOM);
        textView.setText(spannableString);
    }
}
