package com.donggun.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final SecurityUserDetailsService userDetailsService;

	@Autowired
	public SecurityConfig(SecurityUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.userDetailsService(userDetailsService);
		
		// 경로별 접근 권한 설정 
		security.authorizeRequests().antMatchers("/", "/system/**").permitAll()
									.antMatchers("/board/**").authenticated()
									.antMatchers("/admin/**").hasRole("ADMIN");
		
		// csrf 기능 비활성화 
		security.csrf().disable();
		
		// login page 매핑, 로그인 성공 후 이동할 default page 설정 
		security.formLogin().loginPage("/system/login")
					.defaultSuccessUrl("/board/getBoardList", true);
		
		// 접근 권한이 없는 경우 이동 주소 설정 (request url)
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		
		// 로그아웃 주소 및 로그아웃 성공 후 이동 주소 설정 (request url)
		security.logout().logoutUrl("/system/logout")
			.invalidateHttpSession(true).logoutSuccessUrl("/");
	}
	
	
	/**
	 * 비밀번호 암호화하기
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
