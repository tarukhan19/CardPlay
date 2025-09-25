package com.demo.cardplay

data class CardDTO(
    val id: Int,
    val img: Int,
    val name: String,
    var isSelected : Boolean = false)