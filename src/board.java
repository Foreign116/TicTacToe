import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class board {
	private cell[][] ab = new cell[3][3];
	private char whoseturn = 'X';
	private boolean gameover = false;

	public board() {
		JFrame jf = new JFrame();
		jf.setTitle("Welcome");
		jf.setVisible(true);
		jf.setSize(500, 500);
		JPanel jp = new JPanel(new GridLayout(3, 3, 0, 0));
		jp.setVisible(true);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				jp.add(ab[i][j] = new cell());
			}
		}

		jf.add(jp);
	}

	public boolean isfull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (ab[i][j].returntoken() == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	public boolean iswon(char token) {
		boolean k = false;
		for (int i = 0; i < 3; i++) {
			if ((ab[i][0].returntoken() == 'X') && (ab[i][1].returntoken() == 'X') && (ab[i][2].returntoken() == 'X')) {
				k = true;
			}
		}
		for (int i = 0; i < 3; i++) {
			if ((ab[i][0].returntoken() == 'O') && (ab[i][1].returntoken() == 'O') && (ab[i][2].returntoken() == 'O')) {
				k = true;
			}
		}
		for (int j = 0; j < 3; j++) {
			if ((ab[0][j].returntoken() == 'X') && (ab[1][j].returntoken() == 'X') && (ab[2][j].returntoken() == 'X')) {
				k = true;
			}
		}
		for (int j = 0; j < 3; j++) {
			if ((ab[0][j].returntoken() == 'O') && (ab[1][j].returntoken() == 'O') && (ab[2][j].returntoken() == 'O')) {
				k = true;
			}
		}

		if ((ab[0][0].returntoken() == 'X') && (ab[1][1].returntoken() == 'X') && (ab[2][2].returntoken() == 'X')) {
			k = true;
		}
		if ((ab[0][0].returntoken() == 'O') && (ab[1][1].returntoken() == 'O') && (ab[2][2].returntoken() == 'O')) {
			k = true;
		}
		if ((ab[2][0].returntoken() == 'X') && (ab[1][1].returntoken() == 'X') && (ab[0][2].returntoken() == 'X')) {
			k = true;

		}
		if ((ab[2][0].returntoken() == 'O') && (ab[1][1].returntoken() == 'O') && (ab[0][2].returntoken() == 'O')) {
			k = true;

		}
		return k;
	}

	public class cell extends JPanel {
		private char token = ' ';
		JLabel jl;

		public cell() {
			jl = new JLabel();
			jl.setFont(new Font("Serif", Font.PLAIN, 40));
			this.setLayout(new BorderLayout());
			this.add(jl);

			this.setBorder(new LineBorder(Color.black, 1));
			addMouseListener(new MyMouseListener());
		}

		public void settoken(char c) {
			token = c;
		}

		public char returntoken() {
			return token;
		}

		public void addp(char t) {
			jl.setText(t + "");
		}

		private class MyMouseListener extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (gameover) {
					return;
				}
				if (iswon(whoseturn)) {
					whoseturn = (whoseturn == 'X') ? 'O' : 'X';
					System.out.println(whoseturn + " " + "Has won the game");
					whoseturn = ' ';
					gameover = true;
				}
				if (token == ' ' && whoseturn != ' ') {
					settoken(whoseturn);
					addp(whoseturn);

				}
				if (isfull()) {
					System.out.println("Game is a tie");
					whoseturn = ' ';
					gameover = true;
				} else {

					whoseturn = (whoseturn == 'X') ? 'O' : 'X';
				}

			}
		}

	}
}
