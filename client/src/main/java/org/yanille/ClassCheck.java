package org.yanille;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.Map.Entry;


public class ClassCheck {
    public static final String[] DEFAULT_PACKAGE = new String[]{"org.yanille"};

    public static String generate() throws Throwable {
        List classNames = new ArrayList();
        String[] packages = DEFAULT_PACKAGE;
        int length = DEFAULT_PACKAGE.length;

        int i;
        String identifier;
        for(int var2 = 0; var2 < length; ++var2) {
            String pkg = packages[var2];
            Class[] classes = getClasses(pkg);

            for(i = 0; i < length; ++i) {
                Class classz = classes[i];
                identifier = classz.getName();
                if (!identifier.equals(pkg + ".Configuration") && identifier != null) {
                    classNames.add(identifier);
                }
            }
        }

        Collections.sort(classNames, String.CASE_INSENSITIVE_ORDER);
        Map objects = new HashMap();
        Iterator $it = classNames.iterator();

        while($it.hasNext()) {
            String className = (String)$it.next();
            List found = new ArrayList();
            Class classz = Class.forName(className);
            Method[] methods;
            length = (methods = classz.getDeclaredMethods()).length;

            for(i = 0; i < length; ++i) {
                Method method = methods[i];
                if (method != null) {
                    identifier = "method_" + method.getName() + "_" + method.getModifiers() + "_";
                    found.add(identifier);
                }
            }

            Field[] fields;
            length = (fields = classz.getDeclaredFields()).length;

            for(i = 0; i < length; ++i) {
                Field field = fields[i];
                if (field != null) {
                    identifier = "field_" + field.getName() + "_" + field.getModifiers() + "_" + field.getType().getName();
                    found.add(identifier);
                }
            }

            found.sort(String.CASE_INSENSITIVE_ORDER);
            objects.put(className, found);
        }

        StringBuilder builder = new StringBuilder();
        $it = objects.entrySet().iterator();

        while(true) {
            Entry entry;
            do {
                if (!$it.hasNext()) {
                    MessageDigest m = MessageDigest.getInstance("MD5");
                    byte[] buffer = m.digest(builder.toString().getBytes(StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();

                    for(i = 0; i < buffer.length; ++i) {
                        sb.append(String.format("%02x", buffer[i]));
                    }

                    return sb.toString();
                }

                entry = (Entry)$it.next();
            } while(entry == null);

            builder.append((String)entry.getKey());
            builder.append("\n");
            Iterator $it2 = ((List)entry.getValue()).iterator();

            while($it2.hasNext()) {
                builder.append((String)$it2.next());
                builder.append("\n");
            }
        }
    }

    public static Class[] getClasses(String packageName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        assert classLoader != null;

        String path = packageName.replace('.', '/').replaceAll("%20", " ").replaceAll("%e2%84%a2", "ï¿½");
        Enumeration resources = classLoader.getResources(path);
        ArrayList directories = new ArrayList();

        while(resources.hasMoreElements()) {
            directories.add(new File(((URL)resources.nextElement()).getFile().replaceAll("%20", " ").replaceAll("%e2%84%a2", "ï¿½")));
        }

        ArrayList classes = new ArrayList();
        Iterator var7 = directories.iterator();

        while(var7.hasNext()) {
            File directory = (File)var7.next();
            classes.addAll(findClasses(directory, packageName));
        }

        return (Class[])classes.toArray(new Class[classes.size()]);
    }

    private static List findClasses(File directory, String packageName) {
        List classes = new ArrayList();
        if (directory.exists()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(".class")) {
                    try {
                        classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                    } catch (Throwable ignored) {
                    }
                }
            }

            return classes;
        } else {
            return classes;
        }
    }

    public static String getName(Object object) {
        return object.getClass().getSimpleName();
    }

    public static String getLowerCaseName(Object object) {
        return getName(object).toLowerCase();
    }
}