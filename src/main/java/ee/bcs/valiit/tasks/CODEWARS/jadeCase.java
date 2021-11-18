package ee.bcs.valiit.tasks.CODEWARS;

public class jadeCase {

    public static void main(String[] args) {

        System.out.println(toJadenCase(null));


    }

    public static String toJadenCase(String phrase) {

        if (phrase == null || phrase.isEmpty()) {
            return null;
        }

        char[] charArray = phrase.toCharArray();
        String output = "";

        for (int i = 0; i < charArray.length; i++) {
            char charac = charArray[i];
            if (i == 0 || Character.isWhitespace(charArray[i - 1])) {

                output += Character.toUpperCase(charac);
                continue;
            }
            output += charac;

        }

        return output;
    }

}
