package util;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Util {

	// ��ȡ��Ļ�ߴ�
	public static Dimension getScreenDimension() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		return dimension;
	}
}
