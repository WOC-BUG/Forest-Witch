package forestWitch;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayer;
import javax.swing.JPanel;

/**
 * 主菜单
 */
public class MainMenu implements GameState{
	Index game;
	Image bg, menu;
	ImageIcon start_img,exit_img;
	JButton start,exit;
	JPanel jPanel;
	AudioClip audio;
	int deltaMove[] = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,
			-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,0,0};	// 移动规律
	int i=0;
	int posY = 200;
	
	public MainMenu(){
		game=Index.getInstance();
		start_img = new ImageIcon(getClass().getResource("../imgs/start_btn.png"));
		exit_img = new ImageIcon(getClass().getResource("../imgs/exit_btn.png"));
		bg = new ImageIcon(getClass().getResource("../imgs/bg.png")).getImage();
		menu = new ImageIcon(getClass().getResource("../imgs/menu.png")).getImage();

		// 创建JPanel
		jPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				ImageIcon image = new ImageIcon(bg);
				g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), image.getImageObserver());
			}
		};
		jPanel.setLayout(null);
		
		// 创建JButton
		start=new JButton(start_img);
		exit=new JButton(exit_img);
		exit.setBounds(350, 650, 100, 60);
		start.setBounds(550, 650, 100, 60);	
		
		// 去掉JButton自带的边框和填充
		start.setBorderPainted(false);
		exit.setBorderPainted(false);
		start.setContentAreaFilled(false);
		exit.setContentAreaFilled(false);
		
		// 添加事件监听
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				transactionState();
				game.requestFocus(); // 点击按钮后获取焦点
			}
		});
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		jPanel.add(start);
		jPanel.add(exit);
		game.add(jPanel);
	}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics g) {
		g.drawImage(bg, 0, 0, game.getWidth(), game.getHeight(), null);
		posY += deltaMove[i];
		i = (i + 1) % deltaMove.length;
		g.drawImage(menu, 300, posY, 400, 450, null);
		g.drawImage(exit_img.getImage(), 350, 685, 100, 60, null);
		g.drawImage(start_img.getImage(), 550, 685, 100, 60, null);
	}

	@Override
	public void transactionState() {
		game.remove(jPanel);
		
		// 切换到游戏运行状态
		GamePlay gp=new GamePlay();
		game.changeStateTo(gp);
	}

	@Override
	public void keyPressed(KeyEvent ke) {}

	@Override
	public void mouseClicked(MouseEvent me) {}
}
