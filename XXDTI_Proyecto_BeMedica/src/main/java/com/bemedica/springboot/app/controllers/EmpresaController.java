package com.bemedica.springboot.app.controllers;

//import java.awt.Desktop;
/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;*/
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
//import javax.swing.JOptionPane;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bemedica.springboot.app.models.dao.IDireccion;
import com.bemedica.springboot.app.models.dao.IEmpresa;
import com.bemedica.springboot.app.models.entity.Direccion;
import com.bemedica.springboot.app.models.entity.DireccionE;
import com.bemedica.springboot.app.models.entity.Empresa;



@Controller
public class EmpresaController {
	
	@Autowired
	@Qualifier("DireccionDaoJPA")
	private IDireccion DireccionDao;
	
	@Autowired
	@Qualifier("EmpresaDaoJPA")
	private IEmpresa EmpresaDao;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	HttpServletRequest request;
	
	
	@RequestMapping (value="/listar_empresa", method=RequestMethod.GET)
	public String listar (Model model )  {
		model.addAttribute("titulo","Empresas");
		model.addAttribute("vista", EmpresaDao.findAll());
		
	
		return "listar_empresa";
	}
	
	
	@RequestMapping(value="/form_empresa")
	public String crear (Map<String, Object> model , Model m)
	{
		Direccion d = new Direccion();
		Empresa e = new Empresa();
		model.put("direccion", d);
		model.put("empresa",e);
		model.put("titulo","Nueva Empresa");
		return "form_empresa";
	}
	
	
	@RequestMapping(value= "/form_empresa", method=RequestMethod.POST)
	public String guardar (@Valid DireccionE direccion,BindingResult resultd ,@Valid Empresa empresa, BindingResult resulte , Model model){
		
		if ( resultd.hasErrors() || resulte.hasErrors()){
			model.addAttribute("titulo","Nueva Empresa");
			System.out.println ("El pinche if si funcina buscale por otro lado");
			return "form_empresa";
		}
		
			DireccionDao.save(direccion);
			empresa.setIdDireccion(direccion.getDireccionId());
			EmpresaDao.save(empresa);
			return"redirect:listar_empresa";
	}
	
	
	@RequestMapping(value= "/form_empresa_editar/{id}")
	public String editar(@PathVariable (value="id") Long id, Map<String, Object> model, Model m ) {
		
		DireccionE d = null;
		Empresa  e = null;
	
		if (id>0) {
			e=EmpresaDao.findOne(id);
			
			d=DireccionDao.findOne(e.getIdDireccion());
			model.put("titulo", "Editar Empresa");
			model.put("direccion", d);
			model.put("empresa",e);

			return "form_empresa";
			
		}
		
		else {
			return "redirect:/listar_empresa";
		}
	}
	
	
	
	@RequestMapping (value="/eliminar_empresa/{id}")
	public String eliminar(@PathVariable (value="id") Long id) {
		if (id > 0 )
		{
			DireccionE d = null;
			Empresa e = null;
			e=EmpresaDao.findOne(id.longValue());
			d=DireccionDao.findOne(e.getIdDireccion());
			Long iddi =d.getDireccionId();
			EmpresaDao.delete(id);
			DireccionDao.delete(iddi);
		}
		
	
		
		return "redirect:/listar_empresa";
	}
	

	/*
	public void excel (HttpServletRequest request ) throws Exception {
        HSSFWorkbook libro = new HSSFWorkbook();// Creamos nuestro libro de excel
  
        HSSFSheet sheet = libro.createSheet("Reporte de empresas");// crear la hoja 
        
        
        String[] titulos = {"ID", "Nombre","rfc", "RS", "Tel", "direccion"};
        List <Empresa> lista  = EmpresaDao.findAll();
        
        Row fila = sheet.createRow(0);
        
        
        for (int i = 0; i < titulos.length; i++) {
            
            Cell celda = fila.createCell(i);

            celda.setCellValue(titulos[i]);
        }
        
        
        int i=1;
        for (Empresa aux: lista) {
        	fila	 = sheet.createRow(i);
        	Cell celda0 = fila.createCell(0);
        	Cell celda1 = fila.createCell(1);
        	Cell celda2 = fila.createCell(2);
        	Cell celda3 = fila.createCell(3);
        	Cell celda4 = fila.createCell(4);
        	Cell celda5 = fila.createCell(5);
        	
        	celda0.setCellValue(aux.getEmpresaId());
            celda1.setCellValue(aux.getEmpresaNombre());
            celda2.setCellValue(aux.getEmpresaRfc());
            celda3.setCellValue(aux.getEmpresaRazonSocial());
            celda4.setCellValue(aux.getEmpresaTel());
            celda5.setCellValue(aux.getIdDireccion());
           i++;
        }
        
        
    	
    		String fullPath = request.getServletContext().getRealPath("/"+"Reporte"+".xls");
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
	
	
	
	

	@SuppressWarnings("unused")
	private void abrir() {
		String file = new String("C:/Users/DTI/Documents/workspace-spring-tool-suite-4-4.4.0.RELEASE/XXDTI_Proyecto_BeMedica/Reporte.xls");
		try {
		    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
		} catch (IOException e) {
		                e.printStackTrace();
		}
    

}*/
	

	/*
	private void filedownload(String fullPath, HttpServletResponse response) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if(file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+"Reporte.xls");
				OutputStream  outputStream = response.getOutputStream();
				byte[] buffer = new	byte[BUFFER_SIZE];
				int bytesRead = -1;
				while((bytesRead = inputStream.read(buffer))!= -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				
				
				
				file.delete();
			
				inputStream.close();
				outputStream.close();

			}catch(Exception e) {
				
				
			}
		}
	}*/
}
