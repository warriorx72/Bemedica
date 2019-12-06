package com.bemedica.springboot.app.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bemedica.springboot.app.models.dao.IResultados;
import com.bemedica.springboot.app.models.entity.Resultados;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class ResultadosController {
	
	
	@Autowired
	@Qualifier("ResultadosDaoJPA")
	private IResultados ResultadosDao;
	
	@Autowired
	private ServletContext context;
	
	String nombrepdf;
	
	@RequestMapping (value="/listar_ordenes/{id}", method=RequestMethod.GET)
	public String listar (@PathVariable (value="id") Long id, Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		String edad=null;
		List<Object []> paciente =ResultadosDao.PacienteOrden(id);
	    
	       
	       for (Object[] p : paciente) {
	       
	       	if (p[2].toString()=="0" ){
	       		edad=p[3].toString()+" mes(es)";
	       	}else{edad=p[2].toString()+" año(s)";}
	       	if ( p[2].toString()=="0" && p[3].toString()=="0") {
	       		if(p[10].toString()=="0"){
	       			edad="Recien nacido";
	       		}else{ edad=p[10].toString()+" día(s)";}
	       	}
	       }
	       m.addAttribute("edad", edad);
		
		return "listar_ordenes";
	}
	
	
	
	
	
	@RequestMapping (value="/guardar_resultado_linea/{id}/{lo}", method=RequestMethod.POST)
	public String guardar (
			@PathVariable (value="id") Long id, 
			@PathVariable (value="lo") Long lo,
			@Valid Resultados resultado,
			Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		
		resultado.setValidacion("1");
		Long auxLineas= resultado.getOrdenEstudioId();
		int i=0;
		ResultadosDao.save(resultado);
		
		List <String> aux  = ResultadosDao.ValidarLinea(auxLineas);
		for (String a : aux) {
			if ( a.equals("1") ) {
				i++;
			}
		}
		if(i== aux.size()) {
			ResultadosDao.Actualizacion_linea(auxLineas);			
		} 
		m.addAttribute("resul",  ResultadosDao.findAll(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
	
		if (ResultadosDao.LineasOrden(id).size() == ResultadosDao.ValidarOrden(id).size() ){
			 System.out.println("la condicion se cumpple ");
			 ResultadosDao.Actualizacion_Orden(id);
		}
		
		return "listar_ordenes";
	}
	
	
	
	@RequestMapping (value="/agregar_resultado/{id}/{i}/{t}/{lo}", method=RequestMethod.GET)
	public String agregar_resultado (@PathVariable (value="id") Long id, 
			@PathVariable (value="i") Long i,
			@PathVariable (value="t") String tipo,
			@PathVariable (value="lo") Long lo,
			Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		m.addAttribute("lo", lo);
		Resultados auxr = new Resultados ();
		m.addAttribute("aux", auxr);
		if ( ResultadosDao.findAll(lo) == null ||  ResultadosDao.findAll(lo).isEmpty() ){
			if (tipo.equals("estudio")){
				List <Object[]> aux  = ResultadosDao.Estudios(i);
				Resultados resul = new Resultados ();
				 for (Object[] a : aux) {
					 resul.setOrdenEstudioId(lo);
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuanti(0.00);
					 }
					 resul.setValidacion("0");
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 resul.setPerfil(a[1].toString());
					 ResultadosDao.save(resul);
				 }
			}
			if (tipo.equals("perfil")){
				List <Object[]> aux  = ResultadosDao.Perfil(i);
				String nombre = ResultadosDao.NombrePerfil(i);
				for (Object[] a : aux) {
					Resultados resul = new Resultados ();
					 resul.setOrdenEstudioId(lo);
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuanti(0.00);
					 }
					 resul.setValidacion("0");
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 resul.setPerfil(nombre);
					 ResultadosDao.save(resul);
					}
			}
			if (tipo.equals("paquete")){
				List <Object[]> aux  = ResultadosDao.Paquete(i);
				for (Object[] a : aux) {
					Resultados resul = new Resultados ();
					 resul.setOrdenEstudioId(lo);
					 if (a[2].toString().equals("Cualitativo")){
						 resul.setResultadoCuali("Resultado...");
					 }
					 if (a[2].toString().equals("Cuantitativo")){
						 resul.setResultadoCuanti(0.00);
					 }
					 resul.setComentario("");
					 resul.setValidacion("0");
					 if (a[3].toString()=="null" || a[3].toString().equals("null") ){
						 resul.setPerfil(a[1].toString());
					 }else {
						 resul.setPerfil(a[3].toString());
					 }						 
					 resul.setEstudio_id(Long.valueOf(a[0].toString()));
					 resul.setEstudioNombre(a[1].toString());
					 ResultadosDao.save(resul);
					}	
			}
		}
		m.addAttribute("resul",  ResultadosDao.findAll(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		return "listar_ordenes";
	}
	
	
	
	
	@RequestMapping (value="/ayuda", method=RequestMethod.GET)
	public String ayuda ( Map<String, Object> model, Model m) {
	
		return "ayuda";
	}
	
	
	@RequestMapping(value = "/descargar_resultados/{id}")
	public void listaar(@PathVariable (value="id") Long id, Model model, HttpServletRequest request, HttpServletResponse response)throws Exception {
		Long aux= 1000000+id;
		Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd-hhmmss");
	    nombrepdf =  "ORD"+aux+"-"+formateador.format(ahora);
	    
	    System.out.println("La hora de ess"+ nombrepdf);
		String fullPaths = request.getServletContext().getRealPath("/" +nombrepdf+".pdf");
		this.pdf(request, id, fullPaths);
		filedownloadPDF(fullPaths, response,id);

	}
	
public void pdf(HttpServletRequest request , Long id , String fullPath) throws FileNotFoundException, DocumentException{
		
		
		//String fullPath = request.getServletContext().getRealPath("/" + "Resultado" + ".pdf");
		
		Font fuen_1=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.BOLD,BaseColor.BLACK);
		
		Font fuen_2 =new Font(FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL,BaseColor.BLACK);
		
		Font fuen_3 =new Font(FontFamily.TIMES_ROMAN,11.0f,Font.NORMAL,BaseColor.BLACK);
		
		Font fuen_comentario =new Font(FontFamily.TIMES_ROMAN,8.0f,Font.NORMAL,BaseColor.BLACK);
		Font fuen_validacion =new Font(FontFamily.TIMES_ROMAN,8.0f,Font.NORMAL,BaseColor.BLACK);
	    
		
		//Document documento = new Document();
		Document documento = new Document(PageSize.A4, 36, 36, 182, 120);
		
		FileOutputStream ficheroPdf = new FileOutputStream(fullPath);
		PdfWriter writer =PdfWriter.getInstance(documento, ficheroPdf);

		
		
		String fecha = null;
        String medico = null;
        String pacienten = null;
        String folio= null;
        String edad= null;
        String sexo= null;
       List<Object []> paciente =ResultadosDao.PacienteOrden(id);
    
       
       for (Object[] p : paciente) {
       	
       	fecha = p[5].toString();
       	
       	if (p[7].toString()== null){
       		medico="A QUIEN CORRESPONDA";
       	}else{medico = p[7].toString();}
       	
       	pacienten =p[1].toString();
       	folio=p[6].toString();
       	
       	
      
       	
       	if (p[2].toString()=="0" ){
       		edad=p[3].toString()+" mes(es)";
       	} 	
       	else  {
       		edad=p[2].toString()+" año(s)";
       	}
       	
       	if ( p[2].toString()=="0" && p[3].toString()=="0") {
       		if(p[10].toString()=="0"){
       			edad="Recien nacido";
       		}else
       		{
       		edad=p[10].toString()+" día(s)";}
       	}
       	
       	
       	
       	sexo=p[4].toString();
       	
       }
		 
		 
		
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(fecha,medico,pacienten,folio,edad, sexo);
        writer.setPageEvent(event);
        documento.open();	
     
        
        
		PdfPTable tableR = new PdfPTable(4);
        
		tableR.setWidths(new float[] { 40.0f, 20.0f,20.0f, 30.0f });

		tableR.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tableR.setWidthPercentage(100);
		tableR.setHeaderRows(1);
	

		Paragraph Estudio = new Paragraph("Estudio");
		Paragraph Resultado = new Paragraph("Resultado");
		Paragraph Espacio = new Paragraph("");
		Paragraph VR = new Paragraph("Valores de Referencia");

		
		PdfPCell ce = new PdfPCell(Estudio);
		PdfPCell cr = new PdfPCell(Resultado);
		PdfPCell esp = new PdfPCell(Espacio);
		PdfPCell cvr = new PdfPCell(VR);
		
		
		ce.setHorizontalAlignment(Element.ALIGN_LEFT);
		ce.disableBorderSide(Rectangle.BOX);
		ce.setExtraParagraphSpace(1.5f);
		
		ce.setHorizontalAlignment(Element.ALIGN_LEFT);
		ce.disableBorderSide(Rectangle.BOX);
		ce.setExtraParagraphSpace(1.5f);
		
		cr.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cr.disableBorderSide(Rectangle.BOX);
		cr.setExtraParagraphSpace(1.5f);
		
		
		cvr.setHorizontalAlignment(Element.ALIGN_LEFT);
		cvr.disableBorderSide(Rectangle.BOX);
		cvr.setExtraParagraphSpace(1.5f);
		
		esp.setHorizontalAlignment(Element.ALIGN_CENTER);
		esp.disableBorderSide(Rectangle.BOX);
		esp.setExtraParagraphSpace(1.5f);
		
		tableR.addCell(ce);
		tableR.addCell(cr);
		tableR.addCell(esp);
		tableR.addCell(cvr);

		tableR.addCell(esp);
		tableR.addCell(esp);
		tableR.addCell(esp);
		tableR.addCell(esp);

		
		List<Object []> LineasOrdenes =ResultadosDao.LineasOrden(id);
		for (Object[] lo : LineasOrdenes) {
			
			System.out.print(lo[0] +" ");
			System.out.print(lo[1] +" ");
			System.out.print(lo[2] +" ");
			System.out.print(lo[3] +" ");
			System.out.print(lo[4] +" ");
			System.out.print(lo[5] +" ");
			System.out.print(lo[6] +" ");
			System.out.print(lo[7] +" ");
			System.out.print(lo[8] +" ");
			System.out.println(" ");
			
			if (lo[8].toString().equals("estudio")){
				List<Object[]> estudio = ResultadosDao.Resultados((Long.valueOf(lo[4].toString())), (Long.valueOf(lo[7].toString())));
				for (Object [] e : estudio) {
					String Valor="";
									
					if (!e[7].equals(" ")){Valor=Valor+e[7].toString()+"\n";}
					if (!e[8].equals(" ")){Valor=Valor+e[8].toString()+"\n";}
					if (!e[9].equals(" ")){Valor=Valor+e[9].toString()+"\n";}
					if (!e[10].equals(" ")){Valor=Valor+e[10].toString()+"\n";}
					if (!e[11].equals(" ")){Valor=Valor+e[11].toString()+"\n";}
					if (!e[12].equals(" ")){Valor=Valor+e[12].toString()+"\n";}
					if (!e[13].equals(" ")){Valor=Valor+e[6].toString()+"\n";}
									
					Estudio = new Paragraph(e[2].toString() , fuen_1);
					Resultado = new Paragraph(e[6].toString(), fuen_2);
					Espacio = new Paragraph("  ");
					VR = new Paragraph(Valor, fuen_2);
					Paragraph Comentario=null;
									
					if (e[0].toString().equals("") || e[0].toString().equals(null)){Comentario = new Paragraph("");}
					else {Comentario = new Paragraph("OBSERVACIONES\n"+e[0].toString(), fuen_comentario);}
									
					ce = new PdfPCell(Estudio);
					cr = new PdfPCell(Resultado);
					esp = new PdfPCell(Espacio);
					cvr = new PdfPCell(VR);
									
					ce.setHorizontalAlignment(Element.ALIGN_LEFT);
					ce.disableBorderSide(Rectangle.BOX);
					ce.setExtraParagraphSpace(1.5f);
					
					
				
								
					cr.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cr.disableBorderSide(Rectangle.BOX);
					cr.setExtraParagraphSpace(1.5f);
									
					esp.setHorizontalAlignment(Element.ALIGN_RIGHT);
					esp.disableBorderSide(Rectangle.BOX);
					esp.setExtraParagraphSpace(1.5f);
									
					cvr.setHorizontalAlignment(Element.ALIGN_LEFT);
					cvr.disableBorderSide(Rectangle.BOX);
					cvr.setExtraParagraphSpace(1.5f);
									
					Paragraph Validacion = new Paragraph("Estudio(s) validado por: Q.F.B: El doctor Chapatín", fuen_validacion);
				    
					
					PdfPCell cell = new PdfPCell(Comentario);
					
					PdfPCell cell2 = new PdfPCell(Validacion);
				
							    
					cell.setBorder(PdfPCell.NO_BORDER);
					cell2.setBorder(PdfPCell.NO_BORDER);
					tableR.addCell(ce );
					tableR.addCell(cr);
					tableR.addCell(esp);
					tableR.addCell(cvr);
					tableR.addCell(cell);
					tableR.addCell(esp);
					tableR.addCell(esp);
					tableR.addCell(esp);
					tableR.addCell(cell2);
					tableR.addCell(esp);
					tableR.addCell(esp);
					tableR.addCell(esp);
					}
			}
			if (lo[8].toString().equals("perfil")){

				String auxComentario ="";
				tableR.addCell(new Phrase(lo[3].toString() , new  Font(FontFamily.TIMES_ROMAN,12.0f,Font.BOLD,BaseColor.BLACK)));
				tableR.addCell("");
				tableR.addCell("");
				tableR.addCell("");
				
				List<Object []> perfil =ResultadosDao.Perfil((Long.valueOf(lo[7].toString())));
				
				for (Object [] aux : perfil) {
					
					List<Object[]> estudio = ResultadosDao.Resultados((Long.valueOf(lo[4].toString())), (Long.valueOf(aux[0].toString())));
					
					for (Object [] e : estudio) {
						String Valor="";
										
					if (!e[7].equals(" ")){Valor=Valor+e[7].toString()+"\n";}
					if (!e[8].equals(" ")){Valor=Valor+e[8].toString()+"\n";}
					if (!e[9].equals(" ")){Valor=Valor+e[9].toString()+"\n";}
					if (!e[10].equals(" ")){Valor=Valor+e[10].toString()+"\n";}
					if (!e[11].equals(" ")){Valor=Valor+e[11].toString()+"\n";}
					if (!e[12].equals(" ")){Valor=Valor+e[12].toString()+"\n";}
					if (!e[13].equals(" ")){Valor=Valor+e[6].toString()+"\n";}
										
					Estudio = new Paragraph("   "+e[2].toString(), fuen_2);
					Resultado = new Paragraph(e[6].toString(),fuen_2 );
					Espacio = new Paragraph("  ");
					VR = new Paragraph(Valor ,fuen_2);
						
					if (e[0].toString().equals("") || e[0].toString().equals(null)){					
					}
					else {
							if (auxComentario == ""){
								auxComentario =auxComentario+e[0].toString();
								
								}
							else{
								auxComentario += auxComentario +", "+ e[0].toString();
								
							}	
					}
										
					ce = new PdfPCell(Estudio);
					cr = new PdfPCell(Resultado);
					esp = new PdfPCell(Espacio);
					cvr = new PdfPCell(VR);
										
					ce.setHorizontalAlignment(Element.ALIGN_LEFT);
					ce.disableBorderSide(Rectangle.BOX);
					ce.setExtraParagraphSpace(1.5f);
										
					cr.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cr.disableBorderSide(Rectangle.BOX);
					cr.setExtraParagraphSpace(1.5f);
									
					esp.setHorizontalAlignment(Element.ALIGN_RIGHT);
					esp.disableBorderSide(Rectangle.BOX);
					esp.setExtraParagraphSpace(1.5f);
										
					cvr.setHorizontalAlignment(Element.ALIGN_LEFT);
					cvr.disableBorderSide(Rectangle.BOX);
					cvr.setExtraParagraphSpace(1.5f);
										        
					tableR.addCell(ce);
					tableR.addCell(cr);
					tableR.addCell(esp);
					tableR.addCell(cvr);
											
								
					}
				}
				
				Paragraph Validacion = new Paragraph("   Estudio(s) validado por: Q.F.B: El doctor Chapatín",fuen_validacion);
				Paragraph Comentario= new Paragraph("   OBSERVACIONES\n    "+auxComentario , fuen_comentario);
				PdfPCell cell = new PdfPCell(Comentario);
				PdfPCell cell2 = new PdfPCell(Validacion);
		        cell.setColspan(4);
		        cell2.setColspan(4);
		        
		        cell.setBorder(PdfPCell.NO_BORDER);
		        cell2.setBorder(PdfPCell.NO_BORDER);
		       
		        tableR.addCell(cell);
				tableR.addCell(cell2);
			}
			if (lo[8].toString().equals("paquete")){
				String auxComentario ="";
				tableR.addCell(new Phrase(lo[3].toString() , new  Font(FontFamily.TIMES_ROMAN,12.0f,Font.BOLD,BaseColor.BLACK)));
				tableR.addCell("");
				tableR.addCell("");
				tableR.addCell("");
				
			
				
				List<Object []> paquete =ResultadosDao.Paquete((Long.valueOf(lo[7].toString())));
				
				for (Object [] aux : paquete) {
					
					if (aux[3].toString().equals("null")) {
						
						List<Object[]> estudio = ResultadosDao.Resultados((Long.valueOf(lo[4].toString())), (Long.valueOf(aux[0].toString())));
						for (Object [] e : estudio) {
							String Valor="";
											
							if (!e[7].equals(" ")){Valor=Valor+e[7].toString()+"\n";}
							if (!e[8].equals(" ")){Valor=Valor+e[8].toString()+"\n";}
							if (!e[9].equals(" ")){Valor=Valor+e[9].toString()+"\n";}
							if (!e[10].equals(" ")){Valor=Valor+e[10].toString()+"\n";}
							if (!e[11].equals(" ")){Valor=Valor+e[11].toString()+"\n";}
							if (!e[12].equals(" ")){Valor=Valor+e[12].toString()+"\n";}
							if (!e[13].equals(" ")){Valor=Valor+e[6].toString()+"\n";}
											
							Estudio = new Paragraph("   "+e[2].toString(), fuen_2);
							Resultado = new Paragraph(e[6].toString(), fuen_2);
							Espacio = new Paragraph("  ");
							VR = new Paragraph(Valor , fuen_2);
							Paragraph Comentario=null;
											
							if (e[0].toString().equals("") || e[0].toString().equals(null)){Comentario = new Paragraph("");}
							else {Comentario = new Paragraph("   OBSERVACIONES\n   "+e[0].toString(), fuen_comentario);}
											
							ce = new PdfPCell(Estudio);
							cr = new PdfPCell(Resultado);
							esp = new PdfPCell(Espacio);
							cvr = new PdfPCell(VR);
											
							ce.setHorizontalAlignment(Element.ALIGN_LEFT);
							ce.disableBorderSide(Rectangle.BOX);
							ce.setExtraParagraphSpace(1.5f);
											
							cr.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cr.disableBorderSide(Rectangle.BOX);
							cr.setExtraParagraphSpace(1.5f);
											
							esp.setHorizontalAlignment(Element.ALIGN_RIGHT);
							esp.disableBorderSide(Rectangle.BOX);
							esp.setExtraParagraphSpace(1.5f);
											
							cvr.setHorizontalAlignment(Element.ALIGN_LEFT);
							cvr.disableBorderSide(Rectangle.BOX);
							cvr.setExtraParagraphSpace(1.5f);
											
							Paragraph Validacion = new Paragraph("   Estudio(s) validado por: Q.F.B: El doctor Chapatín", fuen_validacion);
						    PdfPCell cell = new PdfPCell(Comentario);
							PdfPCell cell2 = new PdfPCell(Validacion);
							
									    
							cell.setBorder(PdfPCell.NO_BORDER);
							cell2.setBorder(PdfPCell.NO_BORDER);
							tableR.addCell(ce);
							tableR.addCell(cr);
							tableR.addCell(esp);
							tableR.addCell(cvr);
							tableR.addCell(cell);
							tableR.addCell(esp);
							tableR.addCell(esp);
							tableR.addCell(esp);
							tableR.addCell(cell2);
							tableR.addCell(esp);
							tableR.addCell(esp);
							tableR.addCell(esp);
							}
						
						
						
					}//llave del if para ver si no es perfil
					
					else {
						
						tableR.addCell(new Phrase("   "+aux[4].toString() , new  Font(FontFamily.TIMES_ROMAN,12.0f,Font.BOLD,BaseColor.BLACK)));
						
						tableR.addCell("");
						tableR.addCell("");
						tableR.addCell("");   
						
						
						List<Object []> perfil =ResultadosDao.Perfil((Long.valueOf(aux[3].toString())));
						

						
						System.out.println("EL valor de aaaaaa "+aux[3].toString() );
						
						
						for (Object [] aux2 : perfil) {
							
							List<Object[]> estudio = ResultadosDao.Resultados((Long.valueOf(lo[4].toString())), (Long.valueOf(aux2[0].toString())));
							for (Object [] e : estudio) {
								String Valor="";
								
												
							if (!e[7].equals(" ")){Valor=Valor+e[7].toString()+"\n";}
							if (!e[8].equals(" ")){Valor=Valor+e[8].toString()+"\n";}
							if (!e[9].equals(" ")){Valor=Valor+e[9].toString()+"\n";}
							if (!e[10].equals(" ")){Valor=Valor+e[10].toString()+"\n";}
							if (!e[11].equals(" ")){Valor=Valor+e[11].toString()+"\n";}
							if (!e[12].equals(" ")){Valor=Valor+e[12].toString()+"\n";}
							if (!e[13].equals(" ")){Valor=Valor+e[6].toString()+"\n";}
												
							Estudio = new Paragraph("     "+e[2].toString() , fuen_2 );
							Resultado = new Paragraph(e[6].toString()  , fuen_2);
							Espacio = new Paragraph("  ");
							VR = new Paragraph(Valor , fuen_2);
								
							if (e[0].toString().equals("") || e[0].toString().equals(null)){					
							}
							else {
									if (auxComentario == ""){
										auxComentario =auxComentario+e[0].toString();
										
										}
									else{
										auxComentario += auxComentario +", "+ e[0].toString();
										
									}	
							}
												
							ce = new PdfPCell(Estudio);
							cr = new PdfPCell(Resultado);
							esp = new PdfPCell(Espacio);
							cvr = new PdfPCell(VR);
												
							ce.setHorizontalAlignment(Element.ALIGN_LEFT);
							ce.disableBorderSide(Rectangle.BOX);
							ce.setExtraParagraphSpace(1.5f);
												
							cr.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cr.disableBorderSide(Rectangle.BOX);
							cr.setExtraParagraphSpace(1.5f);
											
							esp.setHorizontalAlignment(Element.ALIGN_RIGHT);
							esp.disableBorderSide(Rectangle.BOX);
							esp.setExtraParagraphSpace(1.5f);
												
							cvr.setHorizontalAlignment(Element.ALIGN_LEFT);
							cvr.disableBorderSide(Rectangle.BOX);
							cvr.setExtraParagraphSpace(1.5f);
												        
							tableR.addCell(ce);
							tableR.addCell(cr);
							tableR.addCell(esp);
							tableR.addCell(cvr);
													
										
							}
						}
						
						Paragraph Validacion = new Paragraph("     Estudio(s) validado por: Q.F.B: El doctor Chapatín",fuen_validacion);
						Paragraph Comentario= new Paragraph("     OBSERVACIONES\n      "+auxComentario , fuen_comentario);
						PdfPCell cell = new PdfPCell(Comentario);
						PdfPCell cell2 = new PdfPCell(Validacion);
				        cell.setColspan(4);
				        cell2.setColspan(4);
				        
				        cell.setBorder(PdfPCell.NO_BORDER);
				        cell2.setBorder(PdfPCell.NO_BORDER);
				       
				        tableR.addCell(cell);
						tableR.addCell(cell2);
						
						
						
					}//llave del else de if para verificar que no perfil 
				
						
					}//llave de la consulta de pauete 
					
				
				}// llave de la condición de la paquete 


		
			
		}
		
	
		documento.add(tableR);
		
		
		
		documento.close();

	}
	

private void filedownloadPDF(String fullPath, HttpServletResponse response , Long id) {
	File file = new File(fullPath);
	final int BUFFER_SIZE = 4096;
	if (file.exists()) {
		try {
			FileInputStream inputStream = new FileInputStream(file);
			String mimeType = context.getMimeType(fullPath);
			response.setContentType(mimeType);
			response.setHeader("content-disposition", "attachment; filename="+nombrepdf+".pdf");
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			file.delete();

			inputStream.close();
			outputStream.close();

		} catch (Exception e) {

		}
	}
}



}
