package yalexaner.konturtest.network.models

data class Contact(
    val id: String,
    val name: String,
    val phone: String,
    val height: Float,
    val biography: String,
    val temperature: String,
    val educationPeriod: EducationPeriod
) {

    data class EducationPeriod(
        val start: String,
        val end: String
    )
}