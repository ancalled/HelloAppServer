

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


import java.io.*;

public class QRGeneratorTest {


    public static void generateToFile(String data, String outfile) {
        ByteArrayOutputStream out = QRCode.from(data)
                .to(ImageType.PNG).stream();

        try {
            FileOutputStream fout = new FileOutputStream(outfile);

            fout.write(out.toByteArray());

            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateToFile("Wakawaka", "./out/test.png");
    }
}
