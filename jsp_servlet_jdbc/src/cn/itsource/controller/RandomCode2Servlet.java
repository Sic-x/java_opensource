package cn.itsource.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/randomCode2")
public class RandomCode2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		//=================================创建画板（缓存图片）=================================//
		//1.创建画板(缓存图片)(参数：宽度,高度,图像类型 -- 表示一个图像，该图像具有整数像素的 RGB 颜色)
		BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_BGR);

		//2.根据缓冲图片获取一只画笔，默认颜色为白色
		Graphics g = image.getGraphics();
		//3.创建随机数对象，用于获取随机颜色
		Random r = new Random();


		//==================================画背景（随机颜色）==================================//
		//4.先给笔设置颜色，才能设置画的区域，范围在0~255
		//g.setColor(Color.gray);//设置固定颜色
		g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

		//5.在画板上设置画的区域（参数：0,0表示起点的x和y坐标，100表示宽度，30表示高度）
		g.fillRect(0, 0, 100, 30);


		//==========================画字符串（随机字符串，随机颜色，随机字体）===========================//
		//6.获取随机字符串
		String string = getString(4);
		req.getSession().setAttribute("RANDOMCODE_IN_SESSION", string);//将随机字符串绑定到sesion中，用于验证
		//7.为笔设置随机颜色，少了这一步的话，就会与背景颜色一样
		g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
		//8.为该字符串设置随机的字体
		g.setFont(getFont());
		//9.将字符串画出(参数：画的字符串，从那个点开始画（从字符串的左下角画开始往上画，往右画），原点是在左上角)
		g.drawString(string, 10, 25);


		//==========================画干扰线（随机位置）===========================//
		for(int i = 0 ; i < 4 ; i++){
			g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			//参数：第一个点的坐标（x,y） 第二个点的坐标（x,y）
			//将笔强转成Graphics2D类型
			Graphics2D g1 = (Graphics2D)g;
			//然后设置笔为原始宽度的1.5倍,Stroke:画笔
			g1.setStroke(new BasicStroke(1.5f));
			g.drawLine(r.nextInt(100), r.nextInt(30), r.nextInt(100), r.nextInt(30));
		}


		//==========================画干扰点（随机位置）===========================//
		for(int i = 0 ; i < 20 ; i++){
			g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			//参数：圆心坐标(x,y)  横轴长度 纵轴长度，Oval椭圆
			g.drawOval(r.nextInt(100), r.nextInt(30), 2, 2);
		}

		//============================将缓存图片写出去=============================//
		//x.设置响应的类型
		resp.setContentType("image/jpeg");
		//y.创建一个字节流（图片是二进制文件，只能通过字节流写出）
		ServletOutputStream os = resp.getOutputStream();
		//z.写出图片
		ImageIO.write(image, "jpeg", os);

		//关闭流释放资源
		os.close();
	}
	public String getString(int num){
		//这里不写0，O，1，l，2，z，6，b，U，V，v，u，9，q是因为用于不好区分，提高用户体验度
		String words = "acdefghjkmnprstwxy34578ACEFGHJKLMNPQRSTWXY";
		String result = "";
		Random r = new Random();
		for(int i = 0;i < num;i++){
			result += words.charAt(r.nextInt(words.length()));
		}
		return result;
	}

	public Font getFont(){
		Font[] fonts = new Font[5];
		Random r = new Random();
		fonts[0] = new Font("微软雅黑", Font.ITALIC, 24);
		fonts[1] = new Font("新宋体", Font.PLAIN, 24);
		fonts[2] = new Font("Microsoft YaHei UI", Font.PLAIN, 24);
		fonts[3] = new Font("仿宋", Font.PLAIN, 24);
		fonts[4] = new Font("Cambria", Font.BOLD, 24);
		return fonts[r.nextInt(fonts.length)];
	}

}
