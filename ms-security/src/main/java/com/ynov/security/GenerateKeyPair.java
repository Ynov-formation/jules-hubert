package com.ynov.security;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenerateKeyPair {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        var keyPair = keyPairGenerator.generateKeyPair();
        byte[] pub = keyPair.getPublic().getEncoded();
        byte[] pri = keyPair.getPrivate().getEncoded();
        PemWriter pemWriterPrivate = new PemWriter(new OutputStreamWriter((new FileOutputStream("pri.pem"))));
        PemObject pemObjectPrivate = new PemObject("PRIVATE KEY",pri);
        pemWriterPrivate.writeObject(pemObjectPrivate);
        pemWriterPrivate.close();

        PemWriter pemWriterPublic = new PemWriter(new OutputStreamWriter((new FileOutputStream("pub.pem"))));
        PemObject pemObjectPublic = new PemObject("PUBLIC KEY",pub);
        pemWriterPublic.writeObject(pemObjectPublic);
        pemWriterPublic.close();
    }

}
