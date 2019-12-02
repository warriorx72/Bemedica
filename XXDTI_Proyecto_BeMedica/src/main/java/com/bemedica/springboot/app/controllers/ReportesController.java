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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bemedica.springboot.app.models.dao.IEmpresa;
import com.bemedica.springboot.app.models.dao.IReportes;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Controller
public class ReportesController {

	@Autowired
	@Qualifier("ReportesDaoJPA")
	private IReportes ReporteDao;

	@Autowired
	@Qualifier("EmpresaDaoJPA")
	private IEmpresa EmpresaDao;

	@Autowired
	private ServletContext context;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/herramientas_reportes", method = RequestMethod.GET)
	public String listar(Model model, Map<String, Object> m) {

		return "herramientas_reportes";
	}

	@RequestMapping(value = "/generar_excel_estudios", method = RequestMethod.POST)
	public void listaar(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ff") Date ff, @RequestParam("fi") Date fi, @RequestParam("periodo") String periodo)
			throws Exception {

		SimpleDateFormat formatoEsMX = new SimpleDateFormat("yyyy-MM-dd");
		String fecha1 = formatoEsMX.format(fi);
		String fecha2 = formatoEsMX.format(ff);
		this.excel( request, fecha1,fecha2,periodo);

		//this.pdf(request, fecha1, fecha2, periodo);

		String fullPath = request.getServletContext().getRealPath("/"+"Reporte"+".xls");
		//String fullPaths = request.getServletContext().getRealPath("/" + "CorteCaja" + ".pdf");
		filedownload(fullPath, response);
		//filedownloadPDF(fullPaths, response);

	}

	public void excel(HttpServletRequest request, String f1, String f2, String p) throws Exception {
		HSSFWorkbook libro = new HSSFWorkbook();// Creamos nuestro libro de excel

		HSSFSheet sheet = libro.createSheet("Reporte de empresas");// crear la hoja

		String[] titulos = { "Fecha", "Empleado", "Sucursal", "Estudio", "No. Vendios", "$$" };
		List<Object[]> rp = ReporteDao.estudios_ventas(f1, f2, p);

		Row fila = sheet.createRow(0);

		for (int i = 0; i < titulos.length; i++) {

			Cell celda = fila.createCell(i);

			celda.setCellValue(titulos[i]);
		}

		int i = 1;

		for (Object[] a : rp) {
			fila = sheet.createRow(i);
			Cell celda0 = fila.createCell(0);
			Cell celda1 = fila.createCell(1);
			Cell celda2 = fila.createCell(2);
			Cell celda3 = fila.createCell(3);
			Cell celda4 = fila.createCell(4);
			Cell celda5 = fila.createCell(5);

			celda0.setCellValue(a[0].toString());
			celda1.setCellValue(a[1].toString());
			celda2.setCellValue(a[2].toString());
			celda3.setCellValue(a[3].toString());
			celda4.setCellValue(Integer.valueOf(a[4].toString()));
			celda5.setCellValue(Double.valueOf(a[5].toString()));

			i++;
		}

		String fullPath = request.getServletContext().getRealPath("/" + "Reporte" + ".xls");
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
				response.setHeader("content-disposition", "attachment; filename=" + "Reporte.xls");
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

	public void pdf(HttpServletRequest request, String f1, String f2, String p) throws Exception {

		String fullPath = request.getServletContext().getRealPath("/" + "CorteCaja" + ".pdf");
		Document documento = new Document();
		FileOutputStream ficheroPdf = new FileOutputStream(fullPath);
		PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
		documento.open();

		Long id = (long) 240;
		List<Object[]> rp = ReporteDao.Resultados_imprimir(id);

		// List <Object[]> paciente=ReporteDao.DatosPaciente(id);
		// PdfPTable tablePaciente = new PdfPTable(6);

		PdfPTable tableR = new PdfPTable(4);

		tableR.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tableR.setWidthPercentage(90);
		tableR.spacingAfter();

		Paragraph salto = new Paragraph("\n");
		salto.setAlignment(Element.ALIGN_CENTER);
		documento.add(salto);
		documento.add(salto);
		documento.add(salto);
		documento.add(salto);

		Paragraph info = new Paragraph("Fecha: 2019-11-11 11:04:06\n\n\nMedico: Zempoaltecatl Zarate Erik\n\n\nPAciente:Zempoaltecatl Zarate Erik");
		Paragraph addr = new Paragraph("Folio: 01234567899\n\n\nEdad: 20 años\n\n\nSexo: Masculino");

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(90);
		table.spacingAfter();
		PdfPCell cell = new PdfPCell(info);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.disableBorderSide(Rectangle.BOX);
		cell.setExtraParagraphSpace(1.5f);
		table.addCell(cell);
		cell = new PdfPCell(addr);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.disableBorderSide(Rectangle.BOX);
		cell.setExtraParagraphSpace(1.5f);
		table.addCell(cell);
		documento.add(table);
		documento.add(new Chunk("\n"));
		documento.add(new Chunk("\n"));
		
		Paragraph Nombre = new Paragraph("Nombre");
		Paragraph Resultado = new Paragraph("Resultado");
		Paragraph Comentario = new Paragraph("Comentario");
		Paragraph VR = new Paragraph("Valores de Referencia");

		
		
		PdfPCell cm = new PdfPCell(Nombre);
		PdfPCell cr = new PdfPCell(Resultado);
		PdfPCell cc = new PdfPCell(Comentario);
		PdfPCell cvr = new PdfPCell(VR);
		
		
		cm.setHorizontalAlignment(Element.ALIGN_LEFT);
		cm.disableBorderSide(Rectangle.BOX);
		cm.setExtraParagraphSpace(1.5f);
		
		cr.setHorizontalAlignment(Element.ALIGN_CENTER);
		cr.disableBorderSide(Rectangle.BOX);
		cr.setExtraParagraphSpace(1.5f);
		
		cc.setHorizontalAlignment(Element.ALIGN_CENTER);
		cc.disableBorderSide(Rectangle.BOX);
		cc.setExtraParagraphSpace(1.5f);
		
		cvr.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cvr.disableBorderSide(Rectangle.BOX);
		cvr.setExtraParagraphSpace(1.5f);
		
		tableR.addCell(cm);
		tableR.addCell(cr);
		tableR.addCell(cc);
		tableR.addCell(cvr);

		tableR.addCell("\n");
		tableR.addCell("\n");
		tableR.addCell("\n");
		tableR.addCell("\n");

		
		for (Object[] a : rp) {
			String Valor="";
			if ( !a[7].equals(" "))    {
				Valor=Valor+a[7].toString()+"\n";
			}
			if ( !a[8].equals(" "))  {
				Valor=Valor+a[8].toString()+"\n";
			}
			if ( !a[9].equals(" ")  ) {
				Valor=Valor+a[9].toString()+"\n";	
			}
			if ( !a[10].equals(" ") ) {
				Valor=Valor+a[10].toString()+"\n";
			}
			if (!a[11].equals(" ") ) {
				Valor=Valor+a[11].toString()+"\n";
			}
			if (!a[12].equals(" ")) {
				Valor=Valor+a[12].toString()+"\n";
			}
			if (!a[13].equals(" ")){
				Valor=Valor+a[13].toString()+"\n";
			}
			
			
			
			Paragraph nombre = new Paragraph(a[1].toString());
			Paragraph resultado = new Paragraph(a[5].toString());
			Paragraph comentario = new Paragraph(a[6].toString());
			Paragraph vr = new Paragraph(Valor);
			
			cm = new PdfPCell(nombre);
			cr = new PdfPCell(resultado);
			cc = new PdfPCell(comentario);
			cvr = new PdfPCell(vr);
			
			
			cm.setHorizontalAlignment(Element.ALIGN_LEFT);
			cm.disableBorderSide(Rectangle.BOX);
			cm.setExtraParagraphSpace(1.5f);
			
			cr.setHorizontalAlignment(Element.ALIGN_CENTER);
			cr.disableBorderSide(Rectangle.BOX);
			cr.setExtraParagraphSpace(1.5f);
			
			cc.setHorizontalAlignment(Element.ALIGN_CENTER);
			cc.disableBorderSide(Rectangle.BOX);
			cc.setExtraParagraphSpace(1.5f);
			
			cvr.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cvr.disableBorderSide(Rectangle.BOX);
			cvr.setExtraParagraphSpace(1.5f);

			
			tableR.addCell(cm);
			tableR.addCell(cr);
			tableR.addCell(cc);
			tableR.addCell(cvr);
	
		}

		documento.add(tableR);

		documento.close();

	}

}
