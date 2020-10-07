package ru.korotkov.party.model

data class PartyInfo(
    val name: String,
    val bannerURL: String,
    val inviter: Inviter,
    val guests: List<Guest>
)

data class Inviter(
    val name: String,
    val avatarURL: String
)

data class Guest(
    val name: String,
    val avatarURL: String
)