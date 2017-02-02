package qi_niu;

import java.io.IOException;
import java.util.ArrayList;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import bean.Image;
import main_content.Config;
import util.ClipboardUtil;
import util.DateUtil;
import util.Dialog;
import util.ImageFileDispose;

public class UpLoadImage {

	// �����
	private static UpLoadImage mUpLoadImage;

	// �ϴ�����ţ�󱣴���ļ���
	private String mUpToken;
	// �ϴ��ļ���·��
	private String mFilePath;
	// Ҫ�ϴ����ļ���
	private String mFileName;
	// ��Կ����
	private Auth mAuth;
	// �ϴ�������
	private UploadManager mUploadManager;
	// ���а����
	private ClipboardUtil mClipboardUtil;

	private UpLoadImage() {

	}

	public static UpLoadImage getInstance() {

		if (mUpLoadImage == null) {
			synchronized (UpLoadImage.class) {
				if (mUpLoadImage == null) {
					mUpLoadImage = new UpLoadImage();
				}
			}

		}

		return mUpLoadImage;

	}

	// ��ʼ��
	public void init() {
		Init init = new Init();
		mAuth = init.getAuth();

		// �Զ�ʶ��Ҫ�ϴ��Ŀռ�(bucket)�Ĵ洢�����ǻ��������������ϡ�
		Zone z = Zone.autoZone();
		Configuration c = new Configuration(z);
		// �����ϴ�����
		mUploadManager = new UploadManager(c);
		// ���ϴ���ʹ��Ĭ�ϲ��ԣ�ֻ��Ҫ�����ϴ��Ŀռ����Ϳ�����
		mUpToken = mAuth.uploadToken(init.getBucketName());

	}

	public void upload(ArrayList<Image> uploadList) throws IOException {

		if (mUploadManager == null || mUpToken == null) {
			init();
		}

		mClipboardUtil = ClipboardUtil.getInstance();

		new Thread() {
			public void run() {

				for (int i = 0; i < uploadList.size(); i++) {
					Image image = uploadList.get(i);

					mFileName = DateUtil.getDate() + image.getType();
					mFilePath = image.getPath();

					try {
						// ����put�����ϴ�
						mUploadManager.put(mFilePath, mFileName, mUpToken);
						// ����ϴ��ɹ�������ת����MarkDown��ʽ��ͼƬ���ӣ�Ȼ���Ƶ����а�
						String url = "![](" + "http://" + Config.getConfig()[3] + "/" + mFileName + ")\n";

						mClipboardUtil.append(url);

						if (!image.getIsLocation()) {
							ImageFileDispose.deleteImageFile(image.getPath());
						}

						System.out.println(url);

					} catch (QiniuException e) {
						// ����ϴ�ʧ�ܣ��򽫼��а�����Ա����¼���
						mClipboardUtil.appendClear();
						Response r = e.response;
						try {
							// ����ʧ��ʱ��ӡ���쳣����Ϣ
							// ��Ӧ���ı���Ϣ
							Dialog.showMessageDialog(r.toString() + "\n" + r.bodyString());
							System.out.println(r.bodyString());
						} catch (QiniuException e1) {
							e1.printStackTrace();
						}
					}
				}

				mClipboardUtil.appendComplete();
			}
		}.start();
	}

}
