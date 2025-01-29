package com.prod.test.solution

import com.prod.core.api.ui.payment.PaymentState
import com.prod.solution.impl.ui.payment.PaymentScreenView
import org.junit.Test

class Task11PaymentScreenShotTest : BaseScreenShotTest() {

    @Test
    fun empty_payment_screen_score_7() {
        val paymentState = PaymentState(
            cardNumber = "",
            cardDate = "",
            cardCvv = "",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = true
        )

        val view = PaymentScreenView(paparazzi.context).apply {
            this.updatePaymentScreen(
                paymentState = paymentState,
                onCardNumberChanged = {},
                onCardDateChanged = {},
                onCardCvvChanged = {},
                onPayButtonClicked = {}
            )
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun payment_screen_with_data_score_8() {
        val paymentState = PaymentState(
            cardNumber = "1234 4567 7890 1234",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = true
        )

        val view = PaymentScreenView(paparazzi.context).apply {
            this.updatePaymentScreen(
                paymentState = paymentState,
                onCardNumberChanged = {},
                onCardDateChanged = {},
                onCardCvvChanged = {},
                onPayButtonClicked = {}
            )
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun payment_screen_with_data_alternate_score_8() {
        val paymentState = PaymentState(
            cardNumber = "1234 4567 7890 1234",
            cardDate = "01/25",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = true,
            isCardCvvValid = true,
            isPaymentAvailable = true
        )

        val view = PaymentScreenView(paparazzi.context).apply {
            this.updatePaymentScreen(
                paymentState = paymentState,
                onCardNumberChanged = {},
                onCardDateChanged = {},
                onCardCvvChanged = {},
                onPayButtonClicked = {}
            )
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun payment_screen_with_all_errors_score_8() {
        val paymentState = PaymentState(
            cardNumber = "1asd 4567 7890 1234",
            cardDate = "01/10",
            cardCvv = "1234",
            isCardNumberValid = false,
            isCardDateValid = false,
            isCardCvvValid = false,
            isPaymentAvailable = false
        )

        val view = PaymentScreenView(paparazzi.context).apply {
            this.updatePaymentScreen(
                paymentState = paymentState,
                onCardNumberChanged = {},
                onCardDateChanged = {},
                onCardCvvChanged = {},
                onPayButtonClicked = {}
            )
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun payment_screen_with_all_errors_alternate_score_8() {
        val paymentState = PaymentState(
            cardNumber = "1asd 4567 7890 1234",
            cardDate = "01/10",
            cardCvv = "1234",
            isCardNumberValid = false,
            isCardDateValid = false,
            isCardCvvValid = false,
            isPaymentAvailable = false
        )

        val view = PaymentScreenView(paparazzi.context).apply {
            this.updatePaymentScreen(
                paymentState = paymentState,
                onCardNumberChanged = {},
                onCardDateChanged = {},
                onCardCvvChanged = {},
                onPayButtonClicked = {}
            )
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun payment_screen_with_date_error_score_7() {
        val paymentState = PaymentState(
            cardNumber = "1234 4567 7890 1234",
            cardDate = "01/10",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = false,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val view = PaymentScreenView(paparazzi.context).apply {
            this.updatePaymentScreen(
                paymentState = paymentState,
                onCardNumberChanged = {},
                onCardDateChanged = {},
                onCardCvvChanged = {},
                onPayButtonClicked = {}
            )
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun payment_screen_with_date_error_alternate_score_7() {
        val paymentState = PaymentState(
            cardNumber = "1234 4567 7890 1234",
            cardDate = "01/10",
            cardCvv = "123",
            isCardNumberValid = true,
            isCardDateValid = false,
            isCardCvvValid = true,
            isPaymentAvailable = false
        )

        val view = PaymentScreenView(paparazzi.context).apply {
            this.updatePaymentScreen(
                paymentState = paymentState,
                onCardNumberChanged = {},
                onCardDateChanged = {},
                onCardCvvChanged = {},
                onPayButtonClicked = {}
            )
        }

        paparazzi.snapshot(view)
    }
}
