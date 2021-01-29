package jparser_local;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * CL Options and their responses
 * 
 * @author Micah
 */
public enum Option {
	FROM_DIR {
		@Override
		public void handle(String[] args) {
			ObjectMapper mapper = new ObjectMapper();
			String filename = args[0];
			try {
				mapper.writeValue(new File("ast.json"),
						JParserUtils.getInstance().createAnalysisContextFromDirectory(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	},
	CAMELCASE_JSON {
		@Override
		public void handle(String[] args) {
			try (var br = new BufferedReader(new FileReader(args[0]));
					var bw = new BufferedWriter(new FileWriter("caamelCase.json"))) {
				var regex = Pattern.compile("\".*\":");
				String line;
				while ((line = br.readLine()) != null) {
					line = regex.matcher(line).replaceFirst(match -> deSnakeJSON(match.group()));
					bw.write(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private String deSnakeJSON(String s) {
			StringBuilder sb = new StringBuilder();
			var tokens = s.split("_");
			sb.append(tokens[0]);
			for (int i = 1; i < tokens.length; i++) {
				sb.append(Character.toTitleCase(tokens[i].charAt(0))).append(tokens[i].substring(1));
			}
			return sb.toString();
		}
	};

	/**
	 * Run a CL command
	 * 
	 * @param args The CL arguments, minus the flag that selected this Option
	 */
	public abstract void handle(String[] args);

	public String toString() {
		return "-" + super.toString().toLowerCase(Locale.ENGLISH).replace("_", "");
	}

	public static Option translateFlag(String flag) {
		for (Option o : Option.values())
			if (o.toString().equals(flag))
				return o;
		throw new RuntimeException("Invalid flag " + flag + " provided");
	}
}
