package org.d3if3056.assesment.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3056.assesment.R
import org.d3if3056.assesment.model.MainImage
import org.d3if3056.assesment.navigation.Screen
import org.d3if3056.assesment.ui.theme.AssesmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
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
                        text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = stringResource(id = R.string.info)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        ScreenContent(navController,Modifier.padding(padding))
    }
}

@Composable
fun getDataMain(): List<MainImage>{
    return listOf(
        MainImage(stringResource(id = R.string.image_jenis_kulit), R.drawable.jenis_kulit),
        MainImage(stringResource(id = R.string.image_product_skincare), R.drawable.product_skincare),
        MainImage(stringResource(id = R.string.image_skincare_routine), R.drawable.skincare_routine)
    )
}

@Composable
fun ScreenContent(navController: NavHostController,modifier: Modifier) {
    val data = getDataMain()
    val configuration = LocalConfiguration.current
    val firstTwoImages = data.take(2)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(
            text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineBreak = LineBreak.Paragraph,
                hyphens = Hyphens.Auto
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        LazyRow(
            modifier = Modifier.padding(top = 12.dp)
        ) {
            items(firstTwoImages) {image ->
                ButtonComponent(image, navController)
            }
        }
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ButtonComponent(data[2], navController)
            }
        }
    }
}

@Composable
fun ButtonComponent(mainImage: MainImage, navController: NavHostController){
    OutlinedButton(
        onClick = {
                  when(mainImage){
                      is MainImage -> {
                          when (mainImage.imageResId){
                              R.drawable.jenis_kulit -> navController.navigate(Screen.JenisKulit.route)
                          }
                      }
                  }
        },
        modifier = Modifier
            .height(170.dp)
            .aspectRatio(1f)
            .padding(4.dp),
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = mainImage.imageResId),
                contentDescription = mainImage.nama,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(105.dp)
            )
            Text(
                text = mainImage.nama,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    AssesmentTheme {
        MainScreen(rememberNavController())
    }
}