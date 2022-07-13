package im.syf.affirmations

import android.content.Context
import im.syf.affirmations.adapter.ItemAdapter
import im.syf.affirmations.model.Affirmation
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class AffirmationsAdapterTests {

    private val context = mock(Context::class.java)

    @Test
    fun adapter_size() {
        val data = listOf(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation10, R.drawable.image10),
        )

        val adapter = ItemAdapter(context, data)

        assertEquals("ItemAdapter is not the correct size", data.size, adapter.itemCount)
    }
}
