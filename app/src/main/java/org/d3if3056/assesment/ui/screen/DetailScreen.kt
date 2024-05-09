package org.d3if3056.assesment.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3056.assesment.R
import org.d3if3056.assesment.database.JurnalDb
import org.d3if3056.assesment.ui.theme.AssesmentTheme
import org.d3if3056.assesment.util.ViewModelFactory

const val KEY_ID_JURNAL = "idJurnal"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val db = JurnalDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var kondisi_kulit by remember { mutableStateOf("") }
    var rutinitas by remember { mutableStateOf("") }
    var moods by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }
    var extraSteps by remember { mutableStateOf("") }
    var selectedMoodIndex by remember { mutableIntStateOf(0) }

    var showDialog by remember { mutableStateOf(false) }

    val rutinitasOptions = listOf(
        stringResource(R.string.pagi),
        stringResource(R.string.malam)
    )

    val moodsOptions = listOf(
        stringResource(R.string.luar_biasa),
        stringResource(R.string.baik),
        stringResource(R.string.dapat_diterima),
        stringResource(R.string.buruk),
        stringResource(R.string.jelek),
    )

    val stepsOption = listOf(
        stringResource(R.string.cleanser),
        stringResource(R.string.toner),
        stringResource(R.string.spot_treatment),
        stringResource(R.string.serums),
        stringResource(R.string.eye_cream),
        stringResource(R.string.moisturizer),
        stringResource(R.string.face_oil),
        stringResource(R.string.sunscreen)
    )

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getJurnal(id) ?: return@LaunchedEffect
        kondisi_kulit = data.kondisi_kulit
        rutinitas = data.rutinitas
        moods = data.moods
        notes = data.notes
        steps = data.steps
        extraSteps = data.extra_steps
        val moodIndex = moodsOptions.indexOf(data.moods)
        selectedMoodIndex = if (moodIndex != -1) moodIndex else 0

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    if (id == null)
                        Text(
                            text = stringResource(id = R.string.tambah_jurnal),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    else
                        Text(
                            text = stringResource(id = R.string.edit_jurnal),
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
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (kondisi_kulit == "" || rutinitas == "") {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (id == null) {
                            viewModel.insert(
                                kondisi_kulit, rutinitas, moods, notes, steps, extraSteps
                            )
                        } else {
                            viewModel.update(
                                id, kondisi_kulit, rutinitas, moods, notes, steps, extraSteps
                            )
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null){
                        DeleteAction {
                            showDialog = true
                        }
                        DisplayAlertDialog(
                            openDialog = showDialog,
                            onDismissRequest = { showDialog = false }
                        ) {
                            showDialog = false
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(2.dp)
        ) {
            item {
                FormCatatan(
                    title = kondisi_kulit,
                    onTitleChange = { kondisi_kulit = it },
                    rutinitas = rutinitas,
                    onRutinitasChange = { rutinitas = it },
                    onMoodsChange = { moods = it },
                    notes = notes,
                    onNotesChange = { notes = it },
                    extraSteps = extraSteps,
                    onExtraStepsChange = { extraSteps = it },
                    rutinitasOptions = rutinitasOptions,
                    moodsOptions = moodsOptions,
                    selectedMoodIndex = selectedMoodIndex,
                    onSelectedMoodIndexChange = { selectedMoodIndex = it },
                    stepsOptions = stepsOption,
                    selectedSteps = steps.split(","),
                    onSelectedStepsChange = { steps = it.joinToString(",") },
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCatatan(
    title: String, onTitleChange: (String) -> Unit,
    rutinitas: String, onRutinitasChange: (String) -> Unit,
    onMoodsChange: (String) -> Unit,
    notes: String, onNotesChange: (String) -> Unit,
    extraSteps: String, onExtraStepsChange: (String) -> Unit,
    rutinitasOptions: List<String>,
    moodsOptions: List<String>,
    selectedMoodIndex: Int, onSelectedMoodIndexChange: (Int) -> Unit,
    stepsOptions: List<String>,
    selectedSteps: List<String>, onSelectedStepsChange: (List<String>) -> Unit,
    modifier: Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(id = R.string.judul_kondisi)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.opsi_rutinitas),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(76.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rutinitasOptions.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = rutinitas == option,
                                onClick = { onRutinitasChange(option) }
                            )
                            Text(
                                text = option,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = moodsOptions[selectedMoodIndex],
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.opsi_moods)) },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                moodsOptions.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                modifier = Modifier.padding(start = 4.dp),
                                fontWeight = if (index == selectedMoodIndex) FontWeight.Bold else null,
                            )
                        },
                        onClick = {
                            onMoodsChange(item)
                            onSelectedMoodIndexChange(index)
                            expanded = false
                        }
                    )
                }
            }
        }
        OutlinedTextField(
            value = notes,
            onValueChange = { onNotesChange(it) },
            label = { Text(text = stringResource(id = R.string.notes)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.steps),
                    textAlign = TextAlign.Justify
                )
                stepsOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = selectedSteps.contains(option),
                            onCheckedChange = { isChecked ->
                                val newSteps = if (isChecked) {
                                    selectedSteps + option
                                } else {
                                    selectedSteps - option
                                }
                                onSelectedStepsChange(newSteps)
//                                if (isChecked) {
//                                    setSelectedSteps(selectedSteps + option)
//                                } else {
//                                    setSelectedSteps(selectedSteps - option)
//                                }
                            },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Text(text = option)
                    }
                }
            }
        }
        OutlinedTextField(
            value = extraSteps,
            onValueChange = { onExtraStepsChange(it) },
            label = { Text(text = stringResource(id = R.string.extra_steps)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DeleteAction(delete: () -> Unit){
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.reset))
                       },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    AssesmentTheme {
        DetailScreen(rememberNavController())
    }
}