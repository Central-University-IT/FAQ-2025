package com.prod.test.solution

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule

abstract class BaseScreenShotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6_PRO,
        theme = "Theme.AppCompat.DayNight.NoActionBar"
    )
}
