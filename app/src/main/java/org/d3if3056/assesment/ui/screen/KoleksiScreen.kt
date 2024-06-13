package org.d3if3056.assesment.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.d3if3056.assesment.R
import org.d3if3056.assesment.model.Skincare
import org.d3if3056.assesment.network.SkincareApi
import org.d3if3056.assesment.ui.theme.AssesmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KoleksiScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.page2),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier){
    val viewModel: KoleksiViewModel = viewModel()
    val data by viewModel.data

    LazyVerticalGrid (
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        columns = GridCells.Fixed(2)
    ){
        items(data) { ListItem(skincare = it)}
    }
}

@Composable
fun ListItem(skincare: Skincare){
    Box(
        modifier = Modifier.padding(4.dp).border(1.dp, Color.Gray)
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(SkincareApi.getSkincareUrl(skincare.imageId))
                .crossfade(true)
                .build(), 
            contentDescription = stringResource(id = R.string.gambar, skincare.nama),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KoleksiScreenPreview() {
    AssesmentTheme {
        KoleksiScreen(rememberNavController())
    }
}
