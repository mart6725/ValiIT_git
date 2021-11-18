package ee.bcs.valiit.tasks.CODEWARS;



public class CharProblem {


    public static void main(String[] args) {

        howOld("2 year old");

    }
        public static int howOld(final String herOld) {

            //your code here, return correct age as int ; )

            char[]charArray = herOld.toCharArray();
            int age =Character.getNumericValue(charArray[0]);
            System.out.println(age);
            return age;
        }
    }



