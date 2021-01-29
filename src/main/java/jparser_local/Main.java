package jparser_local;

import java.util.Arrays;

/**
 * Main runner class
 * 
 * @author Micah
 */
public class Main {
	/**
	 * Run the JParser interface
	 * 
	 * @param args <command> <arguments>...
	 */
	public static void main(String[] args) {
		String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
		Option.translateFlag(args[0]).handle(newArgs);
	}
}
