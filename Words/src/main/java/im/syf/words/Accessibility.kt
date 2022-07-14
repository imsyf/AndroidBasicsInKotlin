package im.syf.words

import android.os.Build
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi

// Setup custom accessibility delegate to set the text read with
// an accessibility service
object Accessibility : View.AccessibilityDelegate() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onInitializeAccessibilityNodeInfo(
        host: View?,
        info: AccessibilityNodeInfo?
    ) {
        super.onInitializeAccessibilityNodeInfo(host, info)
        // With `null` as the second argument to [AccessibilityAction], the
        // accessibility service announces "double tap to activate".
        // If a custom string is provided,
        // it announces "double tap to <custom string>".
        val customString = host?.context?.getString(R.string.look_up_words)
        val customClick =
            AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK,
                customString
            )
        info?.addAction(customClick)
    }
}
