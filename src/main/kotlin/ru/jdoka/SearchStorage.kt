package ru.jdoka

import java.util.*
import kotlin.random.Random

class SearchStorage {
    val list = listOf("Рысь", "Картошка", "Рита", "Кабачок", "Ёж")

    fun getRandomSearch(): String {
        val index = Random(Date().time).nextInt(0, list.size)
        return list[index]
    }
}