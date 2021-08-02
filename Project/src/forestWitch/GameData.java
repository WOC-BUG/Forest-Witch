package forestWitch;

import java.applet.Applet;
import java.applet.AudioClip;

public class GameData {
	static int sumLevel = 3;	// 总关卡数
	static int curLevel = 0;	// 当前关卡
	static int curStep[] = {16,22,32}; //当前剩余步数
	static int stepSize = 90;	// 步长
	static boolean ifGetKey = false;	//是否获得钥匙
	static boolean ifOpenBox = false;	// 是否可以打开宝箱
	static int oddEven = 0;	// 奇数/偶数步
	
	// 游戏音乐
	static AudioClip menu = Applet.newAudioClip(ClassLoader.getSystemResource("audios/MalibuNight.wav"));
	static AudioClip game = Applet.newAudioClip(ClassLoader.getSystemResource("audios/Get-Your-Wish.wav"));
	
	// 子弹速度
	static int BulletV = 10;
	
	// 每关背景图
	static String bgSrc[]={"../imgs/level1.png","../imgs/level2.png","../imgs/level3.png"};

	// 墙
	static int wallInfo0[][]={
			{540,110,200,100},
			{270,190,270,100},
			{170,270,100,200},
			{80,520,100,200},
			{95,780,800,100},
			{825,560,100,200},
			{740,210,100,450},
			{635,420,100,150},
			{370,490,250,90}};
	static int wallInfo1[][]={
			{85,455,100,300},
			{160,265,100,200},
			{270,180,400,100},
			{650,270,200,100},
			{835,340,100,500},
			{545,835,300,100},
			{180,750,200,100},
			{370,470,150,400},
			{370,380,80,90}};
	static int wallInfo2[][]={
			{27,650,100,200},
			{130,535,100,100},
			{210,380,90,360},
			{300,200,80,180},
			{390,100,400,80},
			{390,300,270,80},
			{770,200,100,180},
			{855,380,100,300},
			{765,570,100,300},
			{130,830,700,100},
			{405,480,80,80},
			{585,480,80,80},
			{405,660,80,80},
			{585,660,80,80}};
	
	void init(){
		curLevel = 0;
		oddEven = 0;
		curStep[0] = 19;
		curStep[1] = 20;
		curStep[2] = 30;
		ifGetKey = false;
		ifOpenBox = false;
	}
	
	/**
	 * 求一元二次方程的值
	 * 
	 * @param a	二次项系数
	 * @param b	一次项系数
	 * @param c	常数项系数
	 * @param dirX	子弹发射起点到终点的方向向量
	 * @return 由于JFrame渲染render只能为整数，所以坐标就四舍五入取整
	 */
	public double getAnswer(double a, double b, double c, int dirX) {
		double delta = Math.sqrt(b * b - 4 * a * c); // delta= 根号下(b^2-4ac)
		double x1 = (-b + delta) / (2 * a);
		double x2 = (-b - delta) / (2 * a);

		// 由于直线是经过圆心的，因此方向向量dirX为正对应x1，为负对应x2
		if (dirX > 0)
			return x1;
		else
			return x2;
	}
	
	/**
	 * 计算两点之间的距离
	 */
	public double getDistance(double a,double b,double x,double y){
		return (a-x)*(a-x)+(b-y)*(b-y);
	}
}
