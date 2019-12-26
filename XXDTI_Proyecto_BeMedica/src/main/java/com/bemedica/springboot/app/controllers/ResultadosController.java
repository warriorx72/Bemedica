package com.bemedica.springboot.app.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bemedica.springboot.app.models.dao.IAntibiogramasDao;
import com.bemedica.springboot.app.models.dao.IResultados;
import com.bemedica.springboot.app.models.dao.IResultadosAntiDao;
import com.bemedica.springboot.app.models.entity.Resultados;
import com.bemedica.springboot.app.models.entity.ResultadosAnti;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
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
	private IAntibiogramasDao AntibiogramasDao;
	
	@Autowired
	private IResultadosAntiDao ResultadosAntiDao;
	
	@Autowired
	private ServletContext context;

	String nombrepdf;

	@RequestMapping(value = "/listar_ordenes/{id}", method = RequestMethod.GET)
	public String listar(@PathVariable(value = "id") Long id, Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		String edad = null;
		List<Object[]> paciente = ResultadosDao.PacienteOrden(id);

		for (Object[] p : paciente) {

			if (p[2].toString() == "0") {
				edad = p[3].toString() + " mes(es)";
			} else {
				edad = p[2].toString() + " año(s)";
			}
			if (p[2].toString() == "0" && p[3].toString() == "0") {
				if (p[10].toString() == "0") {
					edad = "Recien nacido";
				} else {
					edad = p[10].toString() + " día(s)";
				}
			}
		}
		m.addAttribute("edad", edad);

		return "listar_ordenes";
	}

	@RequestMapping(value = "/guardar_resultado_linea/{id}/{lo}", method = RequestMethod.POST)
	public String guardar(@PathVariable(value = "id") Long id, @PathVariable(value = "lo") Long lo,
			@Valid Resultados resultado, Map<String, Object> model, Model m) {
		m.addAttribute("id", id);
		// count = (request.getParameter("counter") == null) ? 0 :
		// Integer.parseInt(request.getParameter("counter"));
		resultado.setValidacion("1");
		Long auxLineas = resultado.getOrdenEstudioId();
		int i = 0;
		resultado.setImpresion(true);
		ResultadosDao.save(resultado);

		List<String> aux = ResultadosDao.ValidarLinea(auxLineas);
		for (String a : aux) {
			if (a.equals("1")) {
				i++;
			}
		}
		if (i == aux.size()) {
			ResultadosDao.Actualizacion_linea(auxLineas);
		}
		m.addAttribute("resul", ResultadosDao.findAllEstudio(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		m.addAttribute("resulCultivo", ResultadosDao.findAllCultivo(lo));
		m.addAttribute("antibiograma", AntibiogramasDao.findAll()); 
		if (ResultadosDao.LineasOrden(id).size() == ResultadosDao.ValidarOrden(id).size()) {
			System.out.println("la condicion se cumpple ");
			ResultadosDao.Actualizacion_Orden(id);
		}

		return "listar_ordenes";
	}
	
	
	
	@RequestMapping(value = "/guardar_resultado_linea_cultivo/{id}/{lo}", method = RequestMethod.POST)
	public String guardarCultivos(
			@PathVariable(value = "id") Long id, @PathVariable(value = "lo") Long lo,@Valid Resultados resultado, Map<String, Object> model, Model m, @RequestParam(value = "antibiograma", defaultValue="0") Long anti){
		
		if (resultado.getResultadoCuanti().equals("0.0000")) {
			m.addAttribute("id", id);
			resultado.setValidacion("1");
			Long auxLineas = resultado.getOrdenEstudioId();
			int i = 0;
			resultado.setImpresion(true);
			resultado.setResultadoCuali("Negativo");
			resultado.setAntiIdR(null);
			ResultadosDao.save(resultado);

			List<String> aux = ResultadosDao.ValidarLinea(auxLineas);
			for (String a : aux) {
				if (a.equals("1")) {
					i++;
				}
			}
			if (i == aux.size()) {
				ResultadosDao.Actualizacion_linea(auxLineas);
			}
			if (ResultadosDao.LineasOrden(id).size() == ResultadosDao.ValidarOrden(id).size()) {
				ResultadosDao.Actualizacion_Orden(id);
			}
	
		}
		if (resultado.getResultadoCuanti().equals("1.0000")) {
			
			
			m.addAttribute("id", id);
			resultado.setValidacion("1");
			Long auxLineas = resultado.getOrdenEstudioId();
			int i = 0;
			resultado.setImpresion(true);
			resultado.setAntiIdR(anti);
			ResultadosDao.save(resultado);

			List<String> aux = ResultadosDao.ValidarLinea(auxLineas);
			for (String a : aux) {
				if (a.equals("1")) {
					i++;
				}
			}
			if (i == aux.size()) {
				ResultadosDao.Actualizacion_linea(auxLineas);
			}
			if (ResultadosDao.LineasOrden(id).size() == ResultadosDao.ValidarOrden(id).size()) {
				ResultadosDao.Actualizacion_Orden(id);
			}
			
			
			if (ResultadosAntiDao.verficarExistencia(resultado.getResultadoId()) == null || ResultadosAntiDao.verficarExistencia(resultado.getResultadoId()).isEmpty()) 
			{
				List<Object []> auxAnti = ResultadosAntiDao.antibioticos(anti);
				 
				for (Object [] ant : auxAnti) {
					ResultadosAnti resAnti = new ResultadosAnti ();
					
					//resultado_id
					resAnti.setResultadoId(resultado.getResultadoId());
					
					// anti_id
					resAnti.setAntiId(anti);
					
					// resultado 
					resAnti.setResultado("1");
					
					// antibiotico_id
					resAnti.setAntibioticoId(  Long.valueOf(ant[0].toString()));
					
					// nombre antibiotico
					resAnti.setAntibioticoNombre(ant[1].toString());
					
					ResultadosAntiDao.save(resAnti);
				}
				
				

			}
			m.addAttribute("resulCultivo_anti", ResultadosAntiDao.verficarExistencia(resultado.getResultadoId()));
			
			m.addAttribute("Nombre_Antibiograma", ResultadosAntiDao.AntibiogramaNombre(resultado.getResultadoId()));
			
			
		}
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		m.addAttribute("resulCultivo", ResultadosDao.findAllCultivo(lo));
		m.addAttribute("antibiograma", AntibiogramasDao.findAll());
		m.addAttribute("resul", ResultadosDao.findAllEstudio(lo));
		
	
		return "listar_ordenes";
			
	}
	
	
	
	@RequestMapping(value = "/guardar_resultado_linea_cultivo_anti/{id}/{lo}", method = RequestMethod.POST)
	public String guardarCultivos_Anti(
			@PathVariable(value = "id") Long id, 
			@PathVariable(value = "lo") Long lo,
			@Valid ResultadosAnti resultado, 
			Map<String, Object> model, 
			Model m){
		
			m.addAttribute("id", id);
			

			ResultadosAntiDao.save(resultado);


			m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
			m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
			m.addAttribute("resulCultivo", ResultadosDao.findAllCultivo(lo));
			m.addAttribute("antibiograma", AntibiogramasDao.findAll());
			m.addAttribute("resul", ResultadosDao.findAllEstudio(lo));
			m.addAttribute("resulCultivo_anti", ResultadosAntiDao.verficarExistencia(resultado.getResultadoId()));
			return "listar_ordenes";
					
			
	}

	@RequestMapping(value = "/agregar_resultado/{id}/{i}/{t}/{lo}", method = RequestMethod.GET)
	public String agregar_resultado(@PathVariable(value = "id") Long id, @PathVariable(value = "i") Long i,
			@PathVariable(value = "t") String tipo, @PathVariable(value = "lo") Long lo, Map<String, Object> model,
			Model m) {
		m.addAttribute("id", id);
		m.addAttribute("lo", lo);
		Resultados auxr = new Resultados();
		m.addAttribute("aux", auxr);
		if (ResultadosDao.findAll(lo) == null || ResultadosDao.findAll(lo).isEmpty()) {
			
			if (tipo.equals("estudio")) {
				List<Object[]> aux = ResultadosDao.Estudios(i);
				Resultados resul = new Resultados();
				for (Object[] a : aux) {
					resul.setOrdenEstudioId(lo);
					if (a[2].toString().equals("Cualitativo")) {
						resul.setResultadoCuali("Resultado...");
					}
					if (a[2].toString().equals("Cuantitativo")) {
						resul.setResultadoCuanti("0.00");
					}
					resul.setValidacion("0");
					resul.setEstudio_id(Long.valueOf(a[0].toString()));
					resul.setEstudioNombre(a[1].toString());
					resul.setPerfil(a[1].toString());
					resul.setImpresion(true);
					resul.setTipo("1");
					ResultadosDao.save(resul);
				}
			}
			if (tipo.equals("perfil")) {
				List<Object[]> aux = ResultadosDao.Perfil(i);
				String nombre = ResultadosDao.NombrePerfil(i);
				for (Object[] a : aux) {
					
					if ( a[3].toString().equals("estudio")) {
						
						Resultados resul = new Resultados();
						resul.setOrdenEstudioId(lo);
						if (a[2].toString().equals("Cualitativo")) {
							resul.setResultadoCuali("Resultado...");
						}
						if (a[2].toString().equals("Cuantitativo")) {
							resul.setResultadoCuanti("0.00");
						}
						resul.setValidacion("0");
						resul.setEstudio_id(Long.valueOf(a[0].toString()));
						resul.setEstudioNombre(a[1].toString());
						resul.setPerfil(nombre);
						resul.setImpresion(true);
						resul.setTipo("1");
						ResultadosDao.save(resul);
					}
					
					
					if ( a[3].toString().equals("cultivo")) {
						
						Resultados resul = new Resultados();
						resul.setOrdenEstudioId(lo);
					
						
						
						resul.setResultadoCuanti("0.0000");
						
						resul.setValidacion("0");
						resul.setEstudio_id(Long.valueOf(a[0].toString()));
						resul.setEstudioNombre(a[1].toString());
						resul.setPerfil(nombre);
						resul.setImpresion(true);
						resul.setTipo("2");
						ResultadosDao.save(resul);
					}
					
				
				}
			}
			if (tipo.equals("paquete")) {
				List<Object[]> aux = ResultadosDao.Paquete(i);
				for (Object[] a : aux) {
					Resultados resul = new Resultados();
					
					
					
					// en el paquete hay estudios solos 
					if (a[3].equals("null") &&  a[4].equals("aux")) {
						resul.setOrdenEstudioId(lo);
						if (a[2].toString().equals("Cualitativo")) {
							resul.setResultadoCuali("Resultado...");
						}
						if (a[2].toString().equals("Cuantitativo")) {
							resul.setResultadoCuanti("0.00");
						}
						resul.setComentario("");
						resul.setValidacion("0");
						if (a[3].toString() == "null" || a[3].toString().equals("null")) {
							resul.setPerfil(a[1].toString());
						} else {
							resul.setPerfil(a[3].toString());
						}
						resul.setEstudio_id(Long.valueOf(a[0].toString()));
						resul.setEstudioNombre(a[1].toString());
						resul.setImpresion(true);
						resul.setTipo("1");
						ResultadosDao.save(resul);

					} 
					
					// perfiles dentro del paquete
					if (!a[3].equals("null") &&  !a[4].equals("cultivo")  &&  !a[4].equals("aux")) {

						List<Object[]> aux2 = ResultadosDao.Perfil((Long.valueOf(a[3].toString())));
						String nombre2 = ResultadosDao.NombrePerfil((Long.valueOf(a[3].toString())));
						for (Object[] a2 : aux2) {
							
							if ( a2[3].toString().equals("estudio")) {
								Resultados resul2 = new Resultados();
								resul2.setOrdenEstudioId(lo);
								if (a2[2].toString().equals("Cualitativo")) {
									resul2.setResultadoCuali("Resultado...");
								}
								if (a2[2].toString().equals("Cuantitativo")) {
									resul2.setResultadoCuanti("0.00");
								}
								resul2.setValidacion("0");
								resul2.setEstudio_id(Long.valueOf(a2[0].toString()));
								resul2.setEstudioNombre(a2[1].toString());
								resul2.setPerfil(nombre2);
								resul2.setImpresion(true);
								resul2.setTipo("1");
								ResultadosDao.save(resul2);
								
							}
							
							if ( a2[3].toString().equals("cultivo")) {
								
								Resultados resul2 = new Resultados();
								resul2.setOrdenEstudioId(lo);
								resul.setResultadoCuanti("0.0000");
								resul2.setValidacion("0");
								resul2.setEstudio_id(Long.valueOf(a2[0].toString()));
								resul2.setEstudioNombre(a2[1].toString());
								resul2.setPerfil(nombre2);
								resul2.setImpresion(true);
								resul2.setTipo("2");
								ResultadosDao.save(resul2);
							
								
							}
							
						}

					}
					
					// existe un cultiivo individual en el paquete 
					if (a[3].equals("null") && a[4].equals("cultivo")) {
						resul.setOrdenEstudioId(lo);
						
						resul.setResultadoCuanti("0.0000");
						
						resul.setValidacion("0");
						resul.setEstudio_id(Long.valueOf(a[0].toString()));
						resul.setEstudioNombre(a[1].toString());
						resul.setPerfil(a[1].toString());
						resul.setImpresion(true);
						resul.setTipo("2");
						ResultadosDao.save(resul);

					}
				}
			}
			
			if (tipo.equals("cultivo")) {
				List<Object[]> aux = ResultadosDao.Estudios(i);
				Resultados resul = new Resultados();
				for (Object[] a : aux) {
					resul.setOrdenEstudioId(lo);
					resul.setResultadoCuanti("0.0000");
					resul.setValidacion("0");
					resul.setEstudio_id(Long.valueOf(a[0].toString()));
					resul.setEstudioNombre(a[1].toString());
					resul.setPerfil(a[1].toString());
					resul.setImpresion(true);
					resul.setTipo("2");
					ResultadosDao.save(resul);
				}
			}
			
			if (tipo.equals("gabinete")) {
				List<Object[]> aux = ResultadosDao.Estudios(i);
				Resultados resul = new Resultados();
				for (Object[] a : aux) {
					resul.setOrdenEstudioId(lo);
					
					resul.setResultadoCuali("Resultado...");
					
					resul.setResultadoCuanti("0.00");
					resul.setValidacion("0");
					resul.setEstudio_id(Long.valueOf(a[0].toString()));
					resul.setEstudioNombre(a[1].toString());
					resul.setPerfil(a[1].toString());
					resul.setImpresion(true);
					resul.setTipo("3");
					ResultadosDao.save(resul);
				}
			}
		}
		m.addAttribute("resul", ResultadosDao.findAllEstudio(lo));
		m.addAttribute("resulCultivo", ResultadosDao.findAllCultivo(lo));
		m.addAttribute("antibiograma", AntibiogramasDao.findAll());
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		return "listar_ordenes";
	}


	@RequestMapping(value = "/descargar_resultados/{id}")
	public void listaar(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long aux = 1000000 + id;
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd-hhmmss");
		nombrepdf = "ORD" + aux + "-" + formateador.format(ahora);
		String fullPaths = request.getServletContext().getRealPath("/" + nombrepdf + ".pdf");
		this.pdf(request, id, fullPaths);
		filedownloadPDF(fullPaths, response, id);

	}

	public void pdf(HttpServletRequest request, Long id, String fullPath)
			throws FileNotFoundException, DocumentException {

		// String fullPath = request.getServletContext().getRealPath("/" + "Resultado" +
		// ".pdf");

		Font fuen_1 = new Font(FontFamily.HELVETICA, 9.0f, Font.BOLD, BaseColor.BLACK);

		Font fuen_2 = new Font(FontFamily.HELVETICA, 9.0f, Font.NORMAL, BaseColor.BLACK);

		Font fuen_comentario = new Font(FontFamily.HELVETICA, 7.0f, Font.NORMAL, BaseColor.BLACK);
		Font fuen_validacion = new Font(FontFamily.HELVETICA, 7.0f, Font.NORMAL, BaseColor.BLACK);

		// Document documento = new Document();
		Document documento = new Document(PageSize.A4, 36, 36, 182, 120);

		FileOutputStream ficheroPdf = new FileOutputStream(fullPath);
		PdfWriter writer = PdfWriter.getInstance(documento, ficheroPdf);

		String fecha = null;
		String medico = null;
		String pacienten = null;
		String folio = null;
		String edad = null;
		String sexo = null;
		List<Object[]> paciente = ResultadosDao.PacienteOrden(id);

		for (Object[] p : paciente) {

			fecha = p[5].toString();

			if (p[7].toString() == null) {
				medico = "A QUIEN CORRESPONDA";
			} else {
				medico = p[7].toString();
			}

			pacienten = p[1].toString();
			folio = p[6].toString();

			if (p[2].toString() == "0") {
				edad = p[3].toString() + " mes(es)";
			} else {
				edad = p[2].toString() + " año(s)";
			}

			if (p[2].toString() == "0" && p[3].toString() == "0") {
				if (p[10].toString() == "0") {
					edad = "Recien nacido";
				} else {
					edad = p[10].toString() + " día(s)";
				}
			}

			sexo = p[4].toString();

		}

		HeaderFooterPageEvent event = new HeaderFooterPageEvent(fecha, medico, pacienten, folio, edad, sexo);
		writer.setPageEvent(event);
		documento.open();

		PdfPTable tableR = new PdfPTable(4);

		tableR.setWidths(new float[] { 40.0f, 20.0f, 20.0f, 30.0f });

		//tableR.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tableR.setWidthPercentage(100);
		tableR.setHeaderRows(1);

		Paragraph Estudio = new Paragraph("ESTUDIO",fuen_2);
		Paragraph Resultado = new Paragraph("RESULTADO",fuen_2);
		Paragraph Espacio = new Paragraph("",fuen_2);
		Paragraph VR = new Paragraph("VALORES DE REFERENCIA",fuen_2);

		PdfPCell ce = new PdfPCell(Estudio);
		PdfPCell cr = new PdfPCell(Resultado);
		PdfPCell esp = new PdfPCell(Espacio);
		PdfPCell cvr = new PdfPCell(VR);

		ce.setHorizontalAlignment(Element.ALIGN_LEFT);
		//ce.disableBorderSide(Rectangle.BOX);
		ce.setBorder(Rectangle.BOTTOM);
		ce.setExtraParagraphSpace(5f);

		cr.setHorizontalAlignment(Element.ALIGN_RIGHT);
		//cr.disableBorderSide(Rectangle.BOX);
		cr.setBorder(Rectangle.BOTTOM);
		cr.setExtraParagraphSpace(5f);

		cvr.setHorizontalAlignment(Element.ALIGN_LEFT);
		//cvr.disableBorderSide(Rectangle.BOX);
		cvr.setBorder(Rectangle.BOTTOM);
		cvr.setExtraParagraphSpace(5f);

		esp.setHorizontalAlignment(Element.ALIGN_CENTER);
		//esp.disableBorderSide(Rectangle.BOX);
		esp.setBorder(Rectangle.BOTTOM);
		esp.setExtraParagraphSpace(5f);

		tableR.addCell(ce);
		tableR.addCell(cr);
		tableR.addCell(esp);
		tableR.addCell(cvr);

	
		

		List<Object[]> LineasOrdenes = ResultadosDao.LineasOrden(id);
		for (Object[] lo : LineasOrdenes) {

			System.out.print(lo[0] + " ");
			System.out.print(lo[1] + " ");
			System.out.print(lo[2] + " ");
			System.out.print(lo[3] + " ");
			System.out.print(lo[4] + " ");
			System.out.print(lo[5] + " ");
			System.out.print(lo[6] + " ");
			System.out.print(lo[7] + " ");
			System.out.print(lo[8] + " ");
			System.out.println(" ");

			if (lo[8].toString().equals("estudio")) {
				List<Object[]> estudio = ResultadosDao.Resultados((Long.valueOf(lo[4].toString())), (Long.valueOf(lo[7].toString())));
				for (Object[] e : estudio) {
					String Valor = "";

					if (!e[7].equals(" ")) {
						Valor = Valor + e[7].toString() + "\n";
					}
					if (!e[8].equals(" ")) {
						Valor = Valor + e[8].toString() + "\n";
					}
					if (!e[9].equals(" ")) {
						Valor = Valor + e[9].toString() + "\n";
					}
					if (!e[10].equals(" ")) {
						Valor = Valor + e[10].toString() + "\n";
					}
					if (!e[11].equals(" ")) {
						Valor = Valor + e[11].toString() + "\n";
					}
					if (!e[12].equals(" ")) {
						Valor = Valor + e[12].toString() + "\n";
					}
					if (!e[13].equals(" ")) {
						Valor = Valor + e[13].toString() + "\n";
					}

					Estudio = new Paragraph(e[2].toString(), fuen_1);
					Resultado = new Paragraph(e[6].toString(), fuen_2);
					Espacio = new Paragraph("  ");
					VR = new Paragraph(Valor, fuen_2);
					Paragraph Comentario = null;

					if (e[0].toString().equals("") || e[0].toString().equals(null)) {
						Comentario = new Paragraph("");
					} else {
						Comentario = new Paragraph("OBSERVACIONES\n" + e[0].toString(), fuen_comentario);
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

					Paragraph Validacion = new Paragraph("Estudio(s) validado por: Q.F.B: El doctor Chapatín",
							fuen_validacion);

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
			}
			if (lo[8].toString().equals("perfil")) {
				documento.newPage();
				Paragraph perfilTitulo;
				Paragraph perfilTituloR;
				Paragraph perfilTituloE;
				Paragraph perfilTituloVR;
				List<Object[]> perfil = ResultadosDao.Perfil((Long.valueOf(lo[7].toString())));
				String auxComentario = "";
				int contador=0;	
				for (Object[] aux : perfil) {
									
					List<Object[]> estudio = ResultadosDao.Resultados((Long.valueOf(lo[4].toString())),
							(Long.valueOf(aux[0].toString())));
					
					for (Object[] e : estudio) {
						contador++;
						
						perfilTitulo = new Paragraph(lo[3].toString(), fuen_1);
						perfilTituloR = new Paragraph("");
						perfilTituloE = new Paragraph("  ");
						perfilTituloVR = new Paragraph("");
						
						PdfPCell cep = new PdfPCell(perfilTitulo);
						PdfPCell crp = new PdfPCell(perfilTituloR);
						PdfPCell espp = new PdfPCell(perfilTituloE);
						PdfPCell cvrp = new PdfPCell(perfilTituloVR);
						
						cep.setHorizontalAlignment(Element.ALIGN_LEFT);
						cep.disableBorderSide(Rectangle.BOX);
						cep.setExtraParagraphSpace(1.5f);

						crp.setHorizontalAlignment(Element.ALIGN_RIGHT);
						crp.disableBorderSide(Rectangle.BOX);
						crp.setExtraParagraphSpace(1.5f);

						espp.setHorizontalAlignment(Element.ALIGN_RIGHT);
						espp.disableBorderSide(Rectangle.BOX);
						espp.setExtraParagraphSpace(1.5f);

						cvrp.setHorizontalAlignment(Element.ALIGN_LEFT);
						cvrp.disableBorderSide(Rectangle.BOX);
						cvrp.setExtraParagraphSpace(1.5f);
						
						if (contador==1) {
						tableR.addCell(cep);
						tableR.addCell(crp);
						tableR.addCell(espp);
						tableR.addCell(cvrp);}
						
						
						String Valor = "";

						if (!e[7].equals(" ")) {
							Valor = Valor + e[7].toString() + "\n";
						}
						if (!e[8].equals(" ")) {
							Valor = Valor + e[8].toString() + "\n";
						}
						if (!e[9].equals(" ")) {
							Valor = Valor + e[9].toString() + "\n";
						}
						if (!e[10].equals(" ")) {
							Valor = Valor + e[10].toString() + "\n";
						}
						if (!e[11].equals(" ")) {
							Valor = Valor + e[11].toString() + "\n";
						}
						if (!e[12].equals(" ")) {
							Valor = Valor + e[12].toString() + "\n";
						}
						if (!e[13].equals(" ")) {
							Valor = Valor + e[13].toString() + "\n";
						}
						Estudio = new Paragraph("   " + e[2].toString(), fuen_2);
						Resultado = new Paragraph(e[6].toString(), fuen_2);
						Espacio = new Paragraph("  ");
						VR = new Paragraph(Valor, fuen_2);

						if (e[0].toString().equals("") || e[0].toString().equals(null)) {
						} 
						else {
							if (auxComentario == "") {
								auxComentario = e[0].toString();

							} else {
								auxComentario +=  ", " + e[0].toString();

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
				
				esp = new PdfPCell(Espacio);
				esp.setHorizontalAlignment(Element.ALIGN_RIGHT);
				esp.disableBorderSide(Rectangle.BOX);
				esp.setExtraParagraphSpace(1.5f);

				Paragraph Validacion = new Paragraph("   Estudio(s) validado por: Q.F.B: El doctor Chapatín",fuen_validacion);
				PdfPCell cell_validacion = new  PdfPCell(Validacion);
				
				Paragraph Comentario = new Paragraph("   OBSERVACIONES:\n    " + auxComentario, fuen_comentario);
				PdfPCell cell_comentario = new  PdfPCell(Comentario);
				
				cell_validacion.setBorder(PdfPCell.NO_BORDER);
				cell_validacion.disableBorderSide(Rectangle.BOX);
				cell_validacion.setExtraParagraphSpace(1.5f);
				cell_comentario.setBorder(PdfPCell.NO_BORDER);
				cell_comentario.disableBorderSide(Rectangle.BOX);
				cell_comentario.setExtraParagraphSpace(1.5f);
				
				tableR.addCell(cell_comentario);
				tableR.addCell(esp);
				tableR.addCell(esp);
				tableR.addCell(esp);
				tableR.addCell(cell_validacion);
				tableR.addCell(esp);
				tableR.addCell(esp);
				tableR.addCell(esp);
				
			}
			if (lo[8].toString().equals("paquete")) {
				documento.newPage();
				int contadorTitulo=0;
				Paragraph paqueteTitulo;
				Paragraph paqueteTituloR;
				Paragraph paqueteTituloE;
				Paragraph paqueteTituloVR;
				

				List<Object[]> paquete = ResultadosDao.Paquete((Long.valueOf(lo[7].toString())));

				for (Object[] aux : paquete) {

					if (aux[3].toString().equals("null")) {

						List<Object[]> estudio = ResultadosDao.Resultados((Long.valueOf(lo[4].toString())),
								(Long.valueOf(aux[0].toString())));
						for (Object[] e : estudio) {
							String Valor = "";
							
							contadorTitulo++;
							
							paqueteTitulo = new Paragraph(lo[3].toString(), fuen_1);
							paqueteTituloR = new Paragraph("");
							paqueteTituloE = new Paragraph("  ");
							paqueteTituloVR = new Paragraph("");
							
							PdfPCell cepa = new PdfPCell(paqueteTitulo);
							PdfPCell crpa = new PdfPCell(paqueteTituloR);
							PdfPCell esppa = new PdfPCell(paqueteTituloE);
							PdfPCell cvrpa = new PdfPCell(paqueteTituloVR);
							
							cepa.setHorizontalAlignment(Element.ALIGN_LEFT);
							cepa.disableBorderSide(Rectangle.BOX);
							cepa.setExtraParagraphSpace(1.5f);

							crpa.setHorizontalAlignment(Element.ALIGN_RIGHT);
							crpa.disableBorderSide(Rectangle.BOX);
							crpa.setExtraParagraphSpace(1.5f);

							esppa.setHorizontalAlignment(Element.ALIGN_RIGHT);
							esppa.disableBorderSide(Rectangle.BOX);
							esppa.setExtraParagraphSpace(1.5f);

							cvrpa.setHorizontalAlignment(Element.ALIGN_LEFT);
							cvrpa.disableBorderSide(Rectangle.BOX);
							cvrpa.setExtraParagraphSpace(1.5f);
							
							if (contadorTitulo==1) {
							tableR.addCell(cepa);
							tableR.addCell(crpa);
							tableR.addCell(esppa);
							tableR.addCell(cvrpa);}
							

							if (!e[7].equals(" ")) {Valor = Valor + e[7].toString() + "\n";}
							if (!e[8].equals(" ")) {Valor = Valor + e[8].toString() + "\n";}
							if (!e[9].equals(" ")) {Valor = Valor + e[9].toString() + "\n";}
							if (!e[10].equals(" ")) {Valor = Valor + e[10].toString() + "\n";}
							if (!e[11].equals(" ")) {Valor = Valor + e[11].toString() + "\n";}
							if (!e[12].equals(" ")) {Valor = Valor + e[12].toString() + "\n";}
							if (!e[13].equals(" ")) {Valor = Valor + e[13].toString() + "\n";}
							
							Estudio = new Paragraph("   " + e[2].toString(), fuen_2);
							Resultado = new Paragraph(e[6].toString(), fuen_2);
							Espacio = new Paragraph("  ");
							VR = new Paragraph(Valor, fuen_2);
							Paragraph Comentario = null;

							if (e[0].toString().equals("") || e[0].toString().equals(null)) {
								Comentario = new Paragraph("");
							} else {
								Comentario = new Paragraph("   OBSERVACIONES\n   " + e[0].toString(), fuen_comentario);
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

							Paragraph Validacion = new Paragraph(
									"   Estudio(s) validado por: Q.F.B: El doctor Chapatín", fuen_validacion);
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

					} // llave del if para ver si no es perfil

					else {
						
						if (contadorTitulo == 0) {
							
					
		
							paqueteTitulo = new Paragraph(lo[3].toString(), fuen_1);
							paqueteTituloR = new Paragraph("");
							paqueteTituloE = new Paragraph("  ");
							paqueteTituloVR = new Paragraph("");
							
							PdfPCell cepa = new PdfPCell(paqueteTitulo);
							PdfPCell crpa = new PdfPCell(paqueteTituloR);
							PdfPCell esppa = new PdfPCell(paqueteTituloE);
							PdfPCell cvrpa = new PdfPCell(paqueteTituloVR);
							
							cepa.setHorizontalAlignment(Element.ALIGN_LEFT);
							cepa.disableBorderSide(Rectangle.BOX);
							cepa.setExtraParagraphSpace(1.5f);

							crpa.setHorizontalAlignment(Element.ALIGN_RIGHT);
							crpa.disableBorderSide(Rectangle.BOX);
							crpa.setExtraParagraphSpace(1.5f);

							esppa.setHorizontalAlignment(Element.ALIGN_RIGHT);
							esppa.disableBorderSide(Rectangle.BOX);
							esppa.setExtraParagraphSpace(1.5f);

							cvrpa.setHorizontalAlignment(Element.ALIGN_LEFT);
							cvrpa.disableBorderSide(Rectangle.BOX);
							cvrpa.setExtraParagraphSpace(1.5f);
							
							if (contadorTitulo==1) {
							tableR.addCell(cepa);
							tableR.addCell(crpa);
							tableR.addCell(esppa);
							tableR.addCell(cvrpa);}
							
						}
						
						String auxComentario2 = "";
						Paragraph perfilTitulo;
						Paragraph perfilTituloR;
						Paragraph perfilTituloE;
						Paragraph perfilTituloVR;

						List<Object[]> perfil = ResultadosDao.Perfil((Long.valueOf(aux[3].toString())));
						int contadoraux =0;

						for (Object[] aux2 : perfil) {
							List<Object[]> estudio = ResultadosDao.Resultados((Long.valueOf(lo[4].toString())),
									(Long.valueOf(aux2[0].toString())));
							for (Object[] e : estudio) {
								String Valor = "";
								
								contadoraux++;
								perfilTitulo = new Paragraph("    "+aux[4].toString(), fuen_1);
								perfilTituloR = new Paragraph("");
								perfilTituloE = new Paragraph("  ");
								perfilTituloVR = new Paragraph("");
								
								PdfPCell cep = new PdfPCell(perfilTitulo);
								PdfPCell crp = new PdfPCell(perfilTituloR);
								PdfPCell espp = new PdfPCell(perfilTituloE);
								PdfPCell cvrp = new PdfPCell(perfilTituloVR);
								
								cep.setHorizontalAlignment(Element.ALIGN_LEFT);
								cep.disableBorderSide(Rectangle.BOX);
								cep.setExtraParagraphSpace(1.5f);

								crp.setHorizontalAlignment(Element.ALIGN_RIGHT);
								crp.disableBorderSide(Rectangle.BOX);
								crp.setExtraParagraphSpace(1.5f);

								espp.setHorizontalAlignment(Element.ALIGN_RIGHT);
								espp.disableBorderSide(Rectangle.BOX);
								espp.setExtraParagraphSpace(1.5f);

								cvrp.setHorizontalAlignment(Element.ALIGN_LEFT);
								cvrp.disableBorderSide(Rectangle.BOX);
								cvrp.setExtraParagraphSpace(1.5f);
								
								if (contadoraux==1) {
								tableR.addCell(cep);
								tableR.addCell(crp);
								tableR.addCell(espp);
								tableR.addCell(cvrp);
								
								}

								if (!e[7].equals(" ")) {Valor = Valor + e[7].toString() + "\n";}
								if (!e[8].equals(" ")) {Valor = Valor + e[8].toString() + "\n";}
								if (!e[9].equals(" ")) {Valor = Valor + e[9].toString() + "\n";}
								if (!e[10].equals(" ")) {Valor = Valor + e[10].toString() + "\n";}
								if (!e[11].equals(" ")) {Valor = Valor + e[11].toString() + "\n";}
								if (!e[12].equals(" ")) {Valor = Valor + e[12].toString() + "\n";}
								if (!e[13].equals(" ")) {Valor = Valor + e[13].toString() + "\n";}

								Estudio = new Paragraph("     " + e[2].toString(), fuen_2);
								Resultado = new Paragraph(e[6].toString(), fuen_2);
								Espacio = new Paragraph("  ");
								VR = new Paragraph(Valor, fuen_2);

								if (e[0].toString().equals("") || e[0].toString().equals(null)) {
								} else {
									if (auxComentario2 == "") {
										auxComentario2 = auxComentario2 + e[0].toString();

									} else {
										auxComentario2 += auxComentario2 + ", " + e[0].toString();

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
						Paragraph Comentario;
						
						if (contadoraux >= 1) {
						Paragraph Validacion = new Paragraph("     Estudio(s) validado por: Q.F.B: El doctor Chapatín",
								fuen_validacion);
						if( auxComentario2 == null || auxComentario2 =="") {
							Comentario= new Paragraph("");
							}
						else{
							Comentario= new Paragraph("     OBSERVACIONES\n      " + auxComentario2,
									fuen_comentario);
							
							}
						
						
						esp = new PdfPCell(Espacio);
						esp.setHorizontalAlignment(Element.ALIGN_RIGHT);
						esp.disableBorderSide(Rectangle.BOX);
						esp.setExtraParagraphSpace(1.5f);

						//Paragraph Validacion = new Paragraph("   Estudio(s) validado por: Q.F.B: El doctor Chapatín",fuen_validacion);
						PdfPCell cell_validacion = new  PdfPCell(Validacion);
						
						//Paragraph Comentario = new Paragraph("   OBSERVACIONES:\n    " + auxComentario, fuen_comentario);
						PdfPCell cell_comentario = new  PdfPCell(Comentario);
						
						cell_validacion.setBorder(PdfPCell.NO_BORDER);
						cell_validacion.disableBorderSide(Rectangle.BOX);
						cell_validacion.setExtraParagraphSpace(1.5f);
						cell_comentario.setBorder(PdfPCell.NO_BORDER);
						cell_comentario.disableBorderSide(Rectangle.BOX);
						cell_comentario.setExtraParagraphSpace(1.5f);
						
						tableR.addCell(cell_comentario);
						tableR.addCell(esp);
						tableR.addCell(esp);
						tableR.addCell(esp);
						tableR.addCell(cell_validacion);
						tableR.addCell(esp);
						tableR.addCell(esp);
						tableR.addCell(esp);
						
						
						}

					} // llave del else de if para verificar que no perfil

				} // llave de la consulta de pauete

			} // llave de la condición de la paquete
			
			
			if (lo[8].toString().equals("cultivo")) {
				List<Object[]> estudio = ResultadosAntiDao.ResultadoCultivo((Long.valueOf(lo[4].toString())), (Long.valueOf(lo[7].toString())));
				for (Object[] e : estudio) {
					String Valor = "";

					Estudio = new Paragraph(e[0].toString(), fuen_1);
					Resultado = new Paragraph(e[1].toString(), fuen_2);
					Espacio = new Paragraph("  ");
					VR = new Paragraph(Valor, fuen_2);
					Paragraph Comentario = null;

				

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

					Paragraph Validacion = new Paragraph("Estudio(s) validado por: Q.F.B: El doctor Chapatín",
							fuen_validacion);

					PdfPCell cell = new PdfPCell(Comentario);

					PdfPCell cell2 = new PdfPCell(Validacion);
					
					tableR.addCell(ce);
					tableR.addCell(cr);
					tableR.addCell(esp);
					tableR.addCell(cvr);
					
					
					
					if (!e[1].toString().equals("Negativo")) {
						
						List<Object[]> antibiograma = ResultadosAntiDao.ResultadoAntibio((Long.valueOf(lo[4].toString())), (Long.valueOf(lo[7].toString())));
						
						
						Estudio = new Paragraph("Antibiograma", fuen_1);
						Resultado = new Paragraph("", fuen_2);
						Espacio = new Paragraph("  ");
						VR = new Paragraph(Valor, fuen_2);
						

					

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
						
						
						for (Object[] a : antibiograma) {
				
							Estudio = new Paragraph("   "+a[0].toString(), fuen_2	);
							Resultado = new Paragraph(a[1].toString(), fuen_2);
							Espacio = new Paragraph("  ");
							VR = new Paragraph("", fuen_2);
						

						

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
			}

		}

		documento.add(tableR);

		documento.close();

	}

	private void filedownloadPDF(String fullPath, HttpServletResponse response, Long id) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + nombrepdf + ".pdf");
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

	@RequestMapping(value = "/estatus_imp", method = RequestMethod.POST)
	public String estatus(@RequestParam("id") Long id, @RequestParam("id_res") Long id_res, @RequestParam("lo") Long lo,
			@RequestParam("aux") boolean aux, Model m, RedirectAttributes redirectAttrs) {
		Resultados res;
		res = ResultadosDao.findOne(id_res);
		if (aux == true) {
			System.out.print("i´m estatus_imp");
			res.setImpresion(false);
			ResultadosDao.save(res);
		} else {
			System.out.print("i´m estatus_imp else");
			res.setImpresion(true);
			ResultadosDao.save(res);
		}
		m.addAttribute("resul", ResultadosDao.findAll(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		m.addAttribute("id", id);
		m.addAttribute("lo", lo);
		return "listar_ordenes";

	}

	@RequestMapping(value = "/select_all/{id}/{i}/{t}/{lo}", method = RequestMethod.GET)
	public String SelectAll(@PathVariable(value = "id") Long id, @PathVariable(value = "i") Long i,
			@PathVariable(value = "t") String tipo, @PathVariable(value = "lo") Long lo, Map<String, Object> model,
			Model m) {
		ResultadosDao.UpdateImp(lo,1);
		m.addAttribute("id", id);
		m.addAttribute("lo", lo);
		m.addAttribute("resul", ResultadosDao.findAll(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		return "listar_ordenes";
	}
	@RequestMapping(value = "/deselect_all/{id}/{i}/{t}/{lo}", method = RequestMethod.GET)
	public String DeselectAll(@PathVariable(value = "id") Long id, @PathVariable(value = "i") Long i,
			@PathVariable(value = "t") String tipo, @PathVariable(value = "lo") Long lo, Map<String, Object> model,
			Model m) {
		ResultadosDao.UpdateImp(lo,0);
		m.addAttribute("id", id);
		m.addAttribute("lo", lo);
		m.addAttribute("resul", ResultadosDao.findAll(lo));
		m.addAttribute("paciente", ResultadosDao.PacienteOrden(id));
		m.addAttribute("lineas", ResultadosDao.LineasOrden(id));
		return "listar_ordenes";
	}

}
