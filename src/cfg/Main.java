package cfg;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String path = "test3.txt";
		Parser p = new Parser(path);
		p.variableParser();

	}

}