package com.prod.test.solution

import com.prod.core.api.domain.repositories.CartRepository
import com.prod.core.api.domain.repositories.PayRepository
import com.prod.core.api.ui.payment.PaymentState
import com.prod.solution.impl.domain.usecases.PayUseCaseImpl
import com.prod.solution.impl.domain.usecases.PaymentCardValidateUseCaseImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class Task10Test {

    @Test
    fun validateCardNumber_returns_empty_valid_and_not_available_state_when_input_empty_score_2() {
        val initPaymentState = PaymentState(
            cardNumber = "",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardNumber(initPaymentState, "")

        assertEquals(
            initPaymentState,
            result
        )
    }

    @Test
    fun validateCardNumber_returns_empty_valid_and_not_available_state_when_input_less_than_20_symbols_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "234 45",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardNumber(initPaymentState, "1234 456")

        assertEquals(
            initPaymentState.copy(cardNumber = "1234 456"),
            result
        )
    }

    @Test
    fun validateCardNumber_returns_valid_state_when_input_valid_card_number_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "234 45",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardNumber(initPaymentState, "1234 4567 8901 2345")

        assertEquals(
            initPaymentState.copy(
                cardNumber = "1234 4567 8901 2345",
                isCardNumberValid = true,
                isPaymentAvailable = true
            ),
            result
        )
    }

    @Test
    fun validateCardNumber_returns_invalid_state_when_input_invalid_card_number_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "234 45",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardNumber(initPaymentState, "1234 4567 8901 234e")

        assertEquals(
            initPaymentState.copy(
                cardNumber = "1234 4567 8901 234e",
                isCardNumberValid = false,
                isPaymentAvailable = false
            ),
            result
        )
    }

    @Test
    fun validateCardNumber_returns_valid_state_when_input_empty_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = true
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardNumber(initPaymentState, "")

        assertEquals(
            initPaymentState.copy(
                cardNumber = "",
                isCardNumberValid = true,
                isPaymentAvailable = false
            ),
            result
        )
    }

    @Test
    fun validateCardNumber_returns_invalid_state_when_input_more_than_20_symbols_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = true
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardNumber(initPaymentState, "1234 5678 9012 3456 7890")

        assertEquals(
            initPaymentState.copy(
                cardNumber = "1234 5678 9012 3456 7890",
                isCardNumberValid = false,
                isPaymentAvailable = false
            ),
            result
        )
    }

    @Test
    fun validateCardDate_returns_empty_valid_and_not_available_state_when_input_empty_score_2() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardDate(initPaymentState, "", 2025, 1)

        assertEquals(
            initPaymentState,
            result
        )
    }

    @Test
    fun validateCardDate_returns_empty_valid_and_not_available_state_when_input_less_than_5_symbols_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardDate(initPaymentState, "01/2", 2025, 1)

        assertEquals(
            initPaymentState.copy(cardDate = "01/2"),
            result
        )
    }

    @Test
    fun validateCardDate_returns_invalid_state_when_input_invalid_card_date_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardDate(initPaymentState, "13/25", 2025, 1)

        assertEquals(
            initPaymentState.copy(
                cardDate = "13/25",
                isCardDateValid = false,
                isPaymentAvailable = false
            ),
            result
        )
    }

    @Test
    fun validateCardDate_returns_invalid_state_when_input_invalid_card_date_2_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardDate(initPaymentState, "01/2025", 2025, 1)

        assertEquals(
            initPaymentState.copy(
                cardDate = "01/2025",
                isCardDateValid = false,
                isPaymentAvailable = false
            ),
            result
        )
    }

    @Test
    fun validateCardDate_returns_valid_state_when_input_current_date_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardDate(initPaymentState, "01/25", 2025, 1)

        assertEquals(
            initPaymentState.copy(
                cardDate = "01/25",
                isCardDateValid = true,
                isPaymentAvailable = true
            ),
            result
        )
    }

    @Test
    fun validateCardDate_returns_valid_state_when_input_future_date_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardDate(initPaymentState, "01/26", 2025, 1)

        assertEquals(
            initPaymentState.copy(
                cardDate = "01/26",
                isCardDateValid = true,
                isPaymentAvailable = true
            ),
            result
        )
    }

    @Test
    fun validateCardDate_returns_invalid_state_when_input_past_date_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardDate(initPaymentState, "01/24", 2025, 1)

        assertEquals(
            initPaymentState.copy(
                cardDate = "01/24",
                isCardDateValid = false,
                isPaymentAvailable = false
            ),
            result
        )
    }

    @Test
    fun validateCardCvv_returns_empty_valid_and_not_available_state_when_input_empty_score_2() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardCvv(initPaymentState, "")

        assertEquals(
            initPaymentState,
            result
        )
    }

    @Test
    fun validateCardCvv_returns_empty_valid_and_not_available_state_when_input_less_than_3_symbols_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "1",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardCvv(initPaymentState, "12")

        assertEquals(
            initPaymentState.copy(cardCvv = "12"),
            result
        )
    }

    @Test
    fun validateCardCvv_returns_valid_state_when_input_valid_cvv_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "1",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardCvv(initPaymentState, "123")

        assertEquals(
            initPaymentState.copy(
                cardCvv = "123",
                isCardCvvValid = true,
                isPaymentAvailable = true
            ),
            result
        )
    }

    @Test
    fun validateCardCvv_returns_invalid_state_when_input_invalid_cvv_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "1",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardCvv(initPaymentState, "12e")

        assertEquals(
            initPaymentState.copy(
                cardCvv = "12e",
                isCardCvvValid = false,
                isPaymentAvailable = false
            ),
            result
        )
    }

    @Test
    fun validateCardCvv_returns_invalid_state_when_input_more_than_3_symbols_score_1() {
        val initPaymentState = PaymentState(
            cardNumber = "1234 4567 8901 2345",
            cardDate = "01/25",
            cardCvv = "1",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val result = PaymentCardValidateUseCaseImpl().validateCardCvv(initPaymentState, "1234")

        assertEquals(
            initPaymentState.copy(
                cardCvv = "1234",
                isCardCvvValid = false,
                isPaymentAvailable = false
            ),
            result
        )
    }

    @Test
    fun payUseCase_get_data_from_cart_CartRepository_score_2() {
        val cartRepositoryMock = mock<CartRepository>() {
            on { getCart() } doReturn listOf(
                Pair(mock(), 1),
                Pair(mock(), 2),
                Pair(mock(), 3)
            )
        }
        val payRepository = mock<PayRepository>()

        val payUseCase = PayUseCaseImpl(payRepository, cartRepositoryMock)

        payUseCase.pay(mock())

        verify(cartRepositoryMock).getCart()
    }

    @Test
    fun payUseCase_pass_data_from_cartRepository_and_paymentState_to_PayRepository_score_2() {
        val cartRepositoryMock = mock<CartRepository>() {
            on { getCart() } doReturn listOf(
                Pair(mock(), 1),
                Pair(mock(), 2),
                Pair(mock(), 3)
            )
        }
        val payRepositoryMock = mock<PayRepository>()
        val paymentState = mock<PaymentState>()

        val payUseCase = PayUseCaseImpl(payRepositoryMock, cartRepositoryMock)

        payUseCase.pay(paymentState)

        verify(payRepositoryMock).pay(
            cardNumber = paymentState.cardNumber,
            cardDate = paymentState.cardDate,
            cardCvv = paymentState.cardCvv,
            goodsList = cartRepositoryMock.getCart()
        )
    }
}
