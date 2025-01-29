package com.prod.test.solution

import android.graphics.Color
import com.prod.core.api.R
import com.prod.core.api.ui.goods.GoodsBonusUi
import com.prod.core.api.ui.goods.GoodsFavouriteUi
import com.prod.core.api.ui.goods.GoodsItemUi
import com.prod.solution.impl.ui.goods.GoodsItemView
import org.junit.Test

class Task6ScreenshotTest : BaseScreenShotTest() {

    @Test
    fun default_GoodsItemView_with_text_and_image_score_10() {
        val state = GoodsItemUi(
            id = "id1",
            name = "Реклама",
            imageRes = R.drawable.goods_2_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "777 кг",
            cost = "7878 ₽",
            costAndQuantityInCart = null,
            favourite = null,
            bonus = null,
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun default_GoodsItemView_with_text_and_image_alternate_score_10() {
        val state = GoodsItemUi(
            id = "id1",
            name = "Реклама",
            imageRes = R.drawable.goods_2_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "777 кг",
            cost = "7878 ₽",
            costAndQuantityInCart = null,
            favourite = null,
            bonus = null,
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_text_long_text_score_11() {
        val state = GoodsItemUi(
            id = "id2",
            name = "Реклама - Реклама - Реклама - Реклама - Реклама - Реклама - Реклама - Реклама",
            imageRes = R.drawable.goods_2_img,
            producerName = "Здесь могла бы быть ваша реклама. Здесь могла бы быть ваша реклама.",
            quantityInfo = "333 г",
            cost = "3000 ₽",
            costAndQuantityInCart = null,
            favourite = null,
            bonus = null,
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_text_long_text_alternate_score_11() {
        val state = GoodsItemUi(
            id = "id2",
            name = "Реклама - Реклама - Реклама - Реклама - Реклама - Реклама - Реклама - Реклама",
            imageRes = R.drawable.goods_2_img,
            producerName = "Здесь могла бы быть ваша реклама. Здесь могла бы быть ваша реклама.",
            quantityInfo = "333 г",
            cost = "3000 ₽",
            costAndQuantityInCart = null,
            favourite = null,
            bonus = null,
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_favourite_label_score_10() {
        val state = GoodsItemUi(
            id = "id3",
            name = "Реклама",
            imageRes = R.drawable.goods_1_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "3 кг",
            cost = "111 ₽",
            costAndQuantityInCart = null,
            favourite = GoodsFavouriteUi(
                text = "Новинка",
                textColor = Color.RED,
            ),
            bonus = null,
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_favourite_label_alternate_score_10() {
        val state = GoodsItemUi(
            id = "id3",
            name = "Реклама",
            imageRes = R.drawable.goods_1_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "3 кг",
            cost = "111 ₽",
            costAndQuantityInCart = null,
            favourite = GoodsFavouriteUi(
                text = "Новинка",
                textColor = Color.RED,
            ),
            bonus = null,
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_bonus_label_score_10() {
        val state = GoodsItemUi(
            id = "id3",
            name = "Реклама",
            imageRes = R.drawable.goods_2_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "3 кг",
            cost = "111 ₽",
            costAndQuantityInCart = null,
            favourite = null,
            bonus = GoodsBonusUi(
                text = "Кэшбэк 10%",
                textColor = Color.WHITE,
                backgroundColor = Color.parseColor("#5222FF"),
                borderColor = Color.BLACK,
            ),
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_bonus_label_alternate_score_10() {
        val state = GoodsItemUi(
            id = "id3",
            name = "Реклама",
            imageRes = R.drawable.goods_2_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "3 кг",
            cost = "111 ₽",
            costAndQuantityInCart = null,
            favourite = null,
            bonus = GoodsBonusUi(
                text = "Кэшбэк 10%",
                textColor = Color.WHITE,
                backgroundColor = Color.parseColor("#5222FF"),
                borderColor = Color.BLACK,
            ),
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_labels_score_10() {
        val state = GoodsItemUi(
            id = "id3",
            name = "Реклама",
            imageRes = R.drawable.goods_3_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "3 кг",
            cost = "111 ₽",
            costAndQuantityInCart = null,
            favourite = GoodsFavouriteUi(
                text = "Новинка",
                textColor = Color.RED,
            ),
            bonus = GoodsBonusUi(
                text = "Кэшбэк 10%",
                textColor = Color.WHITE,
                backgroundColor = Color.parseColor("#5222FF"),
                borderColor = Color.BLACK,
            ),
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_labels_alternate_score_10() {
        val state = GoodsItemUi(
            id = "id3",
            name = "Реклама",
            imageRes = R.drawable.goods_3_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "3 кг",
            cost = "111 ₽",
            costAndQuantityInCart = null,
            favourite = GoodsFavouriteUi(
                text = "Новинка",
                textColor = Color.RED,
            ),
            bonus = GoodsBonusUi(
                text = "Кэшбэк 10%",
                textColor = Color.WHITE,
                backgroundColor = Color.parseColor("#5222FF"),
                borderColor = Color.BLACK,
            ),
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }


    @Test
    fun goodsItemView_with_item_added_to_cart_score_10() {
        val state = GoodsItemUi(
            id = "id3",
            name = "Реклама",
            imageRes = R.drawable.goods_3_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "3 кг",
            cost = "111 ₽",
            costAndQuantityInCart = "3шт. = 333 ₽",
            favourite = null,
            bonus = null,
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun goodsItemView_with_item_added_to_cart_alternate_score_10() {
        val state = GoodsItemUi(
            id = "id3",
            name = "Реклама",
            imageRes = R.drawable.goods_3_img,
            producerName = "Здесь могла бы быть ваша реклама",
            quantityInfo = "3 кг",
            cost = "111 ₽",
            costAndQuantityInCart = "3шт. = 333 ₽",
            favourite = null,
            bonus = null,
        )

        val view = GoodsItemView(paparazzi.context).apply {
            updateState(state)
        }

        paparazzi.snapshot(view)
    }
}
