package eu.essentialcomplexity.rdfcanon;

import com.apicatalog.jsonld.http.media.MediaType;
import com.apicatalog.rdf.Rdf;
import com.apicatalog.rdf.RdfDataset;
import com.apicatalog.rdf.io.nquad.NQuadsWriter;
import io.setl.rdf.normalization.RdfNormalize;

import java.io.*;

/**
 * Simple RDF hash demo.
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        String fileIn = String.format("input.nq");
        RdfDataset dataIn = null;
        try {
            dataIn = Rdf.createReader(MediaType.N_QUADS, App.class.getClassLoader().getResourceAsStream(fileIn)).readDataset();
            RdfDataset processed = RdfNormalize.normalize(dataIn);
            OutputStream o = new ByteArrayOutputStream();
            Writer out = new BufferedWriter(new OutputStreamWriter(o));
            NQuadsWriter writer = new NQuadsWriter(out);

            writer.write(processed);
            // @todo Use a better, longer hash?
            System.out.print("RDF hash: " + o.toString().hashCode());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
