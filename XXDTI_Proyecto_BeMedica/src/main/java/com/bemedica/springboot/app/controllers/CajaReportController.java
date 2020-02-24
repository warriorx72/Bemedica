package com.bemedica.springboot.app.controllers;

//librerias de pdf
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bemedica.springboot.app.models.dao.ICajaDao;
import com.bemedica.springboot.app.models.dao.ICajaVistaDao;
import com.bemedica.springboot.app.models.dao.ISucursal;
import com.bemedica.springboot.app.models.dao.ISucursalDao;
import com.bemedica.springboot.app.models.entity.SucursalE;
import com.bemedica.springboot.app.service.UserService;




@Controller

public class CajaReportController {
	@Autowired
	private ServletContext context;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ICajaVistaDao cajaVistaDao;
	
	@Autowired
	private ISucursal sucursalDao;
	
	@Autowired
	ICajaDao cajaDao;
	
	@Autowired
  UserService userService;


	@RequestMapping(value = "/herramientas_corte/{a}")
	public void reportListar(Model model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "a") int a)
			/*
			 * @RequestParam("ff") Date ff,
			 * 
			 * @RequestParam("fi") Date fi,
			 * 
			 * 
			 * @RequestParam("periodo") String periodo)
			 */ throws Exception {

//		 SimpleDateFormat formatoEsMX = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 * String fecha1 = formatoEsMX.format(fi); String fecha2 =
		 * formatoEsMX.format(ff); //this.excel( request, fecha1,fecha2,periodo);
		 */
		this.excel(request, a);

		// String fullPath =
		// request.getServletContext().getRealPath("/"+"Reporte"+".xls");
		String fullPath = request.getServletContext().getRealPath("/" + "CorteCaja" + ".xls");
		filedownload(fullPath, response);

	}

	  public void excel (HttpServletRequest request , int num1 ) throws Exception{// Creamos
	  HSSFWorkbook libro = new HSSFWorkbook();
	  HSSFSheet sheet = libro.createSheet("Reporte de empresas");// crear la hoja
	  UserController user = new UserController();
	  
		//-----------------------------------Dar color y formato a tabla de excel---------------------------------------
		 
		//Estilos del indice (Dias)
		HSSFCellStyle styleIndex = libro.createCellStyle();
		styleIndex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleIndex.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
		styleIndex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont FontIndex = libro.createFont();
		FontIndex.setColor(HSSFColor.WHITE.index);
		FontIndex.setBoldweight((short)1000);
		FontIndex.setFontName("Arial");
		FontIndex.setFontHeightInPoints((short)12);
		styleIndex.setFont(FontIndex);
		
		//Estilos del indice (Dias)
		HSSFCellStyle styleFecha = libro.createCellStyle();
		styleFecha.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleFecha.setFillForegroundColor(HSSFColor.AQUA.index);
		styleFecha.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont FontFecha = libro.createFont();
		FontFecha.setColor(HSSFColor.WHITE.index);
		FontFecha.setBoldweight((short)4000);
		FontFecha.setFontName("Arial");
		FontFecha.setFontHeightInPoints((short)12);
		styleFecha.setFont(FontFecha);
		
		//Estilos del indice (Dias)
		HSSFCellStyle styleContenido = libro.createCellStyle();
		styleContenido.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont FontContenido = libro.createFont();
		FontContenido.setFontName("Arial");
		FontContenido.setFontHeightInPoints((short)12);
		styleContenido.setFont(FontContenido);
		
		//Bloquea la primera columna del excel
		sheet.createFreezePane(1, 0);
		

	//-----------------------------------///Dar color y formato a tabla de excel///---------------------------------
	  
 
	  
	  List<Object[]> rp = cajaVistaDao.findAll2(num1);
	  List<Object[]> fc = cajaVistaDao.findAll4(num1);
	  List<Object[]> uc = cajaVistaDao.findAll3(num1);
	  String re = sucursalDao.findOne(Long.parseLong(user.UserSucId(request, userService)[1])).getSucursalNombre(); //para hacer el reporte
	   
	  //Crear la sucursal y las fechas del corte
	  Row fila= sheet.createRow(0);
	  fila.setHeight((short) 400);
	  Cell celda = fila.createCell(0);
	  celda.setCellValue("Sucursal");
	  celda.setCellStyle(styleIndex);
	  celda = fila.createCell(1);
	  celda.setCellValue(re);
	  celda.setCellStyle(styleContenido);
	  fila = sheet.createRow(1);
	  celda = fila.createCell(0);
	  celda.setCellValue("Fechas");
	  celda.setCellStyle(styleIndex);
	  celda = fila.createCell(1);
	  celda.setCellValue(fc.toString());
	  celda.setCellStyle(styleContenido);
	  sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
	  fila.setHeight((short) 400);
	  
	  
	  String[] titulos = {"Folio","Usuario","Monto","Total","Resta","Fecha", "Concepto", "Forma de pago"};
	  fila = sheet.createRow(2);
	  sheet.createFreezePane(1, 3);
	  int i = 0;
	  for (String a: titulos) {
		  celda = fila.createCell(i);
		  celda.setCellValue(a);
		  sheet.setColumnWidth(i,6000);
		  i++;
		  celda.setCellStyle(styleFecha);
		  
	  }
	  
	  i = 3;
		for (Object[] b: rp) {
			fila = sheet.createRow(i);
			i++;
		}
	  
		
		//Rellenar la tabla hasta el campo Resta
	  i=3;
	  int d=0;
	  Row fila1= sheet.getRow(2);
	  	for (int r=1;r<=fila1.getLastCellNum();r++) {	
	  		for (Object[] a : rp) {
	  			float n4 = Float.parseFloat(a[2].toString());
				float n5 = Float.parseFloat(a[3].toString());    //convertir objeto a variable string
				float resta = (n4-n5);
				fila = sheet.getRow(i);
				celda = fila.createCell(d);
				celda.setCellStyle(styleContenido);
				
				if(d<2) {
					celda.setCellValue(a[d].toString());
				}
				if(d==4) { 
					celda.setCellValue(resta);
				}
				if (d==2 || d==3) {
					celda.setCellValue(Double.parseDouble(a[d].toString()));
				}
				i++;
		  }
	  	d++;
	  	i=3;
	  }
	  	
	  	//rellenar la tabla despues de Resta
	  	d=1;
	  	i=3;
	  	for (int r=2;r<=fila1.getLastCellNum();r++) {	
	  		for (Object[] a : rp) {
				fila = sheet.getRow(i);
				
				if(d>=5) {
				celda = fila.getCell(d);
				celda.setCellValue(a[d-1].toString());
				celda.setCellStyle(styleContenido);
				}
				i++;
	  		}
	  		d++;
	  		i=3;
	  	}
	  	
	  	//Llenar datos de montos
	  	String[] montos = {"Total de efectivo","Total tarjeta","Caja chica","Monto Efectivo","Total"};
	  	int max = sheet.getLastRowNum()+4;
	  	int lot = max;
	  		for (String a : montos) {
				fila = sheet.createRow(max);
				celda = fila.createCell(0);
				celda.setCellValue(a);
				celda.setCellStyle(styleIndex);
				max++;	
	  		}
	  	i=0;
	  	
	  	for(int l=lot;l<max;l++) {
		  	for(Object[] a: uc) {
		  		fila = sheet.getRow(lot);
		  		celda = fila.createCell(1);
		  		celda.setCellValue(Double.parseDouble(a[i].toString()));
		  		celda.setCellStyle(styleContenido);
		  		lot++;
		  		
		  	}
		  	i++;
	  	}
	  	

	  String fullPath = request.getServletContext().getRealPath("/" + "CorteCaja" + ".xls");
	  try {
			File archivo = new File(fullPath);
			FileOutputStream out = new FileOutputStream(archivo);
			libro.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println("ERROR AL CREAR EL ARCHIVO!");
			e.printStackTrace();
		}

		System.out.println("Reporte generado");
	  }

	
	private void filedownload(String fullPath, HttpServletResponse response) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + "CorteCaja.xls");
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

	
//	public void pdf(HttpServletRequest request, int num1) throws Exception {
//
//		String fullPath = request.getServletContext().getRealPath("/" + "CorteCaja" + ".pdf");
//		// Se crea el documento
//		Document documento = new Document();
//
//		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
//		FileOutputStream ficheroPdf = new FileOutputStream(fullPath);
//
//		// Se asocia el documento al OutputStream y se indica que el espaciado entre
//		// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
//		Font iTextFont = new Font(Font.TIMES_ROMAN, 9);
//		PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
//		
//		
//        
//		// Se abre el documento.
//		documento.open();
////		int edad=35;
////        Paragraph p = new Paragraph();
////        p.setSpacingAfter(90);
////        p.add("nombre:");        
////        p.add("edad:"+edad);
//		
//		UserController user = new UserController();
//		
//		List<Object[]> rp = cajaVistaDao.findAll2(num1);
//		List<Object[]> fc = cajaVistaDao.findAll4(num1);
//		List<Object[]> uc = cajaVistaDao.findAll3(num1);
//		List<Object[]> re = cajaVistaDao.findAll( Integer.parseInt(user.UserSucId(request, userService)[1]) ); //para hacer el reporte
//		
//		Paragraph p = new Paragraph("\n\n");
//		PdfPCell celda1 = new PdfPCell(p);
//		celda1.setBorder(Rectangle.NO_BORDER);
//		celda1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		
//		
//		/*********************************************************************************/
//
//		PdfPTable table5 = new PdfPTable(1);
//		table5.addCell(celda1);
//		
//		
//
//		/*********************************************************************************/
//
//		Chunk yu = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		Chunk mi = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		
//		
//		
//		PdfPTable table3 = new PdfPTable(2);
//		table3.getDefaultCell().setBorder(0);
//		table3.setWidthPercentage(100);
//		table3.setWidths(new float[] { 2.0f, 2.0f });
//		table3.setSpacingBefore(2);
//		yu.append("Nombre:\n" + "Sucursal:\n");
//		mi.append("Empleado:\n" + "Fecha:\t\t" + fc);
//
//		PdfPCell celda2 = new PdfPCell(table3);
//		celda2.setHorizontalAlignment(Element.ALIGN_LEFT);
//		celda2.setBorder(Rectangle.NO_BORDER);
//		
//		documento.add(yu);
//		documento.add(mi);
//		
//		/**************************************************************************/
//		
//
//		
//		PdfPTable table = new PdfPTable(8);
//		table.getDefaultCell().setBorder(0);
//		table.setWidthPercentage(100);
//		table.setWidths(new float[] { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
//		table.setSpacingBefore(1);
//		Paragraph columna1 = new Paragraph("Folio");
//	    columna1.getFont().setStyle(Font.BOLD);
//	    columna1.getFont().setSize(12);
//	    table.addCell(columna1);
//	    Paragraph columna2 = new Paragraph("Usuario");
//	    columna2.getFont().setStyle(Font.BOLD);
//	    columna2.getFont().setSize(12);
//	    table.addCell(columna2);
//	    Paragraph columna3 = new Paragraph("Monto");
//	    columna3.getFont().setStyle(Font.BOLD);
//	    columna3.getFont().setSize(12);
//	    table.addCell(columna3);
//	    Paragraph columna4 = new Paragraph("Total");
//	    columna4.getFont().setStyle(Font.BOLD);
//	    columna4.getFont().setSize(12);
//	    table.addCell(columna4);
//	    Paragraph columna101 = new Paragraph("Resta");
//	    columna101.getFont().setStyle(Font.BOLD);
//	    columna101.getFont().setSize(12);
//	    table.addCell(columna101);
//	    Paragraph columna5 = new Paragraph("Fecha");
//	    columna5.getFont().setStyle(Font.BOLD);
//	    columna5.getFont().setSize(12);
//	    table.addCell(columna5);
//	    Paragraph columna6 = new Paragraph("Concepto");
//	    columna6.getFont().setStyle(Font.BOLD);
//	    columna6.getFont().setSize(12);
//	    table.addCell(columna6);
//	    Paragraph columna7 = new Paragraph("Forma\tde\tPago");
//	    columna7.getFont().setStyle(Font.BOLD);
//	    columna7.getFont().setSize(12);
//	    table.addCell(columna7);
//	   
//	    
//		//table.addCell("\nFolio");
//		//table.addCell("\nUsuario");
//		//table.addCell("\nMonto");
//		//table.addCell("\nTotal");
//		//table.addCell("\nFecha");
//		//table.addCell("\nConcepto");
//		//table.addCell("\nForma\tde\tpago\n\n");
//	  
//	   
//		for (Object[] a : rp) {
//			
//			  String num5 = a[2].toString();
//			  String num6 = a[3].toString();  //convertir objeto a variable string
//				
//				float n4 = Float.parseFloat (num5);
//				float n5 = Float.parseFloat (num6); //variable string a float
//				
//				float resta = (n4-n5); 
//			
//			
//			Paragraph columna8 = new Paragraph(a[0].toString());
//			columna8.getFont().setStyle(Font.NORMAL);
//			columna8.getFont().setSize(10);
//			table.addCell(columna8);
//			Paragraph columna9 = new Paragraph(a[1].toString());
//			columna9.getFont().setStyle(Font.NORMAL);
//			columna9.getFont().setSize(10);
//			table.addCell(columna9);
//			Paragraph columna10 = new Paragraph(a[2].toString());
//			columna10.getFont().setStyle(Font.NORMAL);
//			columna10.getFont().setSize(10);
//			table.addCell(columna10);
//			Paragraph columna11 = new Paragraph(a[3].toString());
//			columna11.getFont().setStyle(Font.NORMAL);
//			columna11.getFont().setSize(10);
//			table.addCell(columna11);
//			Paragraph columna121 = new Paragraph("" + resta);
//			columna121.getFont().setStyle(Font.NORMAL);
//			columna121.getFont().setSize(10);
//			table.addCell(columna121);
//			Paragraph columna12 = new Paragraph(a[4].toString());
//			columna12.getFont().setStyle(Font.NORMAL);
//			columna12.getFont().setSize(10);
//			table.addCell(columna12);
//			Paragraph columna13 = new Paragraph(a[5].toString());
//			columna13.getFont().setStyle(Font.NORMAL);
//			columna13.getFont().setSize(10);
//			table.addCell(columna13);
//			Paragraph columna14 = new Paragraph(a[6].toString());
//			columna14.getFont().setStyle(Font.NORMAL);
//			columna14.getFont().setSize(10);
//			table.addCell(columna14);
//			
//			//table.addCell(a[0].toString());
//			//table.addCell(a[1].toString());
//			//table.addCell(a[2].toString());
//			//table.addCell(a[3].toString());
//			//table.addCell(a[4].toString());
//			//table.addCell(a[5].toString());
//			//table.addCell(a[6].toString());
//			
//		}
//		
//		
//		/**************************************************************************/
//
//		PdfPTable table4 = new PdfPTable(1);
//		table4.getDefaultCell().setBorder(0);
//		table4.addCell(celda2);
//		
//		
//
//		/**************************************************************************/
//
//		PdfPTable table1 = new PdfPTable(1);
//		table1.getDefaultCell().setBorder(0);
////		table1.setWidthPercentage(10);
////		table1.setWidths(new float[] { 2.0f,2.0f});
////		table1.setSpacingBefore(2);
////		table1.addCell("\n\n\n\n\n\n\n-Efectivo: ");
////		table1.addCell("\n\n\n\n\n\n\n-Tarjeta de\nCrédito: ");
//		
//		
//		//for (Object[] b : uc)
//		//{
//	//		
//		// table1.addCell("\n\n\n\n\nTotal de\nEfectivo:      $"+b[0].toString()+"\n"
//			//		+"\nTotal \nTarjeta:  $" +b[1].toString()+"\n"
//				//	+"\nCaja \nChica:       $"+b[2].toString()+"\n"
//					//+"\nMonto \nEfectivo:  $"+b[3].toString());
//			//table1.addCell("\nTotal:      $" + b[4].toString());
//	//	}
//		Chunk ck = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		Chunk no = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		Chunk si = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		Chunk tu = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));	
//		Chunk pa = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		Chunk ma = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		Chunk du = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		Chunk os = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL));
//		Chunk pi = new Chunk("", new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//		
//		System.out.println("i`m here bitch");
//		
//		for(Object[] r : re) 
//			
//		{
//			
//			System.out.println("i`m here bitch2222222");
//			//System.out.println("szfxdgchj"+r[6]+" "+r[0]+" "+Integer.toString(num1));
//		
//		if (r[0].equals(num1)&&r[6].equals("Corte"))
//		{
//
//			
//			String num2 = r[8].toString();
//			String num3 = r[10].toString();  //convertir objeto a variable string
//			String num4 = r[7].toString();
//			
//			float n1 = Float.parseFloat (num2);
//			float n2 = Float.parseFloat (num3); //variable string a float
//			float n3 = Float.parseFloat (num4);
//			
//			float suma = ((n3)-(n1+n2)); //variable ya flotante hacer la operación para mostrar nuevo campo
//			
//			if(suma==0) {
//				os.setFont(new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.BLACK));
//			}
//			else if(suma>0){
//				os.setFont(new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.green));
//			}
//			else if(suma<0) {
//				os.setFont(new Font(
//				Font.HELVETICA, 12, Font.NORMAL, Color.red));
//			}
//			
//			
//			tu.append("\nTotal\tEfectivo:  $" +r[8].toString());
//			pa.append("\nTotal\tTarjeta:  $" +r[9].toString());
//			ma.append("\nCaja\tChica:  $"+r[10].toString());
//			du.append("\nMonto\tEfectivo: $"+r[7].toString());
//			os.append("\nDiferencia: $"+suma); //concatenar nuevo campo asignado a una  variable ya convertida
//		    pi.append("\nTotal: $" + r[4].toString());
//
//		} 
//		
//		
//		else if(r[0].equals(num1)&&r[6].equals("Cierre"))
//		{
//			//System.out.println("entra2");
//			 ck.append("\nTotal\tEfectivo: $" +r[8].toString());
//			 no.append("\nTotal\tTarjeta:  $" +r[9].toString());
//			 si.append("\nTotal: $" + r[4].toString());
//		}
//		}
//		
//		/**************************************************************************/
//		
//		documento.add(table5);
//		documento.add(table4);
//		documento.add(table);
//		documento.add(table1);
//		documento.add(ck);
//		documento.add(no);
//		documento.add(si);
//		documento.add(tu);
//		documento.add(pa);
//		documento.add(ma);
//		documento.add(du);
//		documento.add(os);
//		documento.add(pi);
//		
//		
//
//		documento.close();
//
//		System.out.println(fullPath);
//		
//	
//		}

		}
