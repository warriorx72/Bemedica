package com.bemedica.springboot.app.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.formula.functions.Replace;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
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
import com.bemedica.springboot.app.models.dao.ReportesDaoImpl;

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
	public String listar(Model model,  Map<String, Object> m) {
		
		return "herramientas_reportes";
	}
	// ayudaaa
	@RequestMapping(value = "/generar_excel_estudios", method = RequestMethod.POST)
	public void listaar(Model model,Map<String, Object> m, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ff") String ff, @RequestParam("fi") String fi, Long cond )
			throws Exception {	

				this.excel( request, fi,ff, cond);
				String fullPath =request.getServletContext().getRealPath("/"+"Reporte"+".xls");
				filedownload(fullPath, response);
				
	}

	public void excel(HttpServletRequest request, String fi, String ff, Long cond) throws Exception {
		HSSFWorkbook libro = new HSSFWorkbook();// Creamos nuestro libro de excel
		HSSFSheet sheet = libro.createSheet("Reporte de empresas");// crear la hoja
		
		
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
		FontFecha.setBoldweight((short)2000);
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
		 

		List<Object[]> rf = ReporteDao.estudios_ventas(fi, ff, 2l);	
		int i = 1;
//Crear los días en texto
		Row fila = sheet.createRow(0);
		fila.setHeight((short)400);
		Cell celda = fila.createCell(0);
		celda.setCellValue("Ingresos Acumulados");
		celda.setCellStyle(styleIndex);
		for(Object a:rf) {
			String mes = a.toString();
			String[] arrayfecha = mes.toString().split("_");
			String dia = arrayfecha[0];
			mes = arrayfecha[1];
			String ano = arrayfecha[2];
			String inputDatestr = String.format("%s/%s/%s", dia,mes,ano);
			Date inputDate = new SimpleDateFormat("dd/MMMM/yyyy").parse(inputDatestr);
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(inputDate);
			String DayofWeek = calendario.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("ES")).toUpperCase();
			celda = fila.createCell(i);
			celda.setCellValue(DayofWeek);
			celda.setCellStyle(styleIndex);
			
			i++;
		}
		
//Crear los titulos (Fechas)
		List<Object[]> rp = ReporteDao.estudios_ventas(fi, ff, 2l);	
		fila = sheet.createRow(1);
		i=1;
		celda = fila.createCell(0);
		celda.setCellValue("nombre_sucursal");
		sheet.setColumnWidth(0, 6500);
		celda.setCellStyle(styleFecha);
		for (Object a:rp) {
			celda = fila.createCell(i);
			celda.setCellValue(a.toString().replace("_", "-"));
			celda.setCellStyle(styleFecha);
			sheet.setColumnWidth(i, 4500);
			i++;
		}
		
//Crear el número de filas
		i = 2;
		List<Object[]> rs = ReporteDao.estudios_ventas(fi, ff, 1l);	
		for (Object[] b: rs) {
			fila = sheet.createRow(i);
			i++;
		}
		i=0;
		
//Llenar en excel la sucursal y los montos
		int d=2;
		Row fila1= sheet.getRow(1);
		for (int r=1;r<=fila1.getLastCellNum();r++) {
			for(Object[] c:rs) {
				fila = sheet.getRow(d);
				celda = fila.createCell(i);
				
				if(i==0) {
					celda.setCellValue(c[i].toString());
					celda.setCellStyle(styleContenido);
				}
				else {
					celda.setCellValue(Double.parseDouble(c[i].toString()));
					celda.setCellStyle(styleContenido);
				}
				d++;
			}
			i++;
			d=2;
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
