package me.ranol.jnationspeech;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class JNationSpeech extends JFrame {
	private static final long serialVersionUID = -2948406227443264839L;
	private JTextField from;
	private JTextField to;
	private JTextField subject;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	JLabel createLabel(String txt, int... bound) {
		JLabel label = new JLabel(txt);
		label.setBounds(bound[0], bound[1], bound[2], bound[3]);
		return label;
	}

	private JNationSpeech() {
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(null);

		getContentPane().add(createLabel("누가 :", 12, 10, 35, 15));

		from = new JTextField();
		from.setBounds(50, 7, 235, 21);
		getContentPane().add(from);
		from.setColumns(10);

		getContentPane().add(createLabel("누구에게 :", 12, 35, 65, 15));

		to = new JTextField();
		to.setBounds(75, 32, 210, 21);
		getContentPane().add(to);
		to.setColumns(10);

		getContentPane().add(createLabel("주제 :", 12, 60, 35, 15));

		subject = new JTextField();
		subject.setColumns(10);
		subject.setBounds(50, 60, 235, 21);
		getContentPane().add(subject);

		getContentPane().add(createLabel("왼쪽 위 자막: (누가) 대(누구에게) 담화", 300, 10, 350, 15));
		getContentPane().add(createLabel("가운데 자막: 이러려고 (주제) 자괴감 들고 괴로워", 300, 35, 350, 15));

		textField = new JTextField();
		textField.setBounds(297, 60, 235, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("배경 파일 선택");
		btnNewButton.setBounds(544, 59, 128, 23);
		getContentPane().add(btnNewButton);
		setTitle("JNationSpeech - 대국민담화 짤 생성기");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JNationSpeech frame = new JNationSpeech();
			frame.setVisible(true);
			try {
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
				SwingUtilities.updateComponentTreeUI(frame);
			} catch (Exception e) {
				System.exit(-1);
			}
		});
	}
}
