package com.github.zzwwws.rxzhihudaily.common.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ImageSpan;


/**
 * Created by Vincent on 12/7/15.
 */
public class SpanUtil {
    public static SpannableString makeSpannable(Context context, String text) {
        return makeSpannable(context, text, MoonUtil.DEF_SCALE, ImageSpan.ALIGN_BOTTOM);
    }

    public static SpannableString makeSpannable(Context context, String text, float scale, int align) {
        SpannableString spannableString;
        spannableString = MoonUtil.replaceEmoticons(context, text, scale, align);

        return spannableString;
    }
}
