package ee.bcs.valiit.tasks.CODEWARS;

public class CamelCase {


    public static void main(String[] args) {

        System.out.println(toCamelCase("The-wild-knight"));

    }


    static String toCamelCase(String s){

        if (s == null || s.isEmpty()) {
            return null;
        }

        char[] charArray = s.toCharArray();
        String output =charArray[0]+ "";

        for (int i = 1; i < charArray.length; i++) {
            char charac = charArray[i];
            if (charArray[i - 1]=='-' || charArray[i - 1]=='_') {

                output += Character.toUpperCase(charac);
                continue;
            }
            output += charac;

        }

        return output.replaceAll("[^a-zA-Z0-9]", "");




    }
}
