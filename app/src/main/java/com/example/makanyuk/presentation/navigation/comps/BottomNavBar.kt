package com.example.makanyuk.presentation.navigation.comps

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.makanyuk.R
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.ui.theme.StarterProjectTheme

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        BottomNavItem(R.drawable.ic_home, "Home"),
        BottomNavItem(R.drawable.ic_saved,"Saved"),
        BottomNavItem(R.drawable.ic_notif,"Notification"),
        BottomNavItem(R.drawable.ic_profile,"Profile")
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 12.dp,
        contentColor = Color.White,

    ){
        items.forEachIndexed { index, item ->
            NavigationBarItem(
               icon = {
                   Icon(painterResource(item.icon), contentDescription = item.name)
               },
                onClick = {
                    onItemSelected(index)
                },
                selected = index == selectedItem,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary100,
                    unselectedIconColor = Gray4,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
    
}

data class BottomNavItem(
    @DrawableRes val icon : Int,
    val name : String
)

@Preview
@Composable
private fun test() {
    StarterProjectTheme {
        BottomNavBar(selectedItem = 1) { }
    }

}