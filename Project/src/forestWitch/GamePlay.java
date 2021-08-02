package forestWitch;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class GamePlay implements GameState{
	Index game;
	Image bg, witchR, witchL, star;
	Image monster1, monster2, monster3;
	Image block, stone, matrix, thorn;
	Image box, key;
	Image stepText, levelText[]=new Image[4];
	ArrayList<Actor> allActors= new ArrayList<Actor>();
	AudioClip audio;
	
	public void loadResources(){
		witchL = new ImageIcon(getClass().getResource("../imgs/witch_Q_Left.PNG")).getImage();
		witchR = new ImageIcon(getClass().getResource("../imgs/witch_Q_Right.PNG")).getImage();
		star = new ImageIcon(getClass().getResource("../imgs/star.png")).getImage();
		monster1 = new ImageIcon(getClass().getResource("../imgs/monster_blue.png")).getImage();
		monster2 = new ImageIcon(getClass().getResource("../imgs/monster_green.png")).getImage();
		monster3 = new ImageIcon(getClass().getResource("../imgs/monster_pink.png")).getImage();
		block = new ImageIcon(getClass().getResource("../imgs/empty.png")).getImage();
		stone = new ImageIcon(getClass().getResource("../imgs/stone.png")).getImage();
		stepText = new ImageIcon(getClass().getResource("../imgs/step.png")).getImage();
		matrix = new ImageIcon(getClass().getResource("../imgs/matrix.png")).getImage();
		thorn = new ImageIcon(getClass().getResource("../imgs/trap.png")).getImage();
		box = new ImageIcon(getClass().getResource("../imgs/box.png")).getImage();
		key = new ImageIcon(getClass().getResource("../imgs/key.png")).getImage();
		levelText[0]=new ImageIcon(getClass().getResource("../imgs/level_1.png")).getImage();
		levelText[1]=new ImageIcon(getClass().getResource("../imgs/level_2.png")).getImage();
		levelText[2]=new ImageIcon(getClass().getResource("../imgs/level_3.png")).getImage();
	}
	
	GamePlay(){
		loadResources();
		new GameData().init();
		
		GameData.menu.stop();
		GameData.game.loop();
		
		game = Index.getInstance();
		allActors.clear();
		changeLevel();
	}
	
	void changeLevel(){
		
		GameData.ifGetKey = false;
		GameData.ifOpenBox = false;
		GameData.oddEven = 0;
		allActors.add(new Text(200,80,0,0,0,0,null));
		switch(GameData.curLevel){
		case 0:
			allActors.add(new Witch(640,225,0,0,80,80,witchL));
			allActors.add(new Monster(460,315,0,0,80,80,monster1));
			allActors.add(new Monster(380,415,0,0,70,70,monster2));
			allActors.add(new Monster(550,410,0,0,80,80,monster3));
			allActors.add(new Monster(740,680,0,0,80,80,monster3));
			allActors.add(new Stone(280,590,0,0,80,80,stone));
			allActors.add(new Stone(280,680,0,0,80,80,stone));
			allActors.add(new Stone(460,680,0,0,80,80,stone));
			allActors.add(new Stone(550,590,0,0,80,80,stone));
			allActors.add(new Matrix(670,690,0,0,70,70,matrix));
			for(int i=0;i<9;i++){
				int a=GameData.wallInfo0[i][0];
				int b=GameData.wallInfo0[i][1];
				int c=GameData.wallInfo0[i][2];
				int d=GameData.wallInfo0[i][3];
				allActors.add(new Wall(a,b,0,0,c,d,block));
			}
			break;
		case 1:
			allActors.add(new Thorn(280,470,0,0,80,80,thorn));
			allActors.add(new Thorn(550,470,0,0,80,80,thorn));
			allActors.add(new Thorn(640,470,0,0,80,80,thorn));
			allActors.add(new Witch(190,655,0,0,80,80,witchR));
			allActors.add(new Monster(275,380,0,0,80,80,monster1));
			allActors.add(new Monster(645,655,0,0,80,80,monster2));
			allActors.add(new Monster(735,745,0,0,80,80,monster3));
			allActors.add(new Stone(550,470,0,0,80,80,stone));
			allActors.add(new Stone(640,470,0,0,80,80,stone));
			allActors.add(new Stone(730,470,0,0,80,80,stone));
			allActors.add(new Matrix(555,740,0,0,80,80,matrix));
			for(int i=0;i<9;i++){
				int a=GameData.wallInfo1[i][0];
				int b=GameData.wallInfo1[i][1];
				int c=GameData.wallInfo1[i][2];
				int d=GameData.wallInfo1[i][3];
				allActors.add(new Wall(a,b,0,0,c,d,block));
			}
			break;
		case 2:
			allActors.add(new Witch(760,388,0,0,80,80,witchL));
			allActors.add(new Monster(490,570,0,0,80,80,monster1));
			allActors.add(new Monster(580,740,0,0,80,80,monster2));
			allActors.add(new Thorn(410,395,0,0,80,80,thorn));
			allActors.add(new Thorn(500,395,0,0,80,80,thorn));
			allActors.add(new Thorn(315,485,0,0,80,80,thorn));
			allActors.add(new Thorn(495,485,0,0,80,80,thorn));
			allActors.add(new Thorn(580,570,0,0,80,80,thorn));
			allActors.add(new Thorn(680,570,0,0,80,80,thorn));
			allActors.add(new Thorn(495,660,0,0,80,80,thorn));
			allActors.add(new Thorn(315,660,0,0,80,80,thorn));
			allActors.add(new Key(135,660,0,0,80,80,key));
			allActors.add(new Box(680,300,0,0,80,80,box));
			allActors.add(new Matrix(580,215,0,0,80,80,matrix));
			for(int i=0;i<14;i++){
				int a=GameData.wallInfo2[i][0];
				int b=GameData.wallInfo2[i][1];
				int c=GameData.wallInfo2[i][2];
				int d=GameData.wallInfo2[i][3];
				allActors.add(new Wall(a,b,0,0,c,d,block));
			}
			break;
		case 3:
			break;
		}
	}

	@Override
	public void update() {
		ArrayList<Actor> removeActors= new ArrayList<Actor>();	// 需要销毁的东西
		
		// 步数不够，返回起始页
		if(GameData.curStep[GameData.curLevel] <= 0){
			GameLose gl=new GameLose();
			Index.getInstance().changeStateTo(gl);
		}
		
		// 查找女巫所在位置
		int wX = 0, wY = 0;
		for (Actor a : allActors) {
			if (a instanceof Witch) {
				wX = ((Witch) a).getX() + a.lenX / 2;
				wY = ((Witch) a).getY() + a.lenY / 2;
			}
		}
		
		// 更新节点
		for(Actor a : allActors){
			if(a instanceof Bullet){
				double dis=new GameData().getDistance(a.getX(), a.getY(), wX, wY);
				Actor tmp = a.collide(this);
				
				// 超过范围/碰撞到物体就销毁(子弹射程不超过一格的大小)
				if(dis >= 100 * 100 || !(tmp instanceof Witch)){
					removeActors.add(a);
				}
				if(tmp instanceof Monster){	// 杀死怪物
					removeActors.add(tmp);
				}
			}
			a.update();
		}
		for(Actor a: removeActors){
			allActors.remove(a);
		}
	}

	@Override
	public void draw(Graphics g) {
		// 更新每关的背景图
		bg=getImage(GameData.bgSrc[GameData.curLevel]);
		g.drawImage(bg, 0, 0, game.getWidth(), game.getHeight(), null);
		g.drawImage(stepText, 20, 50, 155, 40, null);
		g.drawImage(levelText[GameData.curLevel], 850, 50, 120, 40, null);
		// 渲染游戏对象
		for(Actor a:allActors){
			a.render(g);
		}
	}
	
	/**
	 * 获取图片
	 * @param src 图片路径
	 * @return Image
	 */
	Image getImage(String src){
		ImageIcon icon=new ImageIcon(getClass().getResource(src));
		return icon.getImage();
	}
	
	public ArrayList<Actor> getAllActors(){
		return allActors;
	}

	@Override
	public void transactionState() {
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		char ch = ke.getKeyChar();
		System.out.println("敲击了" + ke.getKeyChar() + "键！");
		System.out.println("敲击了" + ke.getKeyCode() + "键！");
		if(ke.getKeyCode() == 27){ // ESC键
			GameData.game.stop();
			GameData.menu.loop();
			MainMenu mm=new MainMenu();
			Index.getInstance().changeStateTo(mm);
		}
		if (ch == 'a') {
			GameData.curStep[GameData.curLevel]--;
			collide(-GameData.stepSize,0,'a');
		} 
		else if (ch == 'd') {
			GameData.curStep[GameData.curLevel]--;
			collide(GameData.stepSize, 0, 'd');
		}
		else if (ch == 'w') {
			GameData.curStep[GameData.curLevel]--;
			collide(0, -GameData.stepSize, 'w');
		}
		else if (ch == 's') {
			GameData.curStep[GameData.curLevel]--;
			collide(0, GameData.stepSize, 's');
		}
	}
	
	/**
	 * 处理碰撞
	 * @param vx 更改速度x
	 * @param vy 更改速度y
	 * @param ch 按键
	 */
	void collide(int vx,int vy,char ch){
		if(allActors.isEmpty())
			return;
		GameData.oddEven = 1 - GameData.oddEven;	// 奇偶次操作更换
		for (Actor a : allActors) {			
			// 判断女巫的下一步是否会撞到物品
			if (a instanceof Witch) {	// 找到女巫
				((Witch) a).setVx(vx);
				((Witch) a).setVy(vy);
				Actor tmp = a.collide(this);	//判断撞到了什么
				Actor trap = ((Witch) a).collideThorn(this);	// 下一步是否撞到尖刺
				
				if(tmp instanceof Stone){	//石头
					((Stone) tmp).setSelf(tmp);
					((Stone) tmp).setVx(vx);
					((Stone) tmp).setVy(vy);
					
					//判断石头能不能移动
					Actor tmp2=tmp.collide(this);
					if(tmp2 == null){
						move(tmp,ch);
					}
					else{
						((Witch) a).setVx(0);
						((Witch) a).setVy(0);
						collideThorn();
						System.out.println("女巫位置：("+a.getX()+","+a.getY()+")");
						return;
					}
				}
				else if(tmp instanceof Key){	// 钥匙
					GameData.ifGetKey = true;
				}
				else if(tmp instanceof Box && GameData.ifGetKey){	// 宝箱且拿到钥匙
					GameData.ifOpenBox = true;
				}
				else if(tmp instanceof Matrix){	// 传送阵
					move(a,ch);
					if(GameData.curLevel < GameData.sumLevel - 1){
						allActors.clear();
						GameData.curLevel++;
						changeLevel();
					}else{
						GameWin gw=new GameWin();
						Index.getInstance().changeStateTo(gw);
					}
				}
				else if(a.collide(this)!=null){
					((Witch) a).setVx(0);
					((Witch) a).setVy(0);
					collideThorn();
					System.out.println("女巫位置：("+a.getX()+","+a.getY()+")");
					return;
				}
				
				if(trap instanceof Thorn){	// 尖刺
					if(GameData.oddEven == 0){
						GameData.curStep[GameData.curLevel]--;
					}
				}
				move(a,ch);

				System.out.println("女巫位置：("+a.getX()+","+a.getY()+")");
			}
		}
	}
	
	/**
	 * 判断尖刺是否碰撞到原地的女巫
	 */
	void collideThorn(){
		for (Actor b : allActors) {
			if(b instanceof Thorn){
				Actor player = b.collide(this);
				if(player != null){
					if(GameData.oddEven == 0){	// 偶数次操作会被刺到，步数--
						GameData.curStep[GameData.curLevel]--;
					}
				}
			}
		}
	}
	
	/**
	 * 根据按键进行移动
	 * @param a 对象
	 * @param ch 按键
	 */
	void move(Actor a, char ch){
		if(ch=='a'){
			a.moveLeft();
		}
		else if(ch=='d'){
			a.moveRight();
		}
		else if(ch=='w'){
			a.moveUp();
		}
		else if(ch=='s'){
			a.moveDown();
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		GameData.oddEven = 1 - GameData.oddEven;	// 奇偶次操作更换
		GameData.curStep[GameData.curLevel]--;	// 操作步数-1
		collideThorn();
		System.out.println("点击：（" + me.getX() + "，" + me.getY() + ")");
		
		// 新建子弹
		int nowX = 0, nowY = 0;
		for (Actor a : allActors) {
			if (a instanceof Witch) {
				nowX = ((Witch) a).getX() + a.lenX / 2;
				nowY = ((Witch) a).getY() + a.lenY / 2;
			}
		}
		
		// 子弹方向向量()
		int dirX = me.getX() - nowX;
		int dirY = me.getY() - nowY;
		int Vx = 0, Vy = 0;
		// System.out.println("方向向量"+"("+dirX+","+dirY+")");
		// 计算子弹轨迹直线，从而获得其速度

		// 直线垂直于x轴的情况
		if (dirX == 0) {
			Vx = 0;
			Vy = dirY > 0 ? GameData.BulletV : -GameData.BulletV;
		}

		// 求子弹的【单位】方向向量，就要求：
		// 【以人物为原点、单位速度(BulletV)为半径】的圆，与【人物到目标点击处所在的】直线的交点
		// 这样才能保证在改变方向的同时，使速度保持一致
		// 1. 圆的方程是(x-nowX)^2+(y-nowY)^2=ConstValue.BulletV^2
		// 2. 直线的方程是y=Kx+B
		// 3.
		// 整理得：[x^2-2nowX*x+nowX^2]+[(Kx)^2+B^2+nowY^2+2KBx-2K*nowY*x-2B*nowY]=ConstValue.BulletV^2
		// 4.
		// 整理得：(1+K^2)x^2+(-2nowX+2KB-2K*nowY)x+(nowX^2+B^2+nowY^2-2B*nowY-ConstValue.BulletV^2)=0
		// 5. 通过一元二次方程的系数求X和Y坐标
		else {
			double K = (double) dirY / (double) dirX;
			double B = nowY - K * nowX;

			// 一元二次方程的三个系数
			double a = 1 + K * K;
			double b = -2 * nowX + 2 * K * B - 2 * K * nowY;
			double c = nowX * nowX + B * B + nowY * nowY - 2 * B * nowY - GameData.BulletV * GameData.BulletV;

			// tmpX和tmpY是求得的圆和射线的交点
			double tmpX = new GameData().getAnswer(a, b, c, dirX);
			double tmpY = K * tmpX + B;

			// 减去起点坐标才是方向向量
			Vx = (int) Math.round(tmpX - nowX);
			Vy = (int) Math.round(tmpY - nowY);
			// System.out.println("女巫位置：("+nowX+","+nowY+")");
			// System.out.println("交点坐标：("+tmpX+","+tmpY+")");
			// System.out.println("k="+K+",b="+B+",子弹速度：("+Vx+","+Vy+")");
		}
		
		// 更新速度
		Bullet bullet=new Bullet(nowX, nowY, 10, 10, 40, 40, new ImageIcon(getClass().getResource("../imgs/star.png")).getImage());
		bullet.setV(Vx, Vy);
		allActors.add(bullet);
	}
}
