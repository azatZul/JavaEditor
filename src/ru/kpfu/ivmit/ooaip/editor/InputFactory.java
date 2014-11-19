package ru.kpfu.ivmit.ooaip.editor;

import java.io.File;

public final class InputFactory {

	private static InputFactory instance = new InputFactory();

	private InputFactory() {
	}

	public static InputFactory getInstance() {
		return instance;
	}

	public IOProvider createTextProvider(File file) {
		IOProvider textProvider = null;
		if (file != null) {
			String fileName = file.getName();
			if (fileName != null) {
				int dotIndex = fileName.lastIndexOf(".");
				if (dotIndex > -1) {
					String extension = fileName.substring(dotIndex + 1,
							fileName.length());
					switch (extension) {
					case XlsTextProvider.XLS_EXT:
						textProvider = new XlsTextProvider(file);
						break;
					case TxtTextProvider.TXT_EXT:
						textProvider = new TxtTextProvider(file);
						break;
					case XmlTextProvider.XML_EXT:
						textProvider = new XmlTextProvider(file);
						break;
					}
				}
			}
		}
		return textProvider;
	}
}
