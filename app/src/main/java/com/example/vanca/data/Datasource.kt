package com.example.vanca.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.vanca.R
import com.example.vanca.model.News
import com.example.vanca.model.Station
import com.example.vanca.model.TeamMember
import com.example.vanca.model.User
import java.time.LocalDate
import kotlin.random.Random


class Datasource {
    private val allFeatures = listOf(
        "4Wheels™ Smart Bracelet bracelet",
        "Accessible restrooms",
        "Elevator access (updates in realtime)",
        "Ramps / Wheelchair accessibility",
        "Support Staff" ,
        "Audio announcements",
    )

    private val allStations = loadAllStations()

    private fun loadAvailableFeatures(availability: List<Boolean>): Map<String, Boolean> {
        val stationFeatures: MutableMap<String, Boolean> = emptyMap<String, Boolean>().toMutableMap()
        for (i in allFeatures.indices) {
            stationFeatures[allFeatures[i]] = availability[i]
        }
        return stationFeatures
    }
    fun loadAllUsers(): List<User> {
        return listOf(
            User(
                id = 0,
                firstName = "",
                lastName = "",
                username = "test",
                password = "test1234",
                email = ""
            ),

            User(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                username = "johndoe",
                password = "123456",
                email = "john.doe@example.com"
            ),
            User(
                id = 2,
                firstName = "Jane",
                lastName = "Smith",
                username = "janesmith",
                password = "123456",
                email = "jane.smith@example.com"
            ),
            User(
                id = 3,
                firstName = "Alice",
                lastName = "Johnson",
                username = "alicejohnson",
                password = "123456",
                email = "alice.johnson@example.com"
            ),
            User(
                id = 4,
                firstName = "Bob",
                lastName = "Brown",
                username = "bobbrown",
                password = "123456",
                email = "bob.brown@example.com"
            ),
            User(
                id = 5,
                firstName = "Charlie",
                lastName = "Davis",
                username = "charliedavis",
                password = "123456",
                email = "charlie.davis@example.com"
            ),
            User(
                id = 6,
                firstName = "Fulano",
                lastName = "de Tal",
                username = "fulanodetal",
                password = "123456",
                email = "fulano.de.tal@example.com"
            ),

        )
    }
    fun loadAllStations(): List<Station> {
        return listOf(
            Station(9, "Campanha", "Campanhã Station, situated in the eastern part of Porto, Portugal, is one of the city's main railway hubs. Opened in 1877, it serves as a crucial junction for both local and long-distance train services, including high-speed Alfa Pendular and international routes. Unlike the more ornate São Bento Station, Campanhã is known for its functional design and modern facilities, catering to a high volume of passengers daily. The station's strategic location provides easy access to various parts of Porto and connects seamlessly with the city's metro and bus networks, making it an essential gateway for travelers heading to and from the city.", loadAvailableFeatures(listOf(true, true, false, true, true, true)), R.drawable.station2),
            //Station(3, "Vila Nova de Gaia", "Vila Nova de Gaia Station, located in the suburb of Vila Nova de Gaia across the Douro River from Porto, Portugal, is a key transport hub for the region. The station serves as an important stop for both local and regional train services, facilitating easy access to the bustling heart of Porto and the surrounding areas. Known for its convenient connections and efficient service, Vila Nova de Gaia Station plays a vital role in the daily commute of residents and visitors alike. The area around the station is famous for its port wine cellars, offering travelers a unique glimpse into the rich cultural heritage of the region. With its blend of modern amenities and strategic location, Vila Nova de Gaia Station ensures a seamless travel experience for all passengers.", loadAvailableFeatures(listOf(true,false,true,true,false,false,true,false,true,true)), R.drawable.station3),
            Station(1, "Bolhão", "Bolhão Station, located in the heart of Porto near the famous Bolhão Market, is a vibrant and bustling metro stop. The station provides easy access to one of the city's most iconic shopping destinations and several historic sites. Its central location and modern facilities make it a convenient choice for both daily commuters and tourists exploring the cultural and commercial heart of Porto.", loadAvailableFeatures(listOf(true, true, false, true, true, true,))),
            Station(2, "Marquês", "Marquês Station is situated in the residential area of Marquês, providing vital connectivity for locals and visitors. This metro stop offers access to several parks, shops, and cafes, making it a pleasant and practical station for everyday travel. Its strategic position ensures efficient transit to various parts of the city, contributing to the ease of commuting for Porto residents.", loadAvailableFeatures(listOf(true, true, false, true, true, true,))),
            Station(3, "Campo 24 de Agosto", "Campo 24 de Agosto Station, named after the nearby historical square, is a key metro stop in Porto. It offers access to several important cultural and commercial areas, including theaters, restaurants, and shops. The station's modern amenities and strategic location make it an essential part of the metro network, providing efficient transport options for both residents and visitors exploring Porto's rich heritage.", loadAvailableFeatures(listOf(true, true, false, true, true, true,))),
            Station(4, "Trindade", "Trindade Station, located in the center of Porto, is a pivotal interchange in the city's metro network. Serving as a hub for several metro lines, it provides seamless connectivity across Porto and to neighboring areas. The station is modern and well-equipped, offering easy access to major attractions, shopping areas, and business districts. Its strategic position and efficient service make it a popular choice for both commuters and tourists.", loadAvailableFeatures(listOf(true, true, false, true, true, true,))),
            Station(5, "Casa da Música", "Casa da Música Station, named after the nearby iconic concert hall, is one of Porto's most frequented metro stations. Situated in the Boavista area, it connects various parts of the city through multiple metro lines. The station is known for its modern design and proximity to cultural landmarks, including the Casa da Música concert hall and several art galleries. It's a key stop for those attending events or exploring the vibrant cultural scene of Porto.", loadAvailableFeatures(listOf(true, true, false, true, true, true,))),
            Station(6, "São João Hospital", "São João Hospital Station is located near one of Porto's largest medical complexes, the São João Hospital. This metro station is crucial for healthcare professionals, patients, and visitors, providing direct and convenient access to the hospital. Its location also makes it a vital link for the surrounding residential areas and educational institutions, ensuring efficient transport for daily commuters.", loadAvailableFeatures(listOf(true, true, false, true, true, true,))),
            Station(7, "Estádio do Dragão", "Estádio do Dragão Station, situated near the famous FC Porto football stadium, is a bustling metro stop, especially on match days. This station serves both fans heading to the stadium and regular commuters. Its proximity to the stadium, shopping centers, and residential areas makes it a key point in the metro network. The station's modern facilities and efficient service enhance the travel experience for all passengers.", loadAvailableFeatures(listOf(true, true, false, true, true, true,))),
            Station(8, "Aliados", "Aliados Station, located along the grand Avenida dos Aliados, is one of Porto's central metro stops. The station is surrounded by notable landmarks, including the City Hall and numerous historic buildings. Its central position makes it an ideal starting point for exploring Porto's cultural and historical sites. With its convenient location and accessibility, Aliados Station is a favorite among tourists and locals alike.", loadAvailableFeatures(listOf(true, true, false, true, true, true,))),

        )
    }

