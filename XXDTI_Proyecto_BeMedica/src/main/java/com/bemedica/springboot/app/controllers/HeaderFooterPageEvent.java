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
        PdfPTable header = new PdfPTable(4);
        try {
        	header.setWidths(new int[]{19,101,13,26});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            
            
           Paragraph fecha_t = new Paragraph("FECHA:",new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK));
    		Paragraph paciente_t = new Paragraph("PACIENTE:",new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK));
    		Paragraph medico_t = new Paragraph("MEDICO:",new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK));
    		
    		Paragraph fecha_b = new Paragraph(fecha,new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK));
     		Paragraph paciente_b = new Paragraph(pacienten,new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK));
     		Paragraph medico_b = new Paragraph(medico,new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK));
    		
    	
            
           
    	Paragraph folio_t = new Paragraph("FOLIO:",new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK));
   		Paragraph edad_t = new Paragraph("EDAD:",new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK));
   		Paragraph sexo_t = new Paragraph("SEXO:",new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK));
   		
    		
   		Paragraph folio_b = new Paragraph(folio,new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK));
     		Paragraph edad_b = new Paragraph(edad,new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK));
     		Paragraph sexo_b = new Paragraph(sexo,new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK));
   		

   		PdfPCell cel_fecha_t = new PdfPCell(fecha_t);
   		PdfPCell cel_paciente_t = new PdfPCell(paciente_t);
   		PdfPCell cel_medico_t = new PdfPCell(medico_t);
   		
   		PdfPCell cel_fecha_b = new PdfPCell(fecha_b);
   		PdfPCell cel_paciente_b = new PdfPCell(paciente_b);
   		PdfPCell cel_medico_b = new PdfPCell(medico_b);
   		//PdfPCell cel_separacion = new PdfPCell(separacion);
   		

   		PdfPCell cel_folio_t = new PdfPCell(folio_t);
   		PdfPCell cel_edad_t = new PdfPCell(edad_t);
   		PdfPCell cel_sexo_t = new PdfPCell(sexo_t);
   		
   		PdfPCell cel_folio_b = new PdfPCell(folio_b);
   		PdfPCell cel_edad_b = new PdfPCell(edad_b);
   		PdfPCell cel_sexo_b = new PdfPCell(sexo_b);
   		
   		
   		cel_fecha_t.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_fecha_t.disableBorderSide(Rectangle.BOX);
   		cel_fecha_t.setExtraParagraphSpace(1.5f);
   		
   		
   		cel_paciente_t.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_paciente_t.disableBorderSide(Rectangle.BOX);
   		cel_paciente_t.setExtraParagraphSpace(1.5f);
   		
   		
   		cel_medico_t.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_medico_t.disableBorderSide(Rectangle.BOX);
   		cel_medico_t.setExtraParagraphSpace(1.5f);
   		
   		cel_folio_t.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_folio_t.disableBorderSide(Rectangle.BOX);
   		cel_folio_t.setExtraParagraphSpace(1.5f);
   		
   		cel_edad_t.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_edad_t.disableBorderSide(Rectangle.BOX);
   		cel_edad_t.setExtraParagraphSpace(1.5f);
   		
   		cel_sexo_t.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_sexo_t.disableBorderSide(Rectangle.BOX);
   		cel_sexo_t.setExtraParagraphSpace(1.5f);
   		
   		
//7777
   		cel_fecha_b.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_fecha_b.disableBorderSide(Rectangle.BOX);
   		cel_fecha_b.setExtraParagraphSpace(1.5f);
   		
   		
   		cel_paciente_b.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_paciente_b.disableBorderSide(Rectangle.BOX);
   		cel_paciente_b.setExtraParagraphSpace(1.5f);
   		
   		
   		cel_medico_b.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_medico_b.disableBorderSide(Rectangle.BOX);
   		cel_medico_b.setExtraParagraphSpace(1.5f);
   		
   		cel_folio_b.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_folio_b.disableBorderSide(Rectangle.BOX);
   		cel_folio_b.setExtraParagraphSpace(1.5f);
   		
   		cel_edad_b.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_edad_b.disableBorderSide(Rectangle.BOX);
   		cel_edad_b.setExtraParagraphSpace(1.5f);
   		
   		cel_sexo_b.setHorizontalAlignment(Element.ALIGN_LEFT);
   		cel_sexo_b.disableBorderSide(Rectangle.BOX);
   		cel_sexo_b.setExtraParagraphSpace(1.5f);
  
   		
   		header.addCell(cel_fecha_t);
   		header.addCell(cel_fecha_b);
   		
   		header.addCell(cel_folio_t);
   		header.addCell(cel_folio_b);
   	

   		header.addCell(cel_paciente_t);
   		header.addCell(cel_paciente_b);
   		
   		header.addCell(cel_edad_t);
   		header.addCell(cel_edad_b);
   		
   		
   		header.addCell(cel_medico_t);
   		header.addCell(cel_medico_b);
   	
   		header.addCell(cel_sexo_t);
   		header.addCell(cel_sexo_b);
   	
   	
   	
    		
    		
    		
    		
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