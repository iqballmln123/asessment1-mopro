package org.d3if3056.assesment.navigation

import org.d3if3056.assesment.ui.screen.KEY_ID_JURNAL

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object JenisKulit: Screen("jenisKulit")
    data object Rekomendasi: Screen("rekomendasi")
    data object Rutinitas: Screen("rutinitas")
    data object Jurnal: Screen("jurnal")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_JURNAL}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}