    fun loadBookmarkedStations(userId: Int): List<Station> {

        return when (userId) {
            1 ->
                allStations

            2 ->
                listOf(
                    allStations[0],
                    allStations[1],
                    allStations[2],
                )

            3 ->
                listOf(
                    allStations[2],
                )

            else ->
                emptyList()
        }

    }

    fun loadRecentStations(userId: Int): List<Station> {

        return when (userId) {
            1 ->
                listOf(
                    allStations[2],
                )

            2 ->
                listOf(
                    allStations[0],
                    allStations[1],
                )

            6 ->
                listOf(
                    allStations[0],
                )

            else ->
                allStations
        }

    }

    fun findStationWithId(stationId: Int): Station? {
        for (station in allStations) {
            if (station.id == stationId) return station
        }

        return null
    }

    fun findStationNameWithId(stationId: Int): String {
        for (station in allStations) {
            if (station.id == stationId) return station.stationName
        }

        return "-"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNews(): List<News> {
        return listOf(
            News(
                id = 1,
                title = "Elevator system malfunction",
                description = "An electrical malfunction has rendered the elevator system at Campanha station temporarily out of order. Service crews are already working on restoring functionality. Passengers affected are kindly requested to modify their commute and/or contact our staff for assistance",
                imageResourceId = R.drawable.news1,
                date = LocalDate.parse("2024-05-29"),
                relatedStationId = 9,
                relatedStationName = "Campanha"
            ),
            /*News(
                id = 2,
                title = "New Policy to Improve Public Transport for Disabled Citizens",
                description = "In a significant move, the government has unveiled a comprehensive policy designed to enhance public transportation for disabled citizens. The new policy includes substantial funding for upgrading infrastructure to be more accessible, such as installing ramps, elevators, and tactile paving at all major transit hubs. Additionally, there will be increased training for staff to better assist disabled passengers and ensure their safety and comfort. The policy also mandates that all new public transport vehicles be fully accessible. This initiative has been praised by disability rights organizations, who have long advocated for such changes to ensure equal access to public services. The government has committed to implementing these changes within the next five years, marking a significant step forward in the fight for inclusivity and accessibility.",
                imageResourceId = R.drawable.news2,
                date = LocalDate.parse("2024-05-23"),
            )*/

        )
    }

    fun loadTeamMembers(): List<TeamMember> {
        return listOf(
            //TeamMember("Rita Soares\n", "Management & Marketing",R.drawable.rita),
            TeamMember("Rita Cerqueira da Silva Soares\n", "Management & Marketing",R.drawable.rita),
            //TeamMember("João Garcez", "Management & Marketing", R.drawable.joao),
            TeamMember("João Guilherme Jansen Verdades Brecha de Carvalho Garcez", "Management & Marketing", R.drawable.joao),
            TeamMember("Iosif Arvanitis", "Software Engineer", R.drawable.iosif),
            TeamMember("Tendai Mazaiwana", "Management & Marketing", R.drawable.tendai),
            TeamMember("Darko Kolarić", "IoT Engineer", R.drawable.darko),
            TeamMember("Jovana Tovladijac", "IoT Engineer", R.drawable.jovana),
            TeamMember("Lefteris Gkliatis", "Software Engineer", R.drawable.lefteris),
        )
    }
}