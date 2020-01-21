package com.bemedica.springboot.app.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bemedica.springboot.app.models.dao.ICajaVistaDao;
import com.bemedica.springboot.app.models.entity.Promociones;
import com.lowagie.text.Anchor;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ElementTags;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;

import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.DottedLineSeparator;

import java.awt.Color;
import java.io.*;
//librerias de pdf

@Controller
public class CajaReportController {
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
		Font iTextFont = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL);
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
		List<Object[]> re = cajaVistaDao.findAll(); //para hacer el reporte
		
		Paragraph p = new Paragraph("\n\n\n\n\n\n");
		PdfPCell celda1 = new PdfPCell(p);
		celda1.setBorder(Rectangle.NO_BORDER);
		
		/*********************************************************************************/

		PdfPTable table5 = new PdfPTable(1);
		table5.addCell(celda1);
		
		

		/*********************************************************************************/

		PdfPTable table3 = new PdfPTable(2);
		table3.getDefaultCell().setBorder(0);
		table3.setWidthPercentage(100);
		table3.setWidths(new float[] { 2.0f, 1.8f });
		table3.setSpacingBefore(2);
		table3.addCell("Nombre: \n" + "Sucursal:");
		table3.addCell("Empleado: \n" + "Fecha: " + fc);

		PdfPCell celda2 = new PdfPCell(table3);
		celda2.setHorizontalAlignment(Element.ALIGN_LEFT);
		celda2.setBorder(Rectangle.NO_BORDER);
		/**************************************************************************/

		PdfPTable table = new PdfPTable(7);
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(90.0f);
		table.setWidths(new float[] { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f });
		table.setSpacingBefore(2);
		table.addCell("\n\n\nFolio");
		table.addCell("\n\n\nUsuario");
		table.addCell("\n\n\nMonto");
		table.addCell("\n\n\nTotal");
		table.addCell("\n\n\nFecha");
		table.addCell("\n\n\nConcepto");
		table.addCell("\n\n\nForma de pago\n\n");

		for (Object[] a : rp) {

			table.addCell(a[0].toString());
			table.addCell(a[1].toString());
			table.addCell(a[2].toString());
			table.addCell(a[3].toString());
			table.addCell(a[4].toString());
			table.addCell(a[5].toString());
			table.addCell(a[6].toString());
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
		for(Object[] r : re) 
		{
			//System.out.println("szfxdgchj"+r[6]+" "+r[0]+" "+Integer.toString(num1));
		if (r[0].equals(num1)&&r[6].equals("Corte"))
		{
			//System.out.println("entra1");
				table1.addCell("\n\n\n\n\nTotal de\nEfectivo:      $" +r[8].toString()+"\n"
						+"\nTotal \nTarjeta:  $" +r[9].toString()+"\n"
							+"\nCaja \nChica:       $"+r[10].toString()+"\n"
						+"\nMonto \nEfectivo:  $"+r[7].toString());
				table1.addCell("\nTotal:      $" + r[4].toString());
			
		} else if(r[0].equals(num1)&&r[6].equals("Cierre"))
		{
			//System.out.println("entra2");
					table1.addCell("\n\n\n\n\nTotal de\nEfectivo:      $" +r[8].toString()+"\n"
							+"\nTotal \nTarjeta:  $" +r[9].toString());
					table1.addCell("\nTotal:      $" + r[4].toString());
		}
		}
		/**************************************************************************/

		documento.add(table5);
		documento.add(table4);
		documento.add(table);
		documento.add(table1);
		

		documento.close();

		System.out.println(fullPath);
	
		}
		}
	
