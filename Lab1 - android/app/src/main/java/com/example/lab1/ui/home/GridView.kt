package com.example.lab1.ui.home
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.lab1.data.Matavimas

class GridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val gridPaint = Paint().apply {
        color = 0xFFAAAAAA.toInt() // Light gray color for the grid lines
        strokeWidth = 2f
    }
    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 25f
        textAlign = Paint.Align.CENTER
    }
    private val squarePaint = Paint().apply {
        color = Color.BLUE // Color for the squares
    }
    private val squarePaint2 = Paint().apply {
        color = Color.RED // Color for the squares
    }
    var sig: List<Matavimas> = emptyList()
    var usr: List<Matavimas> = emptyList()
    private val gridSize = 17f // Set grid cell size

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat() -180f

        val centerX = width / 2
        val centerY = height / 2

        // Draw vertical grid lines and x-axis labels
        for (i in -5..7) {  // Adjust the range as needed for the visible grid
            val x = i * gridSize + centerX
            canvas.drawLine(x, 0f, x, height*2, gridPaint)

        }

        // Draw horizontal grid lines and y-axis labels
        for (i in -12..35) { // Adjust the range as needed
            val y = centerY + (15 - i) * gridSize
            canvas.drawLine(0f, y, width, y, gridPaint)

        }

        // Draw colored squares for each signal
        sig.forEach { matavimas ->
            // Adjust x and y based on the offsets
            val x = (matavimas.x.toFloat()) * gridSize + (width / 2) + 5f
            val y = (height / 2) +(15 - matavimas.y.toFloat()) * gridSize +5f
            canvas.drawRect(x, y, x + gridSize - 10f, y + gridSize -10f, squarePaint)
        }
        usr.forEach { matavimas ->
            // Adjust x and y based on the offsets
            val x = (matavimas.x.toFloat()) * gridSize + (width / 2) + 5f
            val y = (height / 2) +(15 - matavimas.y.toFloat()) * gridSize +5f
            canvas.drawRect(x, y, x + gridSize - 10f, y + gridSize -10f, squarePaint2)
        }


    }


    // Method to set signals and invalidate the view
    fun setSignals(signals: List<Matavimas>) {
        this.sig = signals
        invalidate() // Trigger a redraw
    }

    // Method to set signals and invalidate the view
    fun setUsers(signals: List<Matavimas>) {
        this.usr = signals
        invalidate() // Trigger a redraw
    }
}