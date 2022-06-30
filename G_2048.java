//Name: Gurjap Singh Hajra
//Date: Jan 11,2019
//Purpose:	To make 2048

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

@SuppressWarnings("serial")
public class G_2048 extends Applet implements ActionListener, KeyListener {
	Panel p_card; // to hold all of the screens
	Panel card1, card2, card3, card4, card5; // the two screens
	CardLayout cdLayout = new CardLayout();

	// grid
	int row = 4;
	int col = 4;
	JButton gamebutton[] = new JButton[row * col];
	int gameboard[][] = {	{ 0, 0, 0, 0, },
			{ 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, }};
	
	int originalboard[][] = {	{ 0, 0, 0, 0, },
			{ 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, }};

	public void init() {
		p_card = new Panel();
		p_card.setLayout(cdLayout);
		screen1();
		screen2();
		screen3();
		screen4();
		screen5();
		resize(250, 340);
		setLayout(new BorderLayout());
		add("Center", p_card);
		redraw();
	}

	public void actionPerformed(ActionEvent e) { // moves between the screens
		if (e.getActionCommand().equals("s1"))
			cdLayout.show(p_card, "1");
		else if (e.getActionCommand().equals("s2"))
			cdLayout.show(p_card, "2");
		else if (e.getActionCommand().equals("s3"))
			cdLayout.show(p_card, "3");
		else if (e.getActionCommand().equals("s5"))
			cdLayout.show(p_card, "5");
		else if (e.getActionCommand().equals("s6"))
			System.exit(0);
		else if (e.getActionCommand().equals("Up")) {
			Up();
			add();
		} else if (e.getActionCommand().equals("Down")) {
			Down();
			add();
		} else if (e.getActionCommand().equals("right")) {
			Right();
			add();
		} else if (e.getActionCommand().equals("left")) {
			Left();
			add();
		} else { // code to handle the game
			int n = Integer.parseInt(e.getActionCommand());
			int x = n / col;
			int y = n % col;
			showStatus("(" + x + ", " + y + ")");

		}
	}

	public void screen1() { // screen 1 is set up.
		card1 = new Panel();
		card1.setBackground(new Color(255, 255, 255));
		JLabel title = new JLabel("Welcome");
		JButton next = new JButton("Next");
		next.setActionCommand("s2");
		next.addActionListener(this);
		card1.add(title);
		card1.add(next);
		p_card.add("1", card1);
	}

	public void screen2() { // screen 2 is set up.
		card2 = new Panel();
		card2.setBackground(new Color(255, 255, 255));
		JLabel title = new JLabel("Instructions");
		JButton next = new JButton("Next");
		next.setActionCommand("s3");
		next.addActionListener(this);
		card2.add(title);
		card2.add(next);
		p_card.add("2", card2);
	}

