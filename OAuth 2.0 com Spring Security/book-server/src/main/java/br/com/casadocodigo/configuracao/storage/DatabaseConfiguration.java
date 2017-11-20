package br.com.casadocodigo.configuracao.storage;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.ImmutableMap;

import br.com.casadocodigo.livros.Livro;
import br.com.casadocodigo.usuarios.Usuario;
import br.com.casadocodigo.usuarios.UsuarioRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = { UsuarioRepository.class })
public class DatabaseConfiguration {
	@Value("${database.url}")
	private String urlDataSource;

	@Value("${database.password}")
	private String passwordDataSource;

	@Value("${database.user}")
	private String userDataSource;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(urlDataSource);
		dataSource.setDriverClassName(org.h2.Driver.class.getName());
		dataSource.setUsername(userDataSource);
		dataSource.setPassword(passwordDataSource);

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages(Usuario.class, Livro.class)
				.persistenceUnit("oauth")
				.properties(ImmutableMap.of("hibernate.dialect", org.hibernate.dialect.H2Dialect.class.getName()))
				.build();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}