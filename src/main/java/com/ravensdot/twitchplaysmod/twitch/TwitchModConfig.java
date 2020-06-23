package com.ravensdot.twitchplaysmod.twitch;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import net.minecraft.util.ResourceLocation;

public class TwitchModConfig
{
	private Map<String, String> credentials;
	
	public TwitchModConfig()
	{
		this.credentials = parse();
	}
	
	private Map<String, String> parse()
	{
		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			ResourceLocation location = new ResourceLocation("twitchplaysmod", "xml/credentials.xml");
			File file = new File(location.getPath());
			
			
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(file);
			String id = doc.getElementsByTagName("client_id").item(0).getTextContent();
			String secret = doc.getElementsByTagName("client_secret").item(0).getTextContent();
			String channel = doc.getElementsByTagName("channel").item(0).getTextContent();
			String oauth = doc.getElementsByTagName("oauth_token").item(0).getTextContent();
			System.out.print(id);
			
			Map<String, String> creds = new HashMap<String,String>();
			creds.put("id", id);
			creds.put("secret", secret);
			creds.put("channel", channel);
			creds.put("oauth", oauth);
			
			return creds;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String,String>();
	}
	
	public Map<String,String> getCreds()
	{
		return this.credentials;
	}
	
	public String getID() {
		return this.credentials.get("id");
	}
	
	public String getSecret() {
		return this.credentials.get("secret");
	}
	
	public String getChannel() {
		return this.credentials.get("channel");
	}
}
