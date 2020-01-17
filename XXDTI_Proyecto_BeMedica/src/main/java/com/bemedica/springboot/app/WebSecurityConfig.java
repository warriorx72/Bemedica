package com.bemedica.springboot.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
        .antMatchers(resources).permitAll()  
        .antMatchers("/","/login").permitAll()
        .antMatchers("/catalogos_alertas","/operaciones_facturacion","/operaciones_recepcion","/operaciones_folios"
        		,"/operaciones_facturacion","/administracion_empleados","/administracion_informacion","/administracion_sucursales"
        		,"/administracion_usuarios","/estudios_listar","/estudios_examenes","/estudios_perfiles","/estudios_paquetes"
        		,"/gabinete","/estudios_calculos","/estudios_cultivos","/estudios_antibiogramas","/catalogos_antibiotico"
        		,"/precios_listas","/precios_convenios","/precios_promociones","/proserv_productos","/proserv_servicios"
        		,"/catalogos_alertas","/catalogos_condicion","/herramientas_participaciones","/herramientas_corte"
        		,"/herramientas_notificaciones","/herramientas_reportes","/herramientas_sistema").access("hasRole('ADMIN')")
        
        .antMatchers("/operaciones_recepcion","/operaciones_facturacion"
        		,"/herramientas_participaciones","/herramientas_corte"
        		).access("hasRole('RECEPCIONISTA') or hasRole('ADMIN')")
        
        .antMatchers("/estudios_examenes","/estudios_cultivos","/estudios_antibiogramas"
        		,"/catalogos_antibiotico","/catalogos_alertas","/catalogos_condicion"
        		).access("hasRole('VALIDACION') or hasRole('ADMIN')")
        
        
        .antMatchers("/operaciones_folios","/estudios_listar","/herramientas_reportes"
        		,"/herramientas_sistema","/herramientas_notificaciones").access("hasRole('VALIDACION') or hasRole('ADMIN') or hasRole('RECEPCIONISTA')")
        
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/index")
            .failureUrl("/login?error=true")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .csrf().disable()
        .logout()
            .permitAll()
            .logoutSuccessUrl("/login?logout");
    }
	
	BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
    	//Especificar el encargado del login y encriptacion del password
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
