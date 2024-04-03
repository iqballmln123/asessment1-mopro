package org.d3if3056.assesment.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3056.assesment.R
import org.d3if3056.assesment.ui.theme.AssesmentTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RekomendasiScreen(navController: NavHostController) {
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
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.intro_rekomendasi),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
            // sabun wajah
            Text(
                text = stringResource(id = R.string.sabun_muka),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painter = painterResource(id = R.drawable.facial_wash),
                contentDescription = stringResource(id = R.string.sabun_muka),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .border(1.dp, Color.Gray)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "True to Skin Matcha Oat Gentle Cleanser",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.sabun_muka_deskripsi),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
            // toner wajah
            Text(
                text = stringResource(id = R.string.toner_muka),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painter = painterResource(id = R.drawable.toner_wajah),
                contentDescription = stringResource(id = R.string.toner_muka),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .border(1.dp, Color.Gray)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Dear Klairs Supple Preparation Facial Toner",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.toner_muka_deskripsi),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
            // serum wajah
            Text(
                text = stringResource(id = R.string.serum_muka),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painter = painterResource(id = R.drawable.serum_wajah),
                contentDescription = stringResource(id = R.string.serum_muka),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .border(1.dp, Color.Gray)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Avoskin Your Skin Bae Serum Alpha Arbutin 3% + Grapeseed",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.serum_muka_deskripsi),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
            // masker wajah
            Text(
                text = stringResource(id = R.string.masker_muka),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painter = painterResource(id = R.drawable.masker_wajah),
                contentDescription = stringResource(id = R.string.masker_muka),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .border(1.dp, Color.Gray)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Whitelab Mugwort Pore Clarifying Mask",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.masker_muka_deskripsi),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RekomendasiScreenPreview() {
    AssesmentTheme {
        RekomendasiScreen(rememberNavController())
    }
}
