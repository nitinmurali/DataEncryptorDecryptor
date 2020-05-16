//Encryptor
package  Encryptor;
import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
class CryptoException extends Exception
{ public CryptoException(String message,Throwable throwable)
  { super(message,throwable);                                 
  }
}
public class CryptoEncryption
{ private static final String ALGORITHM="AES";    // algorithm used-Advanced encryption standard 
  private static final String TRANSFORM="AES";    // signature
  public static void encrypt(String key,File inputFile,File outputFile) throws CryptoException
  { doCrypto(Cipher.ENCRYPT_MODE,key,inputFile,outputFile);
  }    //encryption process
 private static void doCrypto(int ciphermode,String key,File inputFile,File outputFile) throws CryptoException
  { try
    { SecretKey secretKey=new SecretKeySpec(key.getBytes(),ALGORITHM);    //construct secret key using given byte array
      Cipher cipher=Cipher.getInstance(TRANSFORM);    // return signature that implements given algorithm
      cipher.init(ciphermode,secretKey);    //initialises cipher with key and constructs parameters for algorithm 
      FileInputStream inputstream=new FileInputStream(inputFile);
      byte[] inputBytes=new byte[(int) inputFile.length()];
      inputstream.read(inputBytes);    //read from the input file in byte format
      byte[] outputBytes=cipher.doFinal(inputBytes);   //encrypts file and returns it in padded format
      FileOutputStream outputstream=new FileOutputStream(outputFile);
      outputstream.write(outputBytes); //write to output file in encrypted format
      inputstream.close();
      outputstream.close();
    }
    catch(InvalidKeyException|NoSuchAlgorithmException|NoSuchPaddingException|BadPaddingException|IllegalBlockSizeException|IOException ex)
    { throw new CryptoException("Error encrypting/decrypting file",ex);
    }
   }
public static void main(String args[])
  { String key="Corona-Virus2020"; // Initial key
    File inputFile=new File("document.txt");
    File encryptedFile=new File("encrypted.txt");
    try
    { 
      encrypt(key,inputFile,encryptedFile);
    }
    catch(CryptoException e)
    { System.out.println( e.getMessage());
      e.printStackTrace();
    }
  }
}
    