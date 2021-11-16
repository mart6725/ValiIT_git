package ee.bcs.valiit.NOTES;

public class MartNotes {

    public static void main(String[] args) {

        int a = 4;
        String tekst = "Keegi ytles: \n\\\"midagi\"";      // escapemine,\n on reavahetus
        System.out.println(tekst);

        int[] b = new int[100];     // 100 int elementi , v22rtus on 0
        int[] c = {1, 2, 3, 4, 5,};     // tekitab kohe array millel v22rtused, eriti ei kasutata

        //int[][] table = new int[4][8];  // kahem66tmeline array , 2 indexit , iga indexi kombinatsioon on erinev muutuja

        //while tsykkel*****************************************

//        while(a<=100){            // tee nii kaua kuni sulgudes boolean on false
//            System.out.println(a);
//            a++;                    // suurendame a 1 v6rra, kui ei suurenda siis endless loop
//
//        }

//        int a1 = 0;
//        while(a1 < c.length){
//            System.out.println(c[a1]);
//            a1++;
//        }

//FOR LOOP*******************************************************

        for (int i = 0; i < c.length; i++) {              // teeb sama asja nagu eelmine while

            System.out.println(c[i]);
        }
        for (int i = c.length - 1; i >= 0; i--) {            // tagurpidi

            System.out.println(c[i]);
        }
// FOR EACH LOOP**********************************************************

        for(int element:c){                 // vasak muutuja on uus element ja parem on mis array
            System.out.println(element);     // ei tea mis indexi jurues on , ta lihtsalt prindib v2lja



        }

        // BREaK JA CONTINUE*******************  continue --teeb kuhugi maani kui tingimus t2idetud siis skipib selle koha ja j2tkab tsykklit
        // break -- sunnib loopi katkema

// OBJEKTID , MAP, LIST******************************************************************************************


        // vaata slaide ja sample



        }









    }



