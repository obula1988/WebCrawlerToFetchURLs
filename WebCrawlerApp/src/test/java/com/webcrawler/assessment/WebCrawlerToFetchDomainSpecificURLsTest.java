package com.webcrawler.assessment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class WebCrawlerToFetchDomainSpecificURLsTest {

	@Test
	public void test() {
		String fileName = "F:\\Webcrawler\\wiprodomainurls.txt";
		WebCrawlerToFetchDomainSpecificURLs crawler = new WebCrawlerToFetchDomainSpecificURLs();
		crawler.getPageLinks("https://wiprodigital.com", fileName);
		
		File file = new File(fileName);
		BufferedReader br = null;
		FileReader fr;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			assertNotNull(br.readLine()!=null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}

		
	}

}
