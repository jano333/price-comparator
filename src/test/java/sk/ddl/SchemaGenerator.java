package sk.ddl;


import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudak on 16.03.2017.
 */

/**
 * @author john.thompson
 */
public class SchemaGenerator {

    private Configuration cfg;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SchemaGenerator gen = new SchemaGenerator("sk.hudak.pricecomparator.server.domain.model");
//        gen.generate(Dialect.MYSQL);
        gen.generate(Dialect.ORACLE);
//        gen.generate(Dialect.HSQL);
//        gen.generate(Dialect.H2);
    }

    public SchemaGenerator(String packageName) throws Exception {
        cfg = new Configuration();
        cfg.setProperty("hibernate.hbm2ddl.auto", "create");

        for (Class<Object> clazz : getClasses(packageName)) {
            cfg.addAnnotatedClass(clazz);
        }
    }

    /**
     * Method that actually creates the file.
     *
     * @param dialect to use
     */
    private void generate(Dialect dialect) {
        cfg.setProperty("hibernate.dialect", dialect.getDialectClass());

        SchemaExport export = new SchemaExport(cfg);
        export.setDelimiter(";");
        export.setOutputFile("ddl_" + dialect.name().toLowerCase() + ".sql");
        export.execute(true, false, false, false);
    }


    /**
     * Utility method used to fetch Class list based on a package name.
     *
     * @param packageName (should be the package containing your annotated beans.
     */
    private List<Class> getClasses(String packageName) throws Exception {
        List<Class> classes = new ArrayList<>();
        File directory = null;
        try {
            ClassLoader cld = Thread.currentThread().getContextClassLoader();
            if (cld == null) {
                throw new ClassNotFoundException("Can't get class loader.");
            }
            String path = packageName.replace('.', '/');
            URL resource = cld.getResource(path);
            if (resource == null) {
                throw new ClassNotFoundException("No resource for " + path);
            }
            directory = new File(resource.getFile());

        } catch (NullPointerException x) {
            throw new ClassNotFoundException(packageName + " (" + directory + ") does not appear to be a valid package");
        }
        if (directory.exists()) {
            String[] files = directory.list();
            for (int i = 0; i < files.length; i++) {
                if (files[i].endsWith(".class")) {
                    // removes the .class extension
                    classes.add(Class.forName(packageName + '.'
                            + files[i].substring(0, files[i].length() - 6)));
                }
            }
        } else {
            throw new ClassNotFoundException(packageName + " is not a valid package");
        }

        return classes;
    }

    /**
     * Holds the classnames of hibernate dialects for easy reference.
     */
    private enum Dialect {
        ORACLE("org.hibernate.dialect.Oracle10gDialect"),
        MYSQL("org.hibernate.dialect.MySQLDialect"),
        H2("org.hibernate.dialect.H2Dialect"),
        HSQL("org.hibernate.dialect.HSQLDialect");

        private String dialectClass;

        private Dialect(String dialectClass) {
            this.dialectClass = dialectClass;
        }

        public String getDialectClass() {
            return dialectClass;
        }
    }
}
