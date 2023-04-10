package com.dogwiki.commu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TrainingData {
	
	private final String KANG_URL = "https://www.youtube.com/@Bodeumofficial/videos";
	private final String SEOL_URL = "https://www.youtube.com/@knollo_with_dvmseol/videos";
	
	public String getKANG_URL() {
		return KANG_URL;
	}

	public String getSEOL_URL() {
		return SEOL_URL;
	}

	public List<String> readData(String url, String infoType) {
		List<String> list = new ArrayList<>();
		HttpURLConnection conn = null;
		BufferedReader br = null;
		
		try {
			conn = (HttpURLConnection)((new URL(url)).openConnection());
			conn.connect();
			br = new BufferedReader(
						new InputStreamReader(
								conn.getInputStream(), "utf-8"));
			
			String temp = null;
			String[] targets = null;
			while((temp = br.readLine()) != null) {
				if(temp.contains("r\":{\"videoId\":\"")) {
					targets = temp.trim().split("r\":\\{\"videoId\":\"");
					if(infoType.equals("trUrl")) {
						for(int i = 1; i < targets.length; i++) {
							String videoId = targets[i].substring(0, 11);
							System.out.println(videoId);
							list.add("https://www.youtube.com/embed/" + videoId);
						}
					} else if(infoType.equals("trTitle")) {
						for(int i = 1; i < targets.length; i++) {
							String title = targets[i].split("title\":\\{\"runs\":\\[\\{\"text\":\"")[1]
									.split("\"\\}\\]")[0].replace("\\u0026", "&");
							System.out.println(title);
							list.add(title);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) br.close();
				if(conn != null) conn.disconnect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}

}