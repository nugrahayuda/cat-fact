package jp.speakbuddy.edisonandroidexercise.ui

import jp.speakbuddy.edisonandroidexercise.ui.fact.FactViewModel
import org.junit.Test

class FactViewModelTest {


    @Test
    fun updateFact() {
        var loading = true
        val initialFact = "initial"
        var fact = initialFact


        assert(!loading)
        assert(fact != initialFact)
    }
}
