import java.io.*;
import java.nio.charset.StandardCharsets;

public class AutomateLines {

    public static void main(String[] args) throws IOException {
        HeaderValidation headerValidation = new HeaderValidation(args);
        //Variables declared
        //String txt = "";
        //String linea = "";
        String inputText = "";
        String inputTextG = "";
        FileOutputStream f;
        FileOutputStream fBDD;

        //---------------------------------------------------------------
        //For search a file
        File text = new File("forExcel.txt");
        File bdd = new File("forGherkin.feature");

        if (text.createNewFile()) {
            System.out.println("File created: " + text.getName());
        } else {
            System.out.println("File .txt was replaced!.");
        }
        if (bdd.createNewFile()) {
            System.out.println("File created: " + bdd.getName());
        } else {
            System.out.println("File .feature was replaced!.");
        }

        FileReader frBDD = new FileReader(bdd);
        BufferedReader brBDD = new BufferedReader(frBDD);

        FileReader fr = new FileReader(text);
        BufferedReader br = new BufferedReader(fr);

        //---------------------------------------------------------------
        //For write a text
        try{
        Boolean isRequired = Boolean.parseBoolean(args[0].toLowerCase());
        int quantityTestCases = Integer.parseInt(args[1]);
        int toHereTcs = Integer.parseInt(args[2]);

        int argNumber = 3;
        int changeTypeTcs = 1;
        String testCase = "";
        String gherkin = "";
        f = new FileOutputStream(text);
        fBDD = new FileOutputStream(bdd);

        while (quantityTestCases <= toHereTcs) {

            if (argNumber >= headerValidation.fields.length){
                argNumber = 3;
                changeTypeTcs++;
            }

            if(changeTypeTcs == 1){
                testCase = "Validar los valores límites del Pattern, mínimos y máximos del campo <" + headerValidation.fields[argNumber] + "> que muestre el código de respuesta 400" ;
                gherkin = "\n\tDado que se enviará una solicitud a la api\n" +
                        "\tCuando completo el campo mencionado con un valor validando Pattern, mínimos y máximos\n" +
                        "\tY selecciono el método \n" +
                        "\tY envío la petición\n" +
                        "\tEntonces obtengo un código de respuesta 400\n\n";
            } else if (changeTypeTcs == 2 && isRequired == true) {
                testCase = "Validar que al ingresar NULL en el campo <" + headerValidation.fields[argNumber] + "> este muestre el código de error 400";
                gherkin = "\n\tDado que se enviará una solicitud a la api\n" +
                        "\tCuando completo el campo mencionado con un valor NULL\n" +
                        "\tY selecciono el método \n" +
                        "\tY envío la petición\n" +
                        "\tEntonces obtengo un código de respuesta 400\n\n";
            } else {
                testCase = "Empty :(";
                gherkin = "\n";
            }

            inputTextG = "#######TC-" + (quantityTestCases) + "#############################################################################################################################################################################################\n" +
                        "Escenario: TC-" + (quantityTestCases) + " - " +  testCase + gherkin;

            inputText = "TC-" + (quantityTestCases) + " - " +  testCase + "\n";

            f.write(inputText.getBytes(StandardCharsets.UTF_8));
            fBDD.write(inputTextG.getBytes(StandardCharsets.UTF_8));

            quantityTestCases++;
            argNumber++;
        }

        //---------------------------------------------------------------
        //For read a text
        /*while ((linea = br.readLine()) != null) {
            txt += linea + "\n";
        }*/
        }catch (Exception E){
            if (br.readLine() == null){
                System.out.println("\nTu Archivo: " + text.getName() + " se creo vacío!");
            }
            if (brBDD.readLine() == null){
                System.out.println("Tu Archivo: " + bdd.getName() + " se creo vacío!\n");
            }
            System.out.println("Algún argumento te falto o lo ingresaste de forma incorrecta!!");
            System.out.println("Formato de args: [boolean] [min] [max] [campos...]");
        }

        fr.close();
        br.close();
        frBDD.close();
        brBDD.close();
    }

}
