package com.bemedica.springboot.app.controllers;

//librerias de pdf
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bemedica.springboot.app.models.dao.ICajaVistaDao;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter; 




@Controller

public class CajaReportController {
	private static final Color RED = null;

	@Autowired
	private ServletContext context;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ICajaVistaDao cajaVistaDao;

	@RequestMapping(value = "/herramientas_corte/{a}")
	public void reportListar(Model model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "a") int a)
			/*
			 * @RequestParam("ff") Date ff,
			 * 
			 * @RequestParam("fi") Date fi,
			 * 
			 * @RequestParam("periodo") String periodo)
			 */ throws Exception {

//		 SimpleDateFormat formatoEsMX = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 * String fecha1 = formatoEsMX.format(fi); String fecha2 =
		 * formatoEsMX.format(ff); //this.excel( request, fecha1,fecha2,periodo);
		 */
		this.pdf(request, a);

		// String fullPath =
		// request.getServletContext().getRealPath("/"+"Reporte"+".xls");
		String fullPaths = request.getServletContext().getRealPath("/" + "CorteCaja" + ".pdf");
		// filedownload(fullPath, response);
		filedownloadPDF(fullPaths, response);

	}

	/**
	 * @param request
	 * @param f1
	 * @param f2
	 * @param p
	 * @return 
	 * @throws Exception
	 */
	/*
	 * public void excel (HttpServletRequest request , String f1 , String f2, String
	 * p ) throws Exception { HSSFWorkbook libro = new HSSFWorkbook();// Creamos
	 * nuestro libro de excel
	 * 
	 * HSSFSheet sheet = libro.createSheet("Reporte de empresas");// crear la hoja
	 * 
	 * 
	 * String[] titulos = {"Fecha","Empleado","Sucursal",
	 * "Estudio","No. Vendios","$$"}; List <Object[]> rp =
	 * ReporteDao.estudios_ventas(f1,f2,p);
	 * 
	 * 
	 * 
	 * Row fila = sheet.createRow(0);
	 * 
	 * 
	 * for (int i = 0; i < titulos.length; i++) {
	 * 
	 * Cell celda = fila.createCell(i);
	 * 
	 * celda.setCellValue(titulos[i]); }
	 * 
	 * 
	 * int i=1;
	 * 
	 * for (Object[] a : rp) { fila = sheet.createRow(i); Cell celda0 =
	 * fila.createCell(0); Cell celda1 = fila.createCell(1); Cell celda2 =
	 * fila.createCell(2); Cell celda3 = fila.createCell(3); Cell celda4 =
	 * fila.createCell(4); Cell celda5 = fila.createCell(5);
	 * 
	 * celda0.setCellValue(a[0].toString()); celda1.setCellValue(a[1].toString());
	 * celda2.setCellValue(a[2].toString()); celda3.setCellValue(a[3].toString());
	 * celda4.setCellValue(Integer.valueOf(a[4].toString()));
	 * celda5.setCellValue(Double.valueOf(a[5].toString()));
	 * 
	 * i++; }
	 * 
	 * 
	 * 
	 * 
	 * String fullPath =
	 * request.getServletContext().getRealPath("/"+"Reporte"+".xls"); try { File
	 * archivo = new File(fullPath); FileOutputStream out = new
	 * FileOutputStream(archivo); libro.write(out); out.flush(); out.close(); }
	 * catch (IOException e) { System.err.println("ERROR AL CREAR EL ARCHIVO!");
	 * e.printStackTrace(); }
	 * 
	 * System.out.println("Reporte generado");
	 * 
	 * }
	 */

