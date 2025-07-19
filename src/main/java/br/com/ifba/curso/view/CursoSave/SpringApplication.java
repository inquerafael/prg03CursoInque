
package br.com.ifba.curso.view.CursoSave;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


/**
 *
 * @author inque
 */
@SpringBootApplication
public class SpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplication.class).headless(true).run(args);
        
        /*CursoView telaCursoSave = context.getBean(CursoView.class);
        telaCursoSave.setVisible(true);*/
    }
}
