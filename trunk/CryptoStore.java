public class CryptoStore
{
	private byte[] keySalt;
	private byte[] iv;
	private byte[] HMAC;
	private byte[] hashSalt;
	
	public CryptoStore()
	{
		keySalt = new byte[0];
		iv = new byte[0];
		HMAC = new byte[0];
		hashSalt = new byte[0];
	}
	public CryptoStore(byte[] kSalt, byte[] IV, byte[] md, byte[] hSalt)
	{
		keySalt = kSalt;
		iv = IV;
		HMAC = md;
		hashSalt = hSalt;
	}
	
	public byte[] getKeySalt()
	{return keySalt;}
	public byte[] getIV()
	{return iv;}
	public byte[] getHMAC()
	{return HMAC;}
	public byte[] getHashSalt()
	{return hashSalt;}
	
	public void setKeySalt(byte[] kSalt)
	{keySalt = kSalt;}
	public void setIV(byte[] IV)
	{iv = IV;}
	public void setHMAC(byte[] md)
	{HMAC = md;}
	public void setHashSalt(byte[] hSalt)
	{hashSalt = hSalt;}
}
