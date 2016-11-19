package me.ranol.jnationspeech;

import java.awt.EventQueue;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import me.ranol.jnationspeech.creator.ImageMaker;

public class JNationSpeech extends JFrame {
	private static final long serialVersionUID = -2948406227443264839L;
	private JTextField from;
	private JTextField to;
	private JTextField subject;
	private JTextField bgDir;
	private PictureBox box;

	private JNationSpeech() {
		setBounds(100, 100, 710, 510);
		getContentPane().setLayout(null);

		getContentPane().add(createLabel("누가 :", 12, 15, 35, 15));

		from = new JTextField();
		from.setBounds(50, 10, 235, 25);
		getContentPane().add(from);
		from.setColumns(10);

		getContentPane().add(createLabel("누구에게 :", 12, 45, 65, 15));

		to = new JTextField();
		to.setBounds(75, 40, 210, 25);
		getContentPane().add(to);
		to.setColumns(10);

		getContentPane().add(createLabel("주제 :", 12, 75, 35, 15));

		subject = new JTextField();
		subject.setColumns(10);
		subject.setBounds(50, 70, 235, 25);
		getContentPane().add(subject);

		getContentPane().add(createLabel("왼쪽 위 자막: (누가) 대(누구에게) 담화", 300, 10, 350, 15));
		getContentPane().add(createLabel("가운데 자막: 이러려고 (주제) 자괴감 들고 괴로워", 300, 35, 350, 15));

		bgDir = new JTextField();
		bgDir.setBounds(297, 70, 248, 25);
		getContentPane().add(bgDir);
		bgDir.setColumns(10);

		box = new PictureBox();
		box.setBounds(10, 100, 640, 360);
		getContentPane().add(box);

		JButton bgFile = new JButton("배경 파일 선택");
		bgFile.setBounds(557, 70, 125, 25);
		getContentPane().add(bgFile);
		bgFile.addActionListener(a -> open());

		JButton imageCreate = new JButton("이미지 만들기");
		imageCreate.setBounds(557, 10, 125, 25);
		imageCreate.addActionListener(a -> update());
		getContentPane().add(imageCreate);

		JButton save = new JButton("저장하기");
		save.setBounds(557, 41, 125, 23);
		getContentPane().add(save);
		save.addActionListener(a -> save());
		setTitle("JNationSpeech - 대국민담화 짤 생성기");
	}

	/**
	 * Create the frame.
	 */
	JLabel createLabel(String txt, int... bound) {
		JLabel label = new JLabel(txt);
		label.setBounds(bound[0], bound[1], bound[2], bound[3]);
		return label;
	}

	void open() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "이미지 파일";
			}

			@Override
			public boolean accept(File f) {
				return f.getName().matches(".+\\.(png|jpg)") || f.isDirectory();
			}
		});
		chooser.showOpenDialog(this);
		bgDir.setText(chooser.getSelectedFile().getAbsolutePath());
	}

	void save() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setApproveButtonText("다음 폴더에 저장");
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.showSaveDialog(this);
		String path = chooser.getSelectedFile().getAbsolutePath();
		File file = new File(path);
		try {
			ImageIO.write((RenderedImage) box.getImage(), "PNG", file);
		} catch (Exception e) {
		}
	}

	void update() {
		try {
			box.setImage(ImageMaker.make(!bgDir.getText().isEmpty() ? bgDir.getText() : null,
					from.getText().trim() + " 대" + to.getText().trim() + " 담화",
					"\"이러려고 " + subject.getText().trim() + " 자괴감 들고 괴로워\""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JNationSpeech frame = new JNationSpeech();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
				SwingUtilities.updateComponentTreeUI(frame);
			} catch (Exception e) {
				System.exit(-1);
			}
		});
	}
}
