package yalexaner.konturtest.network.models

import yalexaner.konturtest.base.models.Contact

data class NetworkContact(
    val id: String,
    val name: String,
    val phone: String,
    val height: Float,
    val biography: String,
    val temperament: String,
    val educationPeriod: EducationPeriod
) : Contact {

    data class EducationPeriod(
        val start: String,
        val end: String
    )
}