package com.bemedica.springboot.app.controllers;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;

public class HeaderFooterPageEvent extends PdfPageEventHelper {
    private PdfTemplate t;
    String fecha = null;
    String medico = null;
    String pacienten = null;
    String folio= null;
    String edad= null;
    String sexo= null;
    
	public HeaderFooterPageEvent(String fecha, String medico, String pacienten, String folio, String edad,
			String sexo) {
		super();
		this.fecha = fecha;
		this.medico = medico;
		this.pacienten = pacienten;
		this.folio = folio;
		this.edad = edad;
		this.sexo = sexo;
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(30, 16);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        addHeader(writer);
        addFooter(writer);
    }

    private void addHeader(PdfWriter writer){
        PdfPTable header = new PdfPTable(2);
        try {
        	 header.setWidths(new int[]{13, 13});
             header.setTotalWidth(527);
             header.setLockedWidth(true);
  
        	Paragraph info = new Paragraph("FECHA: "+fecha+"\nMEDICO: "+medico+"\nPACIENTE: "+pacienten+"",new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK));
    		Paragraph addr = new Paragraph("FOLIO: "+folio+"\nEDAD: "+edad+"\nSEXO: "+sexo+"",new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK));
    		  		
    		PdfPCell cell = new PdfPCell(info);
    		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    		cell.disableBorderSide(Rectangle.BOX);
    		cell.setExtraParagraphSpace(1.5f);
    		header.addCell(cell);
    		cell = new PdfPCell(addr);
    		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    		cell.disableBorderSide(Rectangle.BOX);
    		cell.setExtraParagraphSpace(1.5f);
    		header.addCell(cell);

            header.writeSelectedRows(0, -1, 34,720, writer.getDirectContent());
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    private void addFooter(PdfWriter writer){
        PdfPTable footer = new PdfPTable(3);
        try {
            // set defaults
            footer.setWidths(new int[]{5,39, 17});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
     
    		Paragraph uno = new Paragraph("RESPONSABLE DEL LABORATORIO:" ,new Font(FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL,BaseColor.BLACK));
    		Paragraph  dos = new Paragraph("Q.F.B SELENE lEDEZMA RUIZ\nCED. PFOF. 2661181" ,new Font(FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL,BaseColor.BLACK));
    		Paragraph  pag = new Paragraph(String.format("Pág. %d ", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8));
    		
    		PdfPCell ce = new PdfPCell(uno);
    		PdfPCell cr = new PdfPCell(dos);
    		
    		PdfPCell p = new PdfPCell(pag);
    	
    		ce.setHorizontalAlignment(Element.ALIGN_RIGHT);
    		ce.disableBorderSide(Rectangle.BOX);
    		ce.setBorderColor(BaseColor.LIGHT_GRAY);
    		ce.setExtraParagraphSpace(1.5f);
    		
    		cr.setHorizontalAlignment(Element.ALIGN_LEFT);
    		cr.disableBorderSide(Rectangle.BOX);
    		cr.setBorderColor(BaseColor.LIGHT_GRAY);
    		cr.setExtraParagraphSpace(1.5f);
    		
    		p.setHorizontalAlignment(Element.ALIGN_LEFT);
    		p.disableBorderSide(Rectangle.BOX);
    		p.setBorderColor(BaseColor.LIGHT_GRAY);
    		p.setExtraParagraphSpace(1.5f);

            // add copyright
            //footer.addCell(new Phrase(String.format("Pág. %d ", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));            
            footer.addCell(p);
    		footer.addCell(ce);
            footer.addCell(cr);

           // add placeholder for total page count
            PdfPCell totalPageCount = new PdfPCell();
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            
            System.out.println("El total de la paginas es %d " +totalPageCount);
            footer.addCell(totalPageCount);


            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 100, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }
}