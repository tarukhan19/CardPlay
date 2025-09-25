package com.demo.cardplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardScreen(modifier: Modifier) {
    var rowList = remember {
        mutableStateListOf<CardDTO>(
            CardDTO(id = 1, name = "king of diamonds", img = R.drawable.kingofdiamonds),
            CardDTO(id = 2, name = "Spades 3", img = R.drawable.spades3),
            CardDTO(id = 3, name = "Diamonds 10", img = R.drawable.diamonds10),
            CardDTO(id = 4, name = "Jake of Spades", img = R.drawable.jackofspades),
            CardDTO(id = 5, name = "Queen of hearts", img = R.drawable.queenofhearts),
            CardDTO(id = 6, name = "Hearts 4", img = R.drawable.hearts4)
        )
    }

    var gridList = remember {
        mutableStateListOf<CardDTO>(
            CardDTO(id = 1, name = "king of diamonds", img = R.drawable.kingofdiamonds),
            CardDTO(id = 2, name = "Spades 3", img = R.drawable.spades3),
            CardDTO(id = 3, name = "Diamonds 10", img = R.drawable.diamonds10),
            CardDTO(id = 4, name = "Jake of Spades", img = R.drawable.jackofspades),
            CardDTO(id = 5, name = "Queen of hearts", img = R.drawable.queenofhearts),
            CardDTO(id = 6, name = "Hearts 4", img = R.drawable.hearts4)
        )
    }

    var gridSelectIndex by remember { mutableIntStateOf(-1) } // -1 for no selection
    var rowSelectIndex by remember { mutableIntStateOf(-1) } // -1 for no selection

    fun swapCards() {
        if (gridSelectIndex == -1 || rowSelectIndex == -1) return

        var temp = gridList[gridSelectIndex]
        gridList[gridSelectIndex] = rowList[rowSelectIndex]
        rowList[rowSelectIndex] = temp

        // Reset selections after swap
        gridSelectIndex = -1
        rowSelectIndex = -1

    }

    Scaffold(modifier = modifier) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(gridList) { index, card ->
                    val cardColor = if (index == gridSelectIndex) Color.LightGray else Color.White
                    CardDesign(card, index, cardColor) { cardIndex ->
                        if (gridSelectIndex == -1) {
                            gridSelectIndex = cardIndex
                        }
                    }
                }
            }

            HorizontalDivider(
                Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .background(Color.DarkGray)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                itemsIndexed(rowList) { index, card ->
                    var color = if (index == rowSelectIndex) Color.LightGray else Color.White
                    CardDesign(card, index, color) {
                        if (rowSelectIndex == -1) {
                            rowSelectIndex = index
                            swapCards()
                        }
                    }
                }
            }

            Button(
                onClick = {
                    rowSelectIndex = -1
                    gridSelectIndex = -1
                    gridList.shuffle()
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Shuffle",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Normal,
                )
            }
        }
    }
}

@Composable
fun CardDesign(
    card: CardDTO,
    index: Int,
    cardColor: Color = Color.White,
    onItemClick: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(150.dp)
            .clickable { onItemClick(index) },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = CardDefaults.elevatedShape,

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,        // centers vertically
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(card.img),
                contentDescription = card.name,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        }
    }
}