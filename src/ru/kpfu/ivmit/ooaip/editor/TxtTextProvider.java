package ru.kpfu.ivmit.ooaip.editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class TxtTextProvider extends AbstractTextProvider {

	public static final String TXT_EXT = "txt";

	public TxtTextProvider(File inFile) {
		super(inFile);
		// TODO Auto-generated constructor stub
	}

	static public String replace(File aFile, String source, String text) {

		StringBuilder contents = new StringBuilder();

		try {

			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null; // not declared within while loop

				while ((line = input.readLine()) != null) {
					contents.append(line.replaceAll(source, text));
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return contents.toString();
	}

	static public void setContents(File file, String content)
			throws FileNotFoundException, IOException {
		FileOutputStream fop = null;
		try {

			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void writeFile(String fileName, String source, String text) {
		File file = new File(fileName + "." + TXT_EXT);
		try {
			setContents(file, replace(getReadFile(), source, text));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
