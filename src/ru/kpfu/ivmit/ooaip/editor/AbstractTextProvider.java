package ru.kpfu.ivmit.ooaip.editor;

import java.io.File;

public abstract class AbstractTextProvider implements IOProvider {

	private final File inFile;

	public AbstractTextProvider(File inFile) {
		this.inFile = inFile;

	}

	@Override
	public File getReadFile() {
		return inFile;
	}

}
