<Query Kind="Program">
  <Namespace>System.Drawing</Namespace>
</Query>

void Main()
{
   
	getCircleLineXDistance(40, 90, 20,     200, 50,    000, 250);
	getCircleLineXDistance(40, 140, 20,    10, 10,     200, 200);
	getCircleLineXDistance(40, 140, 20,    300, 100,   300, 200);
	getCircleLineXDistance(100, 150, 20,   50, 100,    200, 100);
	
	getCircleLineXDistance(40, 140, 20,    20, 23,     300, 200);
	getCircleLineXDistance(40, 140, 20,    300, 20,    200, 240);
	getCircleLineXDistance(40, 140, 20,    200, 240,   300, 20);
	
	getCircleLineXDistance(40, 130, 20,    200, 140,   300, 20);
	getCircleLineXDistance(40, 130, 20,    200, 140,   300, 220);
	
	getCircleLineXDistance(40, 150, 20,    200, 140,   300, 20);
	getCircleLineXDistance(40, 150, 20,    200, 140,   300, 220);
	
	
	getCircleLineXDistance(350, 140, 20,   200, 100,   100, 200);
	getCircleLineXDistance(350, 140, 20,   100, 100,   200, 200);
	getCircleLineXDistance(350, 140, 20,   300, 100,   300, 200);
	
	getCircleLineXDistance(350, 130, 20,   200, 140,   300, 20);
	getCircleLineXDistance(350, 130, 20,   100, 60,    300, 220);
	
	getCircleLineXDistance(350, 150, 20,   200, 140,   300, 20);
	getCircleLineXDistance(350, 150, 20,   200, 140,   300, 220);
	
	getCircleLineXDistance(350, 150, 20,   200, 135,   200, 20);
	getCircleLineXDistance(350, 150, 20,   200, 165,   200, 245);
	
	getCircleLineXDistance(350, 150, 20,   100, 100,   300, 100);
	
	getCircleLineXDistance(180, 100, 20,   50, 50,     250, 240);
	
	
	
	getCircleLineXDistance(90,   40,  20,    50,    200,     250,   000);
	getCircleLineXDistance(140,  40,  20,    10,    10,      200,   200);
	getCircleLineXDistance(140,  40,  20,    100,   300,     200,   300);
	getCircleLineXDistance(150,  100, 20,    100,   50,      100,   200);
	                                                                
	getCircleLineXDistance(140,  40,   20,   23,    20,      200,   300);
	getCircleLineXDistance(140,  40,   20,   20,    300,     240,   200);
	getCircleLineXDistance(140,  40,   20,   240,   200,     20 ,   300);
	                                                                
	getCircleLineXDistance(130,  40,   20,   140,   200,     20 ,   300);
	getCircleLineXDistance(130,  40,   20,   140,   200,     220,   300);
	                                                                
	getCircleLineXDistance(150,  40,   20,   140,   200,     20 ,   300);
	getCircleLineXDistance(150,  40,   20,   140,   200,     220,   300);
	                                                                
	                                                                
	getCircleLineXDistance(140,  150,  20,   100,   100,     200,   000);
	getCircleLineXDistance(140,  150,  20,   100,  -100,     200,   000);
	getCircleLineXDistance(140,  150,  20,   100,   100,     200,   100);
	                                                                
	getCircleLineXDistance(130,  150,  20,   140,   000,     20 ,   100);
	getCircleLineXDistance(130,  150,  20,   60,   -100,     220,   100);
	                                                                
	getCircleLineXDistance(100,  150,  20,   140,   000,     20 ,   100);
	getCircleLineXDistance(150,  150,  20,   140,   000,     220,   100);
	                                                                
	getCircleLineXDistance(150,  150,  20,   135,   020,     20 ,   020);
	getCircleLineXDistance(150,  150,  20,   165,   020,     245,   020);
	                                                                
	getCircleLineXDistance(150,  150,  20,   100,  -100,     100,   100);
	                                                                
	getCircleLineXDistance(100,  210,  20,   50,  -120,      240,   280);
	
	
	
	getCircleLineXDistance(40, 140, 20,    100, 140,   150, 140);
}

