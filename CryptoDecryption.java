//Decryptor
package Decryptor;
import Encryption.*;
import java.io.*;
import java.util.Scanner;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
class CryptoException extends Exception
{ public CryptoException(String message,Throwable throwable)
  { super(message,throwable);                                 
  }
}
class CryptoUtils1
{ private static final String ALGORITHM="AES";    // Advanced encryption standard 
  private static final String TRANSFORM="AES";    // signature
  public static void decrypt(String key,File inputFile,File outputFile) throws CryptoException
  { doCrypto(Cipher.DECRYPT_MODE,key,inputFile,outputFile);
  }//decryption process
   private static void doCrypto(int ciphermode,String key,File inputFile,File outputFile) throws CryptoException
  { try
    { SecretKey secretKey=new SecretKeySpec(key.getBytes(),ALGORITHM);    //construct secret key using given byte array
      Cipher cipher=Cipher.getInstance(TRANSFORM);    // return signature that implements given algorithm
      cipher.init(ciphermode,secretKey);    //initialises cipher with key and constructs parameters for algorithm 
      FileInputStream inputstream=new FileInputStream(inputFile);
      byte[] inputBytes=new byte[(int) inputFile.length()];
      inputstream.read(inputBytes);    //read from the encrypted file in byte format
      byte[] outputBytes=cipher.doFinal(inputBytes);//decrypts file 
      FileOutputStream outputstream=new FileOutputStream(outputFile);
      outputstream.write(outputBytes);    //write to output file in decrypted format
      inputstream.close();
      outputstream.close();
    }
     catch(InvalidKeyException|NoSuchAlgorithmException|NoSuchPaddingException|BadPaddingException|IllegalBlockSizeException|IOException ex)
    { throw new CryptoException("Error encrypting/decrypting file",ex);
    }
   }
}
public class CryptoDecryption
{ public static void main(String args[])
  { String key="Corona-Virus2020"; // Initial key
    File encryptedFile=new File("encrypted.txt");
    File decryptedFile=new File("decrypted.txt");
    try
    { 
            CryptoUtils1.decrypt(key,encryptedFile,decryptedFile);
    }
     catch(CryptoException e)
    { System.out.println( e.getMessage());
      e.printStackTrace();
    }
  }
}
