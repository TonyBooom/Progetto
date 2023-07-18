package Control;

import Model.*;   

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

@WebServlet("/Invoice")
public class Invoice extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	

        OrdineDAO dao = new OrdineDAO();
        OrdineBean bean = new OrdineBean();

        try {
            bean = dao.doRetrieveByKey(Integer.parseInt(request.getParameter("ordine")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Creazione del documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDType0Font font = PDType0Font.load(document, new File("C:\\Users\\39351\\git\\Progetto\\TSW_2023\\Helvetica.ttf"));

        // Caricamento del logo
        PDImageXObject logoImage = PDImageXObject.createFromFile("C:\\Users\\39351\\git\\Progetto\\TSW_2023\\logo.jpg", document);

        // Creazione del contenuto della fattura
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(font, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 700);
        contentStream.showText("Fattura");
        contentStream.endText();
        
        // Creazione della tabella
        float margin = 50;
        float yStart = 550;
        float yPosition = yStart;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float tableHeight = 100;
        float rowHeight = 20;
        int numberOfColumns = 4;
        float[] columnWidths = { tableWidth / numberOfColumns,tableWidth / numberOfColumns,tableWidth / numberOfColumns, tableWidth / numberOfColumns, tableWidth / numberOfColumns };
        float cellMargin = 5f;

        // Aggiunta del logo
        float logoWidth = 100;
        float logoHeight = 50;
        contentStream.drawImage(logoImage, 50, 650, logoWidth, logoHeight);
       
        
        // Aggiunta dei dati dell'azienda in alto a destra
        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(page.getMediaBox().getWidth() - 250, 700);
        contentStream.showText("Sito TSW");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(page.getMediaBox().getWidth() - 250, 685);
        contentStream.showText("Via Piazza Largo CAP XXXXX PROV XX");
        contentStream.endText();
        
        // Aggiunta dei dati del cliente in fondo a sinistra
        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, 75);
        contentStream.showText(bean.getCodUtente().getNome()+
        " "+bean.getCodUtente().getCognome());
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(margin, 65);
        contentStream.showText("Via  "+bean.getCodConsegna().getVia() +
        		" CAP "+bean.getCodConsegna().getCap()+
        		" Citta' "+bean.getCodConsegna().getCitta());
        contentStream.endText();
        
        
        // Aggiunta della data di generazione della fattura
        contentStream.setFont(font, 10);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, 40);
        contentStream.showText("Data generazione Fattura: " + currentDate);
        contentStream.endText();

        drawTable(contentStream, yPosition, tableWidth, tableHeight, rowHeight, cellMargin, columnWidths, font, bean);

        contentStream.close();

        // Impostazione dell'intestazione della risposta per il download del file
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"fattura.pdf\"");

        // Invio del documento come flusso di output
        OutputStream outputStream = response.getOutputStream();
        document.save(outputStream);
        document.close();
        outputStream.flush();
        outputStream.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void drawTable(PDPageContentStream contentStream, float y, float tableWidth, float tableHeight,
            float rowHeight, float cellMargin, float[] columnWidths, PDType0Font font, OrdineBean bean)
            throws IOException {
        float margin = 50;

        // Disegna le righe e le colonne della tabella
        float nextY = y;
        for (int i = 0; i < bean.getComposizione().size(); i++) {
            contentStream.moveTo(margin, nextY - rowHeight);
            contentStream.lineTo(margin + tableWidth, nextY - rowHeight);
            contentStream.stroke();
            nextY -= rowHeight;
        }

        // Disegna le colonne della tabella
        float x = margin;
        for (float columnWidth : columnWidths) {
            contentStream.moveTo(x + columnWidth, y);
            contentStream.lineTo(x + columnWidth, y - tableHeight);
            contentStream.stroke();
            x += columnWidth;
        }

        // Scrivi l'intestazione della tabella
        float headerY = y - 15;
        float headerX = margin + cellMargin;
        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(headerX, headerY);
        contentStream.showText("Descrizione");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(headerX + columnWidths[0] + columnWidths[1], headerY);
        contentStream.showText("Prezzo");
        contentStream.endText();
        
        contentStream.beginText();
        contentStream.newLineAtOffset(headerX + columnWidths[0], headerY);
        contentStream.showText("Data Prenotazione");
        contentStream.endText();

        
        contentStream.beginText();
        contentStream.newLineAtOffset(headerX + columnWidths[0] + columnWidths[1] + columnWidths[2], headerY);
        contentStream.showText("Data acquisto");
        contentStream.endText();

        // Scrivi il contenuto della tabella
        float textY = headerY - rowHeight;
        float textX = margin + cellMargin;
        for (ProdottoBean p : bean.getComposizione().keySet()) {
            contentStream.setFont(font, 10);
            contentStream.beginText();
            contentStream.newLineAtOffset(textX, textY);
            contentStream.showText(p.getNome());
            contentStream.endText();

   
            contentStream.beginText();
            contentStream.newLineAtOffset(textX + columnWidths[0] + columnWidths[1], textY);
            contentStream.showText("€" + String.valueOf(bean.getComposizione().get(p).get(2).floatValue()));
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.newLineAtOffset(textX + columnWidths[0], textY);
            contentStream.showText("Inserire datapren");
            contentStream.endText();

            
            contentStream.beginText();
            contentStream.newLineAtOffset(textX + columnWidths[0] + columnWidths[1] + columnWidths[2], textY);
            contentStream.showText(bean.getDataOrdine().toString()); // Inserisci la data d'acquisto
            contentStream.endText();
            
            textY -= rowHeight;
        }
    }
}