<Query Kind="Program">
  <Namespace>System.Drawing</Namespace>
</Query>

void Main()
{/*
	getCircleLineXDistance(40, 140, 20,   200, 100,   100, 200);
	getCircleLineXDistance(40, 140, 20,   100, 100,   200, 200);
	getCircleLineXDistance(40, 140, 20,   300, 100,   300, 200);
	
	getCircleLineXDistance(40, 140, 20,   200, 140,   300, 200);
	getCircleLineXDistance(40, 140, 20,   300, 20,   200, 240);
	getCircleLineXDistance(40, 140, 20,   200, 240,   300, 20);
	
	getCircleLineXDistance(40, 130, 20,   200, 140,   300, 20);
	getCircleLineXDistance(40, 130, 20,   200, 140,   300, 220);
	
	getCircleLineXDistance(40, 150, 20,   200, 140,   300, 20);
	getCircleLineXDistance(40, 150, 20,   200, 140,   300, 220);*/
	
	
	getCircleLineXDistance(350, 140, 20,   200, 100,   100, 200);
	getCircleLineXDistance(350, 140, 20,   100, 100,   200, 200);
	getCircleLineXDistance(350, 140, 20,   300, 100,   300, 200);
	
	getCircleLineXDistance(350, 130, 20,   200, 140,   300, 20);
	getCircleLineXDistance(350, 130, 20,   200, 140,   300, 220);
	
	getCircleLineXDistance(350, 150, 20,   200, 140,   300, 20);
	getCircleLineXDistance(350, 150, 20,   200, 140,   300, 220);
}

static float FDELTA = 0.001f;

void getCircleLineXDistance(float c_x, float c_y, float rad, float p1_x, float p1_y, float p2_x, float p2_y) {
	Image b = new Bitmap(400, 250, System.Drawing.Imaging.PixelFormat.Format16bppRgb555);

	if (p1_y > p2_y) {
		float tmp;
		
		tmp = p1_y; p1_y = p2_y; p2_y = tmp;
		tmp = p1_x; p1_x = p2_x; p2_x = tmp;
	}

	float result;
    using(var graphics = Graphics.FromImage(b))
    {
		float distance_p1 = (p1_x - c_x) - fsqrt(fmax(0, (rad * rad) - fabs(p1_y - c_y)));
		float distance_p2 = (p2_x - c_x) - fsqrt(fmax(0, (rad * rad) - fabs(p2_y - c_y)));
		
		float angle = (float) (Math.Atan2(p2_y - p1_y, p2_x - p1_x) + Math.PI/2);
		
		float d_12_x = (p2_x - p1_x);
		float d_12_y = (p2_y - p1_y);
		
		float circle_coll_x = c_x + fabs(fcos(angle) * rad);
		float circle_coll_y = c_y - fsin(angle) * rad;
		
		float line_coll_x;
		float line_coll_y = circle_coll_y;
		float line_coll_s = (circle_coll_y - p1_y) / d_12_y;
		
		/**/graphics.DrawEllipse(new Pen(Color.Red), new Rectangle((int)(c_x- rad), (int)(c_y-rad), (int)(2*rad), (int)(2*rad)));
		/**/graphics.DrawLine(new Pen(Color.Red), new PointF(p1_x, p1_y), new PointF(p2_x, p2_y));
		
		line_coll_s.Dump();
		
		if (line_coll_s > 0 && (p1_y + line_coll_s * d_12_y) < p2_y) {
			line_coll_x = p1_x + d_12_x * line_coll_s;
			
			/**/graphics.DrawLine(new Pen(Color.Yellow), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
			
			result = line_coll_x - circle_coll_x;
		} else if (line_coll_s <= 0) {
			line_coll_x = p1_x;
			line_coll_y = p1_y;
			
			circle_coll_x = c_x + fsqrt(fsquare(rad) - fsquare(c_y - line_coll_y));
			circle_coll_y = line_coll_y;
			
			/**/graphics.DrawLine(new Pen(Color.Aqua), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
			
			result = distance_p1;
		} else {
			line_coll_x = p2_x;
			line_coll_y = p2_y;
			
			/**/circle_coll_x = c_x + fsqrt(fsquare(rad) - fsquare(c_y - line_coll_y));
			circle_coll_y = line_coll_y;
			
			/**/graphics.DrawLine(new Pen(Color.Green), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
			
			result = distance_p2 + Math.Sign(distance_p2)*FDELTA;
		}
		
		/**/graphics.DrawEllipse(new Pen(Color.LightGray), new Rectangle((int)(c_x - rad + result), (int)(c_y - rad), (int)(2*rad), (int)(2*rad)));
	}
	
	b.Dump();
	result.Dump();
	"".Dump();
}

float fsqrt(float x) {return (float)Math.Sqrt(x);}
float fmax(float x, float y) {return (float)Math.Max(x, y);}
float fabs(float x) {return (float)Math.Abs(x);}
float fsin(float x) {return (float)Math.Sin(x);}
float fcos(float x) {return (float)Math.Cos(x);}
float fsquare(float x) {return x*x;}