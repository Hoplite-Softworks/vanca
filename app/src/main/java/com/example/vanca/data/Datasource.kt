package com.example.vanca.data

import com.example.vanca.R
import com.example.vanca.model.News
import com.example.vanca.model.Station
import com.example.vanca.model.User

class Datasource {

    fun loadAllUsers(): List<User> {
        return listOf(
            User(id = 1, firstName = "John", lastName = "Doe", username = "johndoe", email = "john.doe@example.com"),
            User(id = 2, firstName = "Jane", lastName = "Smith", username = "janesmith", email = "jane.smith@example.com"),
            User(id = 3, firstName = "Alice", lastName = "Johnson", username = "alicejohnson", email = "alice.johnson@example.com"),
            User(id = 4, firstName = "Bob", lastName = "Brown", username = "bobbrown", email = "bob.brown@example.com"),
            User(id = 5, firstName = "Charlie", lastName = "Davis", username = "charliedavis", email = "charlie.davis@example.com")

        )
    }

    fun loadAllStations(): List<Station> {
        return listOf(
            Station(1,R.string.station1, R.drawable.station1),
            Station(2, R.string.station2, R.drawable.station2),
            Station(3,R.string.station3, R.drawable.station3),
        )
    }

    fun loadBookmarkedStations(userId: Int): List<Station> {

        return when (userId) {
            1 ->
                listOf(
                    Station(1,R.string.station1, R.drawable.station1),
                    Station(3,R.string.station3, R.drawable.station3),
                )

            2 ->
                listOf(
                    Station(1,R.string.station1, R.drawable.station1),
                    Station(2, R.string.station2, R.drawable.station2),
                    Station(3,R.string.station3, R.drawable.station3),
                )

            3 ->
                listOf(
                    Station(3,R.string.station3, R.drawable.station3),
                )

            else ->
                emptyList()
        }

    }

    fun loadRecentStations(userId: Int): List<Station> {

        return when (userId) {
            1 ->
                listOf(
                    Station(3,R.string.station3, R.drawable.station3),
                )

            2 ->
                listOf(
                    Station(1,R.string.station1, R.drawable.station1),
                    Station(2, R.string.station2, R.drawable.station2),
                )

            else ->
                emptyList()
        }

    }

    fun loadNews(): List<News> {
        return listOf(
            News(id = 1, title = "Station Malfunction Affects Accessibility", description = "A major malfunction at the downtown station has severely impacted accessibility for disabled passengers. The elevator system, crucial for wheelchair users and those with mobility issues, broke down early this morning. As a result, many passengers were stranded on platforms or forced to seek alternative routes. The station management has issued an apology and stated that repair crews are working around the clock to fix the issue. However, it may take several days before full service is restored. Advocacy groups for disabled individuals have voiced their frustration, calling for more robust maintenance protocols and quicker response times to prevent such incidents in the future.", imageResourceId = R.drawable.news1),
            News(id = 2, title = "New Policy to Improve Public Transport for Disabled Citizens", description = "In a significant move, the government has unveiled a comprehensive policy designed to enhance public transportation for disabled citizens. The new policy includes substantial funding for upgrading infrastructure to be more accessible, such as installing ramps, elevators, and tactile paving at all major transit hubs. Additionally, there will be increased training for staff to better assist disabled passengers and ensure their safety and comfort. The policy also mandates that all new public transport vehicles be fully accessible. This initiative has been praised by disability rights organizations, who have long advocated for such changes to ensure equal access to public services. The government has committed to implementing these changes within the next five years, marking a significant step forward in the fight for inclusivity and accessibility.", imageResourceId = R.drawable.news2)

        )
    }
}