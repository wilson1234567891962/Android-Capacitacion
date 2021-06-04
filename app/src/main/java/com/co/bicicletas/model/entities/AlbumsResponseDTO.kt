package com.co.bicicletas.model.entities

class AlbumsResponseDTO : ArrayList<AlbumsResponseDTOItem>()

data class AlbumsResponseDTOItem(
    val comments: List<Any>,
    val cover: String,
    val description: String,
    val genre: String,
    val id: Int,
    val name: String,
    val performers: List<Performer>,
    val recordLabel: String,
    val releaseDate: String,
    val tracks: List<Any>
)

data class Performer(
    val creationDate: String,
    val description: String,
    val id: Int,
    val image: String,
    val name: String
)