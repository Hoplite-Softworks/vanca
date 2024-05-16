package com.example.vanca.data

import com.example.vanca.R
import com.example.vanca.model.News
import com.example.vanca.model.Station

class Datasource {
    fun loadStations(): List<Station> {
        return listOf<Station>(
            Station(1,R.string.station1, R.drawable.station1),
            Station(2, R.string.station2, R.drawable.station2),
            Station(3,R.string.station3, R.drawable.station3),
            Station(4,R.string.station1, R.drawable.station1),
            Station(5,R.string.station2, R.drawable.station2),
            Station(6,R.string.station3, R.drawable.station3),
            Station(7,R.string.station1, R.drawable.station1),
            Station(8,R.string.station2, R.drawable.station2),
            Station(9, R.string.station3, R.drawable.station3),
        )
    }

    fun loadNews(): List<News> {
        return listOf<News>(
            News(R.string.news1, R.drawable.news1),
            News(R.string.news2, R.drawable.news2),
            News(R.string.news1, R.drawable.news1),
            News(R.string.news2, R.drawable.news2),
            News(R.string.news1, R.drawable.news1),
            News(R.string.news2, R.drawable.news2),
            News(R.string.news1, R.drawable.news1),
            News(R.string.news2, R.drawable.news2),
            News(R.string.news1, R.drawable.news1),
            News(R.string.news2, R.drawable.news2),
        )
    }
}