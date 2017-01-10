import javax.swing.JFrame;

import gui.WorkArea;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new WorkArea();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); //to center the frame
		frame.setVisible(true);
	}
}
