package com.webcrawler.assessment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author ObulaReedy M
 *
 */
public class WebCrawlerToFetchDomainSpecificURLs {

	private HashSet<String> links;
	int count = 0;

	public WebCrawlerToFetchDomainSpecificURLs() {

		links = new HashSet<String>();
	}

	/**
	 * @param URL
	 * @param fileName
	 */
	public void getPageLinks(String URL, String fileName) {

		if (!links.contains(URL)) {
			try {
				if (links.add(URL)) {
					System.out.println("URL : " + URL);
					appendStrToFile(fileName, URL);
				}

				Document document = Jsoup.connect(URL).get();
				Elements linksOnPage = document.select("a[href]");
				if (URL.contains("https://wiprodigital.com")) {
					for (Element page : linksOnPage) {
						getPageLinks(page.attr("abs:href"), fileName);
						count++;
					}
				} else {
					System.exit(0);
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
	}

	/**
	 * @param fileName
	 * @param str
	 */
	public static void appendStrToFile(String fileName, String str) {
		BufferedWriter out = null;
		try {

			out = new BufferedWriter(new FileWriter(fileName, true));
			out.write(str);
			out.newLine();
		} catch (IOException e) {
			System.out.println("exception occoured" + e);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

		}
	}

	public static void main(String[] args) {
		String fileName = args[0];
		new WebCrawlerToFetchDomainSpecificURLs().getPageLinks("https://wiprodigital.com", fileName);
	}

}