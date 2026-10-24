package com.github.rumboalla.apkupdater.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.rumboalla.apkupdater.ui.theme.GlassCard
import com.github.rumboalla.apkupdater.ui.theme.GlassButton
import com.github.rumboalla.apkupdater.data.model.AppModel

@Composable
fun AppsScreen(
    apps: List<AppModel>,
    onRefresh: () -> Unit,
    onAppClick: (AppModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 🔘 Tombol Refresh — Gaya Kaca
        GlassButton(
            onClick = onRefresh,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("🔍 Cari Pembaruan", style = MaterialTheme.typography.labelLarge)
        }

        // 📋 Daftar Aplikasi
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(apps) { app ->
                GlassCard(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AppListItem(
                        app = app,
                        onClick = { onAppClick(app) },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AppListItem(
    app: AppModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Isi item aplikasi — ikon, nama, versi
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = app.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = app.version,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Button(onClick = onClick, modifier = Modifier.padding(start = 8.dp)) {
            Text("Detail")
        }
    }
}
