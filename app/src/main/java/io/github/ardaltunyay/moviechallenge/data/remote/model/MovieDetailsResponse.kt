package io.github.ardaltunyay.moviechallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("belongs_to_collection")
    val belongs_to_collection: BelongsToCollection?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imdb_id")
    val imdb_id: String?,
    @SerializedName("original_language")
    val original_language: String?,
    @SerializedName("original_title")
    val original_title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("production_companies")
    val production_companies: List<ProductionCompany>?,
    @SerializedName("production_countries")
    val production_countries: List<ProductionCountry>?,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spoken_languages: List<SpokenLanguage>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val vote_average: Double?,
    @SerializedName("vote_count")
    val vote_count: Int?
)

data class BelongsToCollection(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("backdrop_path")
    val backdrop_path: String?
)

data class Genre(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)

data class ProductionCompany(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo_path")
    val logo_path: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val origin_country: String?
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso_3166_1: String?,
    @SerializedName("name")
    val name: String?
)

data class SpokenLanguage(
    @SerializedName("english_name")
    val english_name: String?,
    @SerializedName("iso_639_1")
    val iso_639_1: String?,
    @SerializedName("name")
    val name: String?
)

