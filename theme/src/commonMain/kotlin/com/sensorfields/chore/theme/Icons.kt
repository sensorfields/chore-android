package com.sensorfields.chore.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public sealed interface Icons {
    public data object ArrowUpward : Icons
    public data object ArrowDownward : Icons
    public data object Sort : Icons
    public data object Add : Icons
    public data object Dashboard : Icons
    public data object QueryStats : Icons
    public data object Settings : Icons
    public data object Check : Icons
    public data object Close : Icons
    public data object ArrowBack : Icons
}

internal val Icons.imageVector: ImageVector
    get() = when (this) {
        Icons.ArrowUpward -> ImageVector.Builder(
            name = "arrow_upward",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(11f, 20f)
                verticalLineTo(7.82f)
                lineToRelative(-5.6f, 5.6f)
                lineTo(4f, 12f)
                lineTo(12f, 4f)
                lineToRelative(8f, 8f)
                lineToRelative(-1.4f, 1.42f)
                lineTo(13f, 7.82f)
                verticalLineTo(20f)
                horizontalLineTo(11f)
                close()
            }
        }.build()

        Icons.ArrowDownward -> ImageVector.Builder(
            name = "arrow_downward",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(11f, 4f)
                verticalLineTo(16.18f)
                lineTo(5.4f, 10.58f)
                lineTo(4f, 12f)
                lineToRelative(8f, 8f)
                lineToRelative(8f, -8f)
                lineTo(18.6f, 10.58f)
                lineTo(13f, 16.18f)
                verticalLineTo(4f)
                horizontalLineTo(11f)
                close()
            }
        }.build()

        Icons.Sort -> ImageVector.Builder(
            name = "sort",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(3f, 18f)
                verticalLineTo(16f)
                horizontalLineTo(9f)
                verticalLineToRelative(2f)
                horizontalLineTo(3f)
                close()
                moveTo(3f, 13f)
                verticalLineTo(11f)
                horizontalLineTo(15f)
                verticalLineToRelative(2f)
                horizontalLineTo(3f)
                close()
                moveTo(3f, 8f)
                verticalLineTo(6f)
                horizontalLineTo(21f)
                verticalLineTo(8f)
                horizontalLineTo(3f)
                close()
            }
        }.build()

        Icons.Add -> ImageVector.Builder(
            name = "add",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(11f, 13f)
                horizontalLineTo(5f)
                verticalLineTo(11f)
                horizontalLineToRelative(6f)
                verticalLineTo(5f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(6f)
                horizontalLineToRelative(6f)
                verticalLineToRelative(2f)
                horizontalLineTo(13f)
                verticalLineToRelative(6f)
                horizontalLineTo(11f)
                verticalLineTo(13f)
                close()
            }
        }.build()

        Icons.Dashboard -> ImageVector.Builder(
            name = "dashboard",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(13f, 9f)
                verticalLineTo(3f)
                horizontalLineToRelative(8f)
                verticalLineTo(9f)
                horizontalLineTo(13f)
                close()
                moveTo(3f, 13f)
                verticalLineTo(3f)
                horizontalLineToRelative(8f)
                verticalLineTo(13f)
                horizontalLineTo(3f)
                close()
                moveToRelative(10f, 8f)
                verticalLineTo(11f)
                horizontalLineToRelative(8f)
                verticalLineTo(21f)
                horizontalLineTo(13f)
                close()
                moveTo(3f, 21f)
                verticalLineTo(15f)
                horizontalLineToRelative(8f)
                verticalLineToRelative(6f)
                horizontalLineTo(3f)
                close()
                moveTo(5f, 11f)
                horizontalLineTo(9f)
                verticalLineTo(5f)
                horizontalLineTo(5f)
                verticalLineToRelative(6f)
                close()
                moveToRelative(10f, 8f)
                horizontalLineToRelative(4f)
                verticalLineTo(13f)
                horizontalLineTo(15f)
                verticalLineToRelative(6f)
                close()
                moveTo(15f, 7f)
                horizontalLineToRelative(4f)
                verticalLineTo(5f)
                horizontalLineTo(15f)
                verticalLineTo(7f)
                close()
                moveTo(5f, 19f)
                horizontalLineTo(9f)
                verticalLineTo(17f)
                horizontalLineTo(5f)
                verticalLineToRelative(2f)
                close()
                moveTo(9f, 11f)
                close()
                moveTo(15f, 7f)
                close()
                moveToRelative(0f, 6f)
                close()
                moveTo(9f, 17f)
                close()
            }
        }.build()

        Icons.QueryStats -> ImageVector.Builder(
            name = "query_stats",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(2.63f, 14.02f)
                lineTo(1f, 12.85f)
                lineToRelative(5f, -8f)
                lineToRelative(3f, 3.5f)
                lineToRelative(4f, -6.5f)
                lineToRelative(3f, 4.5f)
                lineTo(19.38f, 1f)
                lineTo(21f, 2.17f)
                lineToRelative(-4.95f, 7.85f)
                lineTo(13.08f, 5.55f)
                lineToRelative(-3.8f, 6.17f)
                lineTo(6.25f, 8.2f)
                lineTo(2.63f, 14.02f)
                close()
                moveTo(14.5f, 18f)
                quadToRelative(1.05f, 0f, 1.78f, -0.73f)
                reflectiveQuadTo(17f, 15.5f)
                reflectiveQuadTo(16.28f, 13.73f)
                reflectiveQuadTo(14.5f, 13f)
                reflectiveQuadToRelative(-1.77f, 0.72f)
                reflectiveQuadTo(12f, 15.5f)
                reflectiveQuadToRelative(0.73f, 1.77f)
                reflectiveQuadTo(14.5f, 18f)
                close()
                moveToRelative(5.1f, 4f)
                lineTo(16.9f, 19.3f)
                quadToRelative(-0.53f, 0.35f, -1.14f, 0.52f)
                reflectiveQuadTo(14.5f, 20f)
                quadToRelative(-1.88f, 0f, -3.19f, -1.31f)
                reflectiveQuadTo(10f, 15.5f)
                reflectiveQuadToRelative(1.31f, -3.19f)
                reflectiveQuadTo(14.5f, 11f)
                reflectiveQuadToRelative(3.19f, 1.31f)
                reflectiveQuadTo(19f, 15.5f)
                quadToRelative(0f, 0.65f, -0.18f, 1.26f)
                reflectiveQuadTo(18.3f, 17.9f)
                lineTo(21f, 20.6f)
                lineTo(19.6f, 22f)
                close()
            }
        }.build()

        Icons.Settings -> ImageVector.Builder(
            name = "settings",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(9.25f, 22f)
                lineTo(8.85f, 18.8f)
                quadTo(8.53f, 18.68f, 8.24f, 18.5f)
                reflectiveQuadTo(7.68f, 18.13f)
                lineTo(4.7f, 19.38f)
                lineTo(1.95f, 14.63f)
                lineTo(4.53f, 12.68f)
                quadTo(4.5f, 12.5f, 4.5f, 12.34f)
                quadToRelative(0f, -0.16f, 0f, -0.34f)
                reflectiveQuadToRelative(0f, -0.34f)
                reflectiveQuadTo(4.53f, 11.33f)
                lineTo(1.95f, 9.38f)
                lineTo(4.7f, 4.63f)
                lineTo(7.68f, 5.88f)
                quadTo(7.95f, 5.68f, 8.25f, 5.5f)
                reflectiveQuadTo(8.85f, 5.2f)
                lineTo(9.25f, 2f)
                horizontalLineToRelative(5.5f)
                lineToRelative(0.4f, 3.2f)
                quadToRelative(0.33f, 0.13f, 0.61f, 0.3f)
                reflectiveQuadToRelative(0.56f, 0.38f)
                lineTo(19.3f, 4.63f)
                lineToRelative(2.75f, 4.75f)
                lineToRelative(-2.57f, 1.95f)
                quadToRelative(0.02f, 0.18f, 0.02f, 0.34f)
                reflectiveQuadToRelative(0f, 0.34f)
                reflectiveQuadToRelative(0f, 0.34f)
                reflectiveQuadToRelative(-0.05f, 0.34f)
                lineToRelative(2.57f, 1.95f)
                lineToRelative(-2.75f, 4.75f)
                lineTo(16.33f, 18.13f)
                quadToRelative(-0.27f, 0.2f, -0.57f, 0.38f)
                reflectiveQuadToRelative(-0.6f, 0.3f)
                lineTo(14.75f, 22f)
                horizontalLineTo(9.25f)
                close()
                moveTo(11f, 20f)
                horizontalLineToRelative(1.98f)
                lineToRelative(0.35f, -2.65f)
                quadToRelative(0.78f, -0.2f, 1.44f, -0.59f)
                reflectiveQuadToRelative(1.21f, -0.94f)
                lineToRelative(2.47f, 1.03f)
                lineToRelative(0.98f, -1.7f)
                lineTo(17.28f, 13.52f)
                quadToRelative(0.13f, -0.35f, 0.17f, -0.74f)
                reflectiveQuadTo(17.5f, 12f)
                reflectiveQuadTo(17.45f, 11.21f)
                quadTo(17.4f, 10.83f, 17.28f, 10.48f)
                lineTo(19.43f, 8.85f)
                lineTo(18.45f, 7.15f)
                lineTo(15.98f, 8.2f)
                quadTo(15.43f, 7.63f, 14.76f, 7.24f)
                reflectiveQuadTo(13.33f, 6.65f)
                lineTo(13f, 4f)
                horizontalLineTo(11.03f)
                lineTo(10.68f, 6.65f)
                quadTo(9.9f, 6.85f, 9.24f, 7.24f)
                reflectiveQuadTo(8.03f, 8.17f)
                lineTo(5.55f, 7.15f)
                lineTo(4.58f, 8.85f)
                lineToRelative(2.15f, 1.6f)
                quadTo(6.6f, 10.83f, 6.55f, 11.2f)
                reflectiveQuadTo(6.5f, 12f)
                quadToRelative(0f, 0.4f, 0.05f, 0.77f)
                reflectiveQuadToRelative(0.17f, 0.75f)
                lineTo(4.58f, 15.15f)
                lineToRelative(0.98f, 1.7f)
                lineTo(8.03f, 15.8f)
                quadToRelative(0.55f, 0.58f, 1.21f, 0.96f)
                reflectiveQuadToRelative(1.44f, 0.59f)
                lineTo(11f, 20f)
                close()
                moveToRelative(1.05f, -4.5f)
                quadToRelative(1.45f, 0f, 2.47f, -1.03f)
                reflectiveQuadTo(15.55f, 12f)
                reflectiveQuadTo(14.53f, 9.52f)
                reflectiveQuadTo(12.05f, 8.5f)
                quadToRelative(-1.47f, 0f, -2.49f, 1.02f)
                reflectiveQuadTo(8.55f, 12f)
                reflectiveQuadToRelative(1.01f, 2.47f)
                reflectiveQuadToRelative(2.49f, 1.03f)
                close()
                moveTo(12f, 12f)
                close()
            }
        }.build()

        Icons.Check -> ImageVector.Builder(
            name = "check",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(9.55f, 18f)
                lineTo(3.85f, 12.3f)
                lineTo(5.28f, 10.88f)
                lineToRelative(4.28f, 4.28f)
                lineTo(18.73f, 5.97f)
                lineTo(20.15f, 7.4f)
                lineTo(9.55f, 18f)
                close()
            }
        }.build()

        Icons.Close -> ImageVector.Builder(
            name = "close",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(6.4f, 19f)
                lineTo(5f, 17.6f)
                lineTo(10.6f, 12f)
                lineTo(5f, 6.4f)
                lineTo(6.4f, 5f)
                lineTo(12f, 10.6f)
                lineTo(17.6f, 5f)
                lineTo(19f, 6.4f)
                lineTo(13.4f, 12f)
                lineTo(19f, 17.6f)
                lineTo(17.6f, 19f)
                lineTo(12f, 13.4f)
                lineTo(6.4f, 19f)
                close()
            }
        }.build()

        Icons.ArrowBack -> ImageVector.Builder(
            name = "arrow_back",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Bevel,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.Companion.NonZero,
            ) {
                moveTo(7.83f, 13f)
                lineToRelative(5.6f, 5.6f)
                lineTo(12f, 20f)
                lineTo(4f, 12f)
                lineTo(12f, 4f)
                lineToRelative(1.43f, 1.4f)
                lineTo(7.83f, 11f)
                horizontalLineTo(20f)
                verticalLineToRelative(2f)
                horizontalLineTo(7.83f)
                close()
            }
        }.build()
    }
