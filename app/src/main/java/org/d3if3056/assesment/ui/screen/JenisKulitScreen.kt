package org.d3if3056.assesment.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3056.assesment.R
import org.d3if3056.assesment.ui.theme.AssesmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JenisKulitScreen(navController: NavHostController) {
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
        JenisContent(Modifier.padding(padding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JenisContent(modifier: Modifier) {
    var nama by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    var hasilAnalisis by remember { mutableStateOf("") }

    val jenisKulit = listOf(
        stringResource(id = R.string.jenis_kulit),
        stringResource(id = R.string.kulit_normal),
        stringResource(id = R.string.kulit_kering),
        stringResource(id = R.string.kulit_berminyak),
        stringResource(id = R.string.kulit_kombinasi)
    )
    val komplikasiKulit = listOf(
        stringResource(id = R.string.komedo_putih),
        stringResource(id = R.string.komedo_hitam),
        stringResource(id = R.string.jerawat),
        stringResource(id = R.string.keriput)
    )
    val checked = remember {
        mutableStateListOf<Boolean>().apply {
            repeat(komplikasiKulit.size) {
                add(false)
            }
        }
    }

    if (selectedItemIndex == -1) {
        selectedItemIndex = jenisKulit.indexOf(stringResource(id = R.string.jenis_kulit))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.intro_jenis_kulit),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineBreak = LineBreak.Paragraph,
                hyphens = Hyphens.Auto
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = stringResource(id = R.string.nama)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = jenisKulit[selectedItemIndex],
                onValueChange = { },
                label = { Text(text = stringResource(id = R.string.jenis_kulit)) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                jenisKulit.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                fontWeight = if (index == selectedItemIndex)
                                    FontWeight.Bold else null
                            )
                        },
                        onClick = {
                            selectedItemIndex = index
                            expanded = false
                        }
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.komplikasi_kulit),
                    textAlign = TextAlign.Justify
                )
                komplikasiKulit.forEachIndexed { index, stringResId ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = checked[index],
                            onCheckedChange = { checked[index] = it },
                            modifier = Modifier.alignByBaseline()
                        )
                        Text(
                            text = stringResId,
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }
                }
            }
        }
        Button(
            onClick = {
                      cekHasil(nama, jenisKulit[selectedItemIndex],komplikasiKulit,checked){
                          hasil -> hasilAnalisis = hasil
                      }
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.cek_hasil))
        }
        if (hasilAnalisis.isNotEmpty()){
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.header_hasil),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = hasilAnalisis,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }
}

private fun cekHasil(nama: String, jenisKulit: String, komplikasiKulit: List<String>, checked: List<Boolean>, onHasilAnalisisReady: (String) -> Unit){
    val komplikasiTerpilih = mutableListOf<String>()

    for (i in komplikasiKulit.indices) {
        if (checked[i]) {
            komplikasiTerpilih.add(komplikasiKulit[i])
        }
    }

    val hasilAnalisis = buildString {
        appendLine("Haii $nama setelah dianalisa dengan info yang kamu berikan dibawah ini:")
        appendLine("Jenis Kulit : $jenisKulit")

        // Lakukan logika untuk menambahkan rekomendasi bahan skincare
        appendLine("Rekomendasi bahan skincare sesuai jenis kulit:")
        appendLine("Bahan yang cocok untuk jenis kulit $jenisKulit")

        // Lakukan logika untuk menambahkan peringatan bahan skincare
        appendLine("Hindari bahan skincare sesuai jenis kulit:")
        appendLine("Bahan yang sebaiknya dihindari untuk jenis kulit $jenisKulit")

        // Lakukan logika untuk menambahkan rekomendasi bahan skincare sesuai komplikasi kulit
        if (komplikasiTerpilih.isNotEmpty()) {
            appendLine("Komplikasi : ${komplikasiTerpilih.joinToString()}")
            appendLine("Rekomendasi bahan skincare sesuai komplikasi kulit:")
            appendLine("Bahan yang cocok untuk mengatasi ${komplikasiTerpilih.joinToString()}")
        }
    }
    onHasilAnalisisReady(hasilAnalisis)
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun JenisKulitScreenPreview() {
    AssesmentTheme {
        JenisKulitScreen(rememberNavController())
    }
}