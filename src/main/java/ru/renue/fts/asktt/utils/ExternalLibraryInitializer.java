package ru.renue.fts.asktt.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Класс для добавления внешних jar библиотек.
 */
public class ExternalLibraryInitializer {
    private String librariesPath;

    private final Class[] parameters = new Class[]{URL.class};

    public ExternalLibraryInitializer(final String librariesPath) {
        this.librariesPath = librariesPath;
    }

    public void init() throws IOException {
        for (URL url : getUrls()) {
            addURL(url);
        }
    }

    /**
     * Добавляет библиотеку в classpath.
     *
     * @param url расположение библиотеки
     * @throws IOException
     */
    private void addURL(final URL url) throws IOException {
        URLClassLoader systemClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class classLoaderClazz = URLClassLoader.class;

        try {
            Method method = classLoaderClazz.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(systemClassLoader, new Object[]{url});
        }
        catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }

    /**
     * Возвращает список библиотек, находящихся в директории {@link ExternalLibraryInitializer#librariesPath}.
     *
     * @return
     */
    private URL[] getUrls() {
        File directory = new File(librariesPath);
        File[] listFile = directory.listFiles();
        if (listFile == null) {
            return null;
        }

        List<URL> result = new ArrayList<>();
        for (int i = 0; i < listFile.length; i++) {
            try {
                if (listFile[i].getName().contains("jar")) {
                    JarFile jar = new JarFile(listFile[i]);
                    Manifest mf = jar.getManifest();
                    if (mf != null) {
                        String cp = mf.getMainAttributes().getValue("class-path");
                        if (cp != null) {
                            for (String cpe : cp.split("\\s+")) {
                                File lib = new File(listFile[i].getParentFile(), cpe);
                                result.add(lib.toURI().toURL());
                            }
                        }
                    }
                    jar.close();
                    result.add(listFile[i].toURI().toURL());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println(listFile[i].getAbsolutePath());
            }
        }

        return result.toArray(new URL[result.size()]);
    }
}