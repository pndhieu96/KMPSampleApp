package domain.usecases

import domain.repository_Interfaces.UpdatedTimeRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ValidateFreshCurrencyDataUseCase(
    private val preferencesRepository: UpdatedTimeRepository
) {
    suspend operator fun invoke() : Boolean {
        val updatedTime = preferencesRepository.getLastUpdatedTime()

        return if(updatedTime != 0L) {
            val updatedTimeInstant = Instant.fromEpochMilliseconds(updatedTime)
            val currentTimeInstant = Clock.System.now()
            val updatedTimeDateTime = updatedTimeInstant.toLocalDateTime(TimeZone.currentSystemDefault())
            val currentTimeDateTime = currentTimeInstant.toLocalDateTime(TimeZone.currentSystemDefault())
            val dayDifference =
                currentTimeDateTime.date.dayOfYear - updatedTimeDateTime.date.dayOfYear
            dayDifference <= 1
        } else false
    }
}