//	private void filedownload(String fullPath, HttpServletResponse response) {
//		File file = new File(fullPath);
//		final int BUFFER_SIZE = 4096;
//		if(file.exists()) {
//			try {
//				FileInputStream inputStream = new FileInputStream(file);
//				String mimeType = context.getMimeType(fullPath);
//				response.setContentType(mimeType);
//				response.setHeader("content-disposition", "attachment; filename="+"Reporte.xls");
//				OutputStream  outputStream = response.getOutputStream();
//				byte[] buffer = new	byte[BUFFER_SIZE];
//				int bytesRead = -1;
//				while((bytesRead = inputStream.read(buffer))!= -1) {
//					outputStream.write(buffer, 0, bytesRead);
//				}
//				
//				
//				
//				file.delete();
//			
//				inputStream.close();
//				outputStream.close();
//
//			}catch(Exception e) {
//				
//				
//			}
//		}
//	}
	 
	
	private void filedownloadPDF(String fullPath, HttpServletResponse response) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + "CorteCaja.pdf");
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

	
	@SuppressWarnings("null")
	public void pdf(HttpServletRequest request, int num1) throws Exception {

		String fullPath = request.getServletContext().getRealPath("/" + "CorteCaja" + ".pdf");
		// Se crea el documento
		Document documento = new Document();

		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
		FileOutputStream ficheroPdf = new FileOutputStream(fullPath);

		// Se asocia el documento al OutputStream y se indica que el espaciado entre
		// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
		Font iTextFont = new Font(Font.TIMES_ROMAN, 9);
		PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
		
		
        
		// Se abre el documento.
		documento.open();
//		int edad=35;
//        Paragraph p = new Paragraph();
//        p.setSpacingAfter(90);
//        p.add("nombre:");        
//        p.add("edad:"+edad);
		
		List<Object[]> rp = cajaVistaDao.findAll2(num1);
		List<Object[]> fc = cajaVistaDao.findAll4(num1);
		List<Object[]> uc = cajaVistaDao.findAll3(num1);
		List<Object[]> re = cajaVistaDao.findAll(num1); //para hacer el reporte
		
		Paragraph p = new Paragraph("\n\n");
		PdfPCell celda1 = new PdfPCell(p);
		celda1.setBorder(Rectangle.NO_BORDER);
		celda1.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		
		/*********************************************************************************/

		PdfPTable table5 = new PdfPTable(1);
		table5.addCell(celda1);
		
		

		/*********************************************************************************/

		Chunk yu = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		Chunk mi = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		
		
		
		PdfPTable table3 = new PdfPTable(2);
		table3.getDefaultCell().setBorder(0);
		table3.setWidthPercentage(100);
		table3.setWidths(new float[] { 2.0f, 2.0f });
		table3.setSpacingBefore(2);
		yu.append("Nombre:\n" + "Sucursal:\n");
		mi.append("Empleado:\n" + "Fecha:\t\t" + fc);

		PdfPCell celda2 = new PdfPCell(table3);
		celda2.setHorizontalAlignment(Element.ALIGN_LEFT);
		celda2.setBorder(Rectangle.NO_BORDER);
		
		documento.add(yu);
		documento.add(mi);
		
		/**************************************************************************/
		

		
		PdfPTable table = new PdfPTable(8);
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
		table.setSpacingBefore(1);
		Paragraph columna1 = new Paragraph("Folio");
	    columna1.getFont().setStyle(Font.BOLD);
	    columna1.getFont().setSize(12);
	    table.addCell(columna1);
	    Paragraph columna2 = new Paragraph("Usuario");
	    columna2.getFont().setStyle(Font.BOLD);
	    columna2.getFont().setSize(12);
	    table.addCell(columna2);
	    Paragraph columna3 = new Paragraph("Monto");
	    columna3.getFont().setStyle(Font.BOLD);
	    columna3.getFont().setSize(12);
	    table.addCell(columna3);
	    Paragraph columna4 = new Paragraph("Total");
	    columna4.getFont().setStyle(Font.BOLD);
	    columna4.getFont().setSize(12);
	    table.addCell(columna4);
	    Paragraph columna101 = new Paragraph("Resta");
	    columna101.getFont().setStyle(Font.BOLD);
	    columna101.getFont().setSize(12);
	    table.addCell(columna101);
	    Paragraph columna5 = new Paragraph("Fecha");
	    columna5.getFont().setStyle(Font.BOLD);
	    columna5.getFont().setSize(12);
	    table.addCell(columna5);
	    Paragraph columna6 = new Paragraph("Concepto");
	    columna6.getFont().setStyle(Font.BOLD);
	    columna6.getFont().setSize(12);
	    table.addCell(columna6);
	    Paragraph columna7 = new Paragraph("Forma\tde\tPago");
	    columna7.getFont().setStyle(Font.BOLD);
	    columna7.getFont().setSize(12);
	    table.addCell(columna7);
	   
	    
		//table.addCell("\nFolio");
		//table.addCell("\nUsuario");
		//table.addCell("\nMonto");
		//table.addCell("\nTotal");
		//table.addCell("\nFecha");
		//table.addCell("\nConcepto");
		//table.addCell("\nForma\tde\tpago\n\n");
	  
	   
		for (Object[] a : rp) {
			
			  String num5 = a[2].toString();
			  String num6 = a[3].toString();  //convertir objeto a variable string
				
				float n4 = Float.parseFloat (num5);
				float n5 = Float.parseFloat (num6); //variable string a float
				
				float resta = (n4-n5); 
			
			
			Paragraph columna8 = new Paragraph(a[0].toString());
			columna8.getFont().setStyle(Font.NORMAL);
			columna8.getFont().setSize(10);
			table.addCell(columna8);
			Paragraph columna9 = new Paragraph(a[1].toString());
			columna9.getFont().setStyle(Font.NORMAL);
			columna9.getFont().setSize(10);
			table.addCell(columna9);
			Paragraph columna10 = new Paragraph(a[2].toString());
			columna10.getFont().setStyle(Font.NORMAL);
			columna10.getFont().setSize(10);
			table.addCell(columna10);
			Paragraph columna11 = new Paragraph(a[3].toString());
			columna11.getFont().setStyle(Font.NORMAL);
			columna11.getFont().setSize(10);
			table.addCell(columna11);
			Paragraph columna121 = new Paragraph("" + resta);
			columna121.getFont().setStyle(Font.NORMAL);
			columna121.getFont().setSize(10);
			table.addCell(columna121);
			Paragraph columna12 = new Paragraph(a[4].toString());
			columna12.getFont().setStyle(Font.NORMAL);
			columna12.getFont().setSize(10);
			table.addCell(columna12);
			Paragraph columna13 = new Paragraph(a[5].toString());
			columna13.getFont().setStyle(Font.NORMAL);
			columna13.getFont().setSize(10);
			table.addCell(columna13);
			Paragraph columna14 = new Paragraph(a[6].toString());
			columna14.getFont().setStyle(Font.NORMAL);
			columna14.getFont().setSize(10);
			table.addCell(columna14);
			
			//table.addCell(a[0].toString());
			//table.addCell(a[1].toString());
			//table.addCell(a[2].toString());
			//table.addCell(a[3].toString());
			//table.addCell(a[4].toString());
			//table.addCell(a[5].toString());
			//table.addCell(a[6].toString());
			
		}
		
		
		/**************************************************************************/

		PdfPTable table4 = new PdfPTable(1);
		table4.getDefaultCell().setBorder(0);
		table4.addCell(celda2);
		
		

		/**************************************************************************/

		PdfPTable table1 = new PdfPTable(1);
		table1.getDefaultCell().setBorder(0);
//		table1.setWidthPercentage(10);
//		table1.setWidths(new float[] { 2.0f,2.0f});
//		table1.setSpacingBefore(2);
//		table1.addCell("\n\n\n\n\n\n\n-Efectivo: ");
//		table1.addCell("\n\n\n\n\n\n\n-Tarjeta de\nCrédito: ");
		
		
		//for (Object[] b : uc)
		//{
	//		
		// table1.addCell("\n\n\n\n\nTotal de\nEfectivo:      $"+b[0].toString()+"\n"
			//		+"\nTotal \nTarjeta:  $" +b[1].toString()+"\n"
				//	+"\nCaja \nChica:       $"+b[2].toString()+"\n"
					//+"\nMonto \nEfectivo:  $"+b[3].toString());
			//table1.addCell("\nTotal:      $" + b[4].toString());
	//	}
		Chunk ck = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		Chunk no = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		Chunk si = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		Chunk tu = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));	
		Chunk pa = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		Chunk ma = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		Chunk du = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		Chunk os = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL));
		Chunk pi = new Chunk("", new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
		
		
		for(Object[] r : re) 
			
		{
			
			
			//System.out.println("szfxdgchj"+r[6]+" "+r[0]+" "+Integer.toString(num1));
		if (r[0].equals(num1)&&r[6].equals("Corte"))
		{

			
			String num2 = r[8].toString();
			String num3 = r[10].toString();  //convertir objeto a variable string
			String num4 = r[7].toString();
			
			float n1 = Float.parseFloat (num2);
			float n2 = Float.parseFloat (num3); //variable string a float
			float n3 = Float.parseFloat (num4);
			
			float suma = ((n3)-(n1+n2)); //variable ya flotante hacer la operación para mostrar nuevo campo
			
			if(suma==0) {
				os.setFont(new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
			}
			else if(suma>0){
				os.setFont(new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.green));
			}
			else if(suma<0) {
				os.setFont(new Font(
				Font.HELVETICA, 12, Font.NORMAL, Color.red));
			}
			
			//System.out.println("entra1");
			tu.append("\nTotal\tEfectivo:  $" +r[8].toString());
			pa.append("\nTotal\tTarjeta:  $" +r[9].toString());
			ma.append("\nCaja\tChica:  $"+r[10].toString());
			du.append("\nMonto\tEfectivo: $"+r[7].toString());
			os.append("\nDiferencia: $"+suma); //concatenar nuevo campo asignado a una  variable ya convertida
		    pi.append("\nTotal: $" + r[4].toString());

		} 
		
		
		else if(r[0].equals(num1)&&r[6].equals("Cierre"))
		{
			//System.out.println("entra2");
			 ck.append("\nTotal\tEfectivo: $" +r[8].toString());
			 no.append("\nTotal\tTarjeta:  $" +r[9].toString());
			 si.append("\nTotal: $" + r[4].toString());
		}
		}
		
		/**************************************************************************/
		
		documento.add(table5);
		documento.add(table4);
		documento.add(table);
		documento.add(table1);
		documento.add(ck);
		documento.add(no);
		documento.add(si);
		documento.add(tu);
		documento.add(pa);
		documento.add(ma);
		documento.add(du);
		documento.add(os);
		documento.add(pi);
		
		

		documento.close();

		System.out.println(fullPath);
	
		}

	private void setForeground() {
		// TODO Auto-generated method stub
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
	}
		}
