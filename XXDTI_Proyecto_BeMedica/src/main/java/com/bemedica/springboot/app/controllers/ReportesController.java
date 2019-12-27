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
	// ayudaaa
	@RequestMapping(value = "/generar_excel_estudios", method = RequestMethod.POST)
	public void listaar(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ff") Date ff, @RequestParam("fi") Date fi, @RequestParam("periodo") String periodo)
			throws Exception {

		SimpleDateFormat formatoEsMX = new SimpleDateFormat("yyyy-MM-dd");
				String fecha1 = formatoEsMX.format(fi);
				String fecha2 = formatoEsMX.format(ff);
				this.excel( request, fecha1,fecha2,periodo);
				String fullPath =request.getServletContext().getRealPath("/"+"Reporte"+".xls");
				filedownload(fullPath, response);
				

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

		
}
