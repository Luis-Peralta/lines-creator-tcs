import java.io.*;
import java.nio.charset.StandardCharsets;

public class CreateOnlyLinesToSeparat {
    public static void main(String[] args) throws IOException{

        String inputText = "";
        FileOutputStream f;

        //For search a file
        File text = new File("textSeparates.txt");
        if (text.createNewFile()) {
            System.out.println("File created: " + text.getName());
        } else {
            System.out.println("File .txt was replaced!.");
        }

        FileReader fr = new FileReader(text);
        BufferedReader br = new BufferedReader(fr);

        //---------------------------------------------------------------
        f = new FileOutputStream(text);

        //If you want example: 52, you must put 53
        int quantity = 38;

        for(int i=1;i < quantity;i++){
            if(i<10){
                inputText = "###TC-0"+i+"#############################################################################################################################################################################################\n";
            }else{
                inputText = "###TC-"+i+"#############################################################################################################################################################################################\n";
            }
            f.write(inputText.getBytes(StandardCharsets.UTF_8));
        }



        //For read a text
        String linea = "";
        String txt = "";
        while ((linea = br.readLine()) != null) {
            txt += linea + "\n";
        }

        fr.close();
        br.close();

    }
}
