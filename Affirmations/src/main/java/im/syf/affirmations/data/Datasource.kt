package im.syf.affirmations.data

import im.syf.affirmations.R
import im.syf.affirmations.model.Affirmation

object Datasource {

    fun loadAffirmations(): List<Affirmation> = listOf(
        Affirmation(R.string.affirmation1),
        Affirmation(R.string.affirmation2),
        Affirmation(R.string.affirmation3),
        Affirmation(R.string.affirmation4),
        Affirmation(R.string.affirmation5),
        Affirmation(R.string.affirmation6),
        Affirmation(R.string.affirmation7),
        Affirmation(R.string.affirmation8),
        Affirmation(R.string.affirmation9),
        Affirmation(R.string.affirmation10),
    )
}
