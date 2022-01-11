package com.chatmen.c_men.feature_members.presentation.members_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.chatmen.c_men.core.presentation.components.ProfileIcon
import com.chatmen.c_men.core.presentation.ui.theme.CMenTheme
import com.chatmen.c_men.core.presentation.ui.theme.PaddingSmall
import com.chatmen.c_men.core.presentation.ui.theme.SpaceSmall

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun MemberItem(
    modifier: Modifier = Modifier,
    username: String,
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
                .padding(PaddingSmall)
        ) {
            ProfileIcon(
                iconUrl = profilePictureUrl,
                contentDescription = username,
                modifier = Modifier,
                painter = rememberImagePainter(data = profilePictureUrl)
            )
            Spacer(modifier = Modifier.width(SpaceSmall))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = username,
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

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun PreviewMemberItem() {
    CMenTheme {
        MemberItem(
            modifier = Modifier.fillMaxWidth(),
            username = "Ridill",
            bio = "Ridill Bio",
            profilePictureUrl = "http://192.168.1.5:8080/profile_pictures/south_park_avatar.png",
            onClick = {}
        )
    }
}