static float FDELTA = 0.001f;

static int cnt = 1;

void getCircleLineXDistance( float c_x, float c_y,float rad, float p1_x, float p1_y, float p2_x, float p2_y) {
	("[" + cnt++ + "]").Dump();

	Image b = new Bitmap(400, 250, System.Drawing.Imaging.PixelFormat.Format16bppRgb555);

	using(var graphics = Graphics.FromImage(b))
    {		
		/**/graphics.DrawEllipse(new Pen(Color.Red), new Rectangle((int)(c_x- rad), (int)(c_y-rad), (int)(2*rad), (int)(2*rad)));
		/**/graphics.DrawLine(new Pen(Color.Red), new PointF(p1_x, p1_y), new PointF(p2_x, p2_y));
	}
	
	// get X
    using(var graphics = Graphics.FromImage(b))
    {
		if (p1_y > p2_y) {
			float tmp;
		
			tmp = p1_y; p1_y = p2_y; p2_y = tmp;
			tmp = p1_x; p1_x = p2_x; p2_x = tmp;
		}
	
		float result;
	
		float sign = Math.Sign( (p2_x-p1_x)*(c_y-p1_y) - (p2_y-p1_y)*(c_x-p1_x) );
	
		float distance_p1 = (p1_x - c_x) - fsqrt(fmax(0, (rad * rad) - fabs(p1_y - c_y)));
		float distance_p2 = (p2_x - c_x) - fsqrt(fmax(0, (rad * rad) - fabs(p2_y - c_y)));
		
		float angle = (float) (Math.Atan2(p2_y - p1_y, p2_x - p1_x) + Math.PI/2);
		
		float d_12_x = (p2_x - p1_x);
		float d_12_y = (p2_y - p1_y);
		
		//if (d_12_y == 0) return float.NaN; // unc me
		
		float circle_coll_x = c_x - fcos(angle) * rad * sign;
		float circle_coll_y = c_y - fsin(angle) * rad * sign;
		
		float line_coll_x;
		float line_coll_y = circle_coll_y;
		float line_coll_s = (circle_coll_y - p1_y) / d_12_y;
		
		//line_coll_s.Dump();
		
		if (line_coll_s > 0 && (p1_y + line_coll_s * d_12_y) < p2_y) {
			line_coll_x = p1_x + d_12_x * line_coll_s;
			
			/**/graphics.DrawLine(new Pen(Color.Yellow), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
		} else if (line_coll_s <= 0) {
			line_coll_x = p1_x;
			line_coll_y = p1_y;
			
			circle_coll_x = c_x + fsqrt(fsquare(rad) - fsquare(c_y - line_coll_y)) * sign;
			circle_coll_y = line_coll_y;
			
			/**/graphics.DrawLine(new Pen(Color.Aqua), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
		} else {
			line_coll_x = p2_x;
			line_coll_y = p2_y;
			
			circle_coll_x = c_x + fsqrt(fsquare(rad) - fsquare(c_y - line_coll_y)) * sign;
			circle_coll_y = line_coll_y;
			
			/**/graphics.DrawLine(new Pen(Color.Green), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
		}
		//line_coll_s.Dump();
		
		if (d_12_y != 0 && !float.IsNaN(circle_coll_x)) {
			result = (line_coll_x - circle_coll_x)  - Math.Sign(line_coll_x - circle_coll_x)*FDELTA;
		
			/**/graphics.DrawEllipse(new Pen(Color.LightGray), new Rectangle((int)(c_x - rad + result), (int)(c_y - rad), (int)(2*rad), (int)(2*rad)));
			// return result;
		} else {
			/**/graphics.FillEllipse(new SolidBrush(Color.Red), new Rectangle((int)(c_x- rad/3), (int)(c_y-rad/3), (int)(2*rad/3), (int)(2*rad/3)));
			//return float.NaN;
		}
	}

	// get Y
    using(var graphics = Graphics.FromImage(b))
    {
		if (p1_x > p2_x) {
			float tmp;
		
			tmp = p1_y; p1_y = p2_y; p2_y = tmp;
			tmp = p1_x; p1_x = p2_x; p2_x = tmp;
		}
	
		float result;
	
		float sign = Math.Sign( (p2_x-p1_x)*(c_y-p1_y) - (p2_y-p1_y)*(c_x-p1_x) );
	
		float distance_p1 = (p1_y - c_y) - fsqrt(fmax(0, (rad * rad) - fabs(p1_x - c_x)));
		float distance_p2 = (p2_y - c_y) - fsqrt(fmax(0, (rad * rad) - fabs(p2_x - c_x)));
		
		float angle = (float) (Math.Atan2(p2_y - p1_y, p2_x - p1_x) - Math.PI/2);
		
		float d_12_x = (p2_x - p1_x);
		float d_12_y = (p2_y - p1_y);
		
		//if (d_12_x == 0) return float.NaN; // unc me
		
		float circle_coll_x = c_x + fcos(angle) * rad * sign;
		float circle_coll_y = c_y + fsin(angle) * rad * sign;
		
		float line_coll_x = circle_coll_x;
		float line_coll_y;
		float line_coll_s = (circle_coll_x - p1_x) / d_12_x;
		
		//graphics.DrawEllipse(new Pen(Color.Red), new Rectangle((int)(c_x- rad), (int)(c_y-rad), (int)(2*rad), (int)(2*rad)));
		//graphics.DrawLine(new Pen(Color.Red), new PointF(p1_x, p1_y), new PointF(p2_x, p2_y));
		
		//line_coll_s.Dump();
		
		if (line_coll_s > 0 && (p1_x + line_coll_s * d_12_x) < p2_x) {
			line_coll_y = p1_y + d_12_y * line_coll_s;
			
			/**/graphics.DrawLine(new Pen(Color.Yellow), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
		} else if (line_coll_s <= 0) {
			line_coll_x = p1_x;
			line_coll_y = p1_y;
			
			circle_coll_y = c_y - fsqrt(fsquare(rad) - fsquare(c_x - line_coll_x)) * sign;
			circle_coll_x = line_coll_x;
			
			/**/graphics.DrawLine(new Pen(Color.Aqua), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
		} else {
			line_coll_x = p2_x;
			line_coll_y = p2_y;
			
			circle_coll_y = c_y - fsqrt(fsquare(rad) - fsquare(c_x - line_coll_x)) * sign;
			circle_coll_x = line_coll_x;
			
			/**/graphics.DrawLine(new Pen(Color.Green), new PointF(circle_coll_x, circle_coll_y), new PointF(line_coll_x, line_coll_y));
		}
		//line_coll_s.Dump();
		
		if (!float.IsNaN(circle_coll_y)) {
			result = (line_coll_y - circle_coll_y)  - Math.Sign(line_coll_y - circle_coll_y)*FDELTA;
		
			/**/graphics.DrawEllipse(new Pen(Color.LightGray), new Rectangle((int)(c_x - rad), (int)(c_y - rad + result), (int)(2*rad), (int)(2*rad)));
			// return result;
		} else {
			/**/graphics.FillEllipse(new SolidBrush(Color.Red), new Rectangle((int)(c_x- rad/3), (int)(c_y-rad/3), (int)(2*rad/3), (int)(2*rad/3)));
			//return float.NaN;
		}
	}
	
	b.Dump();
	" ".Dump();
}

float fsqrt(float x) {return (float)Math.Sqrt(x);}
float fmax(float x, float y) {return (float)Math.Max(x, y);}
float fabs(float x) {return (float)Math.Abs(x);}
float fsin(float x) {return (float)Math.Sin(x);}
float fcos(float x) {return (float)Math.Cos(x);}
float fsquare(float x) {return x*x;}