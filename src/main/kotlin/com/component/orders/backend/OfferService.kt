package com.component.orders.backend
import com.component.orders.models.Offer
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.Date

@Service
class OfferService {

    private val offers = listOf(
        Offer(
            offerCode = "OFF50",
            validUntil = SimpleDateFormat("yyyy/mm/dd").parse("2024/12/31")
        ),
        Offer(
            offerCode = "SUMMER21",
            validUntil = SimpleDateFormat("yyyy/mm/dd").parse("2024/08/31")
        ),
        Offer(
            offerCode = "NEWYEAR25",
            validUntil = SimpleDateFormat("yyyy/mm/dd").parse("2025/01/01")
        )
    )

    fun findOfferForDate(date: Date): List<Offer> {
        return offers.filter { it.validUntil >= date }
    }
}
