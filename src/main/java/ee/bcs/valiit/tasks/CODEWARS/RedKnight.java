package ee.bcs.valiit.tasks.CODEWARS;

public class RedKnight {
    public static void main(String[] args) {

       redKnight(1,6);

    }
    public static PawnDistance redKnight(int knight, long pawn) {

        if(knight==0) {
            long distance = pawn * 2;
            String color = "";

            if (pawn % 2 == 0) {
                color = "White";
            } else {
                color = "Black";
            }

            //System.out.println(distance + "" + color);
            return new PawnDistance(color, distance);
        }else{
            long distance = pawn * 2;
            String color = "";

            if (pawn % 2 == 0) {
                color = "Black";
            } else {
                color = "White";
            }

            //System.out.println(distance + "" + color);
            return new PawnDistance(color, distance);
        }
    }



}
