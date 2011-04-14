import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto
{
	// Encrypt AES-256 of Plain Text (Non-Hex Form)
	public static String encryptAES(byte[] cleartext, String pass, byte[] salt)
	{
		byte[] secret = generateSecret(pass, salt, 1024, 256);
		System.out.println("ENC SECRET: " + toHexString(secret) + "\t(" + secret.length + ")");
		SecretKeySpec keySpec = new SecretKeySpec(secret, "AES");
		Arrays.fill(secret, (byte) 0x00);
		//System.out.println("SECRET: " + toHexString(secret) + "\t(" + secret.length + ")");
		
		byte[] iv = generateBytes(16);
		System.out.println("ENC IV: " + toHexString(iv));
		
		byte[] ciphertext = null;
		
		try
		{
			final Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			ciphertext = cipher.doFinal(cleartext);
		}
		catch(NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
		}
		catch(NoSuchPaddingException e2)
		{
			e2.printStackTrace();
		}
		catch(InvalidAlgorithmParameterException e3)
		{
			e3.printStackTrace();
		}
		catch(InvalidKeyException e4)
		{
			e4.printStackTrace();
		}
		catch(IllegalBlockSizeException e5)
		{
			e5.printStackTrace();
		}
		catch(BadPaddingException e6)
		{
			e6.printStackTrace();
		}
		
		//System.out.println(ciphertext.length);
		//System.out.println(toHexString(ciphertext));
		
		String result = toHexString(ciphertext) + toHexString(iv);
		
		return result;
	}
	
	// Decrypt AES-256 of Cipher Text (Non-Hex Form)
	public static String decryptAES(byte[] ciphertext, String pass, byte[] salt, byte[] iv)
	{
		byte[] secret = generateSecret(pass, salt, 1024, 256);
		System.out.println("DEC SECRET: " + toHexString(secret) + "\t(" + secret.length + ")");
		SecretKeySpec keySpec = new SecretKeySpec(secret, "AES");
		Arrays.fill(secret, (byte) 0x00);
		//System.out.println("SECRET: " + toHexString(secret) + "\t(" + secret.length + ")");
		
		System.out.println("DEC IV: " + toHexString(iv));
		
		byte[] cleartext = null;
		
		try
		{
			final Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			cleartext = cipher.doFinal(ciphertext);
		}
		catch(NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
		}
		catch(NoSuchPaddingException e2)
		{
			e2.printStackTrace();
		}
		catch(InvalidAlgorithmParameterException e3)
		{
			e3.printStackTrace();
		}
		catch(InvalidKeyException e4)
		{
			e4.printStackTrace();
		}
		catch(IllegalBlockSizeException e5)
		{
			e5.printStackTrace();
		}
		catch(BadPaddingException e6)
		{
			e6.printStackTrace();
		}
		
		return new String(cleartext);
	}
	
	// Generate 160/256-bit key from password + salt over iterations
	public static byte[] generateSecret(String pass, byte[] salt, int iter, int size)
	{
		byte[] key = pass.getBytes();
		
		final MessageDigest md;
		try
		{
			if(size == 160)
				md = MessageDigest.getInstance("SHA-1");
			else
				md = MessageDigest.getInstance("SHA-256");
			
			for(int i = 0; i < iter; i++)
			{
				final byte[] secret = new byte[key.length + salt.length];
				System.arraycopy(key, 0, secret, 0, key.length);
				System.arraycopy(salt, 0, secret, key.length, salt.length);
				Arrays.fill(key, (byte) 0x00);
				
				md.reset();
				key = md.digest(secret);
				Arrays.fill(secret, (byte) 0x00);
				//System.out.println("KEY (" + i + "): " + toHexString(key));
			}
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return key;
	}
	
	// Calculate HMAC of Cipher Text
	// Input: Cipher Text File w/o HMac + Salt + IV
	// Output: Hex String
	public static String calcHMAC(byte[] ciphertext, String pass, byte[] salt)
	{
		byte[] secret = generateSecret(pass, salt, 1024, 160);
		//System.out.println("SECRET: " + toHexString(secret) + "\t(" + secret.length + ")");
		SecretKeySpec sKey = new SecretKeySpec(secret, "HMacSHA1");
		Arrays.fill(secret, (byte) 0x00);
		//System.out.println("SECRET: " + toHexString(secret) + "\t(" + secret.length + ")");
		
		Mac mac;
		byte[] value = null;
		try
		{
			mac = Mac.getInstance("HMacSHA1");
			mac.init(sKey);
			value = mac.doFinal(ciphertext);
		}
		catch(NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
		}
		catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		
		return toHexString(value);
	}
	
	// Generates Secure Random Number of Bytes
	public static byte[] generateBytes(int num)
	{
		num = (num >= 0) ? num : 0;
		byte[] bytes = new byte[num];
		SecureRandom sr = new SecureRandom();
		sr.nextBytes(bytes);
		return bytes;
	}
	
	// Converts Byte Array to Hex String
	public static String toHexString(byte[] b)
	{
		StringBuffer sb = new StringBuffer(b.length * 2);
	    for (int i = 0; i < b.length; i++) {
	      int v = b[i] & 0xff;
	      if (v < 16)
	        sb.append('0');
	      sb.append(Integer.toHexString(v));
	    }
	    return sb.toString().toUpperCase();
	}
	
	// Converts Hex String to Byte Array
	public static byte[] toByteArray(String s)
	{
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2)
	    {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	// Test Main
	public static void main(String[] args)
	{
		String text = "The quick brown fox jumps over the lazy dog";
		String password = "This is an extremely long generic key 0123456789";
		byte[] hashSalt = generateBytes(8);
		byte[] keySalt = generateBytes(8);
		
		String cipherIV = encryptAES(text.getBytes(), password, keySalt);
		String ciphertext = cipherIV.substring(0, cipherIV.length() - 32);
		String iv = cipherIV.substring(cipherIV.length() - 32, cipherIV.length());
		String cleartext = decryptAES(toByteArray(ciphertext), password, keySalt, toByteArray(iv));
		
		System.out.println("Plain: " + text);
		System.out.println("Password: " + password);
		System.out.println("Hash Salt: " + toHexString(hashSalt));
		System.out.println("Key Salt: " + toHexString(keySalt));
		System.out.println("Cipher: " + cipherIV);
		System.out.println("Cipher Text: " + ciphertext);
		System.out.println("HMAC:" + calcHMAC(toByteArray(ciphertext), password, hashSalt));
		System.out.println("Cipher IV: " + iv);
		System.out.println("Clear Text: " + cleartext);
	}
}
