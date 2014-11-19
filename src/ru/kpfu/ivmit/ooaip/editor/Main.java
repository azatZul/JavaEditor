package ru.kpfu.ivmit.ooaip.editor;

import java.io.File;

public class Main {

	private static final String FIXED_EXAMPLES = "examples/fixed_examples";

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Некорректные параметры");
		} else {
			IOProvider provider = InputFactory.getInstance()
					.createTextProvider(new File(args[0]));
			if (provider != null) {
				provider.writeFile(FIXED_EXAMPLES, "Шило", "Мыло");
			}
		}
	}

}
