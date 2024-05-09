package org.d3if3056.assesment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3056.assesment.model.Jurnal
import org.d3if3056.assesment.ui.screen.AboutScreen
import org.d3if3056.assesment.ui.screen.DetailScreen
import org.d3if3056.assesment.ui.screen.JenisKulitScreen
import org.d3if3056.assesment.ui.screen.JurnalScreen
import org.d3if3056.assesment.ui.screen.KEY_ID_JURNAL
import org.d3if3056.assesment.ui.screen.MainScreen
import org.d3if3056.assesment.ui.screen.RekomendasiScreen
import org.d3if3056.assesment.ui.screen.RutinitasScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
        composable(route = Screen.JenisKulit.route){
            JenisKulitScreen(navController)
        }
        composable(route = Screen.Rekomendasi.route){
            RekomendasiScreen(navController)
        }
        composable(route = Screen.Rutinitas.route){
            RutinitasScreen(navController)
        }
        composable(route = Screen.Jurnal.route){
            JurnalScreen(navController)
        }
        composable(route = Screen.FormBaru.route){
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_JURNAL) { type = NavType.LongType}
            )
        ){navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_JURNAL)
            DetailScreen(navController, id)
        }
    }
}