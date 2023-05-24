package com.edix.Proyecto_Final_Curso.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DataUserConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth
	.jdbcAuthentication().dataSource(dataSource)
	.usersByUsernameQuery("select email, clave, permitido from Usuarios where email=?")
	.authoritiesByUsernameQuery("select u.email, t.tipo "
								+ "from Tipos_Usuarios t " +  
									"inner join Usuarios u on u.id_tipo_usuario = t.id_tipo_usuario " + 
									"where u.email = ?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		// Los directorios estáticos no requieren autenticacion
		.antMatchers("/bootstrap/**", "/images/**", "/css/**", "/js/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		.antMatchers("/app/panelControl").hasAnyAuthority("Administrador")
		//.anyRequest().authenticated()
		.and().formLogin().permitAll();
		// El formulario de Login no requiere autenticacion
					//.and().formLogin().permitAll().successHandler(loginHandler);
		
		/*.antMatchers("/usuarios/altaUsuario").permitAll();*/
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
