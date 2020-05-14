package Serializer;

import Model.Account.Off;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Offs {
    private final static String pathName = "src\\main\\resources\\offs.xml";

    @XmlElement(name = "off")
    private ArrayList<Off> allOffs = new ArrayList<>();
    private Offs(){
    }

    public static void deserializeXML(){
        File file = new File(pathName);
        if(file.exists() && !file.isDirectory() && file.length() > 0) {
            try(BufferedReader input = Files.newBufferedReader(Paths.get(pathName))) {
                Offs offs = JAXB.unmarshal(input, Offs.class);
                Off.setAllOffs(offs.allOffs);
            } catch (IOException e) {
                System.out.println("Error opening file");
            }
        }
    }

    public static void serializeXML(){
        try(BufferedWriter output = Files.newBufferedWriter(Paths.get(pathName))) {
            Offs offs = new Offs();
            offs.allOffs = Off.getAllOffs();
            JAXB.marshal(offs, output);
        }
        catch (IOException e) {
            System.out.println("Error opening file");
        }
    }
}
