package id.hidayatasep.bmitrackerv2.android.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.hidayatasep.bmitrackerv2.data.datamodel.UserGrowth
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryView(viewModel: HistoryViewModel = koinViewModel()) {

    val userGrowthList by viewModel.userGrowthList.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        viewModel.getUserGrowthList()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "User Growth List", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))
        // LazyColumn to show the list of user growth items
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(userGrowthList) { growth ->
                UserGrowthItem(userGrowth = growth)
            }
        }
    }
}

@Composable
fun UserGrowthItem(userGrowth: UserGrowth) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Height: ${userGrowth.height} cm, Weight: ${userGrowth.weight} kg",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Date: $userGrowth.date",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}