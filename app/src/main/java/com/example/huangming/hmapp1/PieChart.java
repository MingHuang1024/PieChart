package com.example.huangming.hmapp1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.List;

/**
 * 自定义饼图
 *
 * @author Huangming
 * @date 2016/6/24
 * @modified [describe][editor][date]
 */

public class PieChart extends View {

	/** 突出部分偏移量 */
	private final int OFFSET = 10;


	private final int RECT_TOP = 20;
	private final int RECT_LEFT = 20;
	private  int RECT_BOTTOM ;
	private  int RECT_RIGHT;

	/** 饼图半径,默认100px */
	private int radius=100;

	private List<PieElement> elements;
	private Paint paint;
	private RectF rectf;

	public PieChart(Context context, List<PieElement> elements) {
		super(context);
		this.elements = elements;
		init();
	}

	/**
	 * 构造方法
	 *
	 * @param radius 饼图半径，px
	 * @return
	 *
	 * @author Huangming
	 * @date 2016/6/27
	 * @modified [describe][editor][date]
	 */
	public PieChart(Context context, List<PieElement> elements,int radius) {
		super(context);
		this.elements = elements;
		if(radius>0){
			this.radius=radius;
		}
		init();
	}

//	public PieChart(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		init();
//	}
//
//	public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//		init();
//	}

	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true); // 消除锯齿
		// this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆或 空心矩形
		paint.setStyle(Paint.Style.FILL); // 绘制实心圆
		RECT_BOTTOM=radius*2+20;
		RECT_RIGHT=radius*2+20;
	}

	@Override
	protected void onDraw(Canvas canvas) {
//		canvas.drawColor(Color.parseColor("#000099"));
		// 绘制饼图
		drawPie(canvas);

		super.onDraw(canvas);
		System.out.println("高=" + getHeight() + "  宽=" + getWidth());
	}

	/**
	 * 画饼图
	 * @param canvas
	 */
	private void drawPie(Canvas canvas) {
		rectf = new RectF(RECT_LEFT, RECT_TOP, RECT_RIGHT, RECT_BOTTOM);
		float start = -90;// 起始角度
		float sweepAngle;// 旋转角度
		for (PieElement elem : elements) {
			sweepAngle = elem.scale * 360;
			paint.setColor(elem.color);
			if (elem.isHighlight) {
				rectf.top = RECT_TOP - OFFSET;
				rectf.left = RECT_LEFT - OFFSET;
				rectf.right = RECT_RIGHT + OFFSET;
				rectf.bottom = RECT_BOTTOM + OFFSET;
			} else {
				rectf.top = RECT_TOP;
				rectf.left = RECT_LEFT;
				rectf.right = RECT_RIGHT;
				rectf.bottom = RECT_BOTTOM;
			}
			canvas.drawArc(rectf, start, sweepAngle, true, paint);
			start = sweepAngle + start;
		}
	}

	/**
	 * 画圆环
	 *
	 * @param
	 * @return
	 *
	 * @author Huangming
	 * @date 2016/6/24
	 * @modified [describe][editor][date]
	 */
	private void drawHuang(Canvas canvas) {
		int center = getWidth() / 2;
		int innerCircle = 40; // 内圆半径
		int ringWidth = 20; // 圆环宽度

		// 第一种方法绘制圆环
		// 绘制内圆
		this.paint.setARGB(255, 138, 43, 226);
		this.paint.setStrokeWidth(2);
		canvas.drawCircle(center, center, innerCircle, this.paint);

		// 绘制圆环
		this.paint.setARGB(255, 138, 43, 226);
		this.paint.setStrokeWidth(ringWidth);
		canvas.drawCircle(center, center, innerCircle + 1 + ringWidth / 2, this.paint);

		// 绘制外圆
		this.paint.setARGB(255, 138, 43, 226);
		this.paint.setStrokeWidth(2);
		canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);

	}

	public PieElement getItem(int index) {
		return elements.get(index);
	}

	public void refresh() {
//		invalidate();
		postInvalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width;
		int height;
//		if (widthMode == MeasureSpec.EXACTLY) {
//			width = widthSize;
//		} else {
//		}
		width = radius*2+40;

//		if (heightMode == MeasureSpec.EXACTLY) {
//			height = heightSize;
//		} else {
//		}
		height = radius*2+40;
		setMeasuredDimension(width, height);
	}

	/**
	 * 饼图元素
	 *
	 * @author Huangming
	 * @date 2016/6/24
	 * @modified [describe][editor][date]
	 */
	public static class PieElement {

		/**
		 * 所占比例，百分比
		 */
		public float scale;

		/**
		 * 显示颜色
		 */
		public int color;

		/**
		 * 是否突出显示
		 */
		public boolean isHighlight;
	}
}
