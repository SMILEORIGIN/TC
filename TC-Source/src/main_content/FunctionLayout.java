package main_content;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import qi_niu.UpLoadImage;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;

import util.ClipboardUtil;
import util.Dialog;

import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import Constant.Constant;
import bean.Image;

public class FunctionLayout {

	private JFrame frame;
	private static ClipboardUtil mClipboardUtil = ClipboardUtil.getInstance();
	private UpLoadImage mUpLoadImage = UpLoadImage.getInstance();
	private boolean now = true;
	private boolean state = true;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FunctionLayout window = new FunctionLayout();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mClipboardUtil.init();
	}

	/**
	 * Create the application.
	 */
	public FunctionLayout() {
		initialize();
		new DropTarget(frame, DnDConstants.ACTION_COPY, new DropTargetAdapter() {
			public void drop(DropTargetDropEvent dtde) {
				try {
					Transferable tf = dtde.getTransferable();
					if (tf.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
						dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
						@SuppressWarnings("unchecked")
						List<File> lt = (List<File>) tf.getTransferData(DataFlavor.javaFileListFlavor);
						Iterator<File> itor = lt.iterator();

						ArrayList<Image> images = new ArrayList<Image>();

						while (itor.hasNext()) {

							File f = (File) itor.next();
							String imagePath = f.getAbsolutePath();
							String houzui = imagePath.substring(imagePath.length() - 4, imagePath.length());

							Image image = new Image();
							image.setPath(imagePath);
							image.setIsLocation(true);

							switch (houzui) {
							case Constant.JPG:
								image.setType(Constant.JPG);
								images.add(image);
								break;
							case Constant.GIF:
								image.setType(Constant.GIF);
								images.add(image);
								break;
							case Constant.PNG:
								image.setType(Constant.PNG);
								images.add(image);
								break;
							default:
								Dialog.showMessageDialog("���ļ�����ͼƬ����ʹ��.jpg,.png,.gif��ͼƬ�ļ�");
							}
						}
						mUpLoadImage.upload(images);
						dtde.dropComplete(true);
					} else {
						dtde.rejectDrop();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int width = 327, height = 336;
		Dimension dimension = util.Util.getScreenDimension();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setBounds(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2, width, height);
		// ������x��ť�Ĳ����������Ϊ�����˳�����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("��ͼƬ�Ͻ����ڼ����ϴ�");

		JButton button = new JButton("�ϴ����а�ͼƬ");
		button.setBackground(new Color(0, 204, 255));
		button.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		button.setBounds(166, 10, 145, 42);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String imagePath = mClipboardUtil.getImageClipboardPath();
				if (imagePath == null) {
					Dialog.showMessageDialog("���ڼ��а�û�н�ͼ");
				} else {
					// �ϴ�ͼƬ
					try {
						Image image = new Image();
						image.setPath(imagePath);
						image.setType(Constant.PNG);
						image.setIsLocation(false);
						ArrayList<Image> images = new ArrayList<>();
						images.add(image);
						mUpLoadImage.upload(images);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JToggleButton button_1 = new JToggleButton("�������а����");
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		button_1.setBackground(new Color(0, 204, 255));
		button_1.setBounds(10, 255, 145, 42);
		frame.getContentPane().add(button_1);

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (now) {
					// ��������ʱҪע��һ�����������������ʱ�������Ѿ���ͼƬ�ˣ���Ӧ�ý�����������Ϊ�ռ�����Ϊ�ı����ͣ��Ա�����ĵļ����������������ͱ仯
					mClipboardUtil.add("");
					button_1.setText("�رռ��а����");
					mClipboardUtil.addListener();
					now = !now;
				} else {
					mClipboardUtil.add("");
					button_1.setText("�������а����");
					mClipboardUtil.removedListener();
					now = !now;
				}
			}
		});

		JButton button_2 = new JButton("ɾ�������ļ�");
		button_2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		button_2.setBackground(new Color(0, 204, 255));
		button_2.setBounds(10, 10, 145, 42);
		frame.getContentPane().add(button_2);

		JToggleButton button_3 = new JToggleButton("���������ö�");
		button_3.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		button_3.setBackground(new Color(0, 204, 255));
		button_3.setBounds(166, 255, 145, 42);
		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (state) {
					state = false;
					button_3.setText("�رմ����ö�");
					frame.setAlwaysOnTop(true);
				} else {
					state = true;
					button_3.setText("���������ö�");
					frame.setAlwaysOnTop(false);
				}
			}
		});
		frame.getContentPane().add(button_3);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setIcon(new ImageIcon(FunctionLayout.class.getResource("/image/xxxx.png")));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(79, 72, 162, 162);
		frame.getContentPane().add(lblNewLabel);

		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean result = Config.removedConfig();
				if (!result) {
					Dialog.showMessageDialog("ɾ�������ļ�����������Ӧ���ٴγ���");
				}
				Dialog.showMessageDialog("ɾ���ɹ�������������Ӧ��");
				frame.dispose();
				System.exit(0);
			}
		});

	}
}