	public void screen3() { // screen 3 is set up.
		card3 = new Panel();
		card3.setBackground(new Color(120, 89, 170));
		JLabel title = new JLabel("Game");

		JButton up = new JButton("Up");
		up.setActionCommand("Up");
		up.addActionListener(this);

		JButton down = new JButton("Down");
		down.setActionCommand("Down");
		down.addActionListener(this);

		JButton right = new JButton("right");
		right.setActionCommand("right");
		right.addActionListener(this);

		JButton left = new JButton("left");
		left.setActionCommand("left");
		left.addActionListener(this);

		JTextField cool = new JTextField("Click here to play with arrow keys");
		cool.addKeyListener(this);

		// Set up grid
		Panel p = new Panel(new GridLayout(row, col, 3, 3));
		int move = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) { // take out when you've got pictures
				// add in when you have pictures
				gamebutton[move] = new JButton(createImageIcon(gameboard[i][j] + ".gif"));
				gamebutton[move].setPreferredSize(new Dimension(60, 60));
				gamebutton[move].addActionListener(this);
				gamebutton[move].setActionCommand("" + move);
				p.add(gamebutton[move]);
				move++;
			}
		}
		rand();
		card3.add(title);
		card3.add(p);
		card3.add(up);
		card3.add(down);
		card3.add(left);
		card3.add(right);
		card3.add(cool);
		p_card.add("3", card3);
	}

	public void screen4() { // screen 4 is set up.
		card4 = new Panel();
		card4.setBackground(new Color(255, 255, 255));
		JLabel title = new JLabel("You Win!");
		JButton next = new JButton("Next");
		next.setActionCommand("s5");
		next.addActionListener(this);
		card4.add(title);
		card4.add(next);
		p_card.add("4", card4);
	}

	public void screen5() { // screen 5 is set up.
		card5 = new Panel();
		card5.setBackground(new Color(255, 255, 255));
		JLabel title = new JLabel("You Lose.");
		JButton next = new JButton("Back to Introduction?");
		next.setActionCommand("s1");
		next.addActionListener(this);
		JButton end = new JButton("Quit?");
		end.setActionCommand("s6");
		end.addActionListener(this);
		card5.add(title);
		card5.add(next);
		card5.add(end);
		p_card.add("5", card5);
	}

	protected static ImageIcon createImageIcon(String path) { // change the red to your class name
		java.net.URL imgURL = G_2048.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void redraw() {
		int move = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				gamebutton[move].setIcon(createImageIcon(gameboard[i][j] + ".gif"));
				move++;
			}
		}
	}

	public void rand() {

		int x = (int) (Math.random() * 4);
		int y = (int) (Math.random() * 4);

		int num1;
		int num2;

		int x1;
		int y1;
		do {
			x1 = (int) (Math.random() * 4);
			y1 = (int) (Math.random() * 4);
		} while (x1 == x || y1 == y);

		num1 = (int) (Math.random() * 5);
		if (num1 % 2 == 0) {
			num1 = 2;
		} else {
			num1 = 4;
		}
		num2 = (int) (Math.random() * 5);
		if (num2 % 2 == 0) {
			num2 = 2;
		} else {
			num2 = 4;
		}

		gameboard[x][y] = num1;
		gameboard[x1][y1] = num2;

	}

	public void Up() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i != 0) {
					if (i == 1) {
						if (gameboard[i][j] == gameboard[i - 1][j]) {
							gameboard[i - 1][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i - 1][j] == 0) {
							gameboard[i - 1][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					}
					if (i == 2) {
						if (gameboard[i][j] == gameboard[i - 2][j] && gameboard[i - 1][j] == 0) {
							gameboard[i - 2][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i - 1][j]) {
							gameboard[i - 1][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i - 2][j] == 0 && gameboard[i - 1][j] == 0) {
							gameboard[i - 2][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i - 1][j] == 0) {
							gameboard[i - 1][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					}
					if (i == 3) {
						if (gameboard[i][j] == gameboard[i - 3][j] && gameboard[i - 2][j] == 0
								&& gameboard[i - 1][j] == 0) {
							gameboard[i - 3][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i - 2][j] && gameboard[i - 1][j] == 0) {
							gameboard[i - 2][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i - 1][j]) {
							gameboard[i - 1][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i - 3][j] == 0 && gameboard[i - 2][j] == 0 && gameboard[i - 1][j] == 0) {
							gameboard[i - 3][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i - 2][j] == 0 && gameboard[i - 1][j] == 0) {
							gameboard[i - 2][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i - 1][j] == 0) {
							gameboard[i - 1][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					}
				}
			}
		}

	}

	public void Down() {
		for (int i = 3; i > -1; i--) {
			for (int j = 0; j < 4; j++) {
				if (i != 3) {
					if (i == 2) {
						if (gameboard[i][j] == gameboard[i + 1][j]) {
							gameboard[i + 1][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i + 1][j] == 0) {
							gameboard[i + 1][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					}
					if (i == 1) {
						if (gameboard[i][j] == gameboard[i + 2][j] && gameboard[i + 1][j] == 0) {
							gameboard[i + 2][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i + 1][j]) {
							gameboard[i + 1][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i + 2][j] == 0 && gameboard[i + 1][j] == 0) {
							gameboard[i + 2][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i + 1][j] == 0) {
							gameboard[i + 1][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					}
					if (i == 0) {
						if (gameboard[i][j] == gameboard[i + 3][j] && gameboard[i + 2][j] == 0
								&& gameboard[i + 1][j] == 0) {
							gameboard[i + 3][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i + 2][j] && gameboard[i + 1][j] == 0) {
							gameboard[i + 2][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i + 1][j]) {
							gameboard[i + 1][j] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i + 3][j] == 0 && gameboard[i + 2][j] == 0 && gameboard[i + 1][j] == 0) {
							gameboard[i + 3][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i + 2][j] == 0 && gameboard[i + 1][j] == 0) {
							gameboard[i + 2][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i + 1][j] == 0) {
							gameboard[i + 1][j] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					}
				}
			}
		}
	}

	public void Left() {

		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				if (j != 0) {
					if (j == 1) {
						if (gameboard[i][j] == gameboard[i][j - 1]) {
							gameboard[i][j - 1] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j - 1] == 0) {
							gameboard[i][j - 1] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					} else if (j == 2) {

						if (gameboard[i][j - 2] == gameboard[i][j] && gameboard[i][j - 1] == 0) {
							gameboard[i][j - 2] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i][j - 1]) {
							gameboard[i][j - 1] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j - 2] == 0 && gameboard[i][j - 1] == 0) {
							gameboard[i][j - 2] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i][j - 1] == 0) {
							gameboard[i][j - 1] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					} else if (j == 3) {

						if (gameboard[i][j - 3] == gameboard[i][j] && gameboard[i][j - 2] == 0
								&& gameboard[i][j - 1] == 0) {
							gameboard[i][j - 3] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j - 2] == gameboard[i][j] && gameboard[i][j - 1] == 0) {
							gameboard[i][j - 2] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i][j - 1]) {
							gameboard[i][j - 1] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j - 3] == 0 && gameboard[i][j - 2] == 0 && gameboard[i][j - 1] == 0) {
							gameboard[i][j - 3] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i][j - 2] == 0 && gameboard[i][j - 1] == 0) {
							gameboard[i][j - 2] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i][j - 1] == 0) {
							gameboard[i][j - 1] = gameboard[i][j];
							gameboard[i][j] = 0;
						}

					}
				}
			}
		}
	}

	public void Right() {
		for (int j = 3; j > -1; j--) {
			for (int i = 0; i < 4; i++) {
				if (j != 3) {
					if (j == 2) {
						if (gameboard[i][j] == gameboard[i][j + 1]) {
							gameboard[i][j + 1] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j + 1] == 0) {
							gameboard[i][j + 1] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					} else if (j == 1) {

						if (gameboard[i][j + 2] == gameboard[i][j] && gameboard[i][j + 1] == 0) {
							gameboard[i][j + 2] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i][j + 1]) {
							gameboard[i][j + 1] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j + 2] == 0 && gameboard[i][j + 1] == 0) {
							gameboard[i][j + 2] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i][j + 1] == 0) {
							gameboard[i][j + 1] = gameboard[i][j];
							gameboard[i][j] = 0;
						}
					} else if (j == 0) {

						if (gameboard[i][j + 3] == gameboard[i][j] && gameboard[i][j + 2] == 0
								&& gameboard[i][j + 1] == 0) {
							gameboard[i][j + 3] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j + 2] == gameboard[i][j] && gameboard[i][j + 1] == 0) {
							gameboard[i][j + 2] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j] == gameboard[i][j + 1]) {
							gameboard[i][j + 1] = gameboard[i][j] * 2;
							gameboard[i][j] = 0;
						} else if (gameboard[i][j + 3] == 0 && gameboard[i][j + 2] == 0 && gameboard[i][j + 1] == 0) {
							gameboard[i][j + 3] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i][j + 2] == 0 && gameboard[i][j + 1] == 0) {
							gameboard[i][j + 2] = gameboard[i][j];
							gameboard[i][j] = 0;
						} else if (gameboard[i][j + 1] == 0) {
							gameboard[i][j + 1] = gameboard[i][j];
							gameboard[i][j] = 0;
						}

					}
				}
			}
		}
	}

	public void add() {
		int num1;
		double random;
		boolean stop = false;
		do {
			int i = (int) (Math.random() * 4);
			int j = (int) (Math.random() * 4);

			if (gameboard[i][j] == 0 && stop == false) {

				random = Math.random();
				if (random > 0.98) {
					num1 = 8;
				} else if(random>0.9){
					num1 = 4;
				}else {
					num1=2;
				}
				gameboard[i][j] = num1;
				stop = true;
			} else {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
			}
			int count = 0;
			for (int k = 0; k < 4; k++) {
				for (int l = 0; l < 4; l++) {
					if (gameboard[k][l] == 0) {
						count++;
					}
				}
			}
			if (count == 0) {
				stop = true;
			}
		} while (stop == false);
	}

	public boolean win() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameboard[i][j] == 2048) {
					count++;
				}
			}
		}
		if (count >= 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP :
			Up();
			add();
			if (win()) {
				JOptionPane.showMessageDialog (null, "You Win", "You Won", JOptionPane.ERROR_MESSAGE);
				cdLayout.show(p_card, "4");
			}
			redraw();
			if(lose()) {
				JOptionPane.showMessageDialog (null, "You Lose", "You Lose", JOptionPane.ERROR_MESSAGE);
				cdLayout.show(p_card, "5");
			}
			redraw();
			break;
		case KeyEvent.VK_DOWN :
			Down();
			add();
			if (win()) {
				JOptionPane.showMessageDialog (null, "You Win", "You Won", JOptionPane.ERROR_MESSAGE);
				cdLayout.show(p_card, "4");
			}
			redraw();
			if(lose()) {
				JOptionPane.showMessageDialog (null, "You Lose", "You Lose", JOptionPane.ERROR_MESSAGE);
				cdLayout.show(p_card, "5");
			}
			redraw();
			break;
		case KeyEvent.VK_RIGHT :
			Right();
			add();
			if (win()) {
				JOptionPane.showMessageDialog (null, "You Win", "You Won", JOptionPane.ERROR_MESSAGE);
				cdLayout.show(p_card, "4");
			}
			redraw();
			if(lose()) {
				JOptionPane.showMessageDialog (null, "You Lose", "You Lose", JOptionPane.ERROR_MESSAGE);
				cdLayout.show(p_card, "5");
			}
			redraw();
			break;
		case KeyEvent.VK_LEFT :
			Left();
			add();
			if (win()) {
				JOptionPane.showMessageDialog (null, "You Win", "You Won", JOptionPane.ERROR_MESSAGE);
				cdLayout.show(p_card, "4");
			}
			redraw();
			if(lose()) {
				JOptionPane.showMessageDialog (null, "You Lose", "You Lose", JOptionPane.ERROR_MESSAGE);
				cdLayout.show(p_card, "5");
			}
			redraw();
			break;
		}
		System.out.println("Done");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Done");

	}

	public boolean lose() {
		
		int count1=0;
		int temp[][] = {{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}};
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				temp[i][j] = gameboard[i][j];
			}
		}
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(gameboard[i][j]==0) {
					count1++;
				}
			}
		}
		
		if(count1==0) {
			Up();
			Down();
			Right();
			Left();
			int count = 0;
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(temp[i][j] != gameboard[i][j]) {
						count++;
					}
				}
			}
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					gameboard[i][j] = temp[i][j];
				}
			}

			if(count>0) {
				return false;
			}else {
				reset();
				rand();
				return true;
			}

		}
		return false;
	}

	public void reset() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				gameboard[i][j]=originalboard[i][j];
			}
		}
	}
}
