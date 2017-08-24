import javax.swing.JFrame;

public class RunnerClass {
	JFrame frame = new JFrame();
	static int width = 800;
	static int height = 500;
	GamePanel panel;

	void setup() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.add(panel);
		panel.startGame();
		frame.addKeyListener(panel);
	}

	RunnerClass() {
		panel = new GamePanel();
	}

	public static void main(String[] args) {
		RunnerClass runner = new RunnerClass();
		runner.setup();
	}
}
