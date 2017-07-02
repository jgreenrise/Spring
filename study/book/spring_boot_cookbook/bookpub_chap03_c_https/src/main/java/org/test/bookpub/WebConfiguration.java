package org.test.bookpub;

import java.io.File;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.test.bookpub.formatters.BookFormatter;
import org.test.bookpub.repository.BookRepository;

@Configuration
@PropertySource("classpath:/tomcat.https.properties")
@EnableConfigurationProperties(WebConfiguration.TomcatSslConnectorProperties.class)
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@ConfigurationProperties(prefix = "custom.tomcat.https")
	public static class TomcatSslConnectorProperties {
		private Integer port;
		private Boolean ssl = true;
		private Boolean secure = true;
		private String scheme = "https";
		private File keystore;
		private String keystorePassword;

		public void configureConnector(Connector connector) {
			if (port != null)
				connector.setPort(port);
			if (secure != null)
				connector.setSecure(secure);
			if (scheme != null)
				connector.setScheme(scheme);
			if (ssl != null)
				connector.setProperty("SSLEnabled", ssl.toString());
			if (keystore != null && keystore.exists()) {
				connector.setProperty("keystoreFile", keystore.getAbsolutePath());
				connector.setProperty("keystorePassword", keystorePassword);
			}
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public Boolean getSsl() {
			return ssl;
		}

		public void setSsl(Boolean ssl) {
			this.ssl = ssl;
		}

		public Boolean getSecure() {
			return secure;
		}

		public void setSecure(Boolean secure) {
			this.secure = secure;
		}

		public String getScheme() {
			return scheme;
		}

		public void setScheme(String scheme) {
			this.scheme = scheme;
		}

		public File getKeystore() {
			return keystore;
		}

		public void setKeystore(File keystore) {
			this.keystore = keystore;
		}

		public String getKeystorePassword() {
			return keystorePassword;
		}

		public void setKeystorePassword(String keystorePassword) {
			this.keystorePassword = keystorePassword;
		}

	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer(TomcatSslConnectorProperties properties) {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(createSslConnector(properties));
		return tomcat;
	}

	private Connector createSslConnector(TomcatSslConnectorProperties properties) {
		Connector connector = new Connector();
		properties.configureConnector(connector);
		return connector;
	}

	/**
	 * JAVA 8 public EmbeddedServletContainerCustomizer
	 * embeddedServletContainerCustomizer() { return
	 * (ConfigurableEmbeddedServletContainer container) -> {
	 * container.setSessionTimeout(1, TimeUnit.MINUTES); }; }
	 */

	/**
	 * start: FORMATTER
	 */
	@Autowired
	private BookRepository bookRepository;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new BookFormatter(bookRepository));
	}

	/**
	 * end
	 */

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/internal/**").addResourceLocations("classpath:/");
	}

	/**
	 * end
	 */

	/**
	 * start: Allows dot in path
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
	}

	/**
	 * end
	 */

}
