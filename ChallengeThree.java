import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChallengeThree {
	
	// change the file name when necessary
	public static String fileName = "test.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		String result = "";
		ArrayList<String> lines = new ArrayList<>();
		Scanner text = new Scanner(new File (fileName));
		while (text.hasNext()) {
			String line = text.next();
			lines.add(line);
		}
		text.close();
		for (int i = 1; i < lines.size() - 1; i++) {
			String first = lines.get(i-1);
			String second = lines.get(i);
			String third = lines.get(i+1);
			for (int j = 3; j < 77; j++) {
				if (second.charAt(j) > 96 && second.charAt(j) < 123) {
					if (checkCurrent(second, j)) {
						if (checkOthers(first, third, j)) {
							result += second.charAt(j);
						}
					}
				}
			}
		}
		// result is a list of all the characters which are surrounded by bodyguards in order
		System.out.println(result);
	}
	
	public static boolean checkCurrent(String second, int j) {
		boolean onlyThree = false;
		if (j == 3 || j == 76) {
			onlyThree = true;
		} else {
			char begin = second.charAt(j-4);
			char end = second.charAt(j+4);
			onlyThree = (begin > 96 && begin < 123) && (end > 96 && end < 123);
		}
		String before = second.substring(j-3, j);
		String after = second.substring(j+1, j+4);
		for (int i = 0; i < before.length(); i++) {
			if ((before.charAt(i) > 96 && before.charAt(i) < 123) || (after.charAt(i) > 96 && after.charAt(i) < 123)) {
				return false;
			}
		}
		return true && onlyThree;
	}
	
	public static boolean checkOthers(String first, String third, int j) {
		boolean onlyThree = false;
		if (j == 3 || j == 76) {
			onlyThree = true;
		} else {
			char a = first.charAt(j-2);
			char b = first.charAt(j+2);
			char c = third.charAt(j-2);
			char d = third.charAt(j+2);
			onlyThree = (a > 96 && a < 123) && (b > 96 && b < 123) && (c > 96 && c < 123) && (d > 96 && d < 123);
		}
		String top = first.substring(j-1, j+2);
		String bottom = third.substring(j-1, j+2);
		for (int i = 0; i < top.length(); i++) {
			if ((top.charAt(i) > 96 && top.charAt(i) < 123) || (bottom.charAt(i) > 96 && bottom.charAt(i) < 123)) {
				return false;
			}
		}
		return true && onlyThree;
	}
}
