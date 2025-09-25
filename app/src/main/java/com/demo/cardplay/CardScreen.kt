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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
    var cardList = remember {
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

    var gridSelectedCard by remember { mutableStateOf<CardDTO?>(null) }
    var horizontalSelectedCard by remember { mutableStateOf<CardDTO?>(null) }

    fun swapCards(gridSelectedCard: CardDTO?, horizontalSelectedCard: CardDTO?) {
        if (gridSelectedCard == null || horizontalSelectedCard == null) return

        var gridIndex = gridList.indexOf(gridSelectedCard)
        var horizontalIndex = cardList.indexOf(horizontalSelectedCard)

        if (gridIndex != -1 && horizontalIndex != -1) {
            var temp = gridList[gridIndex]
            gridList[gridIndex] = cardList[horizontalIndex]
            cardList[horizontalIndex] = temp
        }

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
                items(gridList) { card ->
                    CardDesign(
                        card,
                        onClick = { selected ->
                            gridSelectedCard = selected
                        },
                    )
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
                items(cardList) { card ->
                    CardDesign(card, onClick = { selected ->
                        horizontalSelectedCard = selected
                        swapCards(gridSelectedCard, horizontalSelectedCard)

                        gridSelectedCard = null
                        horizontalSelectedCard = null
                    })
                }
            }

            Button(
                onClick = {
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
fun CardDesign(card: CardDTO, onClick: (CardDTO) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
            .height(150.dp)
            .clickable {
                onClick(card)
            },
        colors = CardDefaults.cardColors(Color.White),
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