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
import androidx.compose.material.icons.filled.Warning
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
                        text = stringResource(id = R.string.page1),
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
    var namaError by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    var hasilAnalisis by remember { mutableStateOf("") }
    var showAnalysisResult by remember { mutableStateOf(false) }
    var jenisKulitError by remember { mutableStateOf(false) }

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
            isError = namaError,
            supportingText = { ErrorHint(isError = namaError) },
            trailingIcon = { IconPicker(isError = namaError, unit = "") },
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
                isError = jenisKulitError,
                supportingText = { ErrorHint(isError = jenisKulitError) },
                readOnly = true,
                trailingIcon = {
                    if (jenisKulitError) {
                        IconPicker(isError = true, unit = "")
                    } else {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    nama = ""
                    selectedItemIndex = 0
                    checked.fill(false)
                    hasilAnalisis = ""
                    showAnalysisResult = false
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.reset))
            }
            Button(
                onClick = {
                    namaError = (nama == "")
                    jenisKulitError = (selectedItemIndex == 0)
                    if (namaError || jenisKulitError) return@Button

                    showAnalysisResult = true
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.cek_hasil))
            }
        }
        if (showAnalysisResult) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.header_hasil),
                style = MaterialTheme.typography.titleLarge
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.sapaan, nama),
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = stringResource(id = R.string.hasil_analisis, hasilAnalisis),
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = stringResource(id = R.string.jenis_kulit_label) + jenisKulit[selectedItemIndex],
                    textAlign = TextAlign.Justify
                )

                if (checked.any { it }) {
                    val selectedComplications = mutableListOf<String>()
                    for ((index, isSelected) in checked.withIndex()) {
                        if (isSelected) {
                            selectedComplications.add(komplikasiKulit[index])
                        }
                    }
                    val finalText = stringResource(
                        id = R.string.komplikasi_label,
                        komplikasiKulit.joinToString(", "),
                        selectedComplications.joinToString(", ")
                    )
                    Row {
                        Text(text = finalText)
                        Text(text = selectedComplications.joinToString(", "))
                    }
                }

                Text(
                    text = stringResource(id = R.string.rekomendasi_jenis_kulit),
                    textAlign = TextAlign.Justify
                )
                when (jenisKulit[selectedItemIndex]) {
                    stringResource(id = R.string.kulit_normal) -> Text(text = stringResource(id = R.string.rekomendasi_kulit_normal))
                    stringResource(id = R.string.kulit_kering) -> Text(text = stringResource(id = R.string.rekomendasi_kulit_kering))
                    stringResource(id = R.string.kulit_berminyak) -> Text(text = stringResource(id = R.string.rekomendasi_kulit_berminyak))
                    stringResource(id = R.string.kulit_kombinasi) -> Text(text = stringResource(id = R.string.rekomendasi_kulit_kombinasi))
                }

                Text(
                    text = stringResource(id = R.string.hindari_jenis_kulit),
                    textAlign = TextAlign.Justify
                )
                when (jenisKulit[selectedItemIndex]) {
                    stringResource(id = R.string.kulit_normal) -> Text(text = stringResource(id = R.string.hindari_kulit_normal))
                    stringResource(id = R.string.kulit_kering) -> Text(text = stringResource(id = R.string.hindari_kulit_kering))
                    stringResource(id = R.string.kulit_berminyak) -> Text(text = stringResource(id = R.string.hindari_kulit_berminyak))
                    stringResource(id = R.string.kulit_kombinasi) -> Text(text = stringResource(id = R.string.hindari_kulit_kombinasi))
                }

                for ((index, complication) in komplikasiKulit.withIndex()) {
                    if (checked[index]) {
                        Text(
                            text = stringResource(id = R.string.rekomendasi_komplikasi),
                            textAlign = TextAlign.Justify
                        )
                        when (complication) {
                            stringResource(id = R.string.komedo_putih) -> {
                                Text(text = stringResource(id = R.string.rekomendasi_komplikasi_komedo_putih))
                            }

                            stringResource(id = R.string.komedo_hitam) -> {
                                Text(text = stringResource(id = R.string.rekomendasi_komplikasi_komedo_hitam))
                            }

                            stringResource(id = R.string.jerawat) -> {
                                Text(text = stringResource(id = R.string.rekomendasi_komplikasi_jerawat))
                            }

                            stringResource(id = R.string.keriput) -> {
                                Text(text = stringResource(id = R.string.rekomendasi_komplikasi_keriput))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(id = R.string.input_invalid))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun JenisKulitScreenPreview() {
    AssesmentTheme {
        JenisKulitScreen(rememberNavController())
    }
}