package modelo;

import Colecciones.ColeccionOrdenes;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Reportes {
    
    public ArrayList reporteMensual(ColeccionOrdenes ord, LocalDate desde, LocalDate hasta) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<Reportable> reportes = new ArrayList();
        
        ArrayList<OrdenServicio> ordenes = ord.listaOrdenes();
        
        if (ordenes == null) return reportes;

        for (OrdenServicio orden : ordenes) {
            if ((orden.getFechaRecepcion().isAfter(desde) || orden.getFechaRecepcion().isEqual(desde)) && (orden.getFechaRecepcion().isBefore(hasta) ||orden.getFechaRecepcion().isEqual(hasta))) {
                reportes.add(orden);
                ArrayList<Dispositivo> dispositivos = orden.listaDispo();
                if (dispositivos != null) {
                    for (Dispositivo dispo : dispositivos) {
                        reportes.add(dispo);
                    }
                }
            }
        }

        return reportes;
    }
    
    public void recReporte(ColeccionOrdenes ord, LocalDate desde, LocalDate hasta) throws IOException {
        
        PdfWriter writer = new PdfWriter("D://hola.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        
        try (Document documento = new Document(pdf)) {
            documento.add(new Paragraph("Reporte Mensual").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)));
            
            ArrayList<Reportable> lista;
            lista = reporteMensual(ord, desde, hasta);
            
            if (lista ==null) return;
            for (int i = 0; i < lista.size(); i++) {
                Reportable repo = lista.get(i);
                documento.add(new Paragraph(repo.descripcionMensual()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)));
            }
        }
    }
}

