package com.itlifelang.m3expressivesample.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Snooze
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.MarkEmailUnread
import androidx.compose.ui.graphics.vector.ImageVector

val actions: Map<String, ImageVector> = mapOf(
    "Attachment" to Icons.Filled.Attachment,
    "Edit" to Icons.Filled.Edit,
    "Star" to Icons.Filled.Star,
    "Snooze" to Icons.Filled.Snooze,
    "MarkEmailUnread" to Icons.Outlined.MarkEmailUnread
)
