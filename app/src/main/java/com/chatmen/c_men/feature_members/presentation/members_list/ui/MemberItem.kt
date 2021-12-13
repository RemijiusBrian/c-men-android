package com.chatmen.c_men.feature_members.presentation.members_list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.chatmen.c_men.R
import com.chatmen.c_men.core.presentation.ui.theme.CMenTheme
import com.chatmen.c_men.core.presentation.ui.theme.PaddingMedium
import com.chatmen.c_men.core.presentation.ui.theme.PaddingSmall
import com.chatmen.c_men.core.presentation.ui.theme.SpaceSmall
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder

@ExperimentalMaterialApi
@Composable
fun MemberItem(
    modifier: Modifier = Modifier,
    name: String,
    bio: String?,
    profilePictureUrl: String?,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = PaddingSmall)
            .padding(top = PaddingSmall)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(PaddingMedium)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = profilePictureUrl,
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                        placeholder(R.drawable.ic_downloading)
                        error(R.drawable.ic_person)
                    },
                ),
                contentDescription = name,
                modifier = Modifier
                    .size(32.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = CircleShape
                    )
                    .placeholder(
                        visible = true,
                        shape = CircleShape,
                        color = MaterialTheme.colors.error,
                        highlight = PlaceholderHighlight.fade()
                    )
            )
            Spacer(modifier = Modifier.width(SpaceSmall))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                bio?.let { bio ->
                    Text(
                        text = bio,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun PreviewMemberItem() {
    CMenTheme {
        MemberItem(
            modifier = Modifier.fillMaxWidth(),
            name = "Ridill",
            bio = "Ridill Bio",
            profilePictureUrl = "",
            onClick = {}
        )
    }
}