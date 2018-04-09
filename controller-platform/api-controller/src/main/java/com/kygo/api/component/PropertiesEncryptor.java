package com.kygo.api.component;

import com.cdvcredit.common.utils.DESUtil;
import com.cdvcredit.common.utils.FileUtil;
import org.jasypt.encryption.StringEncryptor;

import java.net.URL;

/**
 */
public class PropertiesEncryptor implements StringEncryptor {

    private String privateKey;

    private static String privateKeyFileName = "properties_key.txt";

    public PropertiesEncryptor() {
        super();
        this.privateKey = getPrivateKeyString();

    }

    @Override
    public String encrypt(String s) {
        return DESUtil.encrypt(s, privateKey);
    }

    @Override
    public String decrypt(String s) {
        return DESUtil.decrypt(s, privateKey);
    }

    private String getPrivateKeyString() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(privateKeyFileName);
        if (url == null) {
            return null;
        }
        return FileUtil.readToString(url.getFile());
    }
}
