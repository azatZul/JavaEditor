package ru.kpfu.ivmit.ooaip.editor;

import java.io.File;

public interface IOProvider {

	// public static final IOProvider DEFAULT_PROVIDER = new IOProvider() {
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see ru.kpfu.ivmit.ooaip.editor.TextProvider#getText()
	// */
	// @Override
	// public String getText(File file) {
	// return "";
	// }
	//
	// @Override
	// public void replaceText(File file, String text) {
	// }
	//
	// };

	// public String getText(File file);

	public File getReadFile();

	public void writeFile(String fileName, String source, String text);

}
