package main_content;

import java.awt.Dimension;

import util.ClipboardUtil;
import util.Dialog;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class StartLayout {

	private JFrame frame;

	/**
	 * Launch the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if (Config.checkConfig()) {
					// ����Ѿ��л����sk��sk����빦�ܽ���
					FunctionLayout.start();
				} else {
					// �����û�����ak��sk��Ϣ����������
					try {
						StartLayout window = new StartLayout();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public StartLayout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		int width = 560, height = 420;
		Dimension dimension = util.Util.getScreenDimension();

		frame = new JFrame();
		frame.setBounds(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2, width, height);
		// ������x��ť�Ĳ����������Ϊ�����˳�����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("��ţ�ƿ���ͼƬ�ϴ�����");

		JLabel lblAk = new JLabel("Access Key");
		lblAk.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblAk.setBounds(10, 46, 103, 32);
		frame.getContentPane().add(lblAk);

		JLabel lblSecretkey = new JLabel("Secret Key");
		lblSecretkey.setFont(new Font("΢���ź�", Font.PLAIN, 17));
		lblSecretkey.setBounds(10, 114, 103, 32);
		frame.getContentPane().add(lblSecretkey);

		JTextField editorPane = new JTextField();
		editorPane.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editorPane.setBounds(123, 46, 395, 32);
		frame.getContentPane().add(editorPane);

		JTextField editorPane_1 = new JTextField();
		editorPane_1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editorPane_1.setBounds(123, 114, 395, 32);
		frame.getContentPane().add(editorPane_1);

		JLabel lblBucketname = new JLabel("BucketName");
		lblBucketname.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblBucketname.setBounds(10, 184, 103, 32);
		frame.getContentPane().add(lblBucketname);

		JTextField editorPane_2 = new JTextField();
		editorPane_2.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editorPane_2.setBounds(123, 184, 395, 32);
		frame.getContentPane().add(editorPane_2);

		JTextField editorPane_3 = new JTextField();
		editorPane_3.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editorPane_3.setBounds(123, 251, 395, 32);
		frame.getContentPane().add(editorPane_3);

		JButton button = new JButton("����");
		button.setBackground(new Color(0, 191, 255));
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String ak = editorPane.getText();
				String sk = editorPane_1.getText();
				String bucketName = editorPane_2.getText();
				String domain = editorPane_3.getText();

				if (ak.isEmpty() || sk.isEmpty() || bucketName.isEmpty() || domain.isEmpty()) {

					Dialog.showMessageDialog("�����������Ϊ��");

				} else {

					boolean result = Config.createConfig(ak, sk, bucketName, domain);

					if (!result) {
						// ��������ļ�д���������ʾ
						Dialog.showMessageDialog("�½������ļ�����������");
					} else {
						// ����������ak��sk�������ܽ��棬���رյ�ǰ����
						frame.dispose();
						FunctionLayout.start();
					}
				}
			}
		});

		button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button.setBounds(103, 328, 129, 42);
		frame.getContentPane().add(button);

		JLabel lblDomain = new JLabel("Domain       ");
		lblDomain.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblDomain.setBounds(10, 251, 103, 32);
		frame.getContentPane().add(lblDomain);

		JButton button_1 = new JButton("\u5173\u4E8E");
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_1.setBackground(new Color(0, 191, 255));
		button_1.setBounds(315, 328, 129, 42);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��������Ϣ
				int result = JOptionPane.showConfirmDialog(frame, "���ߣ�smileorigin\n" + "���䣺smilewushiyang@foxmail.com\n"
						+ "QQ   ��412390731\n" + "��Ŀ��̬���עGitHub��ҳ\n" + "�Ƿ�����ĿGitHub��ַ��");
				if (result == 0) {
					ClipboardUtil clipboardUtil = ClipboardUtil.getInstance();
					clipboardUtil.init();
					clipboardUtil.add("https://github.com/SMILEORIGIN/TC");
				}
			}
		});

	}
}
