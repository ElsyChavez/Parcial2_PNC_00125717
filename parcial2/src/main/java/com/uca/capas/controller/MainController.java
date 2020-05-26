package com.uca.capas.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.LibroService;

@Controller
public class MainController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	LibroService libroService;
	
	@RequestMapping("/index")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping("/mostrarLibros")
	public ModelAndView mostrarLibros() {
		ModelAndView mav = new ModelAndView();
		List<Libro> libros = null;
		
		try{
			libros = libroService.findAll();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		mav.addObject("libros", libros);
		mav.setViewName("listado");
		
		return mav;
		
	}
	
	@RequestMapping("/ingresarCategoria")
	public ModelAndView ingresarCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors() != true) {
			try{
				categoriaService.insert(categoria);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			categoria = new Categoria();
			mav.addObject("categoria", categoria);
		}
		
		mav.setViewName("categoria");
		
		return mav;
	}
	
	@RequestMapping("/ingresarLibro")
	public ModelAndView ingresarLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			List <Categoria> categorias = null;
			try{
				categorias = categoriaService.findAll();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
			LocalDateTime fechaActual = LocalDateTime.now();
			String fechaI = dtf.format(fechaActual);
		    System.out.println(fechaI); 
			
			mav.addObject("f_ingreso", fechaI);
			mav.addObject("libro", libro);
			mav.addObject("categorias", categorias);
			mav.setViewName("libro");
		}
		
		else {
			libroService.insert(libro);
			mav.setViewName("index");
		}
		
		return mav;
	}